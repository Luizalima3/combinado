package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Mensagem explicativa de uma resposta de erro.")
public record MensagemErroDTO(
        @Schema(description = "Descrição do erro.", example = "Dados inválidos.")
        String message) {
}
