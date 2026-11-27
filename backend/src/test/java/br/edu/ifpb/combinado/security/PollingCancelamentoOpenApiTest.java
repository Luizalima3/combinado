package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PollingCancelamentoOpenApiTest extends GestaoFixture {
    @Autowired CombinadoHistoricoRepository historicos;
    private String base() { return "/api/combinados/" + combinado.getId(); }
    @Test void pollingDeveRefletirAceiteEAtivacaoSemCarregarTarefasOuGerarEventos() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get(base() + "/status").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(header().string("Cache-Control", "no-store"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$.participantes[0].status").value("PENDENTE"))
                .andExpect(jsonPath("$.tarefas").doesNotExist()).andExpect(jsonPath("$.consultadoEm").isString());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
        mvc.perform(patch("/api/convites/{id}/aceitar", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk());
        mvc.perform(get(base() + "/status").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ATIVO"))
                .andExpect(jsonPath("$.participantes[0].status").value("ACEITO"))
                .andExpect(jsonPath("$.acoesPermitidas", hasItem("CONCLUIR")));
        mvc.perform(get(base() + "/status").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.acoesPermitidas", not(hasItem("CONCLUIR"))));
        assertEquals(1, historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).size());
    }
    @Test void pollingDevePreservarAutenticacaoVinculosEPrivacidadeDoRascunho() throws Exception {
        mvc.perform(get(base() + "/status")).andExpect(status().isUnauthorized());
        mvc.perform(get(base() + "/status").header("Authorization", token(externo))).andExpect(status().isForbidden());
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(get(base() + "/status").header("Authorization", token(participante))).andExpect(status().isForbidden());
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(get(base() + "/status").header("Authorization", token(externo))).andExpect(status().isOk())
                .andExpect(jsonPath("$.acoesPermitidas", contains("VER_HISTORICO")));
    }
    @ParameterizedTest
    @EnumSource(value = StatusCombinado.class, names = {"RASCUNHO", "AGUARDANDO_CONFIRMACAO", "ATIVO"})
    void criadorPodeCancelarEmQualquerEstadoNaoFinal(StatusCombinado estado) throws Exception {
        combinado.setStatus(estado); combinados.saveAndFlush(combinado);
        mvc.perform(patch(base() + "/cancelar").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"motivo\":\"  Não será necessário  \"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("CANCELADO"));
        em.flush(); em.clear();
        assertEquals(StatusCombinado.CANCELADO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());
        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertEquals(1, eventos.size());
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, eventos.getFirst().getAcao());
        assertEquals("Status alterado de " + estado + " para CANCELADO.", eventos.getFirst().getDescricao());
        assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
        assertEquals("Não será necessário", eventos.getFirst().getMotivo());
    }
    @ParameterizedTest
    @EnumSource(value = StatusCombinado.class, names = {"CONCLUIDO", "CANCELADO"})
    void estadosFinaisDevemBloquearCancelamentoSemCriarEvento(StatusCombinado estado) throws Exception {
        combinado.setStatus(estado); combinados.saveAndFlush(combinado);
        mvc.perform(patch(base() + "/cancelar").header("Authorization", bearer)).andExpect(status().isBadRequest());
        em.clear();
        assertEquals(estado, combinados.findById(combinado.getId()).orElseThrow().getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
    @Test void participanteEAdminSemVinculoNaoPodemCancelar() throws Exception {
        mvc.perform(patch(base() + "/cancelar").header("Authorization", token(participante))).andExpect(status().isForbidden());
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(patch(base() + "/cancelar").header("Authorization", token(externo))).andExpect(status().isForbidden());
    }
    @Test void openApiDeveDescreverAutenticacaoCadastroPerfilEPolling() throws Exception {
        mvc.perform(get("/v3/api-docs")).andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/auth/login'].post.summary").value("Autenticar usuário"))
                .andExpect(jsonPath("$.paths['/api/auth/login'].post.security", hasSize(0)))
                .andExpect(jsonPath("$.paths['/api/auth/login'].post.responses['200'].content['application/json'].schema").exists())
                .andExpect(jsonPath("$.paths['/api/auth/logout'].post.responses['204']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios'].post.security", hasSize(0)))
                .andExpect(jsonPath("$.paths['/api/usuarios'].post.responses['201']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/buscar'].get.responses['401']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/perfil/email'].put.responses['409']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/perfil/senha'].put.responses['204']").exists())
                .andExpect(jsonPath("$.paths['/api/combinados/{id}/status'].get.summary").exists())
                .andExpect(jsonPath("$.components.schemas.CombinadoStatusDTO").exists());
    }
}
