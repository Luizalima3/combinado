package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.dto.CombinadoResumoDTO;
import br.edu.ifpb.combinado.dto.CombinadoHistoricoDTO;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.AccessDeniedException;
import br.edu.ifpb.combinado.service.CombinadoService;
import br.edu.ifpb.combinado.service.CombinadoNaoEditavelException;
import br.edu.ifpb.combinado.service.ParticipantesPendentesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/combinados")
@CrossOrigin(origins = "*")
public class CombinadoController {

    private final CombinadoService service;

    public CombinadoController(CombinadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Combinado> criar(@RequestBody CombinadoDTO combinado,
                                          @AuthenticationPrincipal UsuarioPrincipal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCombinado(combinado, principal.usuario()));
    }

    @GetMapping
    public ResponseEntity<List<CombinadoResumoDTO>> listar() {
        return ResponseEntity.ok(service.listarResumos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CombinadoResumoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhes(id));
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<List<CombinadoHistoricoDTO>> buscarHistorico(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarHistorico(id));
    }

    @GetMapping("/meus-rascunhos")
    public List<CombinadoResumoDTO> meusRascunhos() { return service.listarMeusRascunhos(); }

    @PostMapping("/{id}/publicar")
    public CombinadoResumoDTO publicar(@PathVariable Long id, @RequestBody(required = false) CombinadoDTO dados) {
        return service.publicar(id, dados);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CombinadoDTO dados) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dados));
        } catch (AccessDeniedException exception) {
            return criadorObrigatorio("editar");
        } catch (CombinadoNaoEditavelException exception) {
            // US63: combinado ativo/concluído/cancelado não pode mais ser editado
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", exception.getMessage()));
        }
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<?> concluir(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.concluir(id));
        } catch (AccessDeniedException exception) {
            return criadorObrigatorio("concluir");
        } catch (ParticipantesPendentesException exception) {
            // US62 / Task #31671: 422 com lista dos participantes que ainda não confirmaram
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of(
                    "message", exception.getMessage(),
                    "pendentes", exception.getParticipantesPendentes()
            ));
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
                : acao.equals("editar")
                        ? "Somente o criador do combinado pode editá-lo."
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
