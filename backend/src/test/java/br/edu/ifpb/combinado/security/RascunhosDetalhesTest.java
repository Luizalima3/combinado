package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.model.CombinadoHistorico;
import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.Responsabilidade;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RascunhosDetalhesTest extends GestaoFixture {
    @Autowired CombinadoHistoricoRepository combinadoEventos;
    @Autowired ConviteHistoricoRepository conviteEventos;

    private String url() { return "/api/combinados/" + combinado.getId(); }
    private void rascunho() {
        combinado.setStatus(StatusCombinado.RASCUNHO);
        combinados.saveAndFlush(combinado);
    }
    @Test void deveSalvarTituloApenasEPersistirCategoriaNula() throws Exception {
        var resposta = mvc.perform(post("/api/combinados").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\"Somente título\",\"status\":\"RASCUNHO\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.status").value("RASCUNHO"))
                .andReturn().getResponse().getContentAsString();
        Long id = ((Number) JsonPath.read(resposta, "$.id")).longValue();
        em.flush(); em.clear();
        assertNull(combinados.findById(id).orElseThrow().getCategoria());
        mvc.perform(post("/api/combinados/{id}/publicar", id).header("Authorization", bearer))
                .andExpect(status().isBadRequest());
        assertEquals(StatusCombinado.RASCUNHO, combinados.findById(id).orElseThrow().getStatus());
    }
    @Test void rascunhosDevemSerVisiveisSomenteAoCriador() throws Exception {
        rascunho();
        convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get("/api/combinados/meus-rascunhos").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
        mvc.perform(get("/api/combinados/meus-rascunhos").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(get("/api/combinados").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(get(url()).header("Authorization", token(participante))).andExpect(status().isForbidden());
        mvc.perform(post(url() + "/publicar").header("Authorization", token(participante))).andExpect(status().isForbidden());
        mvc.perform(get("/api/combinados/meus-rascunhos")).andExpect(status().isUnauthorized());
    }

    @Test void criadorPodeDescartarRascunhoELimparDependencias() throws Exception {
        rascunho();
        Long combinadoId = combinado.getId();

        var tarefa = new Responsabilidade();
        tarefa.setCombinado(combinado);
        tarefa.setDescricao("Tarefa do rascunho");
        tarefa.getResponsaveis().add(participante);
        tarefas.saveAndFlush(tarefa);

        var convite = convite(participante, StatusConvite.PENDENTE);
        conviteEventos.saveAndFlush(new ConviteHistorico(
                convite, criador, AcaoConvite.ENVIO, java.time.Instant.now()));
        combinadoEventos.saveAndFlush(CombinadoHistorico.de(
                combinado, criador, "Rascunho criado.", AcaoCombinadoHistorico.CRIACAO));

        mvc.perform(delete(url()).header("Authorization", bearer))
                .andExpect(status().isNoContent());

        em.flush();
        em.clear();
        assertFalse(combinados.existsById(combinadoId));
        assertTrue(tarefas.findByCombinadoIdOrderByIdAsc(combinadoId).isEmpty());
        assertTrue(convites.findByCombinadoId(combinadoId).isEmpty());
        assertTrue(combinadoEventos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinadoId).isEmpty());
        assertTrue(conviteEventos.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).isEmpty());
    }

    @Test void somenteCriadorPodeDescartarRascunhoECombinadoPublicadoNaoPodeSerExcluido() throws Exception {
        rascunho();
        mvc.perform(delete(url()).header("Authorization", token(participante)))
                .andExpect(status().isForbidden());

        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        combinados.saveAndFlush(combinado);
        mvc.perform(delete(url()).header("Authorization", bearer))
                .andExpect(status().isBadRequest());
        assertTrue(combinados.existsById(combinado.getId()));
    }

    @Test void recusaPelaRotaDoCombinadoAtualizaConviteSemCancelarCombinado() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch(url() + "/recusar")
                        .header("Authorization", token(participante))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Não consigo participar\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"));

        assertEquals(StatusConvite.RECUSADO, convites.findById(convite.getId()).orElseThrow().getStatus());
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());
        assertEquals(AcaoConvite.RECUSA,
                conviteEventos.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).get(0).getAcao());
    }

    @Test void devePublicarRascunhoCompletoPreservandoEstadoCompativel() throws Exception {
        rascunho();
        combinado.setResponsabilidades("{\"rascunho\":true,\"observacao\":\"preservar\"}");
        combinados.saveAndFlush(combinado);
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"));
        assertFalse((Boolean) JsonPath.read(combinado.getResponsabilidades(), "$.rascunho"));
        assertEquals("preservar", JsonPath.read(combinado.getResponsabilidades(), "$.observacao"));
        mvc.perform(get("/api/combinados/meus-rascunhos").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(get(url()).header("Authorization", token(participante))).andExpect(status().isOk());
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer)).andExpect(status().isBadRequest());
    }
    @Test void deveCompletarRascunhoAoPublicarSemConfiarNoCriadorEnviado() throws Exception {
        rascunho();
        combinado.setCategoria(null);
        combinado.setDataPrazo(null);
        combinado.limparParticipantes();
        combinados.saveAndFlush(combinado);
        String body = """
                {"titulo":"Publicado","categoria":"OUTRO","dataPrazo":"%s",
                 "criadorNome":"Forjado","participantesIds":[%d],"status":"ATIVO"}
                """.formatted(LocalDate.now().plusDays(2), participante.getId());
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Publicado"))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"));
        assertTrue(combinado.getUsuariosParticipantes().contains(participante));
    }
    @Test void deveRejeitarPublicacaoIncompletaSemAlterarDados() throws Exception {
        rascunho();
        String titulo = combinado.getTitulo();
        String body = """
                {"titulo":"Alterado","categoria":"OUTRO","dataPrazo":"%s","participantesIds":[%d]}
                """.formatted(LocalDate.now().minusDays(1), participante.getId());
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());
        assertEquals(titulo, combinado.getTitulo());
        assertEquals(StatusCombinado.RASCUNHO, combinado.getStatus());
    }
    @Test void deveExigirParticipanteRealDiferenteDoCriador() throws Exception {
        rascunho();
        combinado.limparParticipantes();
        combinado.adicionarParticipante(criador);
        combinados.saveAndFlush(combinado);
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        assertEquals(StatusCombinado.RASCUNHO, combinado.getStatus());
    }
    @Test void deveBloquearAceiteEmRascunhoEReaberturaDeEstadoFinal() throws Exception {
        rascunho();
        convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isForbidden());
        combinado.setStatus(StatusCombinado.CANCELADO);
        combinados.saveAndFlush(combinado);
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        mvc.perform(patch(url() + "/recusar").header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        assertEquals(StatusCombinado.CANCELADO, combinado.getStatus());
        assertNull(combinado.getAceitoEm());
    }
    @Test void aceiteParcialMantemAguardandoERecusaDeOutroParticipanteNaoAtivaCombinado() throws Exception {
        combinado.adicionarParticipante(externo);
        combinados.saveAndFlush(combinado);
        var conviteAceito = convite(participante, StatusConvite.PENDENTE);
        var conviteRecusado = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"));
        assertNull(combinado.getAceitoEm());
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        mvc.perform(patch("/api/convites/{id}/recusar", conviteRecusado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RECUSADO"));
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO, combinado.getStatus());
        assertEquals(StatusConvite.ACEITO, convites.findById(conviteAceito.getId()).orElseThrow().getStatus());
        assertEquals(StatusConvite.RECUSADO, convites.findById(conviteRecusado.getId()).orElseThrow().getStatus());
    }
    @Test void detalhesDevemSerProtegidosEConterInformacoesDoCombinado() throws Exception {
        mvc.perform(get(url()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$.categoria").value("OUTRO"))
                .andExpect(jsonPath("$.prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"))
                .andExpect(jsonPath("$.criador").doesNotExist()).andExpect(jsonPath("$.participantes").isArray())
                .andExpect(jsonPath("$.participantes[0].senha").doesNotExist());
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        mvc.perform(get("/api/combinados/999999").header("Authorization", bearer)).andExpect(status().isForbidden());
        mvc.perform(get(url())).andExpect(status().isUnauthorized());
    }
}
