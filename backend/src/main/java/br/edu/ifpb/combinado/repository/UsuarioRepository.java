package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.combinado.dto.UsuarioResumoDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    List<UsuarioResumoDTO> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    List<UsuarioResumoDTO> findByNomeContainingIgnoreCaseAndIdNot(String nome, Long id, Pageable pageable);

    @Query("""
            select new br.edu.ifpb.combinado.dto.UsuarioResumoDTO(u.id, u.nome, u.email)
            from Usuario u
            where u.id <> :usuarioId
              and (
                lower(u.nome) like lower(concat('%', replace(replace(replace(:termo, '!', '!!'), '%', '!%'), '_', '!_'), '%')) escape '!'
                or lower(u.email) like lower(concat('%', replace(replace(replace(:termo, '!', '!!'), '%', '!%'), '_', '!_'), '%')) escape '!'
              )
            """)
    List<UsuarioResumoDTO> buscarPorNomeOuEmail(@Param("termo") String termo,
            @Param("usuarioId") Long usuarioId, Pageable pageable);
}
