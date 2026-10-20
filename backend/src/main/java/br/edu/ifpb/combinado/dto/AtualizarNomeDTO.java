package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarNomeDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres.") String nome) {
    public AtualizarNomeDTO {
        if (nome != null) nome = nome.strip();
    }
}
