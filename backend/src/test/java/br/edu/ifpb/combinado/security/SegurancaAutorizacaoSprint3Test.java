package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SegurancaAutorizacaoSprint3Test extends GestaoFixture {
    @Test void falhasDeAutenticacaoEAutorizacaoDevemTerJsonSemDetalhesInternos() throws Exception {
        mvc.perform(get("/api/combinados")).andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(header().string("WWW-Authenticate", "Bearer"))
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.stackTrace").doesNotExist());
        mvc.perform(get("/api/combinados/{id}", combinado.getId()).header("Authorization", token(externo)))
                .andExpect(status().isForbidden()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Acesso negado."))
                .andExpect(jsonPath("$.stackTrace").doesNotExist());
    }
}
