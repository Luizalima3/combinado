package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.LoginRequestDTO;
import br.edu.ifpb.combinado.dto.LoginResponseDTO;
import br.edu.ifpb.combinado.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok().header(HttpHeaders.CACHE_CONTROL, "no-store").body(service.login(dto));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> credenciaisInvalidas() {
        return ResponseEntity.status(401).body(Map.of("message", "E-mail ou senha inválidos."));
    }
}
