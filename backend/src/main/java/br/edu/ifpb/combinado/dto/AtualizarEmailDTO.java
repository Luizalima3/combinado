package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.*;
import java.util.Locale;

public record AtualizarEmailDTO(
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O formato do e-mail é inválido.")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.") String email,
        @NotBlank(message = "A senha atual é obrigatória.") String senhaAtual) {
    public AtualizarEmailDTO {
        email = email == null ? null : email.strip().toLowerCase(Locale.ROOT);
    }
    @Override public String toString() { return "AtualizarEmailDTO[credenciais omitidas]"; }
}
