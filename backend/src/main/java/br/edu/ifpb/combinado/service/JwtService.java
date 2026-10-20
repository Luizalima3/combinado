package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class JwtService {

    private static final String ISSUER = "combinado";
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long expirationMillis;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration}") long expirationMillis) {
        if (secret == null || secret.isBlank()
                || secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("O segredo JWT deve conter pelo menos 32 bytes.");
        }
        if (expirationMillis < 1000) {
            throw new IllegalArgumentException("A expiração JWT deve ser de pelo menos 1000 milissegundos.");
        }
        this.expirationMillis = expirationMillis;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withClaimPresence("sub")
                .withClaimPresence("email")
                .withClaimPresence("perfil")
                .withClaimPresence("iat")
                .withClaimPresence("exp")
                .withClaimPresence("jti")
                .build();
    }

    public String gerarToken(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() <= 0
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getPerfil() == null) {
            throw new IllegalArgumentException("Usuário deve possuir ID, e-mail e perfil para gerar token.");
        }
        Instant emitidoEm = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getId().toString())
                .withClaim("email", usuario.getEmail())
                .withClaim("perfil", usuario.getPerfil().name())
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(emitidoEm)
                .withExpiresAt(emitidoEm.plusMillis(expirationMillis))
                .sign(algorithm);
    }

    /** Retorna os claims somente após verificar assinatura, emissor e expiração.
     * @throws com.auth0.jwt.exceptions.JWTVerificationException se o token for inválido
     */
    public DecodedJWT validarToken(String token) {
        return verifier.verify(token);
    }
}
