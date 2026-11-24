package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConviteRecebidoFiltroPorStatusTest extends GestaoFixture {

    @Test
    @DisplayName("Deve filtrar convites recebidos por cada status (PENDENTE, ACEITO, RECUSADO) (#32296)")
    void deveFiltrarConvitesRecebidosPorCadaStatus() throws Exception {
        var cRecusado = convite(participante, StatusConvite.RECUSADO);
        var cAceito = convite(participante, StatusConvite.ACEITO);
        var cPendente = convite(participante, StatusConvite.PENDENTE);

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("status", "PENDENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(cPendente.getId().intValue()));

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("status", "ACEITO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(cAceito.getId().intValue()));

        mvc.perform(get("/api/convites/recebidos")
                        .header("Authorization", token(participante))
                        .param("status", "RECUSADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(cRecusado.getId().intValue()));
    }
}
