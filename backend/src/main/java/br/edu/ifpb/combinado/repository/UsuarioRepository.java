package br.edu.ifpb.combinado.repository;

import br.edu.ifpb.combinado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.combinado.dto.UsuarioResumoDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<UsuarioResumoDTO> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
