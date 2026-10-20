package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:perfis;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
class PerfilPersistenciaTest {
    @Autowired UsuarioRepository usuarios;
    @Autowired EntityManager entityManager;

    @Test
    void devePersistirPerfisComoTextoEMapearAutoridadesSemDuplicarPrefixo() {
        for (PerfilUsuario perfil : PerfilUsuario.values()) {
            Usuario usuario = usuarios.saveAndFlush(new Usuario("Perfil", perfil.name() + "@combinado.app", "hash-teste", perfil));
            Long id = usuario.getId();
            entityManager.clear();
            Usuario recarregado = usuarios.findById(id).orElseThrow();
            assertEquals(perfil, recarregado.getPerfil());
            assertEquals(perfil.name(), entityManager.createNativeQuery("SELECT perfil FROM tb_usuarios WHERE id = :id")
                    .setParameter("id", id).getSingleResult());
            assertEquals(List.of(new SimpleGrantedAuthority(perfil.name())), new UsuarioPrincipal(recarregado).getAuthorities());
        }
    }

    @Test
    void deveUsarPerfilUsuarioComoPadrao() {
        assertEquals(PerfilUsuario.ROLE_USUARIO, new Usuario().getPerfil());
        assertEquals(PerfilUsuario.ROLE_USUARIO, new Usuario("Padrao", "padrao@combinado.app", "hash-teste", null).getPerfil());
    }
}
