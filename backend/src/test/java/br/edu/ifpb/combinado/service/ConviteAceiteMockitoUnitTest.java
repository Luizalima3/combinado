package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.NotificacaoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConviteAceiteMockitoUnitTest {

    @Mock
    private ConviteRepository conviteRepository;
    @Mock
    private CombinadoRepository combinadoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private NotificacaoRepository notificacaoRepository;
    @Mock
    private ConviteHistoricoRepository historicoRepository;
    @Mock
    private CombinadoService combinadoService;

    @InjectMocks
    private ConviteService conviteService;

    @AfterEach
    void limparContexto() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Deve aceitar convite pendente com Mockito, registrar histórico e acionar ativação do combinado (#31400)")
    void deveAceitarConvitePendenteComMockito() {
        Usuario criador = new Usuario("Criador", "criador@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        criador.setId(1L);
        Usuario destinatario = new Usuario("Ana", "ana@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        destinatario.setId(2L);

        Combinado combinado = new Combinado();
        combinado.setId(10L);
        combinado.setCriador(criador);
        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);

        Convite convite = new Convite();
        convite.setId(50L);
        convite.setCombinado(combinado);
        convite.setRemetente(criador);
        convite.setDestinatario(destinatario);
        convite.setStatus(StatusConvite.PENDENTE);

        var principal = new UsuarioPrincipal(destinatario);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities())
        );

        when(conviteRepository.buscarParaResponder(50L)).thenReturn(Optional.of(convite));
        when(combinadoRepository.buscarParaConvite(10L)).thenReturn(Optional.of(combinado));
        when(conviteRepository.findFirstByCombinadoIdAndDestinatarioIdOrderByDataEnvioDescIdDesc(10L, 2L))
                .thenReturn(Optional.of(convite));

        var resposta = conviteService.aceitar(50L);

        assertEquals(50L, resposta.id());
        assertEquals(StatusConvite.ACEITO, resposta.status());
        assertNotNull(resposta.dataResposta());
        verify(conviteRepository).save(convite);
        verify(historicoRepository).save(any(ConviteHistorico.class));
        verify(combinadoService).ativarSeTodosAceitaram(combinado);
    }
}
