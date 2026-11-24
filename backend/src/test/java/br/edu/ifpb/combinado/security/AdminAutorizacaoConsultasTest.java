package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminAutorizacaoConsultasTest extends GestaoFixture {

    @Test
    @DisplayName("Deve permitir ao perfil ADMIN consultar combinados, rascunhos, participantes e tarefas sem permitir mutação de terceiros (#30964)")
    void deveValidarConsultasEAutorizacaoComPerfilAdmin() throws Exception {
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN);
        usuarios.saveAndFlush(externo);
        String adminToken = token(externo);

        combinado.setStatus(StatusCombinado.RASCUNHO);
        combinados.saveAndFlush(combinado);

        mvc.perform(get("/api/combinados").header("Authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(get("/api/combinados/{id}", combinado.getId()).header("Authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(combinado.getId().intValue()));

        mvc.perform(get("/api/combinados/{id}/participantes", combinado.getId()).header("Authorization", adminToken))
                .andExpect(status().isOk());

        mvc.perform(get("/api/combinados/{id}/tarefas", combinado.getId()).header("Authorization", adminToken))
                .andExpect(status().isOk());

        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId())
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Tentativa Admin\"}"))
                .andExpect(status().isForbidden());

        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", adminToken))
                .andExpect(status().isForbidden());
    }
}
