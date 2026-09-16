package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoRascunhoTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    @Test
    @DisplayName("Deve salvar como rascunho apenas com o título preenchido")
    void deveSalvarComoRascunhoApenasComTitulo() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = new CombinadoDTO(
                null, "Título parcial", null, null, null,
                null, null, null, StatusCombinado.RASCUNHO, null
        );

        Combinado salvo = combinadoService.criarCombinado(dto);

        assertEquals(StatusCombinado.RASCUNHO, salvo.getStatus());
        assertEquals("Título parcial", salvo.getTitulo());
    }

    @Test
    @DisplayName("Deve rejeitar rascunho sem título informado")
    void deveRejeitarRascunhoSemTitulo() {
        CombinadoDTO dto = new CombinadoDTO(
                null, "   ", null, null, null,
                null, null, null, StatusCombinado.RASCUNHO, null
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("Para salvar como rascunho, o título é obrigatório.", excecao.getMessage());
        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Não deve consultar participantes nem disparar pendências ao salvar como rascunho")
    void naoDeveConsultarUsuarioRepositoryAoSalvarRascunho() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = new CombinadoDTO(
                null, "Título parcial", null, null, null,
                null, null, null, StatusCombinado.RASCUNHO, null
        );

        combinadoService.criarCombinado(dto);

        verifyNoInteractions(usuarioRepository);
    }
}