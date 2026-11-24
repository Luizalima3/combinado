package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoDetalheResponsabilidadesTarefasTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar responsabilidades e coleção de tarefas no detalhe do combinado (#32302)")
    void deveRetornarResponsabilidadesETarefasNoDetalhe() throws Exception {
        combinado.setResponsabilidades("Manter comunicação diária");
        combinados.saveAndFlush(combinado);

        mvc.perform(post("/api/combinados/{id}/responsabilidades", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Revisar entrega\",\"usuarioId\":" + participante.getId() + "}"))
                .andExpect(status().isCreated());

        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responsabilidades").value("Manter comunicação diária"))
                .andExpect(jsonPath("$.tarefas", hasSize(1)))
                .andExpect(jsonPath("$.tarefas[0].descricao").value("Revisar entrega"))
                .andExpect(jsonPath("$.tarefas[0].responsaveis[0].nome").value(participante.getNome()));
    }
}
