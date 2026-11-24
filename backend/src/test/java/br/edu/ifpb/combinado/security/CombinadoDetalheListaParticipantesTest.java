package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoDetalheListaParticipantesTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar lista completa de participantes e seus status no detalhe do combinado (#32299)")
    void deveRetornarListaDeParticipantesNoDetalheDoCombinado() throws Exception {
        convite(participante, StatusConvite.ACEITO);

        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantes", hasSize(1)))
                .andExpect(jsonPath("$.participantes[0].id").value(participante.getId().intValue()))
                .andExpect(jsonPath("$.participantes[0].nome").value(participante.getNome()))
                .andExpect(jsonPath("$.participantes[0].status").value("ACEITO"))
                .andExpect(jsonPath("$.participantes[0].senha").doesNotExist());
    }
}
