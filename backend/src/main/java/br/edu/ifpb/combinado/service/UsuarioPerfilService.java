package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.AtualizarNomeDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioPerfilService {
    private final UsuarioRepository usuarios;

    public UsuarioPerfilService(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public UsuarioResponseDTO atualizarNome(AtualizarNomeDTO dto) {
        String nome = dto.nome();
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (nome.length() < 2 || nome.length() > 150) {
            throw new IllegalArgumentException("O nome deve ter entre 2 e 150 caracteres.");
        }
        Usuario usuario = usuarioAutenticado();
        usuario.setNome(nome);
        return UsuarioResponseDTO.fromEntity(usuarios.saveAndFlush(usuario));
    }

    private Usuario usuarioAutenticado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof UsuarioPrincipal principal)) {
            throw new AccessDeniedException("Autenticação necessária.");
        }
        return usuarios.findById(principal.usuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }
}
