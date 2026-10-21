package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import java.time.Instant;

public record ConviteHistoricoDTO(Long id, AcaoConvite acao, Long autorId, String autorNome, Instant ocorridoEm) {
    public static ConviteHistoricoDTO de(ConviteHistorico evento) {
        return new ConviteHistoricoDTO(evento.getId(), evento.getAcao(), evento.getAutor().getId(),
                evento.getAutorNome(), evento.getOcorridoEm());
    }
}
