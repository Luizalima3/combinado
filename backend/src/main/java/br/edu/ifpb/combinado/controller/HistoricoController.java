package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.CombinadoHistoricoDTO;
import br.edu.ifpb.combinado.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/combinados/{id}/historico")
@CrossOrigin(origins = "*")
@Tag(name = "Histórico de combinados")
public class HistoricoController {
    private final HistoricoService service;
    public HistoricoController(HistoricoService service) { this.service = service; }
    @GetMapping
    @Operation(summary = "Consultar linha do tempo do combinado",
            description = "Retorna eventos em ordem cronológica crescente, com filtros opcionais por autor, tipo e datas inclusivas em UTC. Restrito a usuários com acesso ao combinado.")
    public List<CombinadoHistoricoDTO> listar(
            @PathVariable Long id,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate dataInicio,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate dataFim,
            @RequestParam(required = false) Long autorId,
            @RequestParam(required = false) br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico tipo) {
        return service.listar(id, dataInicio, dataFim, autorId, tipo);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> dadosInvalidos(IllegalArgumentException e) {
        return org.springframework.http.ResponseEntity.badRequest().body(java.util.Map.of("message", e.getMessage()));
    }
}
