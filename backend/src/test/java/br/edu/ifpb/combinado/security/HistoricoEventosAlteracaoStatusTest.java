package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoEventosAlteracaoStatusTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve registrar eventos de ALTERACAO_STATUS ao ativar e ao concluir o combinado (#32347)")
    void deveRegistrarEventosDeAlteracaoDeStatus() throws Exception {
        var convitePendente = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/aceitar", convitePendente.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk());

        mvc.perform(patch("/api/combinados/{id}/concluir", combinado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk());

        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId());
        assertEquals(2, eventos.size());
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, eventos.get(0).getAcao());
        assertEquals("Status alterado de AGUARDANDO_CONFIRMACAO para ATIVO.", eventos.get(0).getDescricao());
        assertEquals(AcaoCombinadoHistorico.ALTERACAO_STATUS, eventos.get(1).getAcao());
        assertEquals("Status alterado de ATIVO para CONCLUIDO.", eventos.get(1).getDescricao());
    }
}
