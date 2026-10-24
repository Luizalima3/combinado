package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * US63 - Task #31675: teste unitário da regra de bloqueio de edição.
 * DADO que o combinado está com status "Ativo" (ou concluído/cancelado),
 * QUANDO o usuário tentar editar título, descrição, categoria, prazo ou participantes,
 * ENTÃO o sistema deve bloquear a edição e informar que o combinado não pode mais ser alterado.
 */
@ExtendWith(MockitoExtension.class)
class CombinadoBloqueioEdicaoTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ConviteRepository conviteRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    private Combinado combinadoComStatus(StatusCombinado status) {
        Combinado combinado = new Combinado();
        combinado.setId(1L);
        combinado.setTitulo("Acordo original");
        combinado.setCriadorNome("Criador");
        combinado.setParticipanteNome("Participante");
        combinado.setCategoria(br.edu.ifpb.combinado.model.enums.CategoriaCombinado.OUTRO);
        combinado.setDataPrazo(java.time.LocalDate.now().plusDays(7));
        var criador = new br.edu.ifpb.combinado.model.Usuario("Criador", "criador@teste.app", "hash", null);
        criador.setId(10L);
        combinado.setCriador(criador);
        var participante = new br.edu.ifpb.combinado.model.Usuario("Participante", "participante@teste.app", "hash", null);
        participante.setId(20L);
        combinado.adicionarParticipante(participante);
        combinado.setStatus(status);
        return combinado;
    }

    private CombinadoDTO dtoEdicao() {
        return new CombinadoDTO(
                null, "Título alterado", "Descrição alterada", null, null,
                null, null, null, null, null
        );
    }

    @Test
    @DisplayName("Deve bloquear edição quando o combinado está ATIVO")
    void deveBloquearEdicaoQuandoAtivo() {
        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinadoComStatus(StatusCombinado.ATIVO)));

        CombinadoNaoEditavelException excecao = assertThrows(CombinadoNaoEditavelException.class,
                () -> combinadoService.atualizar(1L, dtoEdicao()));

        assertTrue(excecao.getMessage().contains("não pode mais ser alterado"));
        assertTrue(excecao.getMessage().contains("ATIVO"));
        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve bloquear edição quando o combinado está CONCLUIDO")
    void deveBloquearEdicaoQuandoConcluido() {
        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinadoComStatus(StatusCombinado.CONCLUIDO)));

        assertThrows(CombinadoNaoEditavelException.class,
                () -> combinadoService.atualizar(1L, dtoEdicao()));

        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve bloquear edição quando o combinado está CANCELADO")
    void deveBloquearEdicaoQuandoCancelado() {
        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinadoComStatus(StatusCombinado.CANCELADO)));

        assertThrows(CombinadoNaoEditavelException.class,
                () -> combinadoService.atualizar(1L, dtoEdicao()));

        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve permitir edição quando o combinado está AGUARDANDO_CONFIRMACAO")
    void devePermitirEdicaoQuandoAguardandoConfirmacao() {
        Combinado combinado = combinadoComStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = combinadoService.atualizar(1L, dtoEdicao());

        assertEquals("Título alterado", resultado.titulo());
        verify(combinadoRepository).save(any(Combinado.class));
    }

    @Test
    @DisplayName("Deve permitir edição quando o combinado está em RASCUNHO")
    void devePermitirEdicaoQuandoRascunho() {
        Combinado combinado = combinadoComStatus(StatusCombinado.RASCUNHO);
        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(combinado));
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = combinadoService.atualizar(1L, dtoEdicao());

        assertEquals("Título alterado", resultado.titulo());
        verify(combinadoRepository).save(any(Combinado.class));
    }
}
