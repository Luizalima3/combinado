package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:gestao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
class UsuarioBuscaEndpointTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired JwtService jwt;
    private MockMvc mvc;
    private String bearer;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        Usuario autor = usuarios.saveAndFlush(new Usuario("Autor", "autor-busca@combinado.app", "hash-privado", PerfilUsuario.ROLE_USUARIO));
        bearer = "Bearer " + jwt.gerarToken(autor);
        usuarios.saveAndFlush(new Usuario("Ana Maria", "ana-busca@combinado.app", "hash-privado", PerfilUsuario.ROLE_USUARIO));
        usuarios.saveAndFlush(new Usuario("Mariana", "mariana-busca@combinado.app", "hash-privado", PerfilUsuario.ROLE_USUARIO));
    }

    @Test
    void deveBuscarTrechoDoNomeSemDiferenciarMaiusculasERetornarResumo() throws Exception {
        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "  ANA  "))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].nome", contains("Ana Maria", "Mariana")))
                .andExpect(jsonPath("$[0].id").isNumber()).andExpect(jsonPath("$[0].email").isString())
                .andExpect(jsonPath("$[0].senha").doesNotExist()).andExpect(jsonPath("$[0].perfil").doesNotExist());
    }

    @Test
    void deveExcluirUsuarioLogadoMesmoQuandoSeuNomeCorrespondeAoTermo() throws Exception {
        Usuario atual = usuarios.findByEmail("autor-busca@combinado.app").orElseThrow();
        atual.setNome("Ana Autor");
        usuarios.saveAndFlush(atual);

        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nome", contains("Ana Maria", "Mariana")))
                .andExpect(jsonPath("$[*].id", not(hasItem(atual.getId().intValue()))));
    }

    @Test
    void devePaginarResultadosSemRepetirPrimeiraPagina() throws Exception {
        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "ana").param("limite", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].nome", contains("Ana Maria")));
        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "ana").param("limite", "1").param("pagina", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].nome", contains("Mariana")));
    }

    @Test
    void deveTratarCuringasComoTextoLiteral() throws Exception {
        usuarios.saveAndFlush(new Usuario("Nome_% especial", "especial-busca@combinado.app", "hash-privado", PerfilUsuario.ROLE_USUARIO));
        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "_%"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].nome", contains("Nome_% especial")));
    }

    @Test
    void termoCurtoOuSemCorrespondenciasDeveRetornarListaVazia() throws Exception {
        for (String termo : new String[]{"", "a", "zzzz"}) {
            mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", termo))
                    .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        }
    }

    @Test
    void deveRejeitarPaginacaoInvalida() throws Exception {
        for (String limite : new String[]{"0", "51", "-1"}) {
            mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "ana").param("limite", limite))
                    .andExpect(status().isBadRequest());
        }
        mvc.perform(get("/api/usuarios/buscar").header("Authorization", bearer).param("q", "ana").param("pagina", "-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveExigirAutenticacao() throws Exception {
        mvc.perform(get("/api/usuarios/buscar").param("q", "ana")).andExpect(status().isUnauthorized());
    }
}
