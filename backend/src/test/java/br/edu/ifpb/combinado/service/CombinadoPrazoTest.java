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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoPrazoTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    private CombinadoDTO montarDto(LocalDate prazo) {
        return new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, prazo,
                "Criador", "Participante", "Responsabilidades", null, null
        );
    }

    @Test
    @DisplayName("Deve rejeitar combinado com prazo no passado")
    void deveRejeitarPrazoNoPassado() {
        CombinadoDTO dto = montarDto(LocalDate.now().minusDays(1));

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("O prazo do combinado deve ser uma data presente ou futura.", excecao.getMessage());
        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar combinado com prazo nulo")
    void deveRejeitarPrazoNulo() {
        CombinadoDTO dto = montarDto(null);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("O prazo do combinado é obrigatório.", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve aceitar combinado com prazo futuro")
    void deveAceitarPrazoFuturo() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = montarDto(LocalDate.now().plusDays(10));

        Combinado salvo = combinadoService.criarCombinado(dto);

        assertEquals(LocalDate.now().plusDays(10), salvo.getDataPrazo());
    }

    @Test
    @DisplayName("Deve aceitar combinado com prazo igual à data atual")
    void deveAceitarPrazoNaDataAtual() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = montarDto(LocalDate.now());

        assertDoesNotThrow(() -> combinadoService.criarCombinado(dto));
    }
}