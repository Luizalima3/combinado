package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.model.Usuario;
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
class UsuarioSenhaValidacaoTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve rejeitar senha com menos de 6 caracteres")
    void deveRejeitarSenhaComMenosDeSeisCaracteres() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "12345", "12345");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("A senha deve conter no mínimo 6 caracteres.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve aceitar senha com exatamente 6 caracteres")
    void deveAceitarSenhaComSeisCaracteres() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "123456", "123456");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> usuarioService.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando a senha é nula")
    void deveRejeitarSenhaNula() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", null, null);

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("A senha é obrigatória.", excecao.getMessage());
    }
}