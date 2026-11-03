package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.UsuarioRequestDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.dto.UsuarioResumoDTO;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import java.util.List;
import java.util.Map;
import br.edu.ifpb.combinado.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Cadastrar usuário",
            description = "Rota pública. Valida nome, e-mail, senha e confirmação. Grava senha com BCrypt e perfil ROLE_USUARIO.")
    @io.swagger.v3.oas.annotations.security.SecurityRequirements
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuário cadastrado",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos ou e-mail já cadastrado")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO usuarioCriado = usuarioService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Consultar resumo do usuário", description = "Exige autenticação. Retorna dados sem senha ou hash.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Resumo do usuário",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    @io.swagger.v3.oas.annotations.Operation(summary = "Buscar usuários por nome",
            description = "Busca parcial sem diferenciar caixa. Exclui o usuário autenticado. q exige 2 caracteres, pagina inicia em 0 e limite aceita 1 a 50.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Resumos encontrados",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResumoDTO.class))))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Busca ou paginação inválida")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária")
    public ResponseEntity<?> buscarPorNome(@RequestParam(defaultValue = "") String q,
                                           @RequestParam(defaultValue = "0") int pagina,
                                           @RequestParam(defaultValue = "10") int limite,
                                           @io.swagger.v3.oas.annotations.Parameter(hidden = true) @AuthenticationPrincipal UsuarioPrincipal principal) {
        try {
            List<UsuarioResumoDTO> usuarios = usuarioService.buscarPorNome(
                    q, pagina, limite, principal.usuario().getId());
            return ResponseEntity.ok(usuarios);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }
}
