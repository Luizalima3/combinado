package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.*;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CombinadosBuscaOrdenacaoTest extends ConsultaListagemFixture {
    private static final LocalDateTime BASE = LocalDateTime.of(2026, 11, 1, 12, 0);
    @Test void buscaDeveSerParcialSemDiferenciarMaiusculasESomenteDoCriador() throws Exception {
        criarLista(criador, "Entrega da Furadeira", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        criarLista(externo, "Furadeira privada", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", " FURADEIRA "))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Entrega da Furadeira")));
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("titulo", "furadeira"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Entrega da Furadeira")));
    }
    @Test void buscaDeveEscaparCuringasDeLike() throws Exception {
        criarLista(criador, "Item 100%_ literal!", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        criarLista(criador, "Item 100 porcento", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 30));
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", "100%_"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Item 100%_ literal!")));
    }
    @Test void ordemPadraoDeveUsarDataDeCriacaoEDesempatarPorId() throws Exception {
        criarLista(criador, "Lista recente A", StatusCombinado.ATIVO, BASE.plusDays(2), null);
        criarLista(criador, "Lista recente B", StatusCombinado.ATIVO, BASE.plusDays(2), null);
        criarLista(criador, "Lista antiga com maior ID", StatusCombinado.ATIVO, BASE, null);
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", "Lista"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo",
                        contains("Lista recente B", "Lista recente A", "Lista antiga com maior ID")));
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", "Lista").param("sort", "criadoEm,asc"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].titulo").value("Lista antiga com maior ID"));
    }
    @Test void deveOrdenarPorPrazoEStatusComMultiplosCriterios() throws Exception {
        criarLista(criador, "Ordenada ativa", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 10));
        criarLista(criador, "Ordenada concluida", StatusCombinado.CONCLUIDO, BASE, LocalDate.of(2026, 11, 10));
        criarLista(criador, "Ordenada distante", StatusCombinado.ATIVO, BASE, LocalDate.of(2026, 11, 20));
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer).param("titulo", "Ordenada")
                .param("sort", "prazo,asc", "status,desc"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo",
                        contains("Ordenada concluida", "Ordenada ativa", "Ordenada distante")));
    }
    @Test void adminTemConsultaGlobalMasCriadosContinuaLimitadoAoProprioAutor() throws Exception {
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        criarLista(externo, "Privado admin", StatusCombinado.RASCUNHO, BASE, null);
        mvc.perform(get("/api/combinados").header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
        mvc.perform(get("/api/combinados/criados").header("Authorization", token(externo)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Privado admin")));
    }
    @Test void participantePodeConsultarPublicadoMasNaoRascunhoNemForcarOutroUsuario() throws Exception {
        criarLista(participante, "Titulo proprio", StatusCombinado.RASCUNHO, BASE, null);
        mvc.perform(get("/api/combinados/criados").header("Authorization", bearer)
                .param("usuarioId", participante.getId().toString()).param("titulo", "Titulo proprio"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(get("/api/combinados").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(get("/api/combinados").header("Authorization", token(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", contains("Titulo proprio")));
    }
    @Test void deveRejeitarOrdenacaoInvalidaOuBuscaExcessivaEExigirLogin() throws Exception {
        for (String ordem : new String[]{"criador.senha,asc", "criadoEm,errado", "prazo,asc,extra", "prazo,"}) {
            mvc.perform(get("/api/combinados").header("Authorization", bearer).param("sort", ordem))
                    .andExpect(status().isBadRequest());
        }
        mvc.perform(get("/api/combinados").header("Authorization", bearer).param("titulo", "a".repeat(256)))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/combinados/criados")).andExpect(status().isUnauthorized());
    }
}
