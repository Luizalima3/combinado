package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoRegistroConclusaoTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve registrar evento de conclusão no histórico do combinado com autor e data/hora (#32328)")
    void deveRegistrarConclusaoNoHistorico() throws Exception {
        combinado.setStatus(StatusCombinado.ATIVO);
        combinados.saveAndFlush(combinado);
        convite(participante, StatusConvite.ACEITO);

        mvc.perform(patch("/api/combinados/{id}/concluir", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONCLUIDO"));

        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertFalse(eventos.isEmpty());
        var ultimo = eventos.getLast();
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, ultimo.getAcao());
        assertEquals("Status alterado de ATIVO para CONCLUIDO.", ultimo.getDescricao());
        assertEquals(criador.getId(), ultimo.getAutor().getId());
        assertNotNull(ultimo.getOcorridoEm());
    }
}
