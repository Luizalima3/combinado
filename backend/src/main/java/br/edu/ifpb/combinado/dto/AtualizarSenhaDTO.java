package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarSenhaDTO(
        @NotBlank(message = "A senha atual é obrigatória.") String senhaAtual,
        @NotBlank(message = "A nova senha é obrigatória.")
        @Size(min = 6, message = "A nova senha deve conter no mínimo 6 caracteres.") String novaSenha,
        @NotBlank(message = "A confirmação da nova senha é obrigatória.") String confirmacaoSenha) {
    @Override
    public String toString() { return "AtualizarSenhaDTO[credenciais omitidas]"; }
}
