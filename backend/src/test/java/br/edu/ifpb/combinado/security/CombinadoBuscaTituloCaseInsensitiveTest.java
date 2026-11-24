package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoBuscaTituloCaseInsensitiveTest extends GestaoFixture {

    @Test
    @DisplayName("Deve buscar combinados por parte do título de forma case-insensitive (#32290)")
    void deveBuscarPorParteDoTituloCaseInsensitive() throws Exception {
        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("titulo", "cOmBiNaDo De TeStE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(combinado.getId().intValue()));

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("titulo", "TESTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("titulo", "inexistente-xyz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
