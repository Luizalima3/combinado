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

class ConviteRegrasRespostaTest extends GestaoFixture {
    @Autowired NotificacaoRepository notificacoes;
    @Autowired ConviteHistoricoRepository historicos;
    @Autowired CombinadoHistoricoRepository eventos;
    private void vincularSegundo() { combinado.adicionarParticipante(externo); combinados.saveAndFlush(combinado); }

    @Test void apenasUltimoAceiteDeveAtivarCombinadoERegistrarAutor() throws Exception {
        vincularSegundo();
        var primeiro = convite(participante, StatusConvite.PENDENTE);
        var segundo = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/aceitar", primeiro.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("AGUARDANDO_CONFIRMACAO"));
        assertNull(combinado.getAceitoEm());
        mvc.perform(patch("/api/convites/{id}/aceitar", segundo.getId()).header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("ATIVO"));
        em.flush(); em.clear();
        assertEquals(StatusCombinado.ATIVO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
        var ativacoes = eventos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertEquals(1, ativacoes.size());
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, ativacoes.getFirst().getAcao());
        assertEquals(externo.getId(), ativacoes.getFirst().getAutor().getId());
        assertNotNull(ativacoes.getFirst().getOcorridoEm());
    }
    @Test void recusaDevePreservarEstadoNotificarCriadorEPersistirMotivo() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante))
                .contentType(MediaType.APPLICATION_JSON).content("{\"motivo\":\"  Não poderei participar.  \"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RECUSADO"))
                .andExpect(jsonPath("$.statusCombinado").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$.motivoRecusa").value("Não poderei participar."));
        em.flush(); em.clear();
        assertEquals("Não poderei participar.", convites.findById(convite.getId()).orElseThrow().getMotivoRecusa());
        var alertas = notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(criador.getId());
        assertEquals(1, alertas.size());
        assertTrue(alertas.getFirst().getConteudo().contains("Ana"));
        assertTrue(alertas.getFirst().getConteudo().contains("Não poderei participar."));
        assertEquals("Não poderei participar.", historicos.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).getFirst().getMotivo());
        mvc.perform(get("/api/convites/{id}", convite.getId()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.motivoRecusa").value("Não poderei participar."))
                .andExpect(jsonPath("$.historico[0].motivo").value("Não poderei participar."));
    }
    @Test void motivoPodeSerOmitidoNuloOuVazio() throws Exception {
        for (String corpo : new String[]{"{}", "{\"motivo\":null}", "{\"motivo\":\"   \"}"}) {
            var convite = convite(participante, StatusConvite.PENDENTE);
            mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante))
                    .contentType(MediaType.APPLICATION_JSON).content(corpo)).andExpect(status().isOk());
            assertNull(convites.findById(convite.getId()).orElseThrow().getMotivoRecusa());
        }
    }
    @Test void motivoLongoDeveSerRecusadoAntesDeAlterarConvite() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante))
                .contentType(MediaType.APPLICATION_JSON).content("{\"motivo\":\"" + "a".repeat(501) + "\"}"))
                .andExpect(status().isBadRequest());
        assertEquals(StatusConvite.PENDENTE, convite.getStatus());
        assertTrue(historicos.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).isEmpty());
        assertTrue(notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(criador.getId()).isEmpty());
    }
    @Test void participanteSemConviteImpedeAtivacao() throws Exception {
        var convite = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/aceitar", convite.getId()).header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("AGUARDANDO_CONFIRMACAO"));
        assertTrue(eventos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @Test void recusaImpedeAtivacaoMesmoQuandoOutroAceita() throws Exception {
        vincularSegundo();
        var primeiro = convite(participante, StatusConvite.PENDENTE);
        var segundo = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", primeiro.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk());
        mvc.perform(patch("/api/convites/{id}/aceitar", segundo.getId()).header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("AGUARDANDO_CONFIRMACAO"));
        assertNull(combinado.getAceitoEm());
    }
    @Test void conviteMaisRecenteDevePrevalecerNaRegraDeTodosAceitaram() throws Exception {
        vincularSegundo();
        var antigo = convite(participante, StatusConvite.ACEITO);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1)); convites.saveAndFlush(antigo);
        convite(participante, StatusConvite.RECUSADO);
        var convite = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/aceitar", convite.getId()).header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("AGUARDANDO_CONFIRMACAO"));
    }
    @Test void recusaEmCombinadoAtivoNaoDeveRebaixarEstado() throws Exception {
        combinado.setStatus(StatusCombinado.ATIVO); combinados.saveAndFlush(combinado);
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statusCombinado").value("ATIVO"));
        assertEquals(1, notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(criador.getId()).size());
    }
    @Test void rotaLegadaTambemDeveAguardarTodosAceitaremSemConfiarNaDataDoCliente() throws Exception {
        vincularSegundo();
        var primeiro = convite(participante, StatusConvite.PENDENTE);
        convite(externo, StatusConvite.PENDENTE);
        String rota = "/api/combinados/" + combinado.getId() + "/aceitar";
        mvc.perform(patch(rota).header("Authorization", token(participante)).contentType(MediaType.APPLICATION_JSON)
                .content("{\"respondidoEm\":\"2000-01-01T00:00:00Z\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"));
        assertEquals(StatusConvite.ACEITO, primeiro.getStatus());
        mvc.perform(patch(rota).header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ATIVO"));
        assertTrue(combinado.getAceitoEm().getYear() > 2000);
    }
    @Test void recusaRepetidaNaoPodeDuplicarHistoricoOuNotificacao() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk());
        mvc.perform(patch("/api/convites/{id}/recusar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isConflict());
        assertEquals(1, notificacoes.findByUsuarioIdOrderByDataCriacaoDesc(criador.getId()).size());
        assertEquals(1, historicos.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).size());
    }
}
