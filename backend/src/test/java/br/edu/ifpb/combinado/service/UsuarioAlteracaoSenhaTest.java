package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.AtualizarSenhaDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioAlteracaoSenhaTest {
    private final UsuarioRepository usuarios = mock(UsuarioRepository.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
    private final UsuarioPerfilService service = new UsuarioPerfilService(usuarios, encoder);
    private Usuario usuario;
    private String hashOriginal;

    @BeforeEach
    void autenticar() {
        hashOriginal = encoder.encode("senhaAtual123");
        usuario = new Usuario("Nome original", "perfil@combinado.app", hashOriginal, PerfilUsuario.ROLE_USUARIO);
        usuario.setId(1L);
        var principal = new UsuarioPrincipal(usuario);
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void limparContexto() { SecurityContextHolder.clearContext(); }

    private void carregarUsuario() { when(usuarios.findById(1L)).thenReturn(Optional.of(usuario)); }

    @Test
    void deveVerificarSenhaAtualEPersistirNovoHashBCryptSemAlterarOutrosDados() {
        carregarUsuario();
        service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", "novaSenha456", "novaSenha456"));
        verify(usuarios).saveAndFlush(usuario);
        assertNotEquals(hashOriginal, usuario.getSenha());
        assertNotEquals("novaSenha456", usuario.getSenha());
        assertTrue(encoder.matches("novaSenha456", usuario.getSenha()));
        assertFalse(encoder.matches("senhaAtual123", usuario.getSenha()));
        assertEquals("Nome original", usuario.getNome());
        assertEquals("perfil@combinado.app", usuario.getEmail());
        assertEquals(PerfilUsuario.ROLE_USUARIO, usuario.getPerfil());
    }

    @Test
    void senhaAtualIncorretaDevePreservarHashESemPersistencia() {
        carregarUsuario();
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("incorreta", "novaSenha456", "novaSenha456")));
        verify(usuarios, never()).saveAndFlush(any());
        assertEquals(hashOriginal, usuario.getSenha());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "12345"})
    void deveBloquearNovaSenhaForaDoPadraoMinimo(String nova) {
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", nova, nova)));
        verifyNoInteractions(usuarios);
        assertEquals(hashOriginal, usuario.getSenha());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveExigirSenhaAtual(String atual) {
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO(atual, "novaSenha456", "novaSenha456")));
        verifyNoInteractions(usuarios);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"divergente", "   "})
    void confirmacaoInvalidaDevePreservarHash(String confirmacao) {
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", "novaSenha456", confirmacao)));
        verifyNoInteractions(usuarios);
        assertEquals(hashOriginal, usuario.getSenha());
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 72})
    void deveAceitarLimitesDeTamanhoDaNovaSenha(int tamanho) {
        carregarUsuario();
        String nova = "A".repeat(tamanho);
        service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", nova, nova));
        assertTrue(encoder.matches(nova, usuario.getSenha()));
        verify(usuarios).saveAndFlush(usuario);
    }

    @Test
    void deveBloquearSenhaComMaisDe72BytesMesmoComMenosDe72Caracteres() {
        for (String nova : new String[]{"A".repeat(73), "á".repeat(37)}) {
            assertThrows(IllegalArgumentException.class,
                    () -> service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", nova, nova)));
        }
        verifyNoInteractions(usuarios);
        assertEquals(hashOriginal, usuario.getSenha());
    }

    @Test
    void deveAceitarSenhaMultibyteNoLimiteBCrypt() {
        carregarUsuario();
        String nova = "á".repeat(36);
        service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", nova, nova));
        assertTrue(encoder.matches(nova, usuario.getSenha()));
    }

    @Test
    void devePreservarEspacosDeSenhaValida() {
        carregarUsuario();
        service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", " senha123 ", " senha123 "));
        assertTrue(encoder.matches(" senha123 ", usuario.getSenha()));
        assertFalse(encoder.matches("senha123", usuario.getSenha()));
    }

    @Test
    void deveRejeitarSenhaAtualMaiorQueLimiteSemAcessarBanco() {
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("A".repeat(73), "novaSenha456", "novaSenha456")));
        verifyNoInteractions(usuarios);
    }

    @Test
    void naoDeveAceitarComparacaoComSenhaArmazenadaEmTextoSimples() {
        carregarUsuario();
        usuario.setSenha("senhaAtual123");
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", "novaSenha456", "novaSenha456")));
        verify(usuarios, never()).saveAndFlush(any());
    }

    @Test
    void naoDeveTrocarSenhaSemAutenticacao() {
        SecurityContextHolder.clearContext();
        assertThrows(AccessDeniedException.class,
                () -> service.atualizarSenha(new AtualizarSenhaDTO("senhaAtual123", "novaSenha456", "novaSenha456")));
        verifyNoInteractions(usuarios);
    }

    @Test
    void dtoNaoDeveExporCredenciaisEmToString() {
        var dto = new AtualizarSenhaDTO("senhaAtual123", "novaSenha456", "novaSenha456");
        assertFalse(dto.toString().contains("senhaAtual123"));
        assertFalse(dto.toString().contains("novaSenha456"));
    }
}
