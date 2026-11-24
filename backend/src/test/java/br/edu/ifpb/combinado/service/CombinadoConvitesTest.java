package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.Notificacao;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.NotificacaoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoConvitesTest {

    @Mock private CombinadoRepository combinadoRepository;
    @Mock private CombinadoHistoricoRepository combinadoHistoricoRepository;
    @Mock private UsuarioRepository usuarioRepository;
    @Mock private ConviteRepository conviteRepository;
    @Mock private ConviteHistoricoRepository conviteHistoricoRepository;
    @Mock private NotificacaoRepository notificacaoRepository;

    @InjectMocks private CombinadoService combinadoService;

    private Usuario criador;
    private Usuario participante;

    @AfterEach
    void limparContexto() {
        SecurityContextHolder.clearContext();
    }

    @BeforeEach
    void setUp() {
        criador = new Usuario("Ana", "ana@test.com", "senha", null);
        criador.setId(1L);
        participante = new Usuario("Bruno", "bruno@test.com", "senha", null);
        participante.setId(2L);
        var principal = new UsuarioPrincipal(criador);
        SecurityContextHolder.getContext().setAuthentication(
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
    }

    private void prepararPersistencia() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> {
            Combinado combinado = invocation.getArgument(0);
            if (combinado.getId() == null) combinado.setId(10L);
            return combinado;
        });
        when(conviteRepository.save(any(Convite.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void criaConviteHistoricoENotificacaoAoCriarCombinado() {
        prepararPersistencia();
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(participante));
        when(usuarioRepository.existsById(2L)).thenReturn(true);

        combinadoService.criarCombinado(dados(List.of(2L)), criador);

        ArgumentCaptor<Convite> conviteCaptor = ArgumentCaptor.forClass(Convite.class);
        verify(conviteRepository).save(conviteCaptor.capture());
        assertEquals(StatusConvite.PENDENTE, conviteCaptor.getValue().getStatus());
        assertEquals(participante, conviteCaptor.getValue().getDestinatario());
        verify(conviteHistoricoRepository).save(any(ConviteHistorico.class));
        ArgumentCaptor<Notificacao> notificacaoCaptor = ArgumentCaptor.forClass(Notificacao.class);
        verify(notificacaoRepository).save(notificacaoCaptor.capture());
        assertEquals(participante, notificacaoCaptor.getValue().getUsuario());
    }

    @Test
    void naoPermiteCriadorComoDestinatarioDoProprioConvite() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(criador));

        assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dados(List.of(1L)), criador));

        verify(conviteRepository, never()).save(any(Convite.class));
        verify(notificacaoRepository, never()).save(any(Notificacao.class));
    }

    @Test
    void criaConviteHistoricoENotificacaoAoPublicarRascunho() {
        prepararPersistencia();
        Combinado rascunho = new Combinado();
        rascunho.setId(10L);
        rascunho.setTitulo("Acordo");
        rascunho.setCategoria(CategoriaCombinado.OUTRO);
        rascunho.setDataPrazo(LocalDate.now().plusDays(5));
        rascunho.setCriador(criador);
        rascunho.setCriadorNome(criador.getNome());
        rascunho.setParticipanteNome(participante.getNome());
        rascunho.setStatus(StatusCombinado.RASCUNHO);
        when(combinadoRepository.findById(10L)).thenReturn(Optional.of(rascunho));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(participante));

        combinadoService.publicar(10L, dados(List.of(2L)));

        ArgumentCaptor<Convite> conviteCaptor = ArgumentCaptor.forClass(Convite.class);
        verify(conviteRepository).save(conviteCaptor.capture());
        assertEquals(StatusConvite.PENDENTE, conviteCaptor.getValue().getStatus());
        ArgumentCaptor<ConviteHistorico> historicoCaptor = ArgumentCaptor.forClass(ConviteHistorico.class);
        verify(conviteHistoricoRepository).save(historicoCaptor.capture());
        assertEquals(AcaoConvite.ENVIO, historicoCaptor.getValue().getAcao());
        verify(notificacaoRepository).save(any(Notificacao.class));
        ArgumentCaptor<br.edu.ifpb.combinado.model.CombinadoHistorico> combinadoHistoricoCaptor =
                ArgumentCaptor.forClass(br.edu.ifpb.combinado.model.CombinadoHistorico.class);
        verify(combinadoHistoricoRepository).save(combinadoHistoricoCaptor.capture());
        assertEquals(br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico.ALTERACAO_STATUS,
                combinadoHistoricoCaptor.getValue().getAcao());
        assertEquals("Status alterado de RASCUNHO para AGUARDANDO_CONFIRMACAO.",
                combinadoHistoricoCaptor.getValue().getDescricao());
    }

    private CombinadoDTO dados(List<Long> participantesIds) {
        return new CombinadoDTO(null, "Acordo", "Descrição", CategoriaCombinado.OUTRO,
                LocalDate.now().plusDays(5), criador.getNome(), participante.getNome(),
                "Responsabilidades", null, participantesIds);
    }
}
