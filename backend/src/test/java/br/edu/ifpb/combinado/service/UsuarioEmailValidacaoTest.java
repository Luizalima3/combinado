package br.edu.ifpb.combinado.service;

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
class UsuarioEmailValidacaoTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve rejeitar e-mails com formato inválido")
    void deveRejeitarEmailComFormatoInvalido() {
        String[] emailsInvalidos = {"formato-invalido.com", "@semlocal.com", "semarroba"};

        for (String emailInvalido : emailsInvalidos) {
            UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", emailInvalido, "senha123", "senha123");

            IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                    () -> usuarioService.cadastrar(dto),
                    "Deveria rejeitar o e-mail: " + emailInvalido);

            assertEquals("O formato do e-mail informado é inválido.", excecao.getMessage());
        }

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve rejeitar cadastro quando o e-mail já está cadastrado")
    void deveRejeitarEmailDuplicado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", "senha123");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(true);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrar(dto));

        assertEquals("Já existe um usuário cadastrado com este e-mail.", excecao.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve aceitar e-mail válido e ainda não cadastrado")
    void deveAceitarEmailValidoENaoDuplicado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "ana@combinado.app", "senha123", "senha123");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> usuarioService.cadastrar(dto));
    }

        @Test
    @DisplayName("Deve normalizar o e-mail para minúsculas e sem espaços antes de salvar")
    void deveNormalizarEmailAntesDeSalvar() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Ana Souza", "  ANA@Combinado.APP  ", "senha123", "senha123");

        when(usuarioRepository.existsByEmail("ana@combinado.app")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = usuarioService.cadastrar(dto);

        assertEquals("ana@combinado.app", resultado.email());
    }
}