package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoRegistroCancelamentoTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve registrar evento de cancelamento no histórico do combinado com autor, descrição, motivo e data (#32336)")
    void deveRegistrarCancelamentoNoHistorico() throws Exception {
        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Acordo renegociado\"}"))
                .andExpect(status().isOk());

        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertFalse(eventos.isEmpty());
        var ultimo = eventos.getLast();
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, ultimo.getAcao());
        assertEquals("Status alterado de AGUARDANDO_CONFIRMACAO para CANCELADO.", ultimo.getDescricao());
        assertEquals("Acordo renegociado", ultimo.getMotivo());
        assertEquals(criador.getId(), ultimo.getAutor().getId());
        assertNotNull(ultimo.getOcorridoEm());
    }
}
