package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.service.HistoricoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HistoricoAuditoriaTest extends GestaoFixture {
    @Autowired CombinadoHistoricoRepository historicos;
    @Autowired HistoricoService auditoria;
    private String url() { return "/api/combinados/" + combinado.getId() + "/historico"; }
    @Test void repositorioERotaDevemOrdenarPorDataEDesempatarPorId() throws Exception {
        var data = Instant.parse("2026-11-01T12:00:00Z");
        historicos.saveAndFlush(new CombinadoHistorico(combinado, criador, "Terceiro", AcaoCombinadoHistorico.CRIACAO, data.plusSeconds(1)));
        historicos.saveAndFlush(new CombinadoHistorico(combinado, criador, "Primeiro", AcaoCombinadoHistorico.CRIACAO, data));
        historicos.saveAndFlush(new CombinadoHistorico(combinado, participante, "Segundo", AcaoCombinadoHistorico.ALTERACAO_STATUS, data));
        em.clear();
        mvc.perform(get(url()).header("Authorization", bearer)).andExpect(status().isOk())
                .andExpect(jsonPath("$[*].descricao", contains("Primeiro", "Segundo", "Terceiro")))
                .andExpect(jsonPath("$[1].autorId").value(participante.getId().intValue()))
                .andExpect(jsonPath("$[1].autorNome").value("Ana"))
                .andExpect(jsonPath("$[1].ocorridoEm").value("2026-11-01T12:00:00Z"))
                .andExpect(jsonPath("$[1].autor").doesNotExist());
    }
    @Test void auditoriaDevePersistirAutorDescricaoMotivoETimestampDoServidor() throws Exception {
        Instant inicio = Instant.now().minusSeconds(1);
        auditoria.registrar(combinado, criador, "  Cancelamento de teste  ", "  Indisponível  ", AcaoCombinadoHistorico.CANCELAMENTO);
        em.flush(); em.clear();
        var salvo = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).getFirst();
        assertEquals(criador.getId(), salvo.getAutor().getId());
        assertEquals("Cancelamento de teste", salvo.getDescricao());
        assertEquals("Indisponível", salvo.getMotivo());
        assertTrue(salvo.getOcorridoEm().isAfter(inicio));
        var usuario = usuarios.findById(criador.getId()).orElseThrow();
        usuario.setNome("Nome posterior"); usuarios.saveAndFlush(usuario);
        mvc.perform(get(url()).header("Authorization", bearer)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autorNome").value("Criador"))
                .andExpect(jsonPath("$[0].motivo").value("Indisponível"));
    }
    @Test void consultaDevePreservarPermissoesInclusivePrivacidadeDoRascunho() throws Exception {
        auditoria.registrar(combinado, criador, "Criado", AcaoCombinadoHistorico.CRIACAO);
        mvc.perform(get(url()).header("Authorization", token(participante))).andExpect(status().isOk());
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isForbidden());
        combinado.setStatus(StatusCombinado.RASCUNHO); combinados.saveAndFlush(combinado);
        mvc.perform(get(url()).header("Authorization", token(participante))).andExpect(status().isForbidden());
        externo.setPerfil(PerfilUsuario.ROLE_ADMIN); usuarios.saveAndFlush(externo);
        mvc.perform(get(url()).header("Authorization", token(externo))).andExpect(status().isOk());
        mvc.perform(get(url())).andExpect(status().isUnauthorized());
    }
    @Test void eventoInvalidoNaoDeveSerGravado() {
        assertThrows(IllegalArgumentException.class, () -> auditoria.registrar(combinado, null, "Teste", AcaoCombinadoHistorico.CRIACAO));
        assertThrows(IllegalArgumentException.class, () -> auditoria.registrar(combinado, criador, " ", AcaoCombinadoHistorico.CRIACAO));
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
}
