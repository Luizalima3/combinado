package br.edu.ifpb.combinado.dto;

public record ConviteRequestDTO(
        @io.swagger.v3.oas.annotations.media.Schema(description = "Legado; ignorado pela API, que usa o JWT", deprecated = true) Long remetenteId,
        @io.swagger.v3.oas.annotations.media.Schema(description = "ID do participante convidado", example = "2") Long destinatarioId,
        @io.swagger.v3.oas.annotations.media.Schema(description = "Mensagem opcional", example = "Confirme sua participação") String mensagem
) {}
