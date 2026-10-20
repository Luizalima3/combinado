package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Convite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConviteRepository extends JpaRepository<Convite, Long> {
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "destinatario")
    List<Convite> findByCombinadoIdOrderByDataEnvioDescIdDesc(Long combinadoId);
    List<Convite> findByDestinatarioId(Long destinatarioId);
    List<Convite> findByCombinadoId(Long combinadoId);
}
