package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TarefasRascunhosAdminTest extends GestaoFixture {
    private String url() { return "/api/combinados/" + combinado.getId(); }
    @Test void devePersistirResponsabilidadeSingularEMultiplasTarefasERetornarColecaoCompleta() throws Exception {
        mvc.perform(post(url() + "/responsabilidades").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Primeira\",\"usuarioId\":" + participante.getId() + "}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.responsaveis[0].nome").value("Ana"));
        mvc.perform(post(url() + "/tarefas").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("[{\"descricao\":\"Segunda\",\"responsaveisIds\":[" + criador.getId() + "," + participante.getId() + "]},"
                        + "{\"descricao\":\"Terceira\",\"usuarioId\":" + criador.getId() + "}]"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$", hasSize(2)));
        em.flush(); em.clear();
        mvc.perform(get(url() + "/tarefas").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].descricao", contains("Primeira", "Segunda", "Terceira")))
                .andExpect(jsonPath("$[1].responsaveis", hasSize(2)))
                .andExpect(jsonPath("$[0].responsaveis[0].senha").doesNotExist());
    }
    @Test void loteInvalidoNaoPodePersistirPrimeiroItem() throws Exception {
        mvc.perform(post(url() + "/tarefas").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("[{\"descricao\":\"Valida\",\"usuarioId\":" + criador.getId() + "},"
                        + "{\"descricao\":\"Invalida\",\"usuarioId\":" + externo.getId() + "}]"))
                .andExpect(status().isBadRequest());
        assertTrue(tarefas.findByCombinadoIdOrderByIdAsc(combinado.getId()).isEmpty());
    }
    @Test void somenteCriadorPodeAdicionarTarefasEConsultaExigeVinculo() throws Exception {
        mvc.perform(post(url() + "/tarefas").header("Authorization", token(participante))
                .contentType(MediaType.APPLICATION_JSON).content("[]")).andExpect(status().isForbidden());
        mvc.perform(get(url() + "/tarefas").header("Authorization", token(externo))).andExpect(status().isForbidden());
        mvc.perform(get(url() + "/tarefas")).andExpect(status().isUnauthorized());
    }
    @Test void deveRecusarTarefasVaziasSemResponsavelOuEmCombinadoFinalizado() throws Exception {
        for (String corpo : new String[]{"[]", "[null]", "[{\"descricao\":\"Sem responsável\"}]",
                "[{\"descricao\":\"\",\"usuarioId\":" + participante.getId() + "}]"}) {
            mvc.perform(post(url() + "/tarefas").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                    .content(corpo)).andExpect(status().isBadRequest());
        }
        combinado.setStatus(StatusCombinado.CONCLUIDO); combinados.saveAndFlush(combinado);
        mvc.perform(post(url() + "/responsabilidades").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Tardia\",\"usuarioId\":" + criador.getId() + "}"))
                .andExpect(status().isBadRequest());
    }
    @Test void deveAtualizarRascunhoSomentePeloCriadorSemPublicar() throws Exception {
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\":\" Editado \",\"criadorNome\":\"Forjado\",\"status\":\"ATIVO\",\"participantesIds\":[]}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Editado"))
                .andExpect(jsonPath("$.nomeCriador").value("Criador"))
                .andExpect(jsonPath("$.status").value("RASCUNHO"));
        em.flush(); em.clear();
        assertNull(combinados.findById(combinado.getId()).orElseThrow().getCategoria());
        assertTrue(convites.findByCombinadoId(combinado.getId()).isEmpty());
        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId()).header("Authorization", token(participante))
                .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\"Outro\"}"))
                .andExpect(status().isForbidden());
    }
    @Test void deveBloquearEdicaoDePublicadoETituloVazioSemAlterarRegistro() throws Exception {
        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\"Alterado\"}"))
                .andExpect(status().isBadRequest());
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId()).header("Authorization", bearer)
                .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\" \"}"))
                .andExpect(status().isBadRequest());
        assertEquals("Combinado de teste", combinado.getTitulo());
    }
    @Test void adminPodeConsultarGlobalmenteInclusiveRascunhosMasNaoAlterarOutros() throws Exception {
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        String admin = token(externo);
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(get("/api/combinados").header("Authorization", admin)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        mvc.perform(get(url()).header("Authorization", admin)).andExpect(status().isOk());
        mvc.perform(get(url() + "/participantes").header("Authorization", admin)).andExpect(status().isOk());
        mvc.perform(get(url() + "/tarefas").header("Authorization", admin)).andExpect(status().isOk());
        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId()).header("Authorization", admin)
                .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\"Admin\"}")).andExpect(status().isForbidden());
        mvc.perform(post(url() + "/publicar").header("Authorization", admin)).andExpect(status().isForbidden());
        mvc.perform(patch(url() + "/aceitar").header("Authorization", admin)).andExpect(status().isForbidden());
        mvc.perform(post(url() + "/tarefas").header("Authorization", admin).contentType(MediaType.APPLICATION_JSON)
                .content("[]")).andExpect(status().isForbidden());
    }
}
