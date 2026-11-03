package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CombinadoConclusaoEndpointTest extends GestaoFixture {
    @Autowired CombinadoHistoricoRepository historicos;
    private String url() { return "/api/combinados/" + combinado.getId() + "/concluir"; }
    private void estado(StatusCombinado estado) { combinado.setStatus(estado); combinados.saveAndFlush(combinado); }
    private void segundoParticipanteAceito() {
        combinado.adicionarParticipante(externo); combinados.saveAndFlush(combinado);
        convite(externo, StatusConvite.ACEITO);
    }
    @Test void ativoComTodosAceitosDeveConcluirPersistirHistoricoERetornarMensagem() throws Exception {
        estado(StatusCombinado.ATIVO);
        convite(participante, StatusConvite.ACEITO);
        segundoParticipanteAceito();
        mvc.perform(patch(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("CONCLUIDO"))
                .andExpect(jsonPath("$.message").value("Combinado concluído com sucesso!"))
                .andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$.dataPrazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$.prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$.criador").doesNotExist())
                .andExpect(jsonPath("$.senha").doesNotExist());
        em.flush(); em.clear();
        assertEquals(StatusCombinado.CONCLUIDO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertEquals(1, eventos.size());
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, eventos.getFirst().getAcao());
        assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
        assertNotNull(eventos.getFirst().getOcorridoEm());
    }
    @ParameterizedTest
    @EnumSource(value = StatusConvite.class, names = {"PENDENTE", "RECUSADO"})
    void umPendenteOuRecusadoDeveBloquearMesmoComOutroAceito(StatusConvite resposta) throws Exception {
        estado(StatusCombinado.ATIVO);
        convite(participante, resposta);
        segundoParticipanteAceito();
        mvc.perform(patch(url()).header("Authorization", bearer))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.pendentes", contains("Ana")))
                .andExpect(jsonPath("$.message", containsString("ainda não confirmaram")));
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @Test void participanteVinculadoSemConviteDeveContinuarPendente() throws Exception {
        estado(StatusCombinado.ATIVO);
        mvc.perform(patch(url()).header("Authorization", bearer))
                .andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.pendentes", contains("Ana")));
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
    }
    @Test void convitePendenteMaisRecenteDeveInvalidarAceiteAnterior() throws Exception {
        estado(StatusCombinado.ATIVO);
        var antigo = convite(participante, StatusConvite.ACEITO);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1)); convites.saveAndFlush(antigo);
        convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch(url()).header("Authorization", bearer))
                .andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.pendentes", contains("Ana")));
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @ParameterizedTest
    @EnumSource(value = StatusCombinado.class, names = {"RASCUNHO", "AGUARDANDO_CONFIRMACAO", "CONCLUIDO", "CANCELADO"})
    void estadosDiferentesDeAtivoDevemBloquearConclusao(StatusCombinado estado) throws Exception {
        estado(estado);
        convite(participante, StatusConvite.ACEITO);
        mvc.perform(patch(url()).header("Authorization", bearer))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message", containsString("não permitida")));
        assertEquals(estado, combinado.getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @Test void participanteAceitoNaoPodeConcluirNemGerarHistorico() throws Exception {
        estado(StatusCombinado.ATIVO);
        convite(participante, StatusConvite.ACEITO);
        mvc.perform(patch(url()).header("Authorization", token(participante)))
                .andExpect(status().isForbidden());
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @Test void conclusaoRepetidaNaoDeveDuplicarEvento() throws Exception {
        estado(StatusCombinado.ATIVO);
        convite(participante, StatusConvite.ACEITO);
        mvc.perform(patch(url()).header("Authorization", bearer)).andExpect(status().isOk());
        mvc.perform(patch(url()).header("Authorization", bearer)).andExpect(status().isBadRequest());
        assertEquals(1, historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).size());
        assertEquals(StatusCombinado.CONCLUIDO, combinado.getStatus());
    }
    @Test void usuarioSemVinculoAdminSemVinculoOuSemLoginNaoPodeConcluir() throws Exception {
        estado(StatusCombinado.ATIVO);
        convite(participante, StatusConvite.ACEITO);
        mvc.perform(patch(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(patch(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        mvc.perform(patch(url())).andExpect(status().isUnauthorized());
        mvc.perform(patch("/api/combinados/999999/concluir").header("Authorization", bearer)).andExpect(status().isForbidden());
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
    }
}
