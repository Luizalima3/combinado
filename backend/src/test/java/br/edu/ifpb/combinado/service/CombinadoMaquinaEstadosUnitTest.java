package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinadoMaquinaEstadosUnitTest {

    @Test
    @DisplayName("Deve permitir todas as transições válidas da máquina de estados do Combinado (#31395)")
    void devePermitirTransicoesValidasDaMaquinaDeEstados() {
        assertTrue(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.AGUARDANDO_CONFIRMACAO));
        assertTrue(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.CANCELADO));
        assertTrue(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.ATIVO));
        assertTrue(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.CANCELADO));
        assertTrue(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertTrue(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.CANCELADO));

        assertDoesNotThrow(() -> StatusCombinado.RASCUNHO.validarTransicao(StatusCombinado.AGUARDANDO_CONFIRMACAO));
        assertDoesNotThrow(() -> StatusCombinado.AGUARDANDO_CONFIRMACAO.validarTransicao(StatusCombinado.ATIVO));
        assertDoesNotThrow(() -> StatusCombinado.ATIVO.validarTransicao(StatusCombinado.CONCLUIDO));
    }
}
