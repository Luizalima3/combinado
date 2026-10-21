package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.repository.CombinadoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("combinadoAcesso")
public class CombinadoAcessoService {
    private final CombinadoRepository repository;

    public CombinadoAcessoService(CombinadoRepository repository) {
        this.repository = repository;
    }

    public boolean podeAcessar(Long id, Authentication authentication) {
        return id != null && authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UsuarioPrincipal principal
                && repository.temVinculo(id, principal.usuario().getId());
    }

    public boolean eCriador(Long id, Authentication authentication) {
        return id != null && authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UsuarioPrincipal principal
                && repository.existsByIdAndCriadorId(id, principal.usuario().getId());
    }
}
