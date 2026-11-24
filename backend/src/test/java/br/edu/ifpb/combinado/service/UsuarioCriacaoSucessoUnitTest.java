package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioCriacaoSucessoUnitTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve criar usuário com sucesso, criptografar senha com BCrypt e atribuir perfil ROLE_USUARIO (#31378)")
    void deveCriarUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Felipe Macedo",
                "felipe.sucesso@combinado.app",
                "senhaSegura123",
                "senhaSegura123"
        );

        when(usuarioRepository.existsByEmail("felipe.sucesso@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            u.setId(77L);
            return u;
        });

        var resposta = usuarioService.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(77L, resposta.id());
        assertEquals("Felipe Macedo", resposta.nome());
        assertEquals("felipe.sucesso@combinado.app", resposta.email());
        assertEquals(PerfilUsuario.ROLE_USUARIO, resposta.perfil());
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
