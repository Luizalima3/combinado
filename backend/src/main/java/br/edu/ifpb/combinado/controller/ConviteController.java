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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/combinados/{combinadoId}/convites")
@CrossOrigin(origins = "*")
@Tag(name = "Convites", description = "Endpoints para envio e gerenciamento de convites de combinados")
public class ConviteController {

    private final ConviteService conviteService;

    public ConviteController(ConviteService conviteService) {
        this.conviteService = conviteService;
    }

    @PostMapping
    @Operation(summary = "Enviar convite para participante", description = "Envia um convite vinculado ao combinado para o destinatário informado e gera notificação automática.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Convite enviado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido"),
            @ApiResponse(responseCode = "403", description = "Somente o criador do combinado pode enviar convites"),
            @ApiResponse(responseCode = "409", description = "Conflito com convite pendente/aceito ou estado do combinado")
    })
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
}
