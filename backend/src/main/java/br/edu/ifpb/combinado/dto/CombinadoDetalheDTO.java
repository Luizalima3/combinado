package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.*;
import java.time.*;
import java.util.List;

/** Preserva os campos do resumo e acrescenta somente DTOs seguros aos detalhes. */
public record CombinadoDetalheDTO(
        Long id, String titulo, String descricao, CategoriaCombinado categoria,
        LocalDate dataPrazo, String criadorNome, String participanteNome, String responsabilidades,
        StatusCombinado status, LocalDateTime criadoEm, LocalDateTime aceitoEm, LocalDateTime recusadoEm,
        String nomeCriador, LocalDate prazo, List<ParticipanteStatusDTO> participantes,
        List<TarefaResumoDTO> tarefas, List<AcaoPermitidaCombinado> acoesPermitidas) {
    public static CombinadoDetalheDTO de(CombinadoResumoDTO c, List<ParticipanteStatusDTO> participantes,
            List<TarefaResumoDTO> tarefas, List<AcaoPermitidaCombinado> acoes) {
        return new CombinadoDetalheDTO(c.id(), c.titulo(), c.descricao(), c.categoria(), c.dataPrazo(),
                c.criadorNome(), c.participanteNome(), c.responsabilidades(), c.status(), c.criadoEm(),
                c.aceitoEm(), c.recusadoEm(), c.nomeCriador(), c.prazo(),
                List.copyOf(participantes), List.copyOf(tarefas), List.copyOf(acoes));
    }
}
