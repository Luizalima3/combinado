package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoCancelamentoBloqueioNaoCriadorTest extends GestaoFixture {

    @Test
    @DisplayName("Deve bloquear tentativa de cancelamento do combinado por participante ou usuário externo com 403 (#31406)")
    void deveBloquearCancelamentoParaUsuariosNaoCriadores() throws Exception {
        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", token(participante))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Tentativa indevida\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Somente o criador do combinado pode cancelá-lo."));

        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isForbidden());

        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());
    }
}
