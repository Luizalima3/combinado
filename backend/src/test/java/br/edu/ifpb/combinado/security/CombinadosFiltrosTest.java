package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CombinadosFiltrosTest extends ConsultaListagemFixture {
    private static final LocalDateTime BASE = LocalDateTime.of(2026, 11, 1, 0, 0);
    @Test void deveCombinarTituloStatusECategoriaNasDuasListagens() throws Exception {
        var alvo = criarLista(criador, "Filtro Furadeira", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        alvo.setCategoria(CategoriaCombinado.EMPRESTIMO_OBJETO); combinados.saveAndFlush(alvo);
        criarLista(criador, "Filtro Outro", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        var cancelado = criarLista(criador, "Filtro Cancelado", StatusCombinado.CANCELADO, BASE, LocalDate.of(2026, 11, 30));
        cancelado.setCategoria(CategoriaCombinado.EMPRESTIMO_OBJETO); combinados.saveAndFlush(cancelado);
        for (String rota : new String[]{"/api/combinados", "/api/combinados/criados"}) {
            mvc.perform(get(rota).header("Authorization", bearer).param("titulo", "Filtro").param("status", "ATIVO")
                    .param("categoria", "EMPRESTIMO"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Filtro Furadeira")));
        }
    }
    @Test void inicioDeveUsarCriacaoEFimDeveUsarPrazoComLimitesInclusivos() throws Exception {
        criarLista(criador, "Periodo limite", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        criarLista(criador, "Periodo criação antiga", StatusCombinado.ATIVO, BASE.minusSeconds(1), LocalDate.of(2026, 11, 20));
        criarLista(criador, "Periodo prazo fora", StatusCombinado.ATIVO, BASE.plusDays(5), LocalDate.of(2026, 12, 1));
        criarLista(criador, "Periodo sem prazo", StatusCombinado.RASCUNHO, BASE.plusHours(1), null);
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", "Periodo")
                .param("dataInicio", "2026-11-01").param("dataFim", "2026-11-30"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Periodo limite")));
    }
    @Test void deveAceitarSomenteUmDosLimitesDoPeriodo() throws Exception {
        criarLista(criador, "Limites antigo", StatusCombinado.ATIVO, BASE.minusDays(1), LocalDate.of(2026, 11, 10));
        criarLista(criador, "Limites novo", StatusCombinado.ATIVO, BASE.plusDays(1), LocalDate.of(2026, 12, 10));
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("titulo", "Limites").param("dataInicio", "2026-11-01"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Limites novo")));
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("titulo", "Limites").param("dataFim", "2026-11-30"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Limites antigo")));
    }
    @Test void categoriaEPeriodoDevemFuncionarNosRecebidosAntesDaPaginacao() throws Exception {
        var alvo = criarLista(criador, "Recebido alvo", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        alvo.setCategoria(CategoriaCombinado.DIVISAO_DESPESA); combinados.saveAndFlush(alvo);
        var fora = criarLista(criador, "Recebido antigo", StatusCombinado.ATIVO, BASE.minusDays(1), LocalDate.of(2026, 11, 30));
        fora.setCategoria(CategoriaCombinado.DIVISAO_DESPESA); combinados.saveAndFlush(fora);
        // O convite antigo foi enviado depois, mas dataInicio filtra a criação do combinado.
        convidar(alvo, participante, StatusConvite.ACEITO, BASE.plusDays(10));
        convidar(fora, participante, StatusConvite.ACEITO, BASE.plusDays(20));
        mvc.perform(get("/api/convites/recebidos").header("Authorization", token(participante))
                .param("categoria", "DESPESA").param("status", "ACEITO")
                .param("dataInicio", "2026-11-01").param("dataFim", "2026-11-30").param("limite", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Recebido alvo")));
    }
    @Test void filtrosNaoPodemLiberarCombinadosDeTerceiros() throws Exception {
        criarLista(externo, "Terceiro privado", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("titulo", "Terceiro")
                .param("status", "ATIVO").param("categoria", "OUTRO")
                .param("dataInicio", "2026-11-01").param("dataFim", "2026-11-30"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void devePermitirFiltrarPropriosRascunhosPorStatus() throws Exception {
        criarLista(criador, "Rascunho filtro", StatusCombinado.RASCUNHO, BASE, null);
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("status", "RASCUNHO"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Rascunho filtro")));
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("status", "CONCLUIDO"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
    @Test void deveRejeitarCategoriaStatusDatasInvalidasOuIntervaloInvertido() throws Exception {
        for (String rota : new String[]{"/api/combinados", "/api/combinados/criados", "/api/convites/recebidos"}) {
            mvc.perform(get(rota).header("Authorization", bearer).param("categoria", "INEXISTENTE")).andExpect(status().isBadRequest());
            mvc.perform(get(rota).header("Authorization", bearer).param("dataInicio", "2026-02-30")).andExpect(status().isBadRequest());
            mvc.perform(get(rota).header("Authorization", bearer).param("dataInicio", "2026-12-01")
                    .param("dataFim", "2026-11-01")).andExpect(status().isBadRequest());
        }
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("status", "PENDENTE"))
                .andExpect(status().isBadRequest());
    }
}
