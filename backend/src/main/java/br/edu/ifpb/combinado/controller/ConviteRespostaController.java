package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteRespostaDTO;
import br.edu.ifpb.combinado.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/convites")
@CrossOrigin(origins = "*")
@Tag(name = "Convites")
@ApiResponses({@ApiResponse(responseCode = "200", description = "Resposta e evento persistidos na mesma transação"),
        @ApiResponse(responseCode = "401", description = "JWT ausente ou inválido"),
        @ApiResponse(responseCode = "403", description = "Somente o destinatário pode responder"),
        @ApiResponse(responseCode = "409", description = "Convite respondido, substituído ou combinado em estado incompatível")})
public class ConviteRespostaController {
    private final ConviteService service;
    public ConviteRespostaController(ConviteService service) { this.service = service; }
    @PatchMapping("/{id}/aceitar")
    @Operation(summary = "Aceitar convite pendente", description = "Sem corpo. Autor é o destinatário autenticado; dataResposta vem do servidor em UTC. Não altera o estado global do combinado.")
    public ConviteRespostaDTO aceitar(@PathVariable Long id) { return service.aceitar(id); }
    @PatchMapping("/{id}/recusar")
    @Operation(summary = "Recusar convite pendente", description = "Sem corpo. Registra RECUSA com autor e data do servidor. Nova tentativa de resposta retorna 409.")
    public ConviteRespostaDTO recusar(@PathVariable Long id) { return service.recusar(id); }
    @ExceptionHandler(ConviteConflitoException.class)
    public ResponseEntity<Map<String, String>> conflito(ConviteConflitoException exception) {
        return ResponseEntity.status(409).body(Map.of("message", exception.getMessage()));
    }
}
