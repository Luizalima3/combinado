package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.AtualizarEmailDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioAtualizacaoEmailTest {
    @Mock UsuarioRepository usuarios;
    @Mock PasswordEncoder passwordEncoder;
    @InjectMocks UsuarioPerfilService service;
    private Usuario usuario;

    @BeforeEach
    void autenticar() {
        usuario = new Usuario("Ana Souza", "ana@combinado.app", "hash-original", PerfilUsuario.ROLE_USUARIO);
        usuario.setId(1L);
        var principal = new UsuarioPrincipal(usuario);
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(
                principal, null, principal.getAuthorities()));
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void limparContexto() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void deveAtualizarEmailNormalizadoDoUsuarioAutenticado() {
        when(usuarios.findById(1L)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senha-atual", "hash-original")).thenReturn(true);
        when(usuarios.existsByEmailIgnoreCaseAndIdNot("novo@combinado.app", 1L)).thenReturn(false);
        when(usuarios.saveAndFlush(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = service.atualizarEmail(
                new AtualizarEmailDTO("  NOVO@Combinado.APP ", "senha-atual"));

        assertEquals(1L, resultado.id());
        assertEquals("novo@combinado.app", resultado.email());
        assertEquals("novo@combinado.app", usuario.getEmail());
        assertEquals("hash-original", usuario.getSenha());
        verify(usuarios).saveAndFlush(usuario);
    }

    @Test
    void deveRejeitarFormatoInvalidoSemAlterarOuSalvarUsuario() {
        var excecao = assertThrows(IllegalArgumentException.class,
                () -> service.atualizarEmail(new AtualizarEmailDTO("email-invalido", "senha-atual")));

        assertEquals("Informe um e-mail válido com até 150 caracteres.", excecao.getMessage());
        assertEquals("ana@combinado.app", usuario.getEmail());
        verifyNoInteractions(usuarios, passwordEncoder);
    }

    @Test
    void deveRejeitarEmailJaUtilizadoSemAlterarOuSalvarUsuario() {
        when(usuarios.findById(1L)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senha-atual", "hash-original")).thenReturn(true);
        when(usuarios.existsByEmailIgnoreCaseAndIdNot("ocupado@combinado.app", 1L)).thenReturn(true);

        var excecao = assertThrows(EmailEmUsoException.class,
                () -> service.atualizarEmail(new AtualizarEmailDTO("ocupado@combinado.app", "senha-atual")));

        assertEquals("Este e-mail já está em uso.", excecao.getMessage());
        assertEquals("ana@combinado.app", usuario.getEmail());
        verify(usuarios, never()).saveAndFlush(any(Usuario.class));
    }
}