package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteRecusaComSemJustificativaTest extends GestaoFixture {

    @Test
    @DisplayName("Deve permitir recusar convite pendente sem justificativa informada no corpo da requisição")
    void deveRecusarConviteSemJustificativa() throws Exception {
        var conviteSemMotivo = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/recusar", conviteSemMotivo.getId())
                        .header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(conviteSemMotivo.getId().intValue()))
                .andExpect(jsonPath("$.status").value("RECUSADO"))
                .andExpect(jsonPath("$.dataResposta").isNotEmpty());

        em.flush();
        em.clear();

        var salvo = convites.findById(conviteSemMotivo.getId()).orElseThrow();
        assertEquals(StatusConvite.RECUSADO, salvo.getStatus());
        assertNotNull(salvo.getDataResposta());
    }

    @Test
    @DisplayName("Deve permitir recusar convite pendente quando uma justificativa opcional é enviada no payload")
    void deveRecusarConviteComJustificativaOpcional() throws Exception {
        Usuario convidadoExtra = usuario("Bruno", "bruno-recusa");
        combinado.adicionarParticipante(convidadoExtra);
        combinados.saveAndFlush(combinado);
        var conviteComMotivo = convite(convidadoExtra, StatusConvite.PENDENTE);

        mvc.perform(patch("/api/convites/{id}/recusar", conviteComMotivo.getId())
                        .header("Authorization", token(convidadoExtra))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"justificativa\":\"Não poderei comparecer nesta data\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(conviteComMotivo.getId().intValue()))
                .andExpect(jsonPath("$.status").value("RECUSADO"))
                .andExpect(jsonPath("$.dataResposta").isNotEmpty());

        em.flush();
        em.clear();

        var salvo = convites.findById(conviteComMotivo.getId()).orElseThrow();
        assertEquals(StatusConvite.RECUSADO, salvo.getStatus());
        assertNotNull(salvo.getDataResposta());
    }
}
