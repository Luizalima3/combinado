package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoGravacaoObrigatoriaAutorDataTest extends GestaoFixture {

    @Test
    @DisplayName("Deve exigir obrigatoriamente autor e data/hora (timestamp) ao criar evento de histórico (#32343)")
    void deveExigirGravacaoObrigatoriaDeAutorEDataNoHistorico() {
        assertThrows(IllegalArgumentException.class,
                () -> new CombinadoHistorico(combinado, null, "Sem autor", AcaoCombinadoHistorico.CRIACAO, Instant.now()));

        assertThrows(IllegalArgumentException.class,
                () -> new CombinadoHistorico(combinado, criador, "Sem data", AcaoCombinadoHistorico.CRIACAO, null));

        var valido = CombinadoHistorico.de(combinado, criador, "Evento válido", AcaoCombinadoHistorico.CRIACAO);
        assertEquals(criador.getId(), valido.getAutor().getId());
        assertEquals(criador.getNome(), valido.getAutorNome());
        assertNotNull(valido.getOcorridoEm());
    }
}
