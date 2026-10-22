package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ParticipanteStatusListagemTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar corretamente o status Pendente, Aceito e Recusado por participante")
    void deveRetornarStatusIndividualPorParticipante() throws Exception {
        combinado.adicionarParticipante(externo);
        combinados.saveAndFlush(combinado);

        convite(participante, StatusConvite.ACEITO);
        convite(externo, StatusConvite.RECUSADO);

        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Ana"))
                .andExpect(jsonPath("$[0].status").value("ACEITO"))
                .andExpect(jsonPath("$[1].nome").value("Externo"))
                .andExpect(jsonPath("$[1].status").value("RECUSADO"));
    }
}
