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

class TimelineCamposObrigatoriosTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve retornar todos os campos obrigatórios (id, autorNome, descricao, acao, ocorridoEm) na timeline (#32351)")
    void deveRetornarCamposObrigatoriosNaTimeline() throws Exception {
        historicos.saveAndFlush(new CombinadoHistorico(
                combinado, criador, "Combinado criado.",
                AcaoCombinadoHistorico.CRIACAO, Instant.parse("2026-10-21T10:30:00Z")
        ));

        mvc.perform(get("/api/combinados/{id}/historico", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].autorNome").value(criador.getNome()))
                .andExpect(jsonPath("$[0].descricao").value("Combinado criado."))
                .andExpect(jsonPath("$[0].acao").value("CRIACAO"))
                .andExpect(jsonPath("$[0].ocorridoEm").value("2026-10-21T10:30:00Z"));
    }
}
