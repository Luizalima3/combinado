package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.regex.Pattern;

@Schema(description = "Dados necessários para cadastrar uma conta de usuário.")
public record UsuarioRequestDTO(
        @Schema(description = "Nome do usuário.", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ana Souza")
        String nome,
        @Schema(description = "E-mail único; o domínio deve conter ao menos um ponto.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "email", pattern = EMAIL_REGEX,
                example = "ana@example.com")
        String email,
        @Schema(description = "Senha com no mínimo 6 caracteres e no máximo 72 bytes em UTF-8.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "password", example = "senha123")
        String senha,
        @Schema(description = "Deve ser igual ao valor informado em senha.",
                requiredMode = Schema.RequiredMode.REQUIRED, format = "password", example = "senha123")
        String confirmacaoSenha
) {
    public static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isEmailValido() {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public boolean isSenhaForte() {
        // Mínimo 6 caracteres
        return senha != null && senha.length() >= 6;
    }

    public boolean isSenhasIguais() {
        return senha != null && senha.equals(confirmacaoSenha);
    }
}
