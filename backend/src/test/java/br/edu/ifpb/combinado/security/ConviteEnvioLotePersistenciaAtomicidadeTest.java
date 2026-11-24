package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteEnvioLotePersistenciaAtomicidadeTest extends GestaoFixture {

    @Test
    @DisplayName("Deve persistir convites em lote atomicamente e reverter todo o lote se um ID for inválido (#31081)")
    void deveTestarAtomicidadeEPersistenciaDoEnvioEmLote() throws Exception {
        mvc.perform(post("/api/combinados/{id}/convites/lote", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"participantesIds\":[" + participante.getId() + ",999999]}"))
                .andExpect(status().isBadRequest());

        assertTrue(convites.findByCombinadoId(combinado.getId()).isEmpty());

        mvc.perform(post("/api/combinados/{id}/convites/lote", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"participantesIds\":[" + participante.getId() + "," + externo.getId() + "],\"mensagem\":\"Convite em lote\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(2)));

        assertEquals(2, convites.findByCombinadoId(combinado.getId()).size());
    }
}
