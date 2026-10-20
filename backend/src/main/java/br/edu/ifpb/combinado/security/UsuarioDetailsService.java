package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repository.findByEmail(email.trim().toLowerCase(Locale.ROOT))
                .map(UsuarioPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas."));
    }
}
