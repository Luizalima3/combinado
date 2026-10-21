package br.edu.ifpb.combinado.dto;

public record ConviteRequestDTO(
        Long remetenteId,
        Long destinatarioId,
        String mensagem
) {}
