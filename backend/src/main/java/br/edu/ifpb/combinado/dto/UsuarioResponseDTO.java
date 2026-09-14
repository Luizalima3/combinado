package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        PerfilUsuario perfil,
        LocalDateTime dataCriacao
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getDataCriacao()
        );
    }
}
