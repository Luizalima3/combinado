package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Responsabilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface ResponsabilidadeRepository extends JpaRepository<Responsabilidade, Long> {
    @EntityGraph(attributePaths = "responsaveis")
    List<Responsabilidade> findByCombinadoIdOrderByIdAsc(Long combinadoId);
}
