package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:perfil-edicao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
class PerfilNomeEndpointTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired JwtService jwt;
    @Autowired EntityManager entityManager;
    private MockMvc mvc;
    private Usuario usuario;
    private String bearer;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        usuario = usuarios.saveAndFlush(new Usuario("Nome original", "perfil@combinado.app", "hash-original", PerfilUsuario.ROLE_USUARIO));
        bearer = "Bearer " + jwt.gerarToken(usuario);
    }

    @Test
    void deveAtualizarNomePersistirERetornarSomenteDadosPublicos() throws Exception {
        Long id = usuario.getId();
        mvc.perform(put("/api/usuarios/perfil/nome").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"nome\":\"  José da Silva  \"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id.intValue()))
                .andExpect(jsonPath("$.nome").value("José da Silva"))
                .andExpect(jsonPath("$.senha").doesNotExist());
        entityManager.clear();
        var salvo = usuarios.findById(id).orElseThrow();
        assertEquals("José da Silva", salvo.getNome());
        assertEquals("perfil@combinado.app", salvo.getEmail());
        assertEquals("hash-original", salvo.getSenha());
        assertEquals(PerfilUsuario.ROLE_USUARIO, salvo.getPerfil());
        assertNotNull(salvo.getDataAtualizacao());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "A"})
    void deveRejeitarNomeInvalidoSemAlterarBanco(String nome) throws Exception {
        String body = nome == null ? "{}" : "{\"nome\":\"" + nome + "\"}";
        mvc.perform(put("/api/usuarios/perfil/nome").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());
        entityManager.clear();
        assertEquals("Nome original", usuarios.findById(usuario.getId()).orElseThrow().getNome());
    }

    @Test
    void deveRejeitarNomeAcimaDoLimite() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/nome").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"nome\":\"" + "A".repeat(151) + "\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveIgnorarIdEmailEPerfilEnviadosParaAlterarOutroUsuario() throws Exception {
        var outro = usuarios.saveAndFlush(new Usuario("Outra pessoa", "outra@combinado.app", "outro-hash", PerfilUsuario.ROLE_USUARIO));
        mvc.perform(put("/api/usuarios/perfil/nome").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON).content("{\"nome\":\"Novo nome\",\"id\":" + outro.getId()
                                + ",\"email\":\"forjado@combinado.app\",\"perfil\":\"ROLE_ADMIN\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(usuario.getId().intValue()))
                .andExpect(jsonPath("$.perfil").value("ROLE_USUARIO"));
        entityManager.clear();
        assertEquals("Outra pessoa", usuarios.findById(outro.getId()).orElseThrow().getNome());
        assertEquals("perfil@combinado.app", usuarios.findById(usuario.getId()).orElseThrow().getEmail());
    }

    @Test
    void deveExigirAutenticacao() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/nome").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo nome\"}"))
                .andExpect(status().isUnauthorized());
    }
}
