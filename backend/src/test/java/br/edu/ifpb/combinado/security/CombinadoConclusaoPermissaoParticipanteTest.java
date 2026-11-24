package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoConclusaoPermissaoParticipanteTest extends GestaoFixture {

    @Test
    @DisplayName("Deve validar permissão de conclusão entre participante, usuário externo e criador (#32341)")
    void deveValidarPermissaoDeConclusaoPorParticipanteECriador() throws Exception {
        combinado.setStatus(StatusCombinado.ATIVO);
        combinados.saveAndFlush(combinado);
        convite(participante, StatusConvite.ACEITO);

        mvc.perform(patch("/api/combinados/{id}/concluir", combinado.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isForbidden());

        mvc.perform(patch("/api/combinados/{id}/concluir", combinado.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Somente o criador do combinado pode concluí-lo."));

        mvc.perform(patch("/api/combinados/{id}/concluir", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONCLUIDO"));
    }
}
