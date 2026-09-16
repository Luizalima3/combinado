package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.model.Combinado;
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
    public ResponseEntity<Combinado> criar(@RequestBody Combinado combinado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(combinado));
    }

    @GetMapping
    public ResponseEntity<List<Combinado>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Combinado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
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
}