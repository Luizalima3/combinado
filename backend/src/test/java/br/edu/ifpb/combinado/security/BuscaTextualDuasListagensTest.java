package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuscaTextualDuasListagensTest extends GestaoFixture {

    @Test
    @DisplayName("Deve filtrar por busca textual tanto na listagem de combinados criados quanto na de convites recebidos (#32308)")
    void deveExecutarBuscaTextualNasDuasListagens() throws Exception {
        convite(participante, StatusConvite.PENDENTE);

        mvc.perform(get("/api/combinados/criados")
                        .header("Authorization", bearer)
                        .param("titulo", "Combinado de teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(get("/api/combinados/criados")
                        .header("Authorization", bearer)
                        .param("titulo", "NaoExiste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("titulo", "Combinado de teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("titulo", "NaoExiste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
