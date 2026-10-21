package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Tarefa;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ParticipantesResponsaveisTest extends GestaoFixture {
    @Test void devePersistirRelacionamentoMuitosParaMuitosSemDuplicatas() {
        Tarefa primeira = tarefa("Preparar entrega");
        primeira.getResponsaveis().add(criador);
        primeira.getResponsaveis().add(participante);
        primeira.getResponsaveis().add(participante);
        tarefas.saveAndFlush(primeira);
        Tarefa segunda = tarefa("Conferir entrega");
        segunda.getResponsaveis().add(participante);
        tarefas.saveAndFlush(segunda);
        em.clear();
        var primeiraRecuperada = tarefas.findById(primeira.getId()).orElseThrow();
        var segundaRecuperada = tarefas.findById(segunda.getId()).orElseThrow();
        assertEquals(2, primeiraRecuperada.getResponsaveis().size());
        assertEquals(java.util.Set.of(criador.getId(), participante.getId()),
            primeiraRecuperada.getResponsaveis().stream()
                .map(usuario -> usuario.getId())
                .collect(java.util.stream.Collectors.toSet()));
        assertEquals(java.util.Set.of(participante.getId()),
            segundaRecuperada.getResponsaveis().stream()
                .map(usuario -> usuario.getId())
                .collect(java.util.stream.Collectors.toSet()));
        tarefas.findById(primeira.getId()).orElseThrow().getResponsaveis().clear();
        tarefas.flush();
        tarefas.deleteById(segunda.getId());
        tarefas.flush();
        assertTrue(usuarios.existsById(participante.getId()));
    }
    private Tarefa tarefa(String descricao) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setCombinado(combinado);
        return tarefa;
    }
    @Test void deveRetornarStatusMaisRecenteSemDuplicarParticipante() throws Exception {
        var antigo = convite(participante, StatusConvite.RECUSADO);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1));
        convites.saveAndFlush(antigo);
        convite(participante, StatusConvite.ACEITO);
        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("Ana"))
                .andExpect(jsonPath("$[0].email").value(participante.getEmail()))
                .andExpect(jsonPath("$[0].status").value("ACEITO"))
                .andExpect(jsonPath("$[0].senha").doesNotExist());
    }
    @Test void deveExibirParticipanteSemConviteComoPendente() throws Exception {
        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].status").value("PENDENTE"));
    }
    @Test void deveIncluirDestinatarioDeConviteLegado() throws Exception {
        convite(externo, StatusConvite.RECUSADO);
        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].status").value("RECUSADO"));
    }
    @Test void deveBloquearConsultaSemVinculoOuLogin() throws Exception {
        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId()).header("Authorization", token(externo)))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId())).andExpect(status().isUnauthorized());
    }
}
