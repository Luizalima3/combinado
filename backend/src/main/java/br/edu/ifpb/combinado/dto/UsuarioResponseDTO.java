package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;

import java.time.LocalDateTime;

@Schema(description = "Dados públicos do usuário; não inclui senha nem hash.")
public record UsuarioResponseDTO(
        @Schema(description = "Identificador do usuário.", example = "42")
        Long id,
        @Schema(description = "Nome de exibição.", example = "Ana Souza")
        String nome,
        @Schema(description = "E-mail cadastrado.", format = "email", example = "ana@example.com")
        String email,
        @Schema(description = "Perfil atribuído à conta.")
        PerfilUsuario perfil,
        @Schema(description = "Data e hora da criação; o valor não inclui fuso horário.",
                format = "date-time", example = "2026-11-27T10:30:00")
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
