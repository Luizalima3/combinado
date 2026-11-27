package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Credenciais para alterar a senha do usuário autenticado.")
public record AtualizarSenhaDTO(
        @NotBlank(message = "A senha atual é obrigatória.")
        @Schema(description = "Senha atual da conta.", requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password", example = "senhaAtual123")
        String senhaAtual,
        @NotBlank(message = "A nova senha é obrigatória.")
        @Size(min = 6, message = "A nova senha deve conter no mínimo 6 caracteres.")
        @Schema(description = "Nova senha: pelo menos 6 caracteres e no máximo 72 bytes em UTF-8.",
                requiredMode = Schema.RequiredMode.REQUIRED, minLength = 6,
                format = "password", example = "novaSenha456")
        String novaSenha,
        @NotBlank(message = "A confirmação da nova senha é obrigatória.")
        @Schema(description = "Deve ser igual a novaSenha.", requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password", example = "novaSenha456")
        String confirmacaoSenha) {
    @Override
    public String toString() { return "AtualizarSenhaDTO[credenciais omitidas]"; }
}
