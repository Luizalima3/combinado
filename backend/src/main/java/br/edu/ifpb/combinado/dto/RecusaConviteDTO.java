package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public record RecusaConviteDTO(
        @Size(max = 500, message = "O motivo deve ter até 500 caracteres.")
        @Schema(description = "Motivo opcional da recusa, até 500 caracteres") String motivo) {
    public RecusaConviteDTO {
        motivo = motivo == null || motivo.isBlank() ? null : motivo.strip();
    }
}
