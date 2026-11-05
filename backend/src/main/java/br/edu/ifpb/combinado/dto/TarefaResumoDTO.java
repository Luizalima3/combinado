package br.edu.ifpb.combinado.dto;

import br.edu.ifpb.combinado.model.Tarefa;
import java.util.*;

public record TarefaResumoDTO(Long id, Long combinadoId, String descricao, List<UsuarioResumoDTO> responsaveis) {
    public static TarefaResumoDTO de(Tarefa tarefa) {
        var usuarios = tarefa.getResponsaveis().stream().sorted(Comparator.comparing(u -> u.getId()))
                .map(u -> new UsuarioResumoDTO(u.getId(), u.getNome(), u.getEmail())).toList();
        return new TarefaResumoDTO(tarefa.getId(), tarefa.getCombinado().getId(), tarefa.getDescricao(), usuarios);
    }
}
