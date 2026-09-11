package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.service.CombinadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<Combinado> aceitar(@PathVariable Long id) {
        return ResponseEntity.ok(service.aceitar(id));
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<Combinado> recusar(@PathVariable Long id) {
        return ResponseEntity.ok(service.recusar(id));
    }
}