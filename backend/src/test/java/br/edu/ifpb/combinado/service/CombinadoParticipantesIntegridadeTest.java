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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoParticipantesIntegridadeTest {

    @Mock
    private CombinadoRepository combinadoRepository;

    @Mock
    private CombinadoHistoricoRepository historicoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CombinadoService combinadoService;

    private Usuario criador;
    private Usuario participante1;
    private Usuario participante2;

    @BeforeEach
    void setUp() {
        criador = new Usuario("Ana Criadora", "ana@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        criador.setId(1L);

        participante1 = new Usuario("Bruno Silva", "bruno@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        participante1.setId(2L);

        participante2 = new Usuario("Carla Souza", "carla@combinado.app", "hash", PerfilUsuario.ROLE_USUARIO);
        participante2.setId(3L);
    }

    @Test
    @DisplayName("Deve impedir participantes duplicados quando o payload contém IDs repetidos")
    void deveImpedirParticipantesDuplicadosNoPayload() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(participante1));
        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(participante2));
        when(usuarioRepository.existsById(2L)).thenReturn(true);
        when(usuarioRepository.existsById(3L)).thenReturn(true);
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(inv -> {
            Combinado c = inv.getArgument(0);
            c.setId(100L);
            return c;
        });

        CombinadoDTO dto = new CombinadoDTO(
                null,
                "Divisão de aluguel",
                "Pagamento mensal",
                CategoriaCombinado.DIVISAO_DESPESA,
                LocalDate.now().plusDays(10),
                criador.getNome(),
                null,
                null,
                StatusCombinado.AGUARDANDO_CONFIRMACAO,
                List.of(2L, 3L, 2L, 3L)
        );

        Combinado salvo = combinadoService.criarCombinado(dto, criador);

        assertEquals(2, salvo.getUsuariosParticipantes().size());
        assertEquals(Set.of(2L, 3L), salvo.getUsuariosParticipantes().stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet()));
        assertEquals("Bruno Silva, Carla Souza", salvo.getParticipanteNome());
    }

    @Test
    @DisplayName("Deve atualizar corretamente a lista de participantes ao remover um membro na publicação do rascunho")
    void deveAtualizarListaAoRemoverParticipanteNaPublicacao() {
        Combinado rascunho = new Combinado();
        rascunho.setId(50L);
        rascunho.setTitulo("Rascunho inicial");
        rascunho.setStatus(StatusCombinado.RASCUNHO);
        rascunho.setCriador(criador);
        rascunho.setParticipantesUsuarios(Set.of(participante1, participante2));

        when(combinadoRepository.findById(50L)).thenReturn(Optional.of(rascunho));
        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(participante2));
        when(combinadoRepository.save(any(Combinado.class))).thenAnswer(inv -> inv.getArgument(0));

        CombinadoDTO dadosAtualizados = new CombinadoDTO(
                50L,
                "Rascunho publicado",
                "Apenas Carla permaneceu",
                CategoriaCombinado.OUTRO,
                LocalDate.now().plusDays(5),
                criador.getNome(),
                null,
                null,
                StatusCombinado.AGUARDANDO_CONFIRMACAO,
                List.of(3L)
        );

        var resumo = combinadoService.publicar(50L, dadosAtualizados);

        assertEquals(1, rascunho.getUsuariosParticipantes().size());
        assertTrue(rascunho.getUsuariosParticipantes().contains(participante2));
        assertFalse(rascunho.getUsuariosParticipantes().contains(participante1));
        assertEquals("Carla Souza", resumo.participanteNome());
    }
}
