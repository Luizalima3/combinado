package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import java.time.LocalDate;
import java.util.List;

public record CombinadoDTO(
        Long id,
        String titulo,
        String descricao,
        CategoriaCombinado categoria,
        LocalDate dataPrazo,
        String criadorNome,
        String participanteNome,
        String responsabilidades,
        StatusCombinado status,
        List<Long> participantesIds
) {
    public boolean isTituloValido() {
        return titulo != null && !titulo.trim().isBlank();
    }

    public boolean isCategoriaValida() {
        return categoria != null;
    }

    public boolean isCriadorValido() {
        return criadorNome != null && !criadorNome.trim().isBlank();
    }

    public boolean isParticipanteValido() {
        return (participanteNome != null && !participanteNome.trim().isBlank())
                || (participantesIds != null && !participantesIds.isEmpty());
    }

    public boolean isPrazoValido() {
        return dataPrazo != null && !dataPrazo.isBefore(LocalDate.now());
    }
}