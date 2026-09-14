package br.edu.ifpb.combinado.dto;

import java.util.regex.Pattern;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String confirmacaoSenha
) {
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

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
