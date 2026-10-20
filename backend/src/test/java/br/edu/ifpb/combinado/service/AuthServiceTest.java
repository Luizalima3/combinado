package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.config.AuthenticationConfig;
import br.edu.ifpb.combinado.dto.LoginRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
    private final JwtService jwt = new JwtService("segredo-testes-autenticacao-com-32-bytes", 3600000);
    private final AuthService service = new AuthService(new AuthenticationConfig()
            .authenticationManager(new UsuarioDetailsService(repository), encoder), jwt);
    private Usuario usuario;

    @BeforeEach
    void preparar() {
        usuario = new Usuario("Ana", "ana@combinado.app", encoder.encode("senha123"), PerfilUsuario.USUARIO);
        usuario.setId(1L);
    }

    @Test
    void deveRejeitarSenhaAcimaDoLimiteBCryptSemConsultarBanco() {
        assertThrows(BadCredentialsException.class,
                () -> service.login(new LoginRequestDTO("ana@combinado.app", "á".repeat(37))));
        verifyNoInteractions(repository);
        assertThrows(IllegalArgumentException.class, () -> new UsuarioService(repository, encoder)
                .cadastrar(new UsuarioRequestDTO("Ana", "ana@combinado.app", "á".repeat(37), "á".repeat(37))));
        verify(repository, never()).save(any());
    }

    @Test
    void deveAutenticarComBCryptERetornarTokenDoUsuario() {
        when(repository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        var resposta = service.login(new LoginRequestDTO(" ANA@COMBINADO.APP ", "senha123"));
        assertEquals("Bearer", resposta.tipo());
        assertEquals(1L, resposta.usuario().id());
        assertEquals("1", jwt.validarToken(resposta.token()).getSubject());
        assertEquals(jwt.validarToken(resposta.token()).getExpiresAtAsInstant(), resposta.expiraEm());
    }

    @Test
    void deveRejeitarSenhaIncorreta() {
        when(repository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(BadCredentialsException.class,
                () -> service.login(new LoginRequestDTO(usuario.getEmail(), "senha-errada")));
    }

    @Test
    void deveRejeitarEmailInexistenteComMesmoTipoDeErro() {
        when(repository.findByEmail("inexistente@combinado.app")).thenReturn(Optional.empty());
        assertThrows(BadCredentialsException.class,
                () -> service.login(new LoginRequestDTO("inexistente@combinado.app", "senha123")));
    }

    @Test
    void deveRejeitarSenhaArmazenadaEmTextoSimples() {
        usuario.setSenha("senha123");
        when(repository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(BadCredentialsException.class,
                () -> service.login(new LoginRequestDTO(usuario.getEmail(), "senha123")));
    }

    @Test
    void cadastroDeveSalvarHashCompativelComLogin() {
        when(repository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario salvo = invocation.getArgument(0);
            salvo.setId(2L);
            assertNotEquals("senha123", salvo.getSenha());
            assertTrue(encoder.matches("senha123", salvo.getSenha()));
            when(repository.findByEmail(salvo.getEmail())).thenReturn(Optional.of(salvo));
            return salvo;
        });
        new UsuarioService(repository, encoder)
                .cadastrar(new UsuarioRequestDTO("Ana", "ana@combinado.app", "senha123", "senha123"));
        assertEquals(2L, service.login(new LoginRequestDTO("ana@combinado.app", "senha123")).usuario().id());
    }
}
