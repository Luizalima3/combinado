package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:login;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.flyway.enabled=true", "spring.jpa.show-sql=false"
})
class LoginEndpointTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository repository;
    @Autowired PasswordEncoder encoder;
    @Autowired org.flywaydb.core.Flyway flyway;
    private MockMvc mvc;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        if (!repository.existsByEmail("login@combinado.app")) {
            repository.save(new Usuario("Login", "login@combinado.app", encoder.encode("senha123"), PerfilUsuario.USUARIO));
        }
    }

    @Test
    void deveExecutarMigracoesAutomaticamente() {
        org.junit.jupiter.api.Assertions.assertTrue(java.util.Arrays.stream(flyway.info().applied())
                .anyMatch(migration -> "2".equals(migration.getVersion().getVersion())));
    }

    @Test
    void deveRetornarTokenSemExporSenha() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"login@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.tipo").value("Bearer"))
                .andExpect(jsonPath("$.usuario.senha").doesNotExist())
                .andExpect(header().string("Cache-Control", "no-store"));
    }

    @Test
    void deveRetornar401ParaCredenciaisIncorretas() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"login@combinado.app\",\"senha\":\"incorreta\"}"))
                .andExpect(status().isUnauthorized()).andExpect(jsonPath("$.message").value("E-mail ou senha inválidos."));
    }

    @Test
    void deveRetornar400ParaEntradaInvalida() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"invalido\",\"senha\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
