package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Combinado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinadoRepository extends JpaRepository<Combinado, Long> {
}