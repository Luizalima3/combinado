package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioResumoDTO;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioBuscaServiceTest {
    @Mock UsuarioRepository usuarios;
    @Mock org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    @InjectMocks UsuarioService service;

    @Test
    void deveBuscarTrechoDoNomeExcluindoUsuarioLogado() {
        var resultados = List.of(
                new UsuarioResumoDTO(2L, "Ana Maria", "ana@combinado.app"),
                new UsuarioResumoDTO(3L, "Mariana", "mariana@combinado.app"));
        when(usuarios.findByNomeContainingIgnoreCaseAndIdNot(eq("ana"), eq(1L), any()))
                .thenReturn(resultados);

        var encontrados = service.buscarPorNome("  ana  ", 0, 10, 1L);

        assertEquals(resultados, encontrados);
        verify(usuarios).findByNomeContainingIgnoreCaseAndIdNot(eq("ana"), eq(1L), any());
    }

    @Test
    void deveRetornarListaVaziaParaTermoCurtoSemConsultarRepositorio() {
        assertEquals(List.of(), service.buscarPorNome(" a ", 0, 10, 1L));
        verifyNoInteractions(usuarios);
    }
}