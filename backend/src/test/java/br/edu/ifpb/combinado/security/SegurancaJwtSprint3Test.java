package br.edu.ifpb.combinado.security;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SegurancaJwtSprint3Test extends GestaoFixture {
    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.security.crypto.password.PasswordEncoder encoder;
    @Test void cadastroLoginJwtRotaPrivadaELogoutDevemFuncionarSemSessaoOuCsrf() throws Exception {
        String resposta = mvc.perform(post("/api/usuarios").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Conta de teste\",\"email\":\"contrato-seguranca@combinado.app\","
                        + "\"senha\":\"Segura123\",\"confirmacaoSenha\":\"Segura123\",\"perfil\":\"ROLE_ADMIN\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.perfil").value("ROLE_USUARIO"))
                .andExpect(jsonPath("$.senha").doesNotExist()).andReturn().getResponse().getContentAsString();
        Long id = ((Number) JsonPath.read(resposta, "$.id")).longValue();
        assertInstanceOf(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.class, encoder);
        var persistido = usuarios.findById(id).orElseThrow();
        assertNotEquals("Segura123", persistido.getSenha());
        assertTrue(encoder.matches("Segura123", persistido.getSenha()));
        assertFalse(encoder.matches("SenhaIncorreta", persistido.getSenha()));
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"contrato-seguranca@combinado.app\",\"senha\":\"SenhaIncorreta\"}"))
                .andExpect(status().isUnauthorized());
        String login = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"contrato-seguranca@combinado.app\",\"senha\":\"Segura123\"}"))
                .andExpect(status().isOk()).andExpect(header().string("Cache-Control", "no-store"))
                .andReturn().getResponse().getContentAsString();
        String token = JsonPath.read(login, "$.token");
        var claims = jwt.validarToken(token);
        assertEquals(id.toString(), claims.getSubject());
        assertEquals("contrato-seguranca@combinado.app", claims.getClaim("email").asString());
        assertEquals("ROLE_USUARIO", claims.getClaim("perfil").asString());
        assertNotNull(claims.getExpiresAtAsInstant());
        var consulta = mvc.perform(get("/api/combinados/criados").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk()).andReturn();
        assertNull(consulta.getRequest().getSession(false));
        mvc.perform(get("/api/combinados/criados")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/logout").header("Authorization", "Bearer " + token)).andExpect(status().isNoContent());
        mvc.perform(get("/api/combinados/criados").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }
}
