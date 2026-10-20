package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RascunhosDetalhesTest extends GestaoFixture {
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
        combinado.getParticipantes().clear();
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
        assertTrue(combinado.getParticipantes().contains(participante));
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
        combinado.getParticipantes().clear();
        combinado.getParticipantes().add(criador);
        combinados.saveAndFlush(combinado);
        mvc.perform(post(url() + "/publicar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        assertEquals(StatusCombinado.RASCUNHO, combinado.getStatus());
    }
    @Test void deveBloquearAceiteEmRascunhoEReaberturaDeEstadoFinal() throws Exception {
        rascunho();
        mvc.perform(patch(url() + "/aceitar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        combinado.setStatus(StatusCombinado.CANCELADO);
        combinados.saveAndFlush(combinado);
        mvc.perform(patch(url() + "/aceitar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        mvc.perform(patch(url() + "/recusar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        assertEquals(StatusCombinado.CANCELADO, combinado.getStatus());
        assertNull(combinado.getAceitoEm());
    }
    @Test void deveAceitarPendenteERecusarAtivoSemPermitirAceiteRepetido() throws Exception {
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ATIVO"));
        assertNotNull(combinado.getAceitoEm());
        mvc.perform(patch(url() + "/aceitar").header("Authorization", token(participante)))
                .andExpect(status().isBadRequest());
        mvc.perform(patch(url() + "/recusar").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("CANCELADO"));
        assertNotNull(combinado.getRecusadoEm());
    }
    @Test void detalhesDevemSerProtegidosEConterInformacoesDoCombinado() throws Exception {
        mvc.perform(get(url()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$.categoria").value("OUTRO"))
                .andExpect(jsonPath("$.prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"))
                .andExpect(jsonPath("$.criador").doesNotExist()).andExpect(jsonPath("$.participantes").doesNotExist());
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        mvc.perform(get("/api/combinados/999999").header("Authorization", bearer)).andExpect(status().isForbidden());
        mvc.perform(get(url())).andExpect(status().isUnauthorized());
    }
}
