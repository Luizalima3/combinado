package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.AtualizarNomeDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioAtualizacaoNomeTest {
    @Mock UsuarioRepository usuarios;
    @InjectMocks UsuarioPerfilService service;
    private Usuario usuario;

    @BeforeEach
    void autenticar() {
        usuario = new Usuario("Nome original", "perfil@combinado.app", "hash-original", PerfilUsuario.ROLE_USUARIO);
        usuario.setId(1L);
        var principal = new UsuarioPrincipal(usuario);
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void limparContexto() { SecurityContextHolder.clearContext(); }

    private void prepararPersistencia() {
        when(usuarios.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarios.saveAndFlush(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void deveAtualizarNomeNormalizadoEPersistirUsuarioAutenticado() {
        prepararPersistencia();
        var resultado = service.atualizarNome(new AtualizarNomeDTO("\u2003 José da Silva \t"));
        assertEquals(1L, resultado.id());
        assertEquals("José da Silva", resultado.nome());
        verify(usuarios).findById(1L);
        verify(usuarios).saveAndFlush(usuario);
        assertEquals("perfil@combinado.app", usuario.getEmail());
        assertEquals("hash-original", usuario.getSenha());
        assertEquals(PerfilUsuario.ROLE_USUARIO, usuario.getPerfil());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t\u2003", "A"})
    void deveRejeitarNomeNuloVazioOuCurto(String nome) {
        assertThrows(IllegalArgumentException.class, () -> service.atualizarNome(new AtualizarNomeDTO(nome)));
        verifyNoInteractions(usuarios);
        assertEquals("Nome original", usuario.getNome());
    }

    @Test
    void deveRejeitarNomeExcessivamenteLongo() {
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarNome(new AtualizarNomeDTO("A".repeat(151))));
        verifyNoInteractions(usuarios);
        assertEquals("Nome original", usuario.getNome());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 150})
    void deveAceitarLimitesDeTamanho(int tamanho) {
        prepararPersistencia();
        assertEquals("A".repeat(tamanho), service.atualizarNome(new AtualizarNomeDTO("A".repeat(tamanho))).nome());
        verify(usuarios).saveAndFlush(usuario);
    }

    @Test
    void deveRejeitarAlteracaoSemAutenticacao() {
        SecurityContextHolder.clearContext();
        assertThrows(AccessDeniedException.class, () -> service.atualizarNome(new AtualizarNomeDTO("Novo nome")));
        verifyNoInteractions(usuarios);
    }

    @Test
    void naoDeveSalvarQuandoUsuarioNaoExiste() {
        when(usuarios.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.atualizarNome(new AtualizarNomeDTO("Novo nome")));
        verify(usuarios, never()).saveAndFlush(any());
    }
}
