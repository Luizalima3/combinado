package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteRespostaDTO;
import br.edu.ifpb.combinado.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/convites")
@CrossOrigin(origins = "*")
public class ConviteRespostaController {
    private final ConviteService service;
    public ConviteRespostaController(ConviteService service) { this.service = service; }
    @PatchMapping("/{id}/aceitar")
    public ConviteRespostaDTO aceitar(@PathVariable Long id) { return service.aceitar(id); }
    @PatchMapping("/{id}/recusar")
    public ConviteRespostaDTO recusar(@PathVariable Long id) { return service.recusar(id); }
    @ExceptionHandler(ConviteConflitoException.class)
    public ResponseEntity<Map<String, String>> conflito(ConviteConflitoException exception) {
        return ResponseEntity.status(409).body(Map.of("message", exception.getMessage()));
    }
}
