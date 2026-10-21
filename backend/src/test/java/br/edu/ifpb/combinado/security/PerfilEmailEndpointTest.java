package br.edu.ifpb.combinado.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PerfilEmailEndpointTest extends GestaoFixture {
    @Autowired PasswordEncoder encoder;
    private void senha() { criador.setSenha(encoder.encode("senha123")); usuarios.saveAndFlush(criador); }
    @Test void deveAlterarEmailNormalizarEPersistirSomenteNoUsuarioAutenticado() throws Exception {
        senha();
        String senhaOriginal = criador.getSenha();
        mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"  Novo.Email@Combinado.APP  \",\"senhaAtual\":\"senha123\",\"id\":" + participante.getId() + "}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.email").value("novo.email@combinado.app"))
                .andExpect(jsonPath("$.senha").doesNotExist());
        em.clear();
        var salvo = usuarios.findById(criador.getId()).orElseThrow();
        assertEquals("novo.email@combinado.app", salvo.getEmail());
        assertEquals(senhaOriginal, salvo.getSenha());
        assertEquals(participante.getEmail(), usuarios.findById(participante.getId()).orElseThrow().getEmail());
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"novo.email@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.token").isString());
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"criador-gestao@combinado.app\",\"senha\":\"senha123\"}"))
                .andExpect(status().isUnauthorized());
    }
    @Test void deveRejeitarSenhaIncorretaSemAlterarEmail() throws Exception {
        senha();
        mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"novo@combinado.app\",\"senhaAtual\":\"incorreta\"}"))
                .andExpect(status().isBadRequest());
        assertEquals("criador-gestao@combinado.app", criador.getEmail());
    }
    @Test void deveRejeitarEmailEmUsoMesmoComDiferencaDeCaixa() throws Exception {
        senha();
        participante.setEmail("Existente@Combinado.APP");
        usuarios.saveAndFlush(participante);
        mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"EXISTENTE@COMBINADO.APP\",\"senhaAtual\":\"senha123\"}"))
                .andExpect(status().isConflict());
        assertEquals("criador-gestao@combinado.app", criador.getEmail());
    }
    @Test void devePermitirManterOProprioEmail() throws Exception {
        senha();
        mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"CRIADOR-GESTAO@COMBINADO.APP\",\"senhaAtual\":\"senha123\"}"))
                .andExpect(status().isOk());
    }
    @Test void deveRejeitarFormatoInvalidoVazioLongoOuSemSenha() throws Exception {
        senha();
        for (String email : new String[]{"", "sem-arroba", "a@@b.com", "a..b@c.com", "a@b", "a".repeat(151) + "@b.com"}) {
            mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                    .content("{\"email\":\"" + email + "\",\"senhaAtual\":\"senha123\"}"))
                    .andExpect(status().isBadRequest());
        }
        mvc.perform(put("/api/usuarios/perfil/email").header("Authorization", bearer).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"novo@combinado.app\"}" )).andExpect(status().isBadRequest());
        assertEquals("criador-gestao@combinado.app", criador.getEmail());
    }
    @Test void deveExigirAutenticacao() throws Exception {
        mvc.perform(put("/api/usuarios/perfil/email").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"novo@combinado.app\",\"senhaAtual\":\"senha123\"}"))
                .andExpect(status().isUnauthorized());
    }
}
