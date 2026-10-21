package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.dto.ConviteLoteDTO;
import br.edu.ifpb.combinado.repository.*;
import br.edu.ifpb.combinado.service.ConviteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:lote-atomicidade;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class ConviteLoteAtomicidadeTest extends GestaoFixture {
    @Autowired ConviteService service;
    @Autowired ConviteHistoricoRepository historico;
    @MockitoSpyBean NotificacaoRepository notificacoes;
    @Test @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void falhaNaUltimaEtapaDeveDesfazerConvitesEHistoricoDoLoteInteiro() {
        doThrow(new DataIntegrityViolationException("Falha simulada ao gravar notificações"))
                .when(notificacoes).saveAll(any());
        var principal = new UsuarioPrincipal(criador);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        try {
            assertThrows(DataIntegrityViolationException.class, () -> service.enviarLote(combinado.getId(),
                    new ConviteLoteDTO(List.of(participante.getId(), externo.getId()), "Lote")));
        } finally { SecurityContextHolder.clearContext(); }
        assertTrue(convites.findByCombinadoId(combinado.getId()).isEmpty());
        assertEquals(0, historico.count());
        assertEquals(0, notificacoes.count());
    }
}
