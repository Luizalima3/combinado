package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Combinado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CombinadoRepository extends JpaRepository<Combinado, Long> {
    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Combinado c where c.id = :id")
    java.util.Optional<Combinado> buscarParaConvite(@Param("id") Long id);
    String VINCULO = "(c.criador.id = :usuarioId or (c.status <> br.edu.ifpb.combinado.model.enums.StatusCombinado.RASCUNHO and (p.id = :usuarioId or "
            + "exists (select v.id from Convite v where v.combinado = c and v.destinatario.id = :usuarioId))))";

    @Query("select distinct c from Combinado c left join fetch c.criador left join c.participantes p where " + VINCULO + " order by c.id desc")
    List<Combinado> listarVinculados(@Param("usuarioId") Long usuarioId);

    @Query("select c from Combinado c left join fetch c.criador order by c.id desc")
    List<Combinado> listarGeral();

    @Query("select (count(c) > 0) from Combinado c left join c.participantes p where c.id = :id and " + VINCULO)
    boolean temVinculo(@Param("id") Long id, @Param("usuarioId") Long usuarioId);

    boolean existsByIdAndCriadorId(Long id, Long criadorId);

    List<Combinado> findByCriadorIdAndStatusOrderByIdDesc(Long criadorId,
            br.edu.ifpb.combinado.model.enums.StatusCombinado status);
}
