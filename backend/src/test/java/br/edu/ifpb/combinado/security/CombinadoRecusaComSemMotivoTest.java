package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoRecusaComSemMotivoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve permitir recusar convite tanto com motivo informado quanto sem motivo (#32275)")
    void devePermitirRecusaComESemMotivo() throws Exception {
        var conviteSemMotivo = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/recusar", conviteSemMotivo.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RECUSADO"));

        Usuario convidado2 = usuario("Carlos", "carlos-motivo");
        combinado.adicionarParticipante(convidado2);
        combinados.saveAndFlush(combinado);
        var conviteComMotivo = convite(convidado2, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/recusar", conviteComMotivo.getId())
                        .header("Authorization", token(convidado2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Indisponibilidade de horário\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RECUSADO"));

        assertEquals(StatusConvite.RECUSADO, convites.findById(conviteSemMotivo.getId()).orElseThrow().getStatus());
        assertEquals(StatusConvite.RECUSADO, convites.findById(conviteComMotivo.getId()).orElseThrow().getStatus());
    }
}
