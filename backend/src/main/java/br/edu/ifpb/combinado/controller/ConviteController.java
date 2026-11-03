package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteRequestDTO;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.service.ConviteService;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import br.edu.ifpb.combinado.dto.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

@RestController
@RequestMapping("/api/combinados/{combinadoId}/convites")
@CrossOrigin(origins = "*")
@Tag(name = "Convites")
@ApiResponses({@ApiResponse(responseCode = "400", description = "Participantes ou mensagem inválidos"),
        @ApiResponse(responseCode = "401", description = "JWT ausente ou inválido"),
        @ApiResponse(responseCode = "403", description = "Somente o criador pode enviar"),
        @ApiResponse(responseCode = "409", description = "Estado do combinado ou convite existente impede o envio")})
public class ConviteController {

    private final ConviteService conviteService;

    public ConviteController(ConviteService conviteService) {
        this.conviteService = conviteService;
    }

    @PostMapping
    @Operation(summary = "Enviar convite individual", description = "O remetente é obtido do JWT; remetenteId informado no corpo é ignorado. Cria notificação e evento ENVIO.")
    @ApiResponse(responseCode = "201", description = "Convite criado", content = @Content(schema = @Schema(implementation = Convite.class)))
    @PreAuthorize("@combinadoAcesso.eCriador(#combinadoId, authentication)")
    public ResponseEntity<?> enviarConvite(@PathVariable Long combinadoId, @RequestBody ConviteRequestDTO dto,
                                           @AuthenticationPrincipal UsuarioPrincipal principal) {
        try {
            var dados = new ConviteRequestDTO(principal.usuario().getId(), dto.destinatarioId(), dto.mensagem());
            Convite convite = conviteService.enviarConvite(combinadoId, dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(convite);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
        }
    }

    @PostMapping("/lote")
    @Operation(summary = "Enviar convites em lote", description = "Lote atômico de 1 a 100 IDs distintos. Todos são validados antes de persistir; qualquer falha desfaz convites, eventos e notificações.")
    @ApiResponse(responseCode = "201", description = "Lote criado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConviteResumoDTO.class))))
    public ResponseEntity<java.util.List<ConviteResumoDTO>> lote(@PathVariable Long combinadoId,
                                                                @Valid @RequestBody ConviteLoteDTO dados) {
        return ResponseEntity.status(201).body(conviteService.enviarLote(combinadoId, dados));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
}
