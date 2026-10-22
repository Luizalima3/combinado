package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoListagemCamposObrigatoriosTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar título, status e prazo como campos obrigatórios nos itens da listagem")
    void deveRetornarCamposObrigatoriosNaListagem() throws Exception {
        mvc.perform(get("/api/combinados").header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$[0].status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$[0].prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$[0].dataPrazo").value(combinado.getDataPrazo().toString()));
    }
}
