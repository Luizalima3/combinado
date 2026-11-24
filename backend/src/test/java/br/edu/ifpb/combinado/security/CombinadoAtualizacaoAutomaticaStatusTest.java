package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoAtualizacaoAutomaticaStatusTest extends GestaoFixture {

    @Test
    @DisplayName("Deve manter AGUARDANDO_CONFIRMACAO enquanto houver pendente e atualizar automaticamente para ATIVO no último aceite (#32278)")
    void deveAtualizarAutomaticamenteStatusAposUltimoAceite() throws Exception {
        Usuario segundoParticipante = usuario("Beatriz", "beatriz-auto");
        combinado.adicionarParticipante(segundoParticipante);
        combinados.saveAndFlush(combinado);

        var convite1 = convite(participante, StatusConvite.PENDENTE);
        var convite2 = convite(segundoParticipante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/aceitar", convite1.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk());

        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());

        mvc.perform(patch("/api/convites/{id}/aceitar", convite2.getId())
                        .header("Authorization", token(segundoParticipante)))
                .andExpect(status().isOk());

        assertEquals(StatusCombinado.ATIVO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());
    }
}
