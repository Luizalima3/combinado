package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.AtualizarNomeDTO;
import br.edu.ifpb.combinado.dto.AtualizarSenhaDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioPerfilService {
    private final UsuarioRepository usuarios;
    private final PasswordEncoder passwordEncoder;

    public UsuarioPerfilService(UsuarioRepository usuarios, PasswordEncoder passwordEncoder) {
        this.usuarios = usuarios;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public void atualizarSenha(AtualizarSenhaDTO dto) {
        if (dto.senhaAtual() == null || dto.senhaAtual().isBlank()
                || dto.senhaAtual().getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("A senha atual é inválida.");
        }
        if (dto.novaSenha() == null || dto.novaSenha().isBlank() || dto.novaSenha().length() < 6) {
            throw new IllegalArgumentException("A nova senha deve conter no mínimo 6 caracteres.");
        }
        if (dto.novaSenha().getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("A nova senha deve conter no máximo 72 bytes em UTF-8.");
        }
        if (!dto.novaSenha().equals(dto.confirmacaoSenha())) {
            throw new IllegalArgumentException("A confirmação da nova senha não confere.");
        }
        Usuario usuario = usuarioAutenticado();
        if (!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("A senha atual é inválida.");
        }
        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        usuarios.saveAndFlush(usuario);
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
