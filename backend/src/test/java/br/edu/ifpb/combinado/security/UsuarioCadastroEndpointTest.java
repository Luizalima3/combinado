package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:cadastro-endpoint;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.jpa.show-sql=false"
})
@Transactional
class UsuarioCadastroEndpointTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @DisplayName("Deve retornar 201 Created ao cadastrar usuário com dados válidos pelo endpoint")
    void deveRetornar201AoCadastrarUsuarioValido() throws Exception {
        String payload = """
                {
                  "nome": "Felipe Macedo",
                  "email": "felipe.cadastro@combinado.app",
                  "senha": "senhaSegura123",
                  "confirmacaoSenha": "senhaSegura123"
                }
                """;

        mvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Felipe Macedo"))
                .andExpect(jsonPath("$.email").value("felipe.cadastro@combinado.app"))
                .andExpect(jsonPath("$.perfil").value("ROLE_USUARIO"))
                .andExpect(jsonPath("$.senha").doesNotExist());

        assertTrue(usuarioRepository.existsByEmail("felipe.cadastro@combinado.app"));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request e não persistir usuário quando dados obrigatórios são inválidos")
    void deveRetornar400QuandoDadosObrigatoriosSaoInvalidos() throws Exception {
        String payloadInvalido = """
                {
                  "nome": "   ",
                  "email": "invalido.sem.arroba",
                  "senha": "123",
                  "confirmacaoSenha": "456"
                }
                """;

        mvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadInvalido))
                .andExpect(status().isBadRequest());

        assertFalse(usuarioRepository.existsByEmail("invalido.sem.arroba"));
    }
}
