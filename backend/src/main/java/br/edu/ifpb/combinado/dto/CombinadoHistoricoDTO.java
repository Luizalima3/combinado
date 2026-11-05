package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import java.time.Instant;

public record CombinadoHistoricoDTO(
    Long id,
    String autorNome,
    String descricao,
    String acao,
    Instant ocorridoEm
) {
    public static CombinadoHistoricoDTO de(CombinadoHistorico historico) {
        return new CombinadoHistoricoDTO(
            historico.getId(),
            historico.getAutorNome(),
            historico.getDescricao(),
            historico.getAcao().name(),
            historico.getOcorridoEm()
        );
    }
}
