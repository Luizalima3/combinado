package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoTransicaoAguardandoAtivoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve transicionar combinado de AGUARDANDO_CONFIRMACAO para ATIVO após aceite de todos os participantes (#32269)")
    void deveTransicionarDeAguardandoParaAtivo() throws Exception {
        var convitePendente = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/aceitar", convitePendente.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACEITO"));

        em.flush();
        em.clear();

        var atualizado = combinados.findById(combinado.getId()).orElseThrow();
        assertEquals(StatusCombinado.ATIVO, atualizado.getStatus());
    }
}
