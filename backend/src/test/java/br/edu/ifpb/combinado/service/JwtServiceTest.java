package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    private static final String SECRET = "segredo-de-testes-com-pelo-menos-32-bytes";
    private final JwtService service = new JwtService(SECRET, 3_600_000);

    private Usuario usuario() {
        Usuario usuario = new Usuario("Ana Souza", "ana@combinado.app", "senha-privada", PerfilUsuario.ROLE_USUARIO);
        usuario.setId(42L);
        return usuario;
    }

    @Test
    void deveGerarTokenAssinadoComIdentidadeEExpiracaoConfigurada() {
        DecodedJWT token = service.validarToken(service.gerarToken(usuario()));
        assertEquals("HS256", token.getAlgorithm());
        assertEquals("combinado", token.getIssuer());
        assertEquals("42", token.getSubject());
        assertEquals("ana@combinado.app", token.getClaim("email").asString());
        assertEquals("ROLE_USUARIO", token.getClaim("perfil").asString());
        assertEquals(3600, token.getExpiresAtAsInstant().getEpochSecond()
                - token.getIssuedAtAsInstant().getEpochSecond());
        assertNotNull(token.getId());
        assertFalse(token.getClaims().containsKey("senha"));
    }

    @Test
    void deveGerarIdentificadoresDistintosParaCadaToken() {
        assertNotEquals(service.validarToken(service.gerarToken(usuario())).getId(),
                service.validarToken(service.gerarToken(usuario())).getId());
    }

    @Test
    void devePreservarPerfilAdministrador() {
        Usuario usuario = usuario();
        usuario.setPerfil(PerfilUsuario.ROLE_ADMIN);
        assertEquals("ROLE_ADMIN", service.validarToken(service.gerarToken(usuario)).getClaim("perfil").asString());
    }

    @Test
    void deveRejeitarAssinaturaDeOutraChave() {
        String token = new JwtService("outro-segredo-de-testes-com-32-bytes", 3_600_000).gerarToken(usuario());
        assertThrows(JWTVerificationException.class, () -> service.validarToken(token));
    }

    @Test
    void deveRejeitarPayloadAdulterado() {
        String token = service.gerarToken(usuario());
        String[] partes = token.split("\\.");
        String payload = new String(java.util.Base64.getUrlDecoder().decode(partes[1]),
                java.nio.charset.StandardCharsets.UTF_8);
        String adulterado = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(
                payload.replace("USUARIO", "ADMIN").getBytes(java.nio.charset.StandardCharsets.UTF_8));
        String tokenAdulterado = partes[0] + "." + adulterado + "." + partes[2];
        assertThrows(JWTVerificationException.class, () -> service.validarToken(tokenAdulterado));
    }

    private String tokenAssinado(String issuer, Instant expiresAt) {
        return JWT.create().withIssuer(issuer).withSubject("42")
                .withClaim("email", "ana@combinado.app").withClaim("perfil", "USUARIO")
                .withJWTId("teste").withIssuedAt(Instant.now().minusSeconds(120))
                .withExpiresAt(expiresAt).sign(Algorithm.HMAC256(SECRET));
    }

    @Test
    void deveRejeitarTokenExpirado() {
        String token = tokenAssinado("combinado", Instant.now().minusSeconds(60));
        assertThrows(TokenExpiredException.class, () -> service.validarToken(token));
    }

    @Test
    void deveRejeitarEmissorDiferente() {
        String token = tokenAssinado("outro-sistema", Instant.now().plusSeconds(60));
        assertThrows(JWTVerificationException.class, () -> service.validarToken(token));
    }

    @Test
    void deveRejeitarTokenSemExpiracao() {
        String token = JWT.create().withIssuer("combinado").withSubject("42")
                .withClaim("email", "ana@combinado.app").withClaim("perfil", "USUARIO")
                .withJWTId("teste").withIssuedAt(Instant.now()).sign(Algorithm.HMAC256(SECRET));
        assertThrows(JWTVerificationException.class, () -> service.validarToken(token));
    }

    @Test
    void deveRejeitarTokenMalformado() {
        assertThrows(JWTVerificationException.class, () -> service.validarToken("token-invalido"));
    }

    @Test
    void deveRejeitarUsuarioSemIdentidadePersistida() {
        Usuario usuario = usuario();
        usuario.setId(null);
        assertThrows(IllegalArgumentException.class, () -> service.gerarToken(usuario));
    }

    @Test
    void deveRejeitarConfiguracaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new JwtService("", 3600));
        assertThrows(IllegalArgumentException.class, () -> new JwtService("curto", 3600));
        assertThrows(IllegalArgumentException.class, () -> new JwtService(SECRET, 0));
        assertThrows(IllegalArgumentException.class, () -> new JwtService(SECRET, -1));
    }
}
