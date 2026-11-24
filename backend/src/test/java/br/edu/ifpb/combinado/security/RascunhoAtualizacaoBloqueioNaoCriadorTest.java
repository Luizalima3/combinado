package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RascunhoAtualizacaoBloqueioNaoCriadorTest extends GestaoFixture {

    @Test
    @DisplayName("Deve permitir atualização e exclusão de rascunho pelo criador e bloquear não criadores com 403 (#31068, #34295)")
    void deveValidarAtualizacaoEExclusaoDeRascunhoEBloquearNaoCriadores() throws Exception {
        combinado.setStatus(StatusCombinado.RASCUNHO);
        combinados.saveAndFlush(combinado);

        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Rascunho Atualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Rascunho Atualizado"))
                .andExpect(jsonPath("$.status").value("RASCUNHO"));

        mvc.perform(put("/api/combinados/rascunhos/{id}", combinado.getId())
                        .header("Authorization", token(participante))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Tentativa Participante\"}"))
                .andExpect(status().isForbidden());

        mvc.perform(delete("/api/combinados/rascunhos/{id}", combinado.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isForbidden());

        mvc.perform(delete("/api/combinados/rascunhos/{id}", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isNoContent());
    }
}
