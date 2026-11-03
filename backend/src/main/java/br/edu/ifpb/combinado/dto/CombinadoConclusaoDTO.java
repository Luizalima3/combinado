package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.*;
import java.time.*;

public record CombinadoConclusaoDTO(
        Long id, String titulo, String descricao, CategoriaCombinado categoria,
        LocalDate dataPrazo, String criadorNome, String participanteNome, String responsabilidades,
        StatusCombinado status, LocalDateTime criadoEm, LocalDateTime aceitoEm, LocalDateTime recusadoEm,
        String nomeCriador, LocalDate prazo, String message) {
    public static CombinadoConclusaoDTO de(Combinado combinado) {
        var c = CombinadoResumoDTO.de(combinado);
        return new CombinadoConclusaoDTO(c.id(), c.titulo(), c.descricao(), c.categoria(), c.dataPrazo(),
                c.criadorNome(), c.participanteNome(), c.responsabilidades(), c.status(), c.criadoEm(),
                c.aceitoEm(), c.recusadoEm(), c.nomeCriador(), c.prazo(), "Combinado concluído com sucesso!");
    }
}
