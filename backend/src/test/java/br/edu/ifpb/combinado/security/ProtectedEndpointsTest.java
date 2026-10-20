package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:protecao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false",
        "jwt.secret=segredo-de-testes-protecao-com-pelo-menos-32-bytes"
})
class ProtectedEndpointsTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired PasswordEncoder encoder;
    @Autowired JwtService jwt;
    private MockMvc mvc;
    private Usuario usuario;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        usuario = usuarios.findByEmail("protecao@combinado.app").orElseGet(() -> usuarios.save(
                new Usuario("Protecao", "protecao@combinado.app", encoder.encode("senha123"), PerfilUsuario.ROLE_USUARIO)));
    }

    @Test
    void deveExigirTokenNasRotasPrivadas() throws Exception {
        for (String path : new String[]{"/api/combinados", "/api/convites/1", "/api/usuarios/1", "/api/auth/logout"}) {
            mvc.perform(get(path)).andExpect(status().isUnauthorized())
                    .andExpect(header().string("WWW-Authenticate", "Bearer"));
        }
        mvc.perform(post("/api/combinados").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void devePermitirConsultaComTokenValidoSemCriarSessao() throws Exception {
        var resultado = mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + jwt.gerarToken(usuario)))
                .andExpect(status().isOk()).andReturn();
        assertNull(resultado.getRequest().getSession(false));
        mvc.perform(get("/api/combinados")).andExpect(status().isUnauthorized());
    }

    @Test
    void deveRejeitarTokenMalformadoOuOutroEsquema() throws Exception {
        for (String header : new String[]{"Bearer invalido", "Bearer ", "Basic dXNlcjpwYXNz"}) {
            mvc.perform(get("/api/combinados").header("Authorization", header)).andExpect(status().isUnauthorized());
        }
    }

    @Test
    void deveRejeitarAssinaturaIncorreta() throws Exception {
        String token = new JwtService("outra-chave-de-testes-com-mais-de-32-bytes", 3600000).gerarToken(usuario);
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deveRejeitarTokenExpirado() throws Exception {
        String token = JWT.create().withIssuer("combinado").withSubject(usuario.getId().toString())
                .withClaim("email", usuario.getEmail()).withClaim("perfil", "USUARIO").withJWTId("expirado")
                .withIssuedAt(Instant.now().minusSeconds(120)).withExpiresAt(Instant.now().minusSeconds(60))
                .sign(Algorithm.HMAC256("segredo-de-testes-protecao-com-pelo-menos-32-bytes"));
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deveRejeitarTokenDeUsuarioExcluido() throws Exception {
        Usuario removido = usuarios.save(new Usuario("Removido", "removido@combinado.app",
                encoder.encode("senha123"), PerfilUsuario.ROLE_USUARIO));
        String token = jwt.gerarToken(removido);
        usuarios.deleteById(removido.getId());
        mvc.perform(get("/api/combinados").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void naoDeveAutenticarAPartirDeSessaoHttp() throws Exception {
        var securityContext = SecurityContextHolder.createEmptyContext();
        var principal = new UsuarioPrincipal(usuario);
        securityContext.setAuthentication(UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities()));
        var session = new MockHttpSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        mvc.perform(get("/api/combinados").session(session)).andExpect(status().isUnauthorized());
    }

    @Test
    void cadastroELoginDevemContinuarPublicosMesmoComTokenAntigo() throws Exception {
        mvc.perform(post("/api/auth/login").header("Authorization", "Bearer invalido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"protecao@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isOk());
        mvc.perform(post("/api/usuarios").header("Authorization", "Bearer invalido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo\",\"email\":\"novo@combinado.app\",\"senha\":\"senha123\",\"confirmacaoSenha\":\"senha123\"}"))
                .andExpect(status().isCreated());
    }
}
