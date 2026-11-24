package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConvitesBuscaOrdenacaoTest extends ConsultaListagemFixture {
    private static final LocalDateTime BASE = LocalDateTime.of(2026, 11, 1, 12, 0);
    @Test void ordemDeveUsarDataDoEnvioEPermitirAscendente() throws Exception {
        var recente = criarLista(criador, "Lista recente", StatusCombinado.ATIVO, BASE, null);
        var antigo = criarLista(criador, "Lista antiga", StatusCombinado.ATIVO, BASE.plusDays(5), null);
        convidar(recente, participante, StatusConvite.ACEITO, BASE.plusDays(2));
        convidar(antigo, participante, StatusConvite.PENDENTE, BASE);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante)).param("sort", "criadoEm,desc"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Lista recente", "Lista antiga")));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante)).param("sort", "dataEnvio,asc"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Lista antiga", "Lista recente")));
    }
    @Test void deveFiltrarStatusAntesDePaginar() throws Exception {
        convidar(combinado, participante, StatusConvite.PENDENTE, BASE.plusDays(3));
        var primeiro = convidar(combinado, participante, StatusConvite.ACEITO, BASE.plusDays(2));
        var segundo = convidar(combinado, participante, StatusConvite.ACEITO, BASE);
        convidar(combinado, participante, StatusConvite.RECUSADO, BASE.minusDays(1));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("status", "ACEITO").param("limite", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(primeiro.getId().intValue()));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("status", "ACEITO").param("limite", "1").param("pagina", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(segundo.getId().intValue()));
    }
    @Test void buscaTituloDeveFuncionarNosRecebidosENosCriadosSemCuringas() throws Exception {
        var alvo = criarLista(criador, "Acerto 100%_ FURADEIRA", StatusCombinado.ATIVO, BASE, null);
        var diferente = criarLista(criador, "Acerto 100 por cento", StatusCombinado.ATIVO, BASE, null);
        convidar(alvo, participante, StatusConvite.PENDENTE, BASE);
        convidar(diferente, participante, StatusConvite.PENDENTE, BASE);
        for (String rota : new String[]{"/api/combinados/criados", "/api/convites/recebidos"}) {
            mvc.perform(get(rota).header("Authorization", rota.contains("convites") ? token(participante) : bearer)
                    .param("titulo", " 100%_ fura "))
                    .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Acerto 100%_ FURADEIRA")));
        }
    }
    @Test void filtroNaoPodeExporConvitesAlheiosOuRascunhosNemParaAdmin() throws Exception {
        convidar(combinado, externo, StatusConvite.PENDENTE, BASE);
        var privado = criarLista(criador, "Privado", StatusCombinado.RASCUNHO, BASE, null);
        convidar(privado, participante, StatusConvite.PENDENTE, BASE);
        participante.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(participante);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("status", "PENDENTE").param("usuarioId", externo.getId().toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void buscaSemCorrespondenciaDeveRetornarArrayVazio() throws Exception {
        convidar(combinado, participante, StatusConvite.PENDENTE, BASE);
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante)).param("titulo", "zzzz"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void deveRejeitarStatusEOrdenacaoInvalidos() throws Exception {
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer).param("status", "ATIVO"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer).param("sort", "destinatario.senha,asc"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/convites/recebidos").header("Authorization", bearer).param("sort", "criadoEm,xx"))
                .andExpect(status().isBadRequest());
    }
}
