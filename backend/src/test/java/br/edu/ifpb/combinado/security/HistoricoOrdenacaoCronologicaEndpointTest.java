package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoOrdenacaoCronologicaEndpointTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve retornar eventos do endpoint de histórico em ordem cronológica crescente (#32349)")
    void deveRetornarHistoricoEmOrdemCronologicaCrescente() throws Exception {
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, participante, "Evento posterior",
                AcaoCombinadoHistorico.ALTERACAO_STATUS, Instant.parse("2026-10-25T15:00:00Z")
        ));
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, criador, "Evento inicial",
                AcaoCombinadoHistorico.CRIACAO, Instant.parse("2026-10-20T10:00:00Z")
        ));
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, criador, "Evento intermediário",
                AcaoCombinadoHistorico.ALTERACAO_STATUS, Instant.parse("2026-10-22T12:00:00Z")
        ));

        mvc.perform(get("/api/combinados/{id}/historico", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].descricao").value("Evento inicial"))
                .andExpect(jsonPath("$[1].descricao").value("Evento intermediário"))
                .andExpect(jsonPath("$[2].descricao").value("Evento posterior"));
    }
}
