package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
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
class CombinadoCategoriaTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    @Test
    @DisplayName("Deve persistir corretamente cada categoria válida do enum")
    void devePersistirTodasAsCategoriasValidas() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        for (CategoriaCombinado categoria : CategoriaCombinado.values()) {
            CombinadoDTO dto = new CombinadoDTO(
                    null,
                    "Título do combinado",
                    "Descrição do combinado",
                    categoria,
                    LocalDate.now().plusDays(5),
                    "Criador",
                    "Participante",
                    "Responsabilidades",
                    null,
                    null
            );

            Combinado salvo = combinadoService.criarCombinado(dto);

            assertEquals(categoria, salvo.getCategoria(), "Categoria deveria ser persistida corretamente: " + categoria);
        }
    }

    @Test
    @DisplayName("Deve rejeitar combinado sem categoria informada")
    void deveRejeitarCombinadoSemCategoria() {
        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", null, LocalDate.now().plusDays(5),
                "Criador", "Participante", "Responsabilidades", null, null
        );

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.criarCombinado(dto));

        assertEquals("A categoria do combinado é obrigatória.", excecao.getMessage());
        verify(combinadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Combinado criado com sucesso deve receber status AGUARDANDO_CONFIRMACAO")
    void deveDefinirStatusAguardandoConfirmacaoAoCriar() {
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CombinadoDTO dto = new CombinadoDTO(
                null, "Título", "Descrição", CategoriaCombinado.OUTRO, LocalDate.now().plusDays(5),
                "Criador", "Participante", "Responsabilidades", null, null
        );

        Combinado salvo = combinadoService.criarCombinado(dto);

        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO, salvo.getStatus());
    }
}