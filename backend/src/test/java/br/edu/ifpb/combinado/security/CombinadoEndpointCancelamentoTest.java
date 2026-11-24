package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoEndpointCancelamentoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve cancelar combinado pelo criador via PATCH /api/combinados/{id}/cancelar e impedir segundo cancelamento (#32333)")
    void deveCancelarCombinadoPeloEndpointDeCancelamento() throws Exception {
        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Mudança de planos\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADO"));

        assertEquals(StatusCombinado.CANCELADO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());

        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isBadRequest());
    }
}
