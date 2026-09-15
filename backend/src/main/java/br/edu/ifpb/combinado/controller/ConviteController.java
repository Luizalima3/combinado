package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteRequestDTO;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.service.ConviteService;
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
    public ResponseEntity<?> enviarConvite(@PathVariable Long combinadoId, @RequestBody ConviteRequestDTO dto) {
        try {
            Convite convite = conviteService.enviarConvite(combinadoId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(convite);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
        }
    }
}
