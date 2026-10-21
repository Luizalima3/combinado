package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteDetalheDTO;
import br.edu.ifpb.combinado.service.ConviteConsultaService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import java.util.List;
import br.edu.ifpb.combinado.dto.*;

@RestController
@RequestMapping("/api/convites")
@CrossOrigin(origins = "*")
@Tag(name = "Convites", description = "Consultas, respostas e histórico auditável dos convites")
@ApiResponses({@ApiResponse(responseCode = "401", description = "JWT ausente ou inválido"),
        @ApiResponse(responseCode = "403", description = "Sem permissão para o convite")})
public class ConviteConsultaController {
    private final ConviteConsultaService service;
    public ConviteConsultaController(ConviteConsultaService service) { this.service = service; }
    @GetMapping("/{id}")
    @Operation(summary = "Consultar detalhes de um convite", description = "Somente destinatário ou criador. Inclui participantes confirmados e timeline de eventos.")
    public ConviteDetalheDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @GetMapping("/recebidos")
    @Operation(summary = "Listar meus convites recebidos", description = "Usa exclusivamente o usuário do JWT, inclusive para ADMIN. Exclui rascunhos e ordena do mais recente para o mais antigo.")
    public List<ConviteResumoDTO> recebidos(@RequestParam(defaultValue = "0") int pagina,
                                           @RequestParam(defaultValue = "20") int limite) {
        return service.recebidos(pagina, limite);
    }

    @GetMapping("/{id}/historico")
    @Operation(summary = "Consultar histórico cronológico de um convite", description = "Eventos ENVIO, ACEITE e RECUSA com autor e data/hora UTC, em ordem crescente. Somente destinatário ou criador; eventos legados sem evidência não são inventados.")
    public List<ConviteHistoricoDTO> historico(@PathVariable Long id) { return service.buscar(id).historico(); }

    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> invalido(IllegalArgumentException e) {
        return org.springframework.http.ResponseEntity.badRequest().body(java.util.Map.of("message", e.getMessage()));
    }
}
