package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TarefaColecaoIntegridadeTest extends GestaoFixture {

    @Test
    @DisplayName("Deve preservar a integridade e ordem da coleção de tarefas associadas ao combinado (#31022)")
    void deveManterIntegridadeDaColecaoDeTarefasPorCombinado() throws Exception {
        String lote = "[{\"descricao\":\"Etapa 1\",\"usuarioId\":" + criador.getId() + "},"
                + "{\"descricao\":\"Etapa 2\",\"responsaveisIds\":[" + criador.getId() + "," + participante.getId() + "]}]";

        mvc.perform(post("/api/combinados/{id}/tarefas", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lote))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(2)));

        mvc.perform(get("/api/combinados/{id}/tarefas", combinado.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].descricao", contains("Etapa 1", "Etapa 2")))
                .andExpect(jsonPath("$[1].responsaveis", hasSize(2)));
    }
}
