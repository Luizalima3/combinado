package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.*;
import br.edu.ifpb.combinado.service.TarefaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/combinados/{combinadoId}")
@CrossOrigin(origins = "*")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Responsabilidades", description = "Tarefas vinculadas ao combinado e seus responsáveis")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) { this.service = service; }
    @GetMapping({"/tarefas", "/responsabilidades"})
    @io.swagger.v3.oas.annotations.Operation(summary = "Listar tarefas e responsáveis do combinado",
            description = "Exige vínculo ou ADMIN. Retorna a coleção completa, com DTOs seguros e ordem por ID.")
    public List<TarefaResumoDTO> listar(@PathVariable Long combinadoId) { return service.listar(combinadoId); }
    @PostMapping("/responsabilidades")
    @io.swagger.v3.oas.annotations.Operation(summary = "Adicionar responsabilidade",
            description = "Somente o criador, em rascunho ou aguardando confirmação. Aceita usuarioId ou responsaveisIds.")
    public ResponseEntity<TarefaResumoDTO> adicionar(@PathVariable Long combinadoId, @RequestBody TarefaInputDTO dados) {
        return ResponseEntity.status(201).body(service.adicionar(combinadoId, Collections.singletonList(dados)).getFirst());
    }
    @PostMapping("/tarefas")
    @io.swagger.v3.oas.annotations.Operation(summary = "Adicionar várias tarefas",
            description = "Lote atômico de 1 a 100 tarefas. Valida todos os responsáveis antes de persistir.")
    public ResponseEntity<List<TarefaResumoDTO>> adicionarMultiplas(@PathVariable Long combinadoId, @RequestBody List<TarefaInputDTO> dados) {
        return ResponseEntity.status(201).body(service.adicionar(combinadoId, dados));
    }
    @PutMapping({"/tarefas/{tarefaId}", "/responsabilidades/{tarefaId}"})
    @io.swagger.v3.oas.annotations.Operation(summary = "Editar tarefa e substituir responsáveis",
            description = "Exige criador e estado editável. A tarefa deve pertencer ao combinado da URL. Requer descrição e pelo menos um responsável vinculado.")
    public TarefaResumoDTO editar(@PathVariable Long combinadoId, @PathVariable Long tarefaId,
                                   @RequestBody TarefaInputDTO dados) {
        return service.editar(combinadoId, tarefaId, dados);
    }
    @ExceptionHandler(br.edu.ifpb.combinado.service.TarefaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> naoEncontrada(br.edu.ifpb.combinado.service.TarefaNaoEncontradaException e) {
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
