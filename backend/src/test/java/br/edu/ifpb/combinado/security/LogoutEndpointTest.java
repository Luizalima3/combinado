package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.TokenRevogadoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import br.edu.ifpb.combinado.service.LogoutService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:protecao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false",
        "jwt.secret=segredo-de-testes-protecao-com-pelo-menos-32-bytes"
})
class LogoutEndpointTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired TokenRevogadoRepository revogados;
    @Autowired PasswordEncoder encoder;
    @Autowired JwtService jwt;
    @Autowired JdbcTemplate jdbc;
    private MockMvc mvc;
    private Usuario usuario;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        usuario = usuarios.findByEmail("logout@combinado.app").orElseGet(() -> usuarios.save(
                new Usuario("Logout", "logout@combinado.app", encoder.encode("senha123"), PerfilUsuario.USUARIO)));
    }

    private String login() throws Exception {
        String resposta = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"logout@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        return JsonPath.read(resposta, "$.token");
    }

    @Test
    void deveExecutarLoginAcessoLogoutEBloquearReutilizacao() throws Exception {
        String token = login();
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + token)).andExpect(status().isNoContent());
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + token)).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + token)).andExpect(status().isUnauthorized());
        String novo = login();
        assertNotEquals(token, novo);
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + novo)).andExpect(status().isOk());
    }

    @Test
    void devePersistirAutorHorarioEExpiracaoSemArmazenarTokenCompleto() throws Exception {
        String token = jwt.gerarToken(usuario);
        var claims = jwt.validarToken(token);
        Instant inicio = Instant.now().minusSeconds(1);
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"usuarioId\":9999}"))
                .andExpect(status().isNoContent());
        var registro = revogados.findById(claims.getId()).orElseThrow();
        assertEquals(usuario.getId(), registro.getUsuarioId());
        assertEquals(claims.getExpiresAtAsInstant(), registro.getExpiraEm());
        assertFalse(registro.getEncerradoEm().isBefore(inicio));
        assertFalse(registro.getEncerradoEm().isAfter(Instant.now()));
        assertEquals(1, jdbc.queryForObject("SELECT COUNT(*) FROM tokens_revogados WHERE jti = ?", Integer.class, claims.getId()));
        assertNotEquals(token, registro.getJti());
        // Uma nova instância consulta o registro persistido, sem depender de cache em memória.
        assertTrue(new LogoutService(revogados).estaRevogado(claims.getId()));
    }

    @Test
    void naoDeveRevogarOutrasSessoesDoMesmoUsuario() throws Exception {
        String primeiro = jwt.gerarToken(usuario);
        String segundo = jwt.gerarToken(usuario);
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + primeiro)).andExpect(status().isNoContent());
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + segundo)).andExpect(status().isOk());
        assertFalse(revogados.existsById(jwt.validarToken(segundo).getId()));
    }

    @Test
    void naoDeveRevogarTokensDeOutroUsuario() throws Exception {
        Usuario outro = usuarios.findByEmail("outro-logout@combinado.app").orElseGet(() -> usuarios.save(
                new Usuario("Outro", "outro-logout@combinado.app", encoder.encode("senha123"), PerfilUsuario.USUARIO)));
        String tokenOutro = jwt.gerarToken(outro);
        String token = jwt.gerarToken(usuario);
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + token)).andExpect(status().isNoContent());
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + tokenOutro)).andExpect(status().isOk());
    }

    @Test
    void deveExigirTokenValidoParaRegistrarLogout() throws Exception {
        long antes = revogados.count();
        mvc.perform(post("/api/auth/logout")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer invalido")).andExpect(status().isUnauthorized());
        assertEquals(antes, revogados.count());
    }

    @Test
    void logoutDeveExigirMetodoPost() throws Exception {
        String token = jwt.gerarToken(usuario);
        mvc.perform(get("/api/auth/logout").header("Authorization", "Bearer " + token))
                .andExpect(status().isMethodNotAllowed());
        assertFalse(revogados.existsById(jwt.validarToken(token).getId()));
    }
}
