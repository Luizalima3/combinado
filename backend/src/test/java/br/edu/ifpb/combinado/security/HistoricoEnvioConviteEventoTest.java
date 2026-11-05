package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoEnvioConviteEventoTest extends GestaoFixture {

    @Autowired
    private ConviteHistoricoRepository conviteHistoricoRepository;

    @Test
    @DisplayName("Deve registrar evento de ENVIO no histórico de convites com autor e data ao enviar convite")
    void deveRegistrarEventoDeEnvioDeConviteNoHistorico() throws Exception {
        String resposta = mvc.perform(post("/api/combinados/{id}/convites", combinado.getId())
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"destinatarioId\":" + participante.getId() + ",\"mensagem\":\"Convite de teste\"}"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long conviteId = ((Number) JsonPath.read(resposta, "$.id")).longValue();
        var eventos = conviteHistoricoRepository.findByConviteIdOrderByOcorridoEmAscIdAsc(conviteId);

        assertEquals(1, eventos.size());
        assertEquals(AcaoConvite.ENVIO, eventos.getFirst().getAcao());
        assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
        assertEquals(criador.getNome(), eventos.getFirst().getAutorNome());
        assertNotNull(eventos.getFirst().getOcorridoEm());
    }
}
