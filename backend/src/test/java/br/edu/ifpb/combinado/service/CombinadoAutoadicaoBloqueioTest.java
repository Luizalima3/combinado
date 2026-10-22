package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoAutoadicaoBloqueioTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private CombinadoHistoricoRepository historicoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    private Usuario criador;

    @BeforeEach
    void setUp() {
        criador = new Usuario("Felipe Criador", "felipe@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        criador.setId(10L);
    }

    @Test
    @DisplayName("Deve bloquear publicação de rascunho quando o próprio criador é o único participante informado")
    void deveBloquearAutoadicaoDoCriadorAoPublicarRascunho() {
        Combinado rascunho = new Combinado();
        rascunho.setId(1L);
        rascunho.setTitulo("Empréstimo de livro");
        rascunho.setCategoria(CategoriaCombinado.EMPRESTIMO_OBJETO);
        rascunho.setDataPrazo(LocalDate.now().plusDays(5));
        rascunho.setStatus(StatusCombinado.RASCUNHO);
        rascunho.setCriador(criador);
        rascunho.setParticipantesUsuarios(Set.of(criador));

        when(combinadoRepository.findById(1L)).thenReturn(Optional.of(rascunho));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(criador));

        CombinadoDTO dtoComAutoadicao = new CombinadoDTO(
                1L,
                "Empréstimo de livro",
                "Descrição",
                CategoriaCombinado.EMPRESTIMO_OBJETO,
                LocalDate.now().plusDays(5),
                criador.getNome(),
                null,
                null,
                StatusCombinado.AGUARDANDO_CONFIRMACAO,
                List.of(10L)
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> combinadoService.publicar(1L, dtoComAutoadicao));

        assertEquals("Informe ao menos um participante cadastrado diferente do criador.", ex.getMessage());
        verify(combinadoRepository, never()).save(any());
    }
}
