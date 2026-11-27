package br.edu.ifpb.combinado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

@Schema(description = "Token de acesso e dados básicos do usuário autenticado.")
public record LoginResponseDTO(
        @Schema(description = "JWT de acesso. Enviar como Bearer token no cabeçalho Authorization.",
                example = "eyJhbGciOiJIUzI1NiJ9...")
        String token,
        @Schema(description = "Esquema de autenticação usado no cabeçalho Authorization.",
                allowableValues = {"Bearer"}, example = "Bearer")
        String tipo,
        @Schema(description = "Instante de expiração do token em UTC (ISO-8601).",
                format = "date-time", example = "2026-11-27T15:00:00Z")
        Instant expiraEm,
        @Schema(description = "Resumo do usuário autenticado, sem senha ou hash.")
        UsuarioResponseDTO usuario) {
}
