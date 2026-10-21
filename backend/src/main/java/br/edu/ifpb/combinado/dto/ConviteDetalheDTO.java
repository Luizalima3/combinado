package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.*;
import java.time.LocalDateTime;
import java.util.List;

public record ConviteDetalheDTO(Long id, Long combinadoId, String titulo, CategoriaCombinado categoria,
        StatusConvite status, String mensagem, LocalDateTime dataEnvio,
        UsuarioResumoDTO remetente, UsuarioResumoDTO destinatario,
        List<ParticipanteStatusDTO> participantesConfirmados,
        java.time.Instant dataResposta, List<ConviteHistoricoDTO> historico) {}
