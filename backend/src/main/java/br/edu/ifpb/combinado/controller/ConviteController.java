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

@RestController
@RequestMapping("/api/combinados/{combinadoId}/convites")
@CrossOrigin(origins = "*")
public class ConviteController {

    private final ConviteService conviteService;

    public ConviteController(ConviteService conviteService) {
        this.conviteService = conviteService;
    }

    @PostMapping
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
