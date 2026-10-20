package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ParticipanteStatusDTO;
import br.edu.ifpb.combinado.service.ParticipanteConsultaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/combinados/{combinadoId}/participantes")
@CrossOrigin(origins = "*")
public class ParticipanteController {
    private final ParticipanteConsultaService service;
    public ParticipanteController(ParticipanteConsultaService service) { this.service = service; }
    @GetMapping
    public List<ParticipanteStatusDTO> listar(@PathVariable Long combinadoId) {
        return service.listar(combinadoId);
    }
}
