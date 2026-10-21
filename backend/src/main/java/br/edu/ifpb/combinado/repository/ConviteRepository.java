package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Convite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConviteRepository extends JpaRepository<Convite, Long> {
    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @org.springframework.data.jpa.repository.Query("select v from Convite v where v.id = :id")
    java.util.Optional<Convite> buscarParaResponder(@org.springframework.data.repository.query.Param("id") Long id);
    java.util.Optional<Convite> findFirstByCombinadoIdAndDestinatarioIdOrderByDataEnvioDescIdDesc(Long combinadoId, Long destinatarioId);
    boolean existsByCombinadoIdAndDestinatarioIdAndStatus(Long combinadoId, Long destinatarioId,
            br.edu.ifpb.combinado.model.enums.StatusConvite status);
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "destinatario")
    List<Convite> findByCombinadoIdOrderByDataEnvioDescIdDesc(Long combinadoId);
    List<Convite> findByDestinatarioId(Long destinatarioId);
    List<Convite> findByCombinadoId(Long combinadoId);
}
