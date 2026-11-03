package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.http.MediaType;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ResponsabilidadeEdicaoTest extends GestaoFixture {
    private Responsabilidade tarefa() {
        Responsabilidade t = new Responsabilidade(); t.setCombinado(combinado); t.setDescricao("Original");
        t.getResponsaveis().add(participante);
        return tarefas.saveAndFlush(t);
    }
    private String rota(Responsabilidade t) { return "/api/combinados/" + combinado.getId() + "/tarefas/" + t.getId(); }
    private String corpo(String descricao, Long id) { return "{\"descricao\":\"" + descricao + "\",\"usuarioId\":" + id + "}"; }

    @Test void deveSubstituirMultiplosResponsaveisSemDuplicarTarefaOuExcluirUsuarios() throws Exception {
        combinado.adicionarParticipante(externo); combinados.saveAndFlush(combinado);
        var t = tarefa();
        mvc.perform(put("/api/combinados/{id}/responsabilidades/{tarefa}", combinado.getId(), t.getId())
                .header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"  Alterada  \",\"responsaveisIds\":[" + criador.getId() + "," + externo.getId() + "," + externo.getId() + "]}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(t.getId().intValue()))
                .andExpect(jsonPath("$.descricao").value("Alterada"))
                .andExpect(jsonPath("$.responsaveis[*].nome", contains("Criador", "Externo")))
                .andExpect(jsonPath("$.responsaveis[0].senha").doesNotExist());
        em.clear();
        var salva = tarefas.findByIdAndCombinadoId(t.getId(), combinado.getId()).orElseThrow();
        assertEquals(Set.of(criador.getId(), externo.getId()), salva.getResponsaveis().stream()
                .map(Usuario::getId).collect(java.util.stream.Collectors.toSet()));
        assertTrue(usuarios.existsById(participante.getId()));
        assertEquals(1, tarefas.findByCombinadoIdOrderByIdAsc(combinado.getId()).size());
        mvc.perform(get("/api/combinados/{id}/responsabilidades", combinado.getId()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].descricao").value("Alterada"))
                .andExpect(jsonPath("$[0].responsaveis", hasSize(2)));
    }
    @Test void dadosInvalidosNaoDevemAlterarDescricaoOuVinculos() throws Exception {
        var t = tarefa();
        for (String dados : new String[]{"{}", "{\"descricao\":\"Mudou\",\"responsaveisIds\":[]}",
                corpo("Mudou", 999999L), "{\"descricao\":\"Mudou\",\"responsaveisIds\":[null]}",
                corpo("   ", participante.getId()), corpo("Mudou", externo.getId())}) {
            mvc.perform(put(rota(t)).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON).content(dados))
                    .andExpect(status().isBadRequest());
            assertEquals("Original", t.getDescricao());
            assertEquals(Set.of(participante), t.getResponsaveis());
        }
    }
    @Test void naoPodeEditarTarefaDeOutroCombinadoMesmoSendoCriadorDosDois() throws Exception {
        var outro = new Combinado();
        outro.setTitulo("Outro"); outro.setCategoria(CategoriaCombinado.OUTRO);
        outro.setCriador(criador); outro.setCriadorNome(criador.getNome()); outro.setParticipanteNome("Ana");
        combinados.saveAndFlush(outro);
        var t = new Responsabilidade(); t.setCombinado(outro); t.setDescricao("Privada"); t.getResponsaveis().add(criador);
        tarefas.saveAndFlush(t);
        mvc.perform(put(rota(t)).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content(corpo("Não pode mudar", criador.getId()))).andExpect(status().isNotFound());
        mvc.perform(put("/api/combinados/{id}/tarefas/999999", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON).content(corpo("Não existe", criador.getId())))
                .andExpect(status().isNotFound());
        assertEquals("Privada", t.getDescricao());
    }
    @Test void participanteIntrusoEAdminSemVinculoNaoPodemEditar() throws Exception {
        var t = tarefa();
        for (String acesso : new String[]{token(participante), token(externo)}) {
            mvc.perform(put(rota(t)).header("Authorization", acesso).contentType(MediaType.APPLICATION_JSON)
                    .content(corpo("Mudou", participante.getId()))).andExpect(status().isForbidden());
        }
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(put(rota(t)).header("Authorization", token(externo)).contentType(MediaType.APPLICATION_JSON)
                .content(corpo("Mudou", participante.getId()))).andExpect(status().isForbidden());
        mvc.perform(put(rota(t)).contentType(MediaType.APPLICATION_JSON).content(corpo("Mudou", participante.getId())))
                .andExpect(status().isUnauthorized());
        assertEquals("Original", t.getDescricao());
    }
    @ParameterizedTest
    @EnumSource(value = StatusCombinado.class, names = {"ATIVO", "CONCLUIDO", "CANCELADO"})
    void estadosNaoEditaveisDevemPreservarTarefa(StatusCombinado estado) throws Exception {
        var t = tarefa(); combinado.setStatus(estado); combinados.saveAndFlush(combinado);
        mvc.perform(put(rota(t)).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content(corpo("Mudou", participante.getId()))).andExpect(status().isBadRequest());
        assertEquals("Original", t.getDescricao());
        assertEquals(Set.of(participante), t.getResponsaveis());
    }
    @Test void criadorPodeEditarTarefaEmRascunhoSemPublicarCombinado() throws Exception {
        var t = tarefa(); combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(put(rota(t)).header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content(corpo("Rascunho editado", criador.getId()))).andExpect(status().isOk());
        em.flush(); em.clear();
        assertEquals("Rascunho editado", tarefas.findByIdAndCombinadoId(t.getId(), combinado.getId()).orElseThrow().getDescricao());
        assertEquals(StatusCombinado.RASCUNHO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
    }
}
