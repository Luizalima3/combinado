package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioCadastroTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve cadastrar usuário com sucesso quando todos os dados são válidos")
    void deveCadastrarUsuarioComDadosValidos() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", "senha123");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(1L);
            return usuario;
        });

        UsuarioResponseDTO resultado = usuarioService.cadastrar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Ana Souza", resultado.nome());
        assertEquals("ana@combinado.app", resultado.email());
        assertEquals(PerfilUsuario.USUARIO, resultado.perfil());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando o nome está em branco")
    void deveRejeitarCadastroComNomeEmBranco() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("   ", "ana@combinado.app", "senha123", "senha123");

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("O nome é obrigatório.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando o nome é nulo")
    void deveRejeitarCadastroComNomeNulo() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(null, "ana@combinado.app", "senha123", "senha123");

        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrar(dto));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando o e-mail está em branco")
    void deveRejeitarCadastroComEmailEmBranco() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "  ", "senha123", "senha123");

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("O e-mail é obrigatório.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando a senha está em branco")
    void deveRejeitarCadastroComSenhaEmBranco() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "  ", "  ");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("A senha é obrigatória.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}