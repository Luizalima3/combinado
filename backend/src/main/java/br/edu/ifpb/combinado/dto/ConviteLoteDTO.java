package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record ConviteLoteDTO(
        @NotEmpty @Size(max = 100)
        @Schema(description = "IDs de 1 a 100 participantes distintos; o criador não pode constar na lista", example = "[2,3]")
        List<@NotNull Long> participantesIds,
        @Size(max = 255) @Schema(description = "Mensagem opcional comum ao lote", example = "Vamos combinar a entrega?")
        String mensagem) {}
