package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConsultasGestaoTest extends GestaoFixture {
    @Test void listaDeveRetornarTituloEstadoPrazoENomeAtualDoCriador() throws Exception {
        mvc.perform(get("/api/combinados").header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$[0].status").value("AGUARDANDO_CONFIRMACAO"))
                .andExpect(jsonPath("$[0].prazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$[0].dataPrazo").value(combinado.getDataPrazo().toString()))
                .andExpect(jsonPath("$[0].nomeCriador").value("Criador"))
                .andExpect(jsonPath("$[0].criadorNome").value("Criador"))
                .andExpect(jsonPath("$[0].criador").doesNotExist());
        mvc.perform(get("/api/combinados").header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void conviteDeveMostrarCategoriaEConfirmadosSemDadosPrivados() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        convite(externo, StatusConvite.ACEITO);
        for (String acesso : new String[]{bearer, token(participante)}) {
            mvc.perform(get("/api/convites/{id}", convite.getId()).header("Authorization", acesso))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.categoria").value("OUTRO"))
                    .andExpect(jsonPath("$.titulo").value(combinado.getTitulo()))
                    .andExpect(jsonPath("$.destinatario.nome").value("Ana"))
                    .andExpect(jsonPath("$.participantesConfirmados", hasSize(1)))
                    .andExpect(jsonPath("$.participantesConfirmados[0].nome").value("Externo"))
                    .andExpect(jsonPath("$.remetente.senha").doesNotExist())
                    .andExpect(jsonPath("$.destinatario.perfil").doesNotExist());
        }
    }
    @Test void participanteNaoPodeConsultarConviteDeOutraPessoa() throws Exception {
        var convite = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", convite.getId()).header("Authorization", token(participante)))
                .andExpect(status().isForbidden());
        mvc.perform(get("/api/convites/{id}", convite.getId())).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/convites/999999").header("Authorization", bearer)).andExpect(status().isForbidden());
    }
    @Test void reenvioPendenteNaoDeveContarAceiteAnterior() throws Exception {
        var antigo = convite(participante, StatusConvite.ACEITO);
        antigo.setDataEnvio(antigo.getDataEnvio().minusDays(1));
        convites.saveAndFlush(antigo);
        var atual = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", atual.getId()).header("Authorization", bearer))
                .andExpect(status().isOk()).andExpect(jsonPath("$.participantesConfirmados", hasSize(0)));
    }
}
