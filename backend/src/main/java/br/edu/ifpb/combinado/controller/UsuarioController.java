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
@io.swagger.v3.oas.annotations.tags.Tag(name = "Usuários",
        description = "Cadastro público e consulta autenticada de usuários.")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Cadastrar usuário",
            description = "Rota pública. Todos os campos são obrigatórios. Exige nome não vazio, e-mail único com domínio contendo ponto, senha com pelo menos 6 caracteres e no máximo 72 bytes UTF-8, e confirmação igual à senha. Normaliza nome e e-mail e atribui ROLE_USUARIO.")
    @io.swagger.v3.oas.annotations.security.SecurityRequirements
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuário cadastrado",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos ou e-mail já cadastrado; o corpo é uma mensagem textual",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "text/plain",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", example = "O formato do e-mail informado é inválido.")))
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO usuarioCriado = usuarioService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Consultar dados públicos do usuário",
            description = "Exige autenticação e permite consultar qualquer usuário pelo ID. Retorna dados sem senha ou hash; não restringe a consulta ao próprio perfil.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Resumo do usuário",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Usuário não encontrado; o corpo é uma mensagem textual",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "text/plain",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", example = "Usuário não encontrado com ID: 42")))
    public ResponseEntity<?> buscarPorId(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID do usuário a consultar.", example = "42")
            @PathVariable Long id) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    @io.swagger.v3.oas.annotations.Operation(summary = "Buscar usuários por nome ou e-mail",
            description = "Busca parcial, sem diferenciar maiúsculas/minúsculas, por nome ou e-mail. Exclui o usuário autenticado e ordena por nome e ID crescentes. q é removido de espaços nas extremidades: com menos de 2 caracteres retorna lista vazia; acima de 150 caracteres retorna 400. pagina é zero-based (padrão 0) e limite aceita de 1 a 50 (padrão 10).")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Resumos encontrados",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResumoDTO.class))))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Termo acima de 150 caracteres ou paginação inválida",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<?> buscarPorNome(
                                           @io.swagger.v3.oas.annotations.Parameter(description = "Trecho de nome ou e-mail; mínimo 2 para pesquisar, máximo 150.", example = "ana")
                                           @RequestParam(defaultValue = "") String q,
                                           @io.swagger.v3.oas.annotations.Parameter(description = "Índice da página, começando em zero.", example = "0")
                                           @RequestParam(defaultValue = "0") int pagina,
                                           @io.swagger.v3.oas.annotations.Parameter(description = "Quantidade de resultados por página, entre 1 e 50.", example = "10")
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
