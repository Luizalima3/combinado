package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Participante;
import br.edu.ifpb.combinado.model.ParticipanteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, ParticipanteId> {
    List<Participante> findByIdCombinadoId(Long combinadoId);
    List<Participante> findByIdUsuarioId(Long usuarioId);
    boolean existsByIdCombinadoIdAndIdUsuarioId(Long combinadoId, Long usuarioId);
}
