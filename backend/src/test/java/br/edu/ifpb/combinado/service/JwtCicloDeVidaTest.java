package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JwtCicloDeVidaTest {

    private static final String CHAVE_SECRETA = "chave-secreta-ciclo-vida-jwt-com-32-bytes";
    private final JwtService jwtService = new JwtService(CHAVE_SECRETA, 60_000);

    @Test
    @DisplayName("Deve extrair corretamente todos os claims obrigatórios do token JWT emitido")
    void deveExtrairTodosOsClaimsObrigatoriosDoToken() {
        Usuario usuario = new Usuario("Beatriz Lima", "beatriz@combinado.app", "hash-oculto", PerfilUsuario.ROLE_USUARIO);
        usuario.setId(77L);

        Instant antes = Instant.now().minusSeconds(2);
        String token = jwtService.gerarToken(usuario);
        DecodedJWT decoded = jwtService.validarToken(token);
        Instant depois = Instant.now().plusSeconds(2);

        assertEquals("77", decoded.getSubject());
        assertEquals("beatriz@combinado.app", decoded.getClaim("email").asString());
        assertEquals("ROLE_USUARIO", decoded.getClaim("perfil").asString());
        assertEquals("combinado", decoded.getIssuer());
        assertNotNull(decoded.getId());
        assertFalse(decoded.getId().isBlank());
        assertFalse(decoded.getIssuedAtAsInstant().isBefore(antes));
        assertFalse(decoded.getIssuedAtAsInstant().isAfter(depois));
        assertEquals(60, decoded.getExpiresAtAsInstant().getEpochSecond() - decoded.getIssuedAtAsInstant().getEpochSecond());
    }

    @Test
    @DisplayName("Deve recusar token expirado e token sem claims obrigatórios na validação")
    void deveRecusarTokenExpiradoETokenSemClaimObrigatorio() {
        String tokenExpirado = com.auth0.jwt.JWT.create()
                .withIssuer("combinado")
                .withSubject("77")
                .withClaim("email", "beatriz@combinado.app")
                .withClaim("perfil", "ROLE_USUARIO")
                .withJWTId("id-expirado")
                .withIssuedAt(Instant.now().minusSeconds(120))
                .withExpiresAt(Instant.now().minusSeconds(30))
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(CHAVE_SECRETA));

        assertThrows(com.auth0.jwt.exceptions.TokenExpiredException.class,
                () -> jwtService.validarToken(tokenExpirado));

        String tokenSemPerfil = com.auth0.jwt.JWT.create()
                .withIssuer("combinado")
                .withSubject("77")
                .withClaim("email", "beatriz@combinado.app")
                .withJWTId("id-sem-perfil")
                .withIssuedAt(Instant.now().minusSeconds(10))
                .withExpiresAt(Instant.now().plusSeconds(60))
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(CHAVE_SECRETA));

        assertThrows(com.auth0.jwt.exceptions.JWTVerificationException.class,
                () -> jwtService.validarToken(tokenSemPerfil));
    }
}
