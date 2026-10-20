package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.AtualizarNomeDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.service.UsuarioPerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios/perfil")
public class UsuarioPerfilController {
    private final UsuarioPerfilService service;

    public UsuarioPerfilController(UsuarioPerfilService service) {
        this.service = service;
    }

    @PutMapping("/nome")
    public ResponseEntity<UsuarioResponseDTO> atualizarNome(@Valid @RequestBody AtualizarNomeDTO dto) {
        return ResponseEntity.ok(service.atualizarNome(dto));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacaoInvalida(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("message", message == null ? "Dados inválidos." : message));
    }
}
