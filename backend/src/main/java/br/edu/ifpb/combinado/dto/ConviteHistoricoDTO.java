package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.ConviteHistorico;
import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import java.time.Instant;

@io.swagger.v3.oas.annotations.media.Schema(description = "Evento cronológico imutável de convite")
public record ConviteHistoricoDTO(Long id,
        @io.swagger.v3.oas.annotations.media.Schema(description = "Tipo de evento: ENVIO, ACEITE ou RECUSA") AcaoConvite acao,
        @io.swagger.v3.oas.annotations.media.Schema(description = "ID do usuário que realizou a ação") Long autorId,
        @io.swagger.v3.oas.annotations.media.Schema(description = "Nome do autor no momento da ação") String autorNome,
        @io.swagger.v3.oas.annotations.media.Schema(description = "Data/hora do servidor em UTC", example = "2026-10-20T18:30:00Z") Instant ocorridoEm) {
    public static ConviteHistoricoDTO de(ConviteHistorico evento) {
        return new ConviteHistoricoDTO(evento.getId(), evento.getAcao(), evento.getAutor().getId(),
                evento.getAutorNome(), evento.getOcorridoEm());
    }
}
