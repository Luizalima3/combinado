package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoListagemCriadosTest extends GestaoFixture {

    @Test
    @DisplayName("Deve listar combinados criados pelo usuário autenticado sem incluir combinados de terceiros")
    void deveListarCombinadosCriadosPeloUsuario() throws Exception {
        mvc.perform(get("/api/combinados").header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(combinado.getId().intValue()))
                .andExpect(jsonPath("$[0].titulo").value(combinado.getTitulo()));

        mvc.perform(get("/api/combinados").header("Authorization", token(externo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
