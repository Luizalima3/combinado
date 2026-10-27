package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteRespostaDTO;
import br.edu.ifpb.combinado.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/convites")
@CrossOrigin(origins = "*")
@Tag(name = "Convites")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resposta ao convite registrada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido"),
        @ApiResponse(responseCode = "403", description = "Somente o destinatário do convite pode responder"),
        @ApiResponse(responseCode = "409", description = "Convite já respondido ou estado incompatível")
})
public class ConviteRespostaController {
    private final ConviteService service;
    public ConviteRespostaController(ConviteService service) { this.service = service; }
    @PatchMapping("/{id}/aceitar")
    @Operation(summary = "Aceitar convite pendente", description = "Registra o aceite do destinatário autenticado com data/hora de resposta.")
    public ConviteRespostaDTO aceitar(@PathVariable Long id) { return service.aceitar(id); }
    @PatchMapping("/{id}/recusar")
    @Operation(summary = "Recusar convite pendente", description = "Registra a recusa do destinatário autenticado com data/hora de resposta.")
    public ConviteRespostaDTO recusar(@PathVariable Long id) { return service.recusar(id); }
    @ExceptionHandler(ConviteConflitoException.class)
    public ResponseEntity<Map<String, String>> conflito(ConviteConflitoException exception) {
        return ResponseEntity.status(409).body(Map.of("message", exception.getMessage()));
    }
}
