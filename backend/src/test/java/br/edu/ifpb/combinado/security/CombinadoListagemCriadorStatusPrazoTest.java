package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoListagemCriadorStatusPrazoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar criador, status e prazo para o participante convidado na listagem")
    void deveRetornarCriadorStatusEPrazoParaParticipante() throws Exception {
        mvc.perform(get("/api/combinados").header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCriador").value("Criador"))
                .andExpect(jsonPath("$[0].criadorNome").value("Criador"))
                .andExpect(jsonPath("$[0].status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$[0].prazo").value(combinado.getDataPrazo().toString()));
    }
}
