package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteEndpointsAceiteRecusaTest extends GestaoFixture {

    @Test
    @DisplayName("Deve permitir aceite e recusa em convites pendentes pelo destinatário e retornar erro em repetição")
    void deveValidarSucessoEErroNosEndpointsDeAceiteERecusa() throws Exception {
        var conviteParaAceitar = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/aceitar", conviteParaAceitar.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACEITO"));

        mvc.perform(patch("/api/convites/{id}/aceitar", conviteParaAceitar.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isConflict());

        var conviteParaRecusar = convite(externo, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/recusar", conviteParaRecusar.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RECUSADO"));

        mvc.perform(patch("/api/convites/{id}/recusar", conviteParaRecusar.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isConflict());
    }
}
