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

class HistoricoFiltroIntervaloDatasTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve filtrar eventos do histórico por intervalo de datas dataInicio e dataFim (#32363)")
    void deveFiltrarHistoricoPorIntervaloDeDatas() throws Exception {
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, criador, "Evento outubro",
                AcaoCombinadoHistorico.CRIACAO, Instant.parse("2026-10-10T10:00:00Z")
        ));
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, participante, "Evento novembro",
                AcaoCombinadoHistorico.ALTERACAO_STATUS, Instant.parse("2026-11-15T12:00:00Z")
        ));

        mvc.perform(get("/api/combinados/{id}/historico", combinado.getId())
                        .header("Authorization", bearer)
                        .param("dataInicio", "2026-11-01")
                        .param("dataFim", "2026-11-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].descricao").value("Evento novembro"));
    }
}
