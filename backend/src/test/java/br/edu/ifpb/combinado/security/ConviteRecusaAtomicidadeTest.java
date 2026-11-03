package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.dto.RecusaConviteDTO;
import br.edu.ifpb.combinado.model.Notificacao;
import br.edu.ifpb.combinado.model.enums.*;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:recusa-atomicidade;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class ConviteRecusaAtomicidadeTest extends GestaoFixture {
    @Autowired ConviteService service;
    @Autowired ConviteHistoricoRepository historico;
    @MockitoSpyBean NotificacaoRepository notificacoes;
    @Test @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void falhaNaNotificacaoDeveDesfazerRecusaMotivoEHistorico() {
        var convite = convite(participante, StatusConvite.PENDENTE);
        doThrow(new DataIntegrityViolationException("Falha simulada")).when(notificacoes).save(any(Notificacao.class));
        var principal = new UsuarioPrincipal(participante);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        try {
            assertThrows(DataIntegrityViolationException.class,
                    () -> service.recusar(convite.getId(), new RecusaConviteDTO("Indisponível")));
        } finally { SecurityContextHolder.clearContext(); }
        var salvo = convites.findById(convite.getId()).orElseThrow();
        assertEquals(StatusConvite.PENDENTE, salvo.getStatus());
        assertNull(salvo.getMotivoRecusa()); assertNull(salvo.getDataResposta());
        assertTrue(historico.findByConviteIdOrderByOcorridoEmAscIdAsc(convite.getId()).isEmpty());
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(combinado.getId()).orElseThrow().getStatus());
    }
}
