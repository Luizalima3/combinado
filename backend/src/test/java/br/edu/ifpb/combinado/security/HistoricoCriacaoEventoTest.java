package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricoCriacaoEventoTest extends GestaoFixture {

    @Autowired
    private CombinadoHistoricoRepository historicoRepository;

    @Test
    @DisplayName("Deve registrar evento de CRIACAO no histórico ao criar novo combinado")
    void deveRegistrarEventoDeCriacaoNoHistorico() throws Exception {
        String payload = """
                {
                  "titulo": "Empréstimo de câmera",
                  "categoria": "EMPRESTIMO_OBJETO",
                  "dataPrazo": "%s",
                  "participantesIds": [%d],
                  "status": "AGUARDANDO_CONFIRMACAO"
                }
                """.formatted(LocalDate.now().plusDays(5), participante.getId());

        String resposta = mvc.perform(post("/api/combinados")
                        .header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long combinadoId = ((Number) JsonPath.read(resposta, "$.id")).longValue();
        var eventos = historicoRepository.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinadoId);

        assertEquals(1, eventos.size());
        assertEquals(AcaoCombinadoHistorico.CRIACAO, eventos.getFirst().getAcao());
        assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
        assertEquals("Combinado criado.", eventos.getFirst().getDescricao());
        assertNotNull(eventos.getFirst().getOcorridoEm());
    }
}
