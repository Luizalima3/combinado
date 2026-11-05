package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinadoMaquinaEstadosTransicoesTest {

    @Test
    @DisplayName("Deve permitir todas as transições válidas do fluxo RASCUNHO -> AGUARDANDO_CONFIRMACAO -> ATIVO -> CONCLUIDO e CANCELADO")
    void devePermitirTransicoesValidas() {
        assertTrue(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.AGUARDANDO_CONFIRMACAO));
        assertTrue(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.CANCELADO));
        assertTrue(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.ATIVO));
        assertTrue(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.CANCELADO));
        assertTrue(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertTrue(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.CANCELADO));
    }

    @Test
    @DisplayName("Deve bloquear transições inválidas, retrocessos e saídas de estados terminais CONCLUIDO e CANCELADO")
    void deveBloquearTransicoesInvalidasEEstadosTerminais() {
        assertFalse(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.ATIVO));
        assertFalse(StatusCombinado.RASCUNHO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertFalse(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.CONCLUIDO));
        assertFalse(StatusCombinado.AGUARDANDO_CONFIRMACAO.podeTransicionarPara(StatusCombinado.RASCUNHO));
        assertFalse(StatusCombinado.ATIVO.podeTransicionarPara(StatusCombinado.RASCUNHO));

        for (StatusCombinado destino : StatusCombinado.values()) {
            assertFalse(StatusCombinado.CONCLUIDO.podeTransicionarPara(destino));
            assertFalse(StatusCombinado.CANCELADO.podeTransicionarPara(destino));
        }
    }
}
