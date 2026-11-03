package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConvitesLoteRecebidosSwaggerTest extends GestaoFixture {
    @Autowired ConviteHistoricoRepository historico;
    @Autowired NotificacaoRepository notificacoes;
    private String lote() { return "/api/combinados/" + combinado.getId() + "/convites/lote"; }
    @Test void devePersistirLotePendenteComEventosENotificacoes() throws Exception {
        mvc.perform(post(lote()).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"participantesIds\":[" + participante.getId() + "," + externo.getId() + "],\"mensagem\":\"Participe\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].status", everyItem(is("PENDENTE"))));
        em.flush(); em.clear();
        var salvos = convites.findByCombinadoId(combinado.getId());
        assertEquals(2, salvos.size());
        for (var salvo : salvos) {
            assertEquals("Participe", salvo.getMensagem());
            assertEquals(criador.getId(), salvo.getRemetente().getId());
            var eventos = historico.findByConviteIdOrderByOcorridoEmAscIdAsc(salvo.getId());
            assertEquals(1, eventos.size());
            assertEquals(AcaoConvite.ENVIO, eventos.getFirst().getAcao());
            assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
            assertEquals(1, notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(salvo.getDestinatario().getId()).size());
        }
    }
    @Test void participanteInexistenteDeveInvalidarLoteInteiro() throws Exception {
        mvc.perform(post(lote()).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"participantesIds\":[" + participante.getId() + ",999999]}"))
                .andExpect(status().isBadRequest());
        assertTrue(convites.findByCombinadoId(combinado.getId()).isEmpty());
        assertTrue(notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(participante.getId()).isEmpty());
    }
    @Test void deveRejeitarListaVaziaDuplicatasNulosECriador() throws Exception {
        for (String lista : new String[]{"[]", "[null]", "[" + participante.getId() + "," + participante.getId() + "]",
                "[" + criador.getId() + "]"}) {
            mvc.perform(post(lote()).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                    .content("{\"participantesIds\":" + lista + "}")).andExpect(status().isBadRequest());
        }
        assertTrue(convites.findByCombinadoId(combinado.getId()).isEmpty());
    }
    @Test void pendenciaExistenteNaoPodeDeixarParteDoLoteGravada() throws Exception {
        convite(externo, StatusConvite.PENDENTE);
        mvc.perform(post(lote()).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"participantesIds\":[" + participante.getId() + "," + externo.getId() + "]}"))
                .andExpect(status().isConflict());
        assertEquals(1, convites.findByCombinadoId(combinado.getId()).size());
        assertTrue(notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(participante.getId()).isEmpty());
    }
    @Test void recebidosDevemUsarSomenteUsuarioLogadoEPaginar() throws Exception {
        var primeiro = convite(participante, StatusConvite.RECUSADO);
        primeiro.setDataEnvio(primeiro.getDataEnvio().minusDays(1)); convites.saveAndFlush(primeiro);
        var recente = convite(participante, StatusConvite.PENDENTE);
        convite(externo, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("usuarioId", externo.getId().toString()).param("limite", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(recente.getId().intValue()))
                .andExpect(jsonPath("$[0].titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$[0].nomeCriador").value("Criador"))
                .andExpect(jsonPath("$[0].senha").doesNotExist());
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("pagina", "1").param("limite", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(primeiro.getId().intValue()));
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void recebidosNaoDevemExporRascunhosNemAceitarPaginacaoInvalida() throws Exception {
        convite(participante, StatusConvite.PENDENTE);
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer).param("limite", "101"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer).param("pagina", "-1"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/convites/recebidos")).andExpect(status().isUnauthorized());
        mvc.perform(post(lote()).header("Authorization", token(participante)).contentType(MediaType.APPLICATION_JSON)
                .content("{\"participantesIds\":[" + externo.getId() + "]}")).andExpect(status().isForbidden());
    }
    @Test void historicoDeveTerRotaPropriaComPermissaoDoConvite() throws Exception {
        mvc.perform(post(lote()).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"participantesIds\":[" + participante.getId() + "]}")).andExpect(status().isCreated());
        Long id = convites.findByCombinadoId(combinado.getId()).getFirst().getId();
        mvc.perform(patch("/api/convites/{id}/aceitar", id).header("Authorization", token(participante)))
                .andExpect(status().isOk());
        mvc.perform(get("/api/convites/{id}/historico", id).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].acao", contains("ENVIO", "ACEITE")))
                .andExpect(jsonPath("$[1].autorId").value(participante.getId().intValue()))
                .andExpect(jsonPath("$[1].ocorridoEm").isString());
        mvc.perform(get("/api/convites/{id}/historico", id).header("Authorization", token(externo)))
                .andExpect(status().isForbidden());
    }
    @Test void swaggerDeveDocumentarRotasEsquemasETiposDoHistorico() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/combinados/{combinadoId}/convites'].post.summary").isString())
                .andExpect(jsonPath("$.paths['/api/combinados/{combinadoId}/convites/lote'].post.requestBody").exists())
                .andExpect(jsonPath("$.paths['/api/convites/{id}/aceitar'].patch.responses['409']").exists())
                .andExpect(jsonPath("$.paths['/api/convites/{id}/recusar'].patch.responses['403']").exists())
                .andExpect(jsonPath("$.paths['/api/convites/{id}/historico'].get.summary").isString())
                .andExpect(jsonPath("$.components.schemas.ConviteHistoricoDTO.properties.autorId").exists())
                .andExpect(jsonPath("$.components.schemas.ConviteHistoricoDTO.properties.ocorridoEm.format").value("date-time"))
                .andExpect(jsonPath("$.components.schemas.ConviteHistoricoDTO.properties.acao.enum", hasItems("ENVIO", "ACEITE", "RECUSA")))
                .andExpect(jsonPath("$.components.securitySchemes.bearerAuth.scheme").value("bearer"));
        mvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk());
    }
}
