package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.CombinadoHistorico;
import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface CombinadoHistoricoRepository extends JpaRepository<CombinadoHistorico, Long> {
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "autor")
    List<CombinadoHistorico> findByCombinadoIdOrderByOcorridoEmAscIdAsc(Long combinadoId);

    @Query("""
            select h from CombinadoHistorico h
            where h.combinado.id = :combinadoId
              and (:autorId is null or h.autor.id = :autorId)
              and (:tipo is null or h.acao = :tipo)
              and (:inicio is null or h.ocorridoEm >= :inicio)
              and (:fim is null or h.ocorridoEm <= :fim)
            order by h.ocorridoEm asc, h.id asc
            """)
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "autor")
    List<CombinadoHistorico> buscarPorCombinadoPeriodoAutorETipo(@Param("combinadoId") Long combinadoId,
            @Param("autorId") Long autorId, @Param("tipo") AcaoCombinadoHistorico tipo,
            @Param("inicio") Instant inicio, @Param("fim") Instant fim);
}
