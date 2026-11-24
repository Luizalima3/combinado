package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TarefaCriacaoVinculoParticipantesTest extends GestaoFixture {

    @Test
    @DisplayName("Deve criar tarefas vinculadas aos participantes do combinado e rejeitar responsável sem vínculo (#31018)")
    void deveCriarTarefasEVincularAosParticipantes() throws Exception {
        mvc.perform(post("/api/combinados/{id}/responsabilidades", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Organizar documentos\",\"usuarioId\":" + participante.getId() + "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao").value("Organizar documentos"))
                .andExpect(jsonPath("$.responsaveis", hasSize(1)))
                .andExpect(jsonPath("$.responsaveis[0].id").value(participante.getId().intValue()));

        mvc.perform(post("/api/combinados/{id}/responsabilidades", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Tarefa inválida\",\"usuarioId\":" + externo.getId() + "}"))
                .andExpect(status().isBadRequest());
    }
}
