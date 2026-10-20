package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.StatusConvite;

public record ParticipanteStatusDTO(Long id, String nome, String email, StatusConvite status) {}
