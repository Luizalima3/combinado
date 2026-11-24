package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoEventosAceiteRecusaTest extends GestaoFixture {

    @Autowired
    private ConviteHistoricoRepository conviteHistoricoRepository;

    @Test
    @DisplayName("Deve registrar eventos de ACEITE e RECUSA no histórico de convites com autor e data (#32345)")
    void deveRegistrarEventosDeAceiteERecusaNoHistorico() throws Exception {
        var conviteRecusa = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/recusar", conviteRecusa.getId())
                        .header("Authorization", token(externo)))
                .andExpect(status().isOk());

        var eventosRecusa = conviteHistoricoRepository.findByConviteIdOrderByOcorridoEmAscIdAsc(conviteRecusa.getId());
        assertEquals(1, eventosRecusa.size());
        assertEquals(AcaoConvite.RECUSA, eventosRecusa.getFirst().getAcao());
        assertEquals(externo.getId(), eventosRecusa.getFirst().getAutor().getId());
        assertNotNull(eventosRecusa.getFirst().getOcorridoEm());

        var conviteAceite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(patch("/api/convites/{id}/aceitar", conviteAceite.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk());

        var eventosAceite = conviteHistoricoRepository.findByConviteIdOrderByOcorridoEmAscIdAsc(conviteAceite.getId());
        assertEquals(1, eventosAceite.size());
        assertEquals(AcaoConvite.ACEITE, eventosAceite.getFirst().getAcao());
        assertEquals(participante.getId(), eventosAceite.getFirst().getAutor().getId());
        assertNotNull(eventosAceite.getFirst().getOcorridoEm());
    }
}
