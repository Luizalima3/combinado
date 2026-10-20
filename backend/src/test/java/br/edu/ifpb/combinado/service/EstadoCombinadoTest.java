package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class EstadoCombinadoTest {
    @Test void deveValidarTodasAsCombinacoesDeEstado() {
        Set<String> permitidas = Set.of("RASCUNHO:AGUARDANDO_CONFIRMACAO", "RASCUNHO:CANCELADO",
                "AGUARDANDO_CONFIRMACAO:ATIVO", "AGUARDANDO_CONFIRMACAO:CANCELADO",
                "ATIVO:CONCLUIDO", "ATIVO:CANCELADO");
        for (var origem : StatusCombinado.values()) {
            for (var destino : StatusCombinado.values()) {
                if (permitidas.contains(origem + ":" + destino)) {
                    assertDoesNotThrow(() -> origem.validarTransicao(destino));
                } else {
                    assertThrows(IllegalArgumentException.class, () -> origem.validarTransicao(destino));
                }
            }
            assertThrows(IllegalArgumentException.class, () -> origem.validarTransicao(null));
        }
    }
}
