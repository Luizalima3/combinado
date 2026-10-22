package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoBloqueioAcessoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve retornar 403 Forbidden quando usuário alheio tenta acessar detalhes ou participantes do combinado")
    void deveRetornar403ParaUsuarioAlheioAoCombinado() throws Exception {
        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isForbidden());

        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Deve retornar 200 OK quando criador ou participante vinculado acessa detalhes do combinado")
    void deveRetornar200ParaCriadorEParticipanteVinculado() throws Exception {
        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(combinado.getId().intValue()));

        mvc.perform(get("/api/combinados/{id}", combinado.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(combinado.getId().intValue()));
    }
}
