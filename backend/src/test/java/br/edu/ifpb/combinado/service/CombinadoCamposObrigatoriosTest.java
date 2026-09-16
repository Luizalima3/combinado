package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoCamposObrigatoriosTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    @Test
    @DisplayName("Deve rejeitar combinado com título em branco")
    void deveRejeitarTituloEmBranco() {
        CombinadoDTO dto = new CombinadoDTO(
                null, "   ", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "Criador", "Participante", "Responsabilidades", null, null
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("O título do combinado é obrigatório.", excecao.getMessage());
        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar combinado com nome do criador em branco")
    void deveRejeitarCriadorEmBranco() {
        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "   ", "Participante", "Responsabilidades", null, null
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("O nome do criador é obrigatório.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar combinado sem nome de participante informado")
    void deveRejeitarParticipanteEmBranco() {
        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "Criador", "   ", "Responsabilidades", null, null
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("O nome do participante é obrigatório.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve aceitar combinado quando todos os campos obrigatórios estão preenchidos")
    void deveAceitarQuandoTodosCamposObrigatoriosPreenchidos() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "Criador", "Participante", "Responsabilidades", null, null
        );

        assertDoesNotThrow(() -> combinadoService.criarCombinado(dto));
    }

    @Test
    @DisplayName("Deve rejeitar combinado quando um participante da lista de IDs não existe")
    void deveRejeitarParticipanteInexistente() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "Criador", "Participante", "Responsabilidades", null, List.of(99L)
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("Participante com ID 99 não encontrado.", excecao.getMessage());
        verify(combinadoRepository, never()).save(any());
    }
}