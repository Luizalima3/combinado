package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.ConviteRequestDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.Notificacao;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.NotificacaoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConviteEnvioTest {

    @Mock
    private ConviteRepository conviteRepository;

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @InjectMocks
    private ConviteService conviteService;

    private Combinado combinado;
    private Usuario remetente;
    private Usuario destinatario;

    @BeforeEach
    void setUp() {
        combinado = new Combinado();
        combinado.setId(1L);
        combinado.setTitulo("Empréstimo da furadeira");

        remetente = new Usuario("Ana Souza", "ana@combinado.app", "senha123", null);
        remetente.setId(10L);

        destinatario = new Usuario("Bruno Lima", "bruno@combinado.app", "senha123", null);
        destinatario.setId(20L);
    }

    @Test
    @DisplayName("Deve criar convite com status PENDENTE e gerar notificação para o destinatário")
    void deveCriarConviteComStatusPendenteEGerarNotificacao() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 20L, "Pode me emprestar a furadeira?");

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(remetente));
        when(usuarioRepository.findById(20L)).thenReturn(Optional.of(destinatario));
        when(conviteRepository.save(any(Convite.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(notificacaoRepository.save(any(Notificacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Convite resultado = conviteService.enviarConvite(1L, dto);

        assertEquals(StatusConvite.PENDENTE, resultado.getStatus());
        assertEquals(combinado, resultado.getCombinado());
        assertEquals(remetente, resultado.getRemetente());
        assertEquals(destinatario, resultado.getDestinatario());
        assertEquals("Pode me emprestar a furadeira?", resultado.getMensagem());

        ArgumentCaptor<Notificacao> captor = ArgumentCaptor.forClass(Notificacao.class);
        verify(notificacaoRepository).save(captor.capture());
        Notificacao notificacao = captor.getValue();
        assertEquals(destinatario, notificacao.getUsuario());
        assertEquals("Novo Convite de Combinado", notificacao.getTitulo());
        assertTrue(notificacao.getConteudo().contains("Ana Souza"));
        assertTrue(notificacao.getConteudo().contains("Empréstimo da furadeira"));
    }

    @Test
    @DisplayName("Deve criar convite mesmo sem mensagem personalizada")
    void deveCriarConviteSemMensagemPersonalizada() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 20L, null);

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(remetente));
        when(usuarioRepository.findById(20L)).thenReturn(Optional.of(destinatario));
        when(conviteRepository.save(any(Convite.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(notificacaoRepository.save(any(Notificacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Convite resultado = conviteService.enviarConvite(1L, dto);

        assertEquals(StatusConvite.PENDENTE, resultado.getStatus());
        assertNull(resultado.getMensagem());
        verify(notificacaoRepository).save(any(Notificacao.class));
    }

    @Test
    @DisplayName("Deve rejeitar envio de convite para combinado inexistente")
    void deveRejeitarConviteParaCombinadoInexistente() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 20L, "Mensagem");

        when(combinadoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> conviteService.enviarConvite(1L, dto));
        verify(conviteRepository, never()).save(any());
        verify(notificacaoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar envio de convite quando remetente não existe")
    void deveRejeitarConviteParaRemetenteInexistente() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 20L, "Mensagem");

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> conviteService.enviarConvite(1L, dto));
        verify(conviteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar envio de convite quando destinatário não existe")
    void deveRejeitarConviteParaDestinatarioInexistente() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 20L, "Mensagem");

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(remetente));
        when(usuarioRepository.findById(20L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> conviteService.enviarConvite(1L, dto));
        verify(conviteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar quando o criador tenta convidar a si mesmo")
    void deveRejeitarAutoConvite() {
        ConviteRequestDTO dto = new ConviteRequestDTO(10L, 10L, "Mensagem");

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(remetente));

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> conviteService.enviarConvite(1L, dto));

        assertEquals("O criador não pode enviar convite para si mesmo.", excecao.getMessage());
        verify(conviteRepository, never()).save(any());
    }
}