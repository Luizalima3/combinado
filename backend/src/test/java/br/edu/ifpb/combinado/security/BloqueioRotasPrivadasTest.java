package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:bloqueio-rotas;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.jpa.show-sql=false"
})
class BloqueioRotasPrivadasTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @DisplayName("Deve bloquear com 401 Unauthorized o acesso a rotas privadas de combinados, rascunhos e perfil sem token")
    void deveBloquearRotasPrivadasSemCredenciais() throws Exception {
        mvc.perform(get("/api/combinados/meus-rascunhos"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("WWW-Authenticate", "Bearer"));

        mvc.perform(get("/api/usuarios/buscar").param("q", "ana"))
                .andExpect(status().isUnauthorized());

        mvc.perform(put("/api/usuarios/perfil/nome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Nome\"}"))
                .andExpect(status().isUnauthorized());

        mvc.perform(put("/api/usuarios/perfil/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"novoEmail\":\"novo@combinado.app\",\"senhaAtual\":\"123456\"}"))
                .andExpect(status().isUnauthorized());

        mvc.perform(put("/api/usuarios/perfil/senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senhaAtual\":\"123456\",\"novaSenha\":\"654321\",\"confirmacaoNovaSenha\":\"654321\"}"))
                .andExpect(status().isUnauthorized());

        mvc.perform(patch("/api/convites/1/aceitar"))
                .andExpect(status().isUnauthorized());

        mvc.perform(patch("/api/convites/1/recusar"))
                .andExpect(status().isUnauthorized());
    }
}
