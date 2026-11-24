package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoCancelamentoComSemMotivoTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicos;

    @Test
    @DisplayName("Deve permitir cancelar combinado tanto com motivo informado quanto sem motivo (motivo nulo) (#32338)")
    void deveCancelarCombinadoComESemMotivo() throws Exception {
        mvc.perform(patch("/api/combinados/{id}/cancelar", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Motivo explícito\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADO"));

        var eventoComMotivo = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).getLast();
        assertEquals("Motivo explícito", eventoComMotivo.getMotivo());

        Combinado outro = new Combinado();
        outro.setTitulo("Combinado sem motivo de cancelamento");
        outro.setCategoria(CategoriaCombinado.OUTRO);
        outro.setCriador(criador);
        outro.setCriadorNome(criador.getNome());
        outro.setParticipanteNome(participante.getNome());
        outro.adicionarParticipante(participante);
        outro.setDataPrazo(LocalDate.now().plusDays(10));
        outro.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        combinados.saveAndFlush(outro);

        mvc.perform(patch("/api/combinados/{id}/cancelar", outro.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADO"));

        var eventoSemMotivo = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(outro.getId()).getLast();
        assertNull(eventoSemMotivo.getMotivo());
    }
}
