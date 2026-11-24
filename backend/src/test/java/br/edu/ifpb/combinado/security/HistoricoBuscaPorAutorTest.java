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

class HistoricoBuscaPorAutorTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve filtrar eventos do histórico por autorId (#32365)")
    void deveFiltrarHistoricoPorAutor() throws Exception {
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, criador, "Ação do criador",
                AcaoCombinadoHistorico.CRIACAO, Instant.parse("2026-10-20T10:00:00Z")
        ));
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, participante, "Ação da participante",
                AcaoCombinadoHistorico.ALTERACAO_STATUS, Instant.parse("2026-10-21T11:00:00Z")
        ));

        mvc.perform(get("/api/combinados/{id}/historico", combinado.getId())
                        .header("Authorization", bearer)
                        .param("autorId", participante.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].autorNome").value(participante.getNome()))
                .andExpect(jsonPath("$[0].descricao").value("Ação da participante"));
    }
}
