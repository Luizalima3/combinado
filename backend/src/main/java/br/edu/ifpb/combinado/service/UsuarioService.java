package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.dto.UsuarioResumoDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class UsuarioService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (dto.email() == null || dto.email().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        String emailSanitizado = dto.email().trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(emailSanitizado).matches()) {
            throw new IllegalArgumentException("O formato do e-mail informado é inválido.");
        }

        if (usuarioRepository.existsByEmail(emailSanitizado)) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }

        if (dto.senha() == null || dto.senha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        if (dto.senha().length() < 6) {
            throw new IllegalArgumentException("A senha deve conter no mínimo 6 caracteres.");
        }

        if (dto.senha().getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("A senha deve conter no máximo 72 bytes em UTF-8.");
        }

        if (dto.confirmacaoSenha() == null || !dto.senha().equals(dto.confirmacaoSenha())) {
            throw new IllegalArgumentException("A confirmação de senha não confere com a senha informada.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome().trim());
        usuario.setEmail(emailSanitizado);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setPerfil(PerfilUsuario.ROLE_USUARIO);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public List<UsuarioResumoDTO> buscarPorNome(String termo, int pagina, int limite, Long usuarioLogadoId) {
        if (pagina < 0 || limite < 1 || limite > 50) {
            throw new IllegalArgumentException("Página deve ser não negativa e limite deve estar entre 1 e 50.");
        }
        if (usuarioLogadoId == null) {
            throw new IllegalArgumentException("Usuário autenticado não informado.");
        }
        String nome = termo == null ? "" : termo.strip();
        if (nome.length() > 150) {
            throw new IllegalArgumentException("O termo deve conter no máximo 150 caracteres.");
        }
        if (nome.length() < 2) return List.of();
        return usuarioRepository.findByNomeContainingIgnoreCaseAndIdNot(nome, usuarioLogadoId,
                PageRequest.of(pagina, limite, Sort.by("nome").ascending().and(Sort.by("id").ascending())));
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        return UsuarioResponseDTO.fromEntity(usuario);
    }
}
