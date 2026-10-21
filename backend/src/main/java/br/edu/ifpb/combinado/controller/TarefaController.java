package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.*;
import br.edu.ifpb.combinado.service.TarefaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/combinados/{combinadoId}")
@CrossOrigin(origins = "*")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) { this.service = service; }
    @GetMapping({"/tarefas", "/responsabilidades"})
    public List<TarefaResumoDTO> listar(@PathVariable Long combinadoId) { return service.listar(combinadoId); }
    @PostMapping("/responsabilidades")
    public ResponseEntity<TarefaResumoDTO> adicionar(@PathVariable Long combinadoId, @RequestBody TarefaInputDTO dados) {
        return ResponseEntity.status(201).body(service.adicionar(combinadoId, Collections.singletonList(dados)).getFirst());
    }
    @PostMapping("/tarefas")
    public ResponseEntity<List<TarefaResumoDTO>> adicionarMultiplas(@PathVariable Long combinadoId, @RequestBody List<TarefaInputDTO> dados) {
        return ResponseEntity.status(201).body(service.adicionar(combinadoId, dados));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
