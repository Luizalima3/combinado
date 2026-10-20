package br.edu.ifpb.combinado.dto;

import java.time.Instant;

public record LoginResponseDTO(String token, String tipo, Instant expiraEm, UsuarioResponseDTO usuario) {
}
