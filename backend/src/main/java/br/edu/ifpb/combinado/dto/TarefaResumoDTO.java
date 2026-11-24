package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Responsabilidade;
import java.util.*;

public record TarefaResumoDTO(Long id, Long combinadoId, String descricao, List<UsuarioResumoDTO> responsaveis) {
    public static TarefaResumoDTO de(Responsabilidade responsabilidade) {
        var usuarios = responsabilidade.getResponsaveis().stream().sorted(Comparator.comparing(u -> u.getId()))
                .map(u -> new UsuarioResumoDTO(u.getId(), u.getNome(), u.getEmail())).toList();
        return new TarefaResumoDTO(responsabilidade.getId(), responsabilidade.getCombinado().getId(),
                responsabilidade.getDescricao(), usuarios);
    }
}
