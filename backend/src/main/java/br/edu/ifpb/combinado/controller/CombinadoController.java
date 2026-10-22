package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.dto.CombinadoResumoDTO;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.AccessDeniedException;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/combinados")
@CrossOrigin(origins = "*")
@Tag(name = "Combinados", description = "Gerenciamento do ciclo de vida, criação, rascunhos e consultas de combinados")
public class CombinadoController {

    private final CombinadoService service;

    public CombinadoController(CombinadoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar combinado ou salvar como rascunho", description = "Cria um novo combinado ativo (AGUARDANDO_CONFIRMACAO) ou salva como RASCUNHO sem disparar notificações.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Combinado ou rascunho criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios ausentes ou dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido")
    })
    public ResponseEntity<Combinado> criar(@RequestBody CombinadoDTO combinado,
                                          @AuthenticationPrincipal UsuarioPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCombinado(combinado, principal.usuario()));
    }

    @GetMapping
    @Operation(summary = "Listar combinados vinculados ao usuário", description = "Retorna os combinados criados pelo usuário ou recebidos como participante, com resumo de status, criador e prazo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de combinados retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido")
    })
    public ResponseEntity<List<CombinadoResumoDTO>> listar() {
        return ResponseEntity.ok(service.listarResumos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar detalhes de um combinado por ID", description = "Retorna todos os detalhes, participantes, tarefas e histórico de auditoria do combinado para usuários vinculados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do combinado retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui vínculo como criador ou participante do combinado")
    })
    public ResponseEntity<CombinadoResumoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhes(id));
    }

    @GetMapping("/meus-rascunhos")
    @Operation(summary = "Listar rascunhos do usuário autenticado", description = "Retorna exclusivamente os combinados em status RASCUNHO pertencentes ao criador autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de rascunhos retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido")
    })
    public List<CombinadoResumoDTO> meusRascunhos() { return service.listarMeusRascunhos(); }

    @PostMapping("/{id}/publicar")
    @Operation(summary = "Publicar rascunho de combinado", description = "Valida os campos obrigatórios de um rascunho e o publica como AGUARDANDO_CONFIRMACAO, enviando convites aos participantes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rascunho publicado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Rascunho incompleto ou inválido para publicação"),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido"),
            @ApiResponse(responseCode = "403", description = "Somente o criador pode publicar o rascunho")
    })
    public CombinadoResumoDTO publicar(@PathVariable Long id, @RequestBody(required = false) CombinadoDTO dados) {
        return service.publicar(id, dados);
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<?> concluir(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.concluir(id));
        } catch (AccessDeniedException exception) {
            return criadorObrigatorio("concluir");
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.cancelar(id));
        } catch (AccessDeniedException exception) {
            return criadorObrigatorio("cancelar");
        }
    }

    @PatchMapping("/{id}/aceitar")
    public ResponseEntity<Combinado> aceitar(@PathVariable Long id, @RequestBody(required = false) Map<String, String> payload) {
        return ResponseEntity.ok(service.aceitar(id, parseTimestamp(payload, "respondidoEm")));
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<?> recusar(@PathVariable Long id, @RequestBody(required = false) Map<String, String> payload) {
        try {
            return ResponseEntity.ok(service.recusar(id, parseTimestamp(payload, "respondidoEm")));
        } catch (AccessDeniedException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "message", "Somente o criador pode cancelar o combinado por esta rota. Para recusar um convite, use a rota de convites."
            ));
        }
    }

    private ResponseEntity<Map<String, String>> criadorObrigatorio(String acao) {
        String message = acao.equals("concluir")
                ? "Somente o criador do combinado pode concluí-lo."
                : "Somente o criador do combinado pode cancelá-lo.";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", message));
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }
}
