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
class UsuarioConfirmacaoSenhaTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve rejeitar cadastro quando a confirmação de senha diverge da senha")
    void deveRejeitarQuandoSenhasDivergem() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", "senha456");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("A confirmação de senha não confere com a senha informada.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve aceitar cadastro quando a confirmação de senha coincide com a senha")
    void deveAceitarQuandoSenhasCoincidem() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", "senha123");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> usuarioService.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando a confirmação de senha é nula")
    void deveRejeitarQuandoConfirmacaoENula() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", null);

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrar(dto));
    }
}