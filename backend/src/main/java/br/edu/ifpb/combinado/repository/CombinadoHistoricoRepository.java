package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CombinadoHistoricoRepository extends JpaRepository<CombinadoHistorico, Long> {
    List<CombinadoHistorico> findByCombinadoIdOrderByOcorridoEmAscIdAsc(Long combinadoId);
}