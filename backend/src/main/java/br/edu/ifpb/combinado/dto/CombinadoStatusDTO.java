package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.*;
import java.time.Instant;
import java.util.List;

public record CombinadoStatusDTO(Long id, StatusCombinado status, List<ParticipanteStatusDTO> participantes,
        List<AcaoPermitidaCombinado> acoesPermitidas, Instant consultadoEm) {}
