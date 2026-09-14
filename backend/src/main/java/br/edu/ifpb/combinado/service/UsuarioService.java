package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (dto.email() == null || dto.email().isBlank()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }
        if (!dto.isEmailValido()) {
            throw new IllegalArgumentException("O formato do e-mail informado é inválido.");
        }
        if (usuarioRepository.existsByEmail(dto.email().trim().toLowerCase())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        if (!dto.isSenhaForte()) {
            throw new IllegalArgumentException("A senha deve conter no mínimo 6 caracteres.");
        }

        if (!dto.isSenhasIguais()) {
            throw new IllegalArgumentException("A confirmação de senha não confere com a senha informada.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome().trim());
        usuario.setEmail(dto.email().trim().toLowerCase());
        usuario.setSenha(dto.senha()); 
        usuario.setPerfil(PerfilUsuario.USUARIO);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }
}