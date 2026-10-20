package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record UsuarioPrincipal(Usuario usuario) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));
    }

    @Override
    public String getPassword() { return usuario.getSenha(); }

    @Override
    public String getUsername() { return usuario.getEmail(); }
}
