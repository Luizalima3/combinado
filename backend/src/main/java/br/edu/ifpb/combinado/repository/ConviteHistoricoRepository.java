package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.ConviteHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConviteHistoricoRepository extends JpaRepository<ConviteHistorico, Long> {
    List<ConviteHistorico> findByConviteIdOrderByOcorridoEmAscIdAsc(Long conviteId);
}
