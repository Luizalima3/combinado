package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.service.ConviteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:convites-concorrentes;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
class ConviteRespostasConcorrentesTest extends GestaoFixture {
    @Autowired ConviteService service;
    @Autowired CombinadoHistoricoRepository historico;
    @Test @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void doisAceitesSimultaneosDevemAtivarUmaUnicaVez() throws Exception {
        combinado.adicionarParticipante(externo); combinados.saveAndFlush(combinado);
        var a = convite(participante, StatusConvite.PENDENTE);
        var b = convite(externo, StatusConvite.PENDENTE);
        CountDownLatch inicio = new CountDownLatch(1);
        try (var executor = Executors.newFixedThreadPool(2)) {
            var primeiro = executor.submit(() -> aceitar(a.getId(), participante, inicio));
            var segundo = executor.submit(() -> aceitar(b.getId(), externo, inicio));
            inicio.countDown();
            assertEquals(StatusConvite.ACEITO, primeiro.get(20, TimeUnit.SECONDS));
            assertEquals(StatusConvite.ACEITO, segundo.get(20, TimeUnit.SECONDS));
        }
        assertEquals(StatusCombinado.ATIVO, combinados.findById(combinado.getId()).orElseThrow().getStatus());
        assertEquals(1, historico.findByCombinadoIdOrderByOcorridoEmAscIdAsc(combinado.getId()).stream()
                .filter(e -> e.getAcao() == AcaoCombinadoHistorico.ALTERACAO_STATUS).count());
    }
    private StatusConvite aceitar(Long id, Usuario usuario, CountDownLatch inicio) throws Exception {
        var principal = new UsuarioPrincipal(usuario);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        try {
            inicio.await(10, TimeUnit.SECONDS);
            return service.aceitar(id).status();
        } finally { SecurityContextHolder.clearContext(); }
    }
}
