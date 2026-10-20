package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.TokenRevogado;
import br.edu.ifpb.combinado.repository.TokenRevogadoRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class LogoutService {
    private final TokenRevogadoRepository repository;

    public LogoutService(TokenRevogadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void revogarToken(DecodedJWT tokenVerificado) {
        if (!repository.existsById(tokenVerificado.getId())) {
            repository.save(new TokenRevogado(tokenVerificado.getId(), Long.valueOf(tokenVerificado.getSubject()),
                    Instant.now(), tokenVerificado.getExpiresAtAsInstant()));
        }
    }

    @Transactional(readOnly = true)
    public boolean estaRevogado(String jti) {
        return repository.existsById(jti);
    }
}
