package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import br.edu.ifpb.combinado.service.ConviteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:convite-atomicidade;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class ConviteAtomicidadeTest extends GestaoFixture {
    @Autowired ConviteService service;
    @MockitoSpyBean ConviteHistoricoRepository historico;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void falhaAoGravarHistoricoDeveDesfazerRespostaNoBanco() {
        // Sem transação de teste: a leitura final verifica a transação real do serviço.
        Convite convite = convite(participante, StatusConvite.PENDENTE);
        doThrow(new DataIntegrityViolationException("Falha de persistência simulada"))
                .when(historico).save(any(ConviteHistorico.class));
        var principal = new UsuarioPrincipal(participante);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        try {
            assertThrows(DataIntegrityViolationException.class, () -> service.aceitar(convite.getId()));
        } finally {
            SecurityContextHolder.clearContext();
        }
        Convite salvo = convites.findById(convite.getId()).orElseThrow();
        assertEquals(StatusConvite.PENDENTE, salvo.getStatus());
        assertNull(salvo.getDataResposta());
        assertTrue(historico.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).isEmpty());
    }
}
