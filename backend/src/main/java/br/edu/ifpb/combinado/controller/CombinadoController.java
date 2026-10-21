package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.dto.CombinadoResumoDTO;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import br.edu.ifpb.combinado.service.CombinadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/combinados")
@CrossOrigin(origins = "*")
public class CombinadoController {

    private final CombinadoService service;

    public CombinadoController(CombinadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Combinado> criar(@RequestBody CombinadoDTO combinado,
                                          @AuthenticationPrincipal UsuarioPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCombinado(combinado, principal.usuario()));
    }

    @GetMapping
    public ResponseEntity<List<CombinadoResumoDTO>> listar() {
        return ResponseEntity.ok(service.listarResumos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CombinadoResumoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhes(id));
    }

    @GetMapping("/meus-rascunhos")
    public List<CombinadoResumoDTO> meusRascunhos() { return service.listarMeusRascunhos(); }

    @PostMapping("/{id}/publicar")
    public CombinadoResumoDTO publicar(@PathVariable Long id, @RequestBody(required = false) CombinadoDTO dados) {
        return service.publicar(id, dados);
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Combinado> concluir(@PathVariable Long id) {
        return ResponseEntity.ok(service.concluir(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Combinado> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @PatchMapping("/{id}/aceitar")
    public ResponseEntity<Combinado> aceitar(@PathVariable Long id, @RequestBody(required = false) Map<String, String> payload) {
        return ResponseEntity.ok(service.aceitar(id, parseTimestamp(payload, "respondidoEm")));
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<Combinado> recusar(@PathVariable Long id, @RequestBody(required = false) Map<String, String> payload) {
        return ResponseEntity.ok(service.recusar(id, parseTimestamp(payload, "respondidoEm")));
    }

    private LocalDateTime parseTimestamp(Map<String, String> payload, String key) {
        if (payload == null || payload.get(key) == null || payload.get(key).isBlank()) {
            return LocalDateTime.now();
        }

        String value = payload.get(key);
        try {
            return OffsetDateTime.parse(value).toLocalDateTime();
        } catch (Exception ignored) {
            try {
                return LocalDateTime.parse(value);
            } catch (Exception ignoredAgain) {
                return Instant.parse(value).atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }
}
