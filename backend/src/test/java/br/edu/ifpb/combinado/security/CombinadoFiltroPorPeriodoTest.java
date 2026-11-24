package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoFiltroPorPeriodoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve filtrar combinados por período dataInicio e dataFim e rejeitar intervalo invertido (#32317)")
    void deveFiltrarCombinadosPorPeriodo() throws Exception {
        LocalDate hoje = LocalDate.now();
        LocalDate prazo = combinado.getDataPrazo();

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("dataInicio", hoje.minusDays(1).toString())
                        .param("dataFim", prazo.plusDays(1).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("dataFim", prazo.minusDays(2).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("dataInicio", prazo.plusDays(5).toString())
                        .param("dataFim", hoje.toString()))
                .andExpect(status().isBadRequest());
    }
}
