package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoDetalhesPermissaoAcessoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar todos os detalhes do combinado para envolvidos e negar acesso a não vinculados")
    void deveRetornarDetalhesParaEnvolvidosENegarNaoVinculados() throws Exception {
        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(combinado.getId().intValue()))
                .andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$.categoria").value("OUTRO"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$.prazo").value(combinado.getDataPrazo().toString()));

        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isForbidden());
    }
}
