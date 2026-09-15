package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO salvo = usuarioService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
        }
    }

    @GetMapping("/buscar-email")
    public ResponseEntity<?> buscarPorEmail(
            @RequestParam("email") String email,
            @RequestParam(value = "usuarioAtualId", required = false) Long usuarioAtualId) {

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "O e-mail para busca é obrigatório."));
        }

        return usuarioRepository.findByEmail(email.trim().toLowerCase())
                .map(usuario -> {
                    if (usuarioAtualId != null && usuario.getId().equals(usuarioAtualId)) {
                        return ResponseEntity.badRequest().body(Map.of("erro", "Não é possível convidar o próprio usuário."));
                    }
                    return ResponseEntity.ok(UsuarioResponseDTO.fromEntity(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}