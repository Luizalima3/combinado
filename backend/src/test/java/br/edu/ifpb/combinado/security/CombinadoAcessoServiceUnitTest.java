package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombinadoAcessoServiceUnitTest {
    @Mock
    private CombinadoRepository combinadoRepository;

    @InjectMocks
    private CombinadoAcessoService acessoService;

    @Test
    void permiteQueSomenteOCriadorSejaIdentificadoComoResponsavelPelaConclusao() {
        Long combinadoId = 10L;
        Usuario criador = usuario(1L);
        Usuario participante = usuario(2L);
        when(combinadoRepository.existsByIdAndCriadorId(combinadoId, criador.getId())).thenReturn(true);
        when(combinadoRepository.existsByIdAndCriadorId(combinadoId, participante.getId())).thenReturn(false);

        assertTrue(acessoService.eCriador(combinadoId, autenticacao(criador)));
        assertFalse(acessoService.eCriador(combinadoId, autenticacao(participante)));

        verify(combinadoRepository).existsByIdAndCriadorId(combinadoId, criador.getId());
        verify(combinadoRepository).existsByIdAndCriadorId(combinadoId, participante.getId());
    }

    private UsernamePasswordAuthenticationToken autenticacao(Usuario usuario) {
        var principal = new UsuarioPrincipal(usuario);
        return UsernamePasswordAuthenticationToken.authenticated(
                principal, null, principal.getAuthorities());
    }

    private Usuario usuario(Long id) {
        var usuario = new Usuario("Usuário " + id, "usuario" + id + "@teste.com", "senha", null);
        usuario.setId(id);
        return usuario;
    }
}
