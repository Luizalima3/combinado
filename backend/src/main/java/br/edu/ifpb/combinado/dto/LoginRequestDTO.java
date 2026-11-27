package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais para autenticação.")
public record LoginRequestDTO(
        @NotBlank @Email
        @jakarta.validation.constraints.Pattern(
                regexp = UsuarioRequestDTO.EMAIL_REGEX,
                message = "O e-mail deve conter um domínio válido.")
        @Schema(description = "E-mail cadastrado; o domínio deve conter ao menos um ponto.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "email",
                pattern = UsuarioRequestDTO.EMAIL_REGEX, example = "ana@example.com")
        String email,
        @Schema(description = "Senha cadastrada; aceita até 72 bytes em UTF-8.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "password", example = "senha123")
        @NotBlank String senha) {
}
