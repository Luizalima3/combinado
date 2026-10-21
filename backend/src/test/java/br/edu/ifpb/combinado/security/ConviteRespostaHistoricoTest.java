package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConviteRespostaHistoricoTest extends GestaoFixture {
    @Autowired ConviteHistoricoRepository historico;
    private Long enviar() throws Exception {
        String json = mvc.perform(post("/api/combinados/{id}/convites", combinado.getId())
                        .header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"destinatarioId\":" + participante.getId() + ",\"remetenteId\":" + externo.getId() + "}"))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(json, "$.id")).longValue();
    }
    @Test void devePersistirEnvioAceiteAutorEDatasETornarHistoricoConsultavel() throws Exception {
        Instant inicio = Instant.now().minusSeconds(1);
        Long id = enviar();
        mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", token(participante))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"autorId\":" + externo.getId() + ",\"dataResposta\":\"2000-01-01T00:00:00Z\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ACEITO"))
                .andExpect(jsonPath("$.dataResposta").isString());
        em.flush(); em.clear();
        var salvo = convites.findById(id).orElseThrow();
        assertEquals(StatusConvite.ACEITO, salvo.getStatus());
        assertTrue(salvo.getDataResposta().isAfter(inicio));
        var eventos = historico.findByConviteIdOrderByOcorridoEmAscIdAsc(id);
        assertEquals(2, eventos.size());
        assertEquals(AcaoConvite.ENVIO, eventos.get(0).getAcao());
        assertEquals(criador.getId(), eventos.get(0).getAutor().getId());
        assertEquals(AcaoConvite.ACEITE, eventos.get(1).getAcao());
        assertEquals(participante.getId(), eventos.get(1).getAutor().getId());
        assertEquals(salvo.getDataResposta(), eventos.get(1).getOcorridoEm());
        var usuario = usuarios.findById(participante.getId()).orElseThrow();
        usuario.setNome("Nome alterado depois");
        usuarios.saveAndFlush(usuario);
        mvc.perform(get("/api/convites/{id}", id).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.historico", hasSize(2)))
                .andExpect(jsonPath("$.historico[1].autorNome").value("Ana"))
                .andExpect(jsonPath("$.historico[1].acao").value("ACEITE"))
                .andExpect(jsonPath("$.historico[1].ocorridoEm").isString())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(1)))
                .andExpect(jsonPath("$.historico[1].autor.senha").doesNotExist());
    }
    @Test void devePersistirRecusaSemAlterarEstadoDoCombinado() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RECUSADO"));
        em.flush(); em.clear();
        assertEquals(StatusConvite.RECUSADO, convites.findById(convite.getId()).orElseThrow().getStatus());
        var evento = historico.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).getFirst();
        assertEquals(AcaoConvite.RECUSA, evento.getAcao());
        assertEquals(participante.getId(), evento.getAutor().getId());
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
    }
    @Test void somenteDestinatarioPodeResponder() throws Exception {
        Long id = enviar();
        for (String acesso : new String[]{bearer, token(externo)}) {
            mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", acesso)).andExpect(status().isForbidden());
            mvc.perform(patch("/api/convites/{id}/recusar", id).header("Authorization", acesso)).andExpect(status().isForbidden());
        }
        mvc.perform(patch("/api/convites/{id}/aceitar", id)).andExpect(status().isUnauthorized());
        mvc.perform(patch("/api/convites/999999/aceitar").header("Authorization", bearer)).andExpect(status().isForbidden());
        assertEquals(StatusConvite.PENDENTE, convites.findById(id).orElseThrow().getStatus());
        assertEquals(1, historico.findByConviteIdOrderByOcorridoEmAscIdAsc(id).size());
    }
    @Test void respostaRepetidaOuContraditoriaNaoPodeDuplicarHistorico() throws Exception {
        Long id = enviar();
        mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", token(participante))).andExpect(status().isOk());
        Instant data = convites.findById(id).orElseThrow().getDataResposta();
        mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", token(participante))).andExpect(status().isConflict());
        mvc.perform(patch("/api/convites/{id}/recusar", id).header("Authorization", token(participante))).andExpect(status().isConflict());
        assertEquals(2, historico.findByConviteIdOrderByOcorridoEmAscIdAsc(id).size());
        assertEquals(data, convites.findById(id).orElseThrow().getDataResposta());
        assertEquals(StatusConvite.ACEITO, convites.findById(id).orElseThrow().getStatus());
    }
    @Test void deveBloquearConviteAntigoSubstituidoPorOutro() throws Exception {
        var antigo = convite(participante, StatusConvite.PENDENTE);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1));
        convites.saveAndFlush(antigo);
        convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/aceitar", antigo.getId()).header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        assertEquals(StatusConvite.PENDENTE, antigo.getStatus());
        assertTrue(historico.findByConviteIdOrderByOcorridoEmAscIdAsc(antigo.getId()).isEmpty());
    }
    @Test void combinadoFinalizadoNaoPermiteRespostaNemEnvio() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        combinado.setStatus(StatusCombinado.CANCELADO);
        combinados.saveAndFlush(combinado);
        mvc.perform(patch("/api/convites/{id}/aceitar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        mvc.perform(post("/api/combinados/{id}/convites", combinado.getId()).header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"destinatarioId\":" + externo.getId() + "}"))
                .andExpect(status().isConflict());
        assertNull(convite.getDataResposta());
    }
    @Test void naoDeveEnviarConviteDuplicadoNemReenviarParaQuemAceitou() throws Exception {
        Long id = enviar();
        String corpo = "{\"destinatarioId\":" + participante.getId() + "}";
        mvc.perform(post("/api/combinados/{id}/convites", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON).content(corpo)).andExpect(status().isConflict());
        mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", token(participante))).andExpect(status().isOk());
        mvc.perform(post("/api/combinados/{id}/convites", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON).content(corpo)).andExpect(status().isConflict());
        assertEquals(1, convites.findByCombinadoId(combinado.getId()).size());
    }
    @Test void podeReenviarDepoisDeRecusaMantendoHistoricoAnterior() throws Exception {
        Long antigo = enviar();
        mvc.perform(patch("/api/convites/{id}/recusar", antigo).header("Authorization", token(participante))).andExpect(status().isOk());
        Long novo = enviar();
        assertNotEquals(antigo, novo);
        assertEquals(2, historico.findByConviteIdOrderByOcorridoEmAscIdAsc(antigo).size());
        assertEquals(1, historico.findByConviteIdOrderByOcorridoEmAscIdAsc(novo).size());
        assertEquals(StatusConvite.PENDENTE, convites.findById(novo).orElseThrow().getStatus());
    }
}
