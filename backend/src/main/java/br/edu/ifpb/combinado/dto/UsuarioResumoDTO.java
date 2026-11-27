package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Identificação pública usada nos resultados da busca de usuários.")
public record UsuarioResumoDTO(
        @Schema(description = "Identificador do usuário.", example = "42") Long id,
        @Schema(description = "Nome de exibição.", example = "Ana Souza") String nome,
        @Schema(description = "E-mail cadastrado.", format = "email", example = "ana@example.com") String email) {
}
