package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.LoginRequestDTO;
import br.edu.ifpb.combinado.dto.LoginResponseDTO;
import br.edu.ifpb.combinado.service.AuthService;
import br.edu.ifpb.combinado.service.LogoutService;
import br.edu.ifpb.combinado.security.JwtAuthenticationFilter;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    private final LogoutService logoutService;

    public AuthController(AuthService service, LogoutService logoutService) {
        this.service = service;
        this.logoutService = logoutService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok().header(HttpHeaders.CACHE_CONTROL, "no-store").body(service.login(dto));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> credenciaisInvalidas() {
        return ResponseEntity.status(401).body(Map.of("message", "E-mail ou senha inválidos."));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestAttribute(JwtAuthenticationFilter.TOKEN_ATTRIBUTE) DecodedJWT token) {
        logoutService.revogarToken(token);
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
