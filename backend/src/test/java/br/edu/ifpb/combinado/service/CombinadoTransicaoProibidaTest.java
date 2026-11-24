package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinadoTransicaoProibidaTest {

    @Test
    @DisplayName("Deve bloquear todas as transições de status proibidas no backend (#31399)")
    void deveBloquearTransicoesProibidas() {
        assertFalse(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.ATIVO));
        assertFalse(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertFalse(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertFalse(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.RASCUNHO));
        assertFalse(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.RASCUNHO));
        assertFalse(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.AGUARDANDO_CONFIRMACAO));

        for (StatusCombinado destino : StatusCombinado.values()) {
            assertFalse(StatusCombinado.CONCLUIDO.podeTransicionarPara(destino));
            assertFalse(StatusCombinado.CANCELADO.podeTransicionarPara(destino));
            assertThrows(IllegalArgumentException.class, () -> StatusCombinado.CONCLUIDO.validarTransicao(destino));
            assertThrows(IllegalArgumentException.class, () -> StatusCombinado.CANCELADO.validarTransicao(destino));
        }
    }
}
