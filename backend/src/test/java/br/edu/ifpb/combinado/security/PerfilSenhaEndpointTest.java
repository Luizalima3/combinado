package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:perfil-edicao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
class PerfilSenhaEndpointTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired JwtService jwt;
    @Autowired PasswordEncoder encoder;
    @Autowired EntityManager entityManager;
    private MockMvc mvc;
    private Usuario usuario;
    private String bearer;
    private String hashOriginal;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        hashOriginal = encoder.encode("senha123");
        usuario = usuarios.saveAndFlush(new Usuario("Perfil senha", "senha@combinado.app", hashOriginal, PerfilUsuario.ROLE_USUARIO));
        bearer = "Bearer " + jwt.gerarToken(usuario);
    }

    @Test
    void deveTrocarSenhaPersistirHashEAutenticarApenasComNovaSenha() throws Exception {
        Long id = usuario.getId();
        mvc.perform(put("/api/usuarios/perfil/senha").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senhaAtual\":\"senha123\",\"novaSenha\":\"novaSenha456\",\"confirmacaoSenha\":\"novaSenha456\"}"))
                .andExpect(status().isNoContent()).andExpect(content().string(""));
        entityManager.clear();
        var salvo = usuarios.findById(id).orElseThrow();
        assertNotEquals("novaSenha456", salvo.getSenha());
        assertNotEquals(hashOriginal, salvo.getSenha());
        assertTrue(encoder.matches("novaSenha456", salvo.getSenha()));
        assertFalse(encoder.matches("senha123", salvo.getSenha()));
        assertEquals("Perfil senha", salvo.getNome());
        assertEquals(PerfilUsuario.ROLE_USUARIO, salvo.getPerfil());
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"senha@combinado.app\",\"senha\":\"novaSenha456\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.token").isString());
        // O login com senha incorreta é testado em requisição separada para não marcar
        // a transação de teste como rollback-only antes das demais verificações.
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"senha@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void senhaAtualIncorretaDeveSerRecusadaSemModificarHash() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/senha").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senhaAtual\":\"incorreta\",\"novaSenha\":\"novaSenha456\",\"confirmacaoSenha\":\"novaSenha456\"}"))
                .andExpect(status().isBadRequest());
        entityManager.clear();
        assertEquals(hashOriginal, usuarios.findById(usuario.getId()).orElseThrow().getSenha());
    }

    @Test
    void novaSenhaFracaDeveSerRecusadaSemModificarHash() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/senha").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senhaAtual\":\"senha123\",\"novaSenha\":\"123\",\"confirmacaoSenha\":\"123\"}"))
                .andExpect(status().isBadRequest());
        entityManager.clear();
        assertEquals(hashOriginal, usuarios.findById(usuario.getId()).orElseThrow().getSenha());
    }

    @Test
    void deveExigirAutenticacaoParaTrocarSenha() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/senha").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senhaAtual\":\"senha123\",\"novaSenha\":\"novaSenha456\",\"confirmacaoSenha\":\"novaSenha456\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void naoDeveAlterarOutroUsuarioPorIdEnviadoNoJson() throws Exception {
        var outro = usuarios.saveAndFlush(new Usuario("Outra pessoa", "outra-senha@combinado.app", encoder.encode("outraSenha"), PerfilUsuario.ROLE_USUARIO));
        String hashOutro = outro.getSenha();
        mvc.perform(put("/api/usuarios/perfil/senha").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":" + outro.getId() + ",\"senhaAtual\":\"senha123\",\"novaSenha\":\"novaSenha456\",\"confirmacaoSenha\":\"novaSenha456\"}"))
                .andExpect(status().isNoContent());
        entityManager.clear();
        assertEquals(hashOutro, usuarios.findById(outro.getId()).orElseThrow().getSenha());
    }
}
