package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.enums.*;
import java.time.*;

public record ConviteResumoDTO(Long id, Long combinadoId, String titulo, CategoriaCombinado categoria,
        String nomeCriador, LocalDate prazo, StatusCombinado statusCombinado, StatusConvite status,
        LocalDateTime dataEnvio, Instant dataResposta) {
    public static ConviteResumoDTO de(Convite v) {
        var c = v.getCombinado();
        return new ConviteResumoDTO(v.getId(), c.getId(), c.getTitulo(), c.getCategoria(),
                c.getCriador() == null ? c.getCriadorNome() : c.getCriador().getNome(), c.getDataPrazo(),
                c.getStatus(), v.getStatus(), v.getDataEnvio(), v.getDataResposta());
    }
}
