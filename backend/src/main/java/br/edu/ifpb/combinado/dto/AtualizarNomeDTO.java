package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Novo nome do usuário autenticado.")
public record AtualizarNomeDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres.")
        @Schema(description = "Nome após remoção de espaços nas extremidades; entre 2 e 150 caracteres.",
                requiredMode = Schema.RequiredMode.REQUIRED, minLength = 2, maxLength = 150,
                example = "Ana Souza")
        String nome) {
    public AtualizarNomeDTO {
        if (nome != null) nome = nome.strip();
    }
}
