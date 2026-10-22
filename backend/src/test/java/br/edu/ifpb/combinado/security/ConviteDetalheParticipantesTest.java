package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testa o endpoint GET /api/convites/{id}:
 *  - retorno de categoria no detalhe do convite
 *  - lista participantesConfirmados com 0, 1 e multiplos aceitos
 *  - validacao de que apenas convites com status ACEITO aparecem na lista
 */
@TestPropertySource(properties =
    "spring.datasource.url=jdbc:h2:mem:convite-detalhe-partic;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class ConviteDetalheParticipantesTest extends GestaoFixture {

    // Categoria
    @Test
    @DisplayName("Detalhe do convite deve retornar a categoria correta do combinado")
    void deveRetornarCategoriaCorretaNaRespostaDoConvite() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", convite.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoria").value(CategoriaCombinado.OUTRO.name()));
    }

    // 0 aceitos
    @Test
    @DisplayName("Sem nenhum aceito: participantesConfirmados deve ser lista vazia")
    void semAceitosParticipantesConfirmadosDeveSerVazio() throws Exception {
        var convite = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", convite.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(0)));
    }

    @Test
    @DisplayName("Convite RECUSADO nao deve aparecer em participantesConfirmados")
    void recusadoNaoDeveAparecerEmConfirmados() throws Exception {
        convite(participante, StatusConvite.RECUSADO);
        var consultado = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", consultado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(0)));
    }

    // 1 aceito
    @Test
    @DisplayName("Com exatamente um aceito: participantesConfirmados deve ter 1 entrada")
    void umAceito_deveRetornarUmParticipanteConfirmado() throws Exception {
        convite(participante, StatusConvite.ACEITO);
        var consultado = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", consultado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(1)))
                .andExpect(jsonPath("$.participantesConfirmados[0].nome").value("Ana"))
                .andExpect(jsonPath("$.participantesConfirmados[0].status").value("ACEITO"));
    }

    // multiplos aceitos
    @Test
    @DisplayName("Com multiplos aceitos: todos devem aparecer em participantesConfirmados")
    void multiplosAceitos_devemTodosAparecerNaLista() throws Exception {
        // Usa usuarios novos (nao pre-adicionados ao combinado pelo fixture)
        // para evitar que putIfAbsent ignore o convite ACEITO de quem ja esta
        // na lista de usuariosParticipantes.
        var aceitante1 = usuario("Aceita Um", "aceita-um");
        var aceitante2 = usuario("Aceita Dois", "aceita-dois");
        combinado.adicionarParticipante(aceitante1);
        combinado.adicionarParticipante(aceitante2);
        combinados.saveAndFlush(combinado);
        convite(aceitante1, StatusConvite.ACEITO);
        convite(aceitante2, StatusConvite.ACEITO);
        var consultado = convite(participante, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", consultado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(2)))
                .andExpect(jsonPath("$.participantesConfirmados[*].status",
                        everyItem(equalTo("ACEITO"))));
    }

    // somente ACEITOS aparecem
    @Test
    @DisplayName("Apenas convites com status ACEITO devem aparecer em participantesConfirmados")
    void somenteStatusAceitoDeveAparecerNaLista() throws Exception {
        var conviteParticipante = convite(participante, StatusConvite.PENDENTE);
        convite(externo, StatusConvite.RECUSADO);
        var aceitante = usuario("Aceitante", "aceitante");
        combinado.adicionarParticipante(aceitante);
        combinados.saveAndFlush(combinado);
        convite(aceitante, StatusConvite.ACEITO);
        mvc.perform(get("/api/convites/{id}", conviteParticipante.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados", hasSize(1)))
                .andExpect(jsonPath("$.participantesConfirmados[0].nome").value("Aceitante"))
                .andExpect(jsonPath("$.participantesConfirmados[0].status").value("ACEITO"));
    }

    // dados privados
    @Test
    @DisplayName("Senha e perfil nao devem aparecer nos participantesConfirmados")
    void dadosPrivadosNaoDevemVazarNosConfirmados() throws Exception {
        convite(participante, StatusConvite.ACEITO);
        var consultado = convite(externo, StatusConvite.PENDENTE);
        mvc.perform(get("/api/convites/{id}", consultado.getId())
                        .header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantesConfirmados[0].senha").doesNotExist())
                .andExpect(jsonPath("$.participantesConfirmados[0].perfil").doesNotExist());
    }
}