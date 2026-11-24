package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioEmailDuplicadoBloqueioUnitTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve bloquear cadastro com e-mail duplicado mesmo com variação de caixa alta/baixa (#31393)")
    void deveBloquearEmailDuplicado() {
        when(usuarioRepository.existsByEmail("duplicado@combinado.app")).thenReturn(true);

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Usuário Duplicado",
                "  DUPLICADO@Combinado.app ",
                "senha123",
                "senha123"
        );

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto)
        );

        assertEquals("Já existe um usuário cadastrado com este e-mail.", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}
