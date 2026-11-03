package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.service.CombinadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:historico-atomicidade;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class CombinadoHistoricoAtomicidadeTest extends GestaoFixture {
    @Autowired CombinadoService service;
    @MockitoSpyBean CombinadoHistoricoRepository historicos;
    @Test @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void falhaNaAuditoriaDeveDesfazerConclusao() {
        combinado.setStatus(StatusCombinado.ATIVO); combinados.saveAndFlush(combinado);
        convite(participante, StatusConvite.ACEITO);
        doThrow(new DataIntegrityViolationException("Falha simulada")).when(historicos).save(any(CombinadoHistorico.class));
        var principal = new UsuarioPrincipal(criador);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        try { assertThrows(DataIntegrityViolationException.class, () -> service.concluir(combinado.getId())); }
        finally { SecurityContextHolder.clearContext(); }
        assertEquals(StatusCombinado.ATIVO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
        assertTrue(historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).isEmpty());
    }
}
