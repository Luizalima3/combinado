package br.edu.ifpb.combinado.dto;

import java.util.List;

public record TarefaInputDTO(String descricao, Long usuarioId, List<Long> responsaveisIds) {}
