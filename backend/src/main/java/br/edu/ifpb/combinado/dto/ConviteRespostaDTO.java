package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import java.time.Instant;

public record ConviteRespostaDTO(Long id, Long combinadoId, StatusConvite status, Instant dataResposta) {}
