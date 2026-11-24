package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteOrdenacaoPorDataEnvioTest extends GestaoFixture {

    @Test
    @DisplayName("Deve ordenar convites recebidos por data do convite em ordem crescente e decrescente (#32293)")
    void deveOrdenarConvitesPorDataDeEnvio() throws Exception {
        var maisAntigo = convite(participante, StatusConvite.RECUSADO);
        maisAntigo.setDataEnvio(maisAntigo.getDataEnvio().minusDays(2));
        convites.saveAndFlush(maisAntigo);

        var maisRecente = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("sort", "dataEnvio,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(maisRecente.getId().intValue()))
                .andExpect(jsonPath("$[1].id").value(maisAntigo.getId().intValue()));

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("sort", "dataEnvio,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(maisAntigo.getId().intValue()))
                .andExpect(jsonPath("$[1].id").value(maisRecente.getId().intValue()));
    }
}
