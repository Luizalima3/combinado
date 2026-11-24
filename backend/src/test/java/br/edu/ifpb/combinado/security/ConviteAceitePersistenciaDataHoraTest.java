package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteAceitePersistenciaDataHoraTest extends GestaoFixture {

    @Test
    @DisplayName("Deve persistir data e hora exatas no momento do aceite do convite e do combinado")
    void devePersistirDataHoraNoAceite() throws Exception {
        var convitePendente = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/aceitar", convitePendente.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACEITO"))
                .andExpect(jsonPath("$.dataResposta").isNotEmpty());

        em.flush();
        em.clear();

        var conviteSalvo = convites.findById(convitePendente.getId()).orElseThrow();
        var combinadoSalvo = combinados.findById(combinado.getId()).orElseThrow();

        assertEquals(StatusConvite.ACEITO, conviteSalvo.getStatus());
        assertNotNull(conviteSalvo.getDataResposta());
        assertNotNull(combinadoSalvo.getAceitoEm());
    }
}
