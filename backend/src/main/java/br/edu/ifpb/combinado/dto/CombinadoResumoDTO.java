package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.*;
import java.time.*;

/** Mantém os nomes antigos consumidos pelo frontend e fornece os campos da sprint. */
public record CombinadoResumoDTO(Long id, String titulo, String descricao, CategoriaCombinado categoria,
        LocalDate dataPrazo, String criadorNome, String participanteNome, String responsabilidades,
        StatusCombinado status, LocalDateTime criadoEm, LocalDateTime aceitoEm, LocalDateTime recusadoEm,
        String nomeCriador, LocalDate prazo) {
    public static CombinadoResumoDTO de(Combinado c) {
        String nome = c.getCriador() == null ? c.getCriadorNome() : c.getCriador().getNome();
        return new CombinadoResumoDTO(c.getId(), c.getTitulo(), c.getDescricao(), c.getCategoria(),
                c.getDataPrazo(), nome, c.getParticipanteNome(), c.getResponsabilidades(), c.getStatus(),
                c.getCriadoEm(), c.getAceitoEm(), c.getRecusadoEm(), nome, c.getDataPrazo());
    }
}
