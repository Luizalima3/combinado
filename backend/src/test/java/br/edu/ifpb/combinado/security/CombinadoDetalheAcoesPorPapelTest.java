package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoDetalheAcoesPorPapelTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar acoesPermitidas diferenciadas por papel de criador e participante no detalhe (#32305)")
    void deveRetornarAcoesPermitidasPorPapel() throws Exception {
        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acoesPermitidas", hasItems(
                        "EDITAR", "ADICIONAR_TAREFAS", "CANCELAR", "VER_HISTORICO"
                )));

        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acoesPermitidas", contains("VER_HISTORICO")));
    }
}
