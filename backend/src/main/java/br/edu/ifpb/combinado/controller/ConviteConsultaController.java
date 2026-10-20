package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.ConviteDetalheDTO;
import br.edu.ifpb.combinado.service.ConviteConsultaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convites")
@CrossOrigin(origins = "*")
public class ConviteConsultaController {
    private final ConviteConsultaService service;
    public ConviteConsultaController(ConviteConsultaService service) { this.service = service; }
    @GetMapping("/{id}")
    public ConviteDetalheDTO buscar(@PathVariable Long id) { return service.buscar(id); }
}
