package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Tarefa;
import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CombinadoDetalheCompletoTest extends GestaoFixture {
    private String url() { return "/api/combinados/" + combinado.getId(); }
    private void estado(StatusCombinado valor) { combinado.setStatus(valor); combinados.saveAndFlush(combinado); }

    @Test void deveRetornarParticipantesTarefasEResponsaveisSemEntidadesOuCredenciais() throws Exception {
        convite(participante, StatusConvite.ACEITO);
        convite(externo, StatusConvite.RECUSADO);
        Tarefa t = new Tarefa();
        t.setDescricao("Conferir entrega"); t.setCombinado(combinado);
        t.getResponsaveis().add(criador); t.getResponsaveis().add(participante);
        tarefas.saveAndFlush(t); em.clear();
        mvc.perform(get(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"))
                .andExpect(jsonPath("$.prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$.participantes[*].nome", contains("Ana", "Externo")))
                .andExpect(jsonPath("$.participantes[*].status", contains("ACEITO", "RECUSADO")))
                .andExpect(jsonPath("$.tarefas", hasSize(1)))
                .andExpect(jsonPath("$.tarefas[0].descricao").value("Conferir entrega"))
                .andExpect(jsonPath("$.tarefas[0].responsaveis", hasSize(2)))
                .andExpect(jsonPath("$.participantes[0].senha").doesNotExist())
                .andExpect(jsonPath("$.participantes[0].perfil").doesNotExist())
                .andExpect(jsonPath("$.tarefas[0].responsaveis[0].senha").doesNotExist())
                .andExpect(jsonPath("$.tarefas[0].combinado").doesNotExist())
                .andExpect(jsonPath("$.criador").doesNotExist());
    }
    @Test void deveUsarUltimoConviteSemDuplicarParticipante() throws Exception {
        var antigo = convite(participante, StatusConvite.ACEITO);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1)); convites.saveAndFlush(antigo);
        convite(participante, StatusConvite.PENDENTE);
        estado(StatusCombinado.ATIVO);
        mvc.perform(get(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.participantes", hasSize(1)))
                .andExpect(jsonPath("$.participantes[0].status").value("PENDENTE"))
                .andExpect(jsonPath("$.acoesPermitidas", not(hasItem("CONCLUIR"))));
    }
    @Test void rascunhoDoCriadorDeveExibirAcoesDeEdicaoEPublicacao() throws Exception {
        estado(StatusCombinado.RASCUNHO);
        mvc.perform(get(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RASCUNHO"))
                .andExpect(jsonPath("$.tarefas", hasSize(0)))
                .andExpect(jsonPath("$.participantes[0].status").value("PENDENTE"))
                .andExpect(jsonPath("$.acoesPermitidas", containsInAnyOrder("EDITAR", "PUBLICAR", "ADICIONAR_TAREFAS", "CANCELAR", "VER_HISTORICO")));
        mvc.perform(get(url()).header("Authorization", token(participante))).andExpect(status().isForbidden());
    }
    @Test void aguardandoPermiteEdicaoAoCriadorMasNaoAoParticipante() throws Exception {
        mvc.perform(get(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.acoesPermitidas",
                        containsInAnyOrder("EDITAR", "ADICIONAR_TAREFAS", "CANCELAR", "VER_HISTORICO")));
        mvc.perform(get(url()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.acoesPermitidas", contains("VER_HISTORICO")));
    }
    @Test void conclusaoSoDeveAparecerQuandoAtivoTodosAceitaramEUsuarioPodeConcluir() throws Exception {
        estado(StatusCombinado.ATIVO);
        var convite = convite(participante, StatusConvite.RECUSADO);
        mvc.perform(get(url()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.acoesPermitidas", not(hasItem("CONCLUIR"))));
        convite.setStatus(StatusConvite.ACEITO); convites.saveAndFlush(convite);
        for (String acesso : new String[]{bearer, token(participante)}) {
            mvc.perform(get(url()).header("Authorization", acesso)).andExpect(status().isOk())
                    .andExpect(jsonPath("$.acoesPermitidas", hasItem("CONCLUIR")))
                    .andExpect(jsonPath("$.acoesPermitidas", not(hasItem("EDITAR"))));
        }
        mvc.perform(get(url()).header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.acoesPermitidas", not(hasItem("CANCELAR"))));
        assertEquals(StatusCombinado.ATIVO, combinado.getStatus());
    }
    @Test void estadosFinaisSoDevemOferecerHistorico() throws Exception {
        for (var valor : new StatusCombinado[]{StatusCombinado.CONCLUIDO, StatusCombinado.CANCELADO}) {
            estado(valor);
            for (String acesso : new String[]{bearer, token(participante)}) {
                mvc.perform(get(url()).header("Authorization", acesso)).andExpect(status().isOk())
                        .andExpect(jsonPath("$.status").value(valor.name()))
                        .andExpect(jsonPath("$.acoesPermitidas", contains("VER_HISTORICO")));
            }
        }
    }
    @Test void adminSemVinculoPodeLerMasNaoRecebeAcoesDeAlteracao() throws Exception {
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        estado(StatusCombinado.RASCUNHO);
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isOk())
                .andExpect(jsonPath("$.participantes").isArray()).andExpect(jsonPath("$.tarefas").isArray())
                .andExpect(jsonPath("$.acoesPermitidas", contains("VER_HISTORICO")));
    }
    @Test void detalhesDevemExigirVinculoEAutenticacao() throws Exception {
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        mvc.perform(get(url())).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/combinados/999999").header("Authorization", bearer)).andExpect(status().isForbidden());
    }
}
