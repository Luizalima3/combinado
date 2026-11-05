package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.JwtService;
import br.edu.ifpb.combinado.service.LogoutService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_ATTRIBUTE = "combinado.jwt";
    private final JwtService jwtService;
    private final UsuarioRepository usuarios;
    private final ApiAuthenticationEntryPoint entryPoint;
    private final LogoutService logoutService;

    public JwtAuthenticationFilter(JwtService jwtService, UsuarioRepository usuarios,
                                   ApiAuthenticationEntryPoint entryPoint, LogoutService logoutService) {
        this.jwtService = jwtService;
        this.usuarios = usuarios;
        this.entryPoint = entryPoint;
        this.logoutService = logoutService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return ("POST".equals(request.getMethod())
                && ("/api/auth/login".equals(path) || "/api/usuarios".equals(path)))
                || path.equals("/swagger-ui.html") || path.startsWith("/swagger-ui/")
                || path.equals("/v3/api-docs") || path.startsWith("/v3/api-docs/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        try {
            if (!header.regionMatches(true, 0, "Bearer ", 0, 7) || header.substring(7).isBlank()) {
                throw new BadCredentialsException("Token inválido.");
            }
            var token = jwtService.validarToken(header.substring(7));
            if (logoutService.estaRevogado(token.getId())) {
                throw new BadCredentialsException("Token revogado.");
            }
            long id = Long.parseLong(token.getSubject());
            var usuario = usuarios.findById(id)
                    .orElseThrow(() -> new BadCredentialsException("Token inválido."));
            var principal = new UsuarioPrincipal(usuario);
            var authentication = UsernamePasswordAuthenticationToken.authenticated(
                    principal, null, principal.getAuthorities());
            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            request.setAttribute(TOKEN_ATTRIBUTE, token);
        } catch (JWTVerificationException | IllegalArgumentException | BadCredentialsException exception) {
            SecurityContextHolder.clearContext();
            // Não dispara entryPoint aqui para evitar respostas 401 prematuras em rotas que podem ser públicas
            // ou que tratam a ausência de autenticação de forma diferente.
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
