package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {}
