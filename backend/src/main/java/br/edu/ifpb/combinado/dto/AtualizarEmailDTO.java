package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.Locale;

@Schema(description = "Dados para atualizar o e-mail do usuário autenticado.")
public record AtualizarEmailDTO(
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O formato do e-mail é inválido.")
        @Pattern(regexp = UsuarioRequestDTO.EMAIL_REGEX,
                message = "O domínio do e-mail deve conter ao menos um ponto.")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
        @Schema(description = "E-mail novo, único e com domínio contendo ao menos um ponto. Será normalizado para minúsculas.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "email",
                pattern = UsuarioRequestDTO.EMAIL_REGEX, maxLength = 150, example = "ana@example.com")
        String email,
        @NotBlank(message = "A senha atual é obrigatória.")
        @Schema(description = "Senha atual usada para confirmar a alteração.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "password", example = "senhaAtual123")
        String senhaAtual) {
    public AtualizarEmailDTO {
        email = email == null ? null : email.strip().toLowerCase(Locale.ROOT);
    }
    @Override public String toString() { return "AtualizarEmailDTO[credenciais omitidas]"; }
}
