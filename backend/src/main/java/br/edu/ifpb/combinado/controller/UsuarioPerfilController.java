package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.AtualizarNomeDTO;
import br.edu.ifpb.combinado.dto.AtualizarSenhaDTO;
import br.edu.ifpb.combinado.dto.AtualizarEmailDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.service.UsuarioPerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios/perfil")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Perfil",
        description = "Operações sobre o perfil do usuário autenticado.")
@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária",
        content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
public class UsuarioPerfilController {
    private final UsuarioPerfilService service;

    public UsuarioPerfilController(UsuarioPerfilService service) {
        this.service = service;
    }

    @PutMapping("/nome")
    @io.swagger.v3.oas.annotations.Operation(summary = "Atualizar nome do perfil",
            description = "Atualiza somente o nome da conta autenticada. O nome é removido de espaços nas extremidades e deve conter entre 2 e 150 caracteres.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Perfil atualizado",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Nome inválido",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<UsuarioResponseDTO> atualizarNome(@Valid @RequestBody AtualizarNomeDTO dto) {
        return ResponseEntity.ok(service.atualizarNome(dto));
    }

    @PutMapping("/email")
    @io.swagger.v3.oas.annotations.Operation(summary = "Atualizar e-mail do perfil",
            description = "Exige senha atual e e-mail único com domínio contendo ao menos um ponto. Normaliza espaços nas extremidades e caixa.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Perfil atualizado",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "E-mail ou senha atual inválidos",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "E-mail já utilizado por outra conta",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<UsuarioResponseDTO> atualizarEmail(@Valid @RequestBody AtualizarEmailDTO dto) {
        return ResponseEntity.ok(service.atualizarEmail(dto));
    }

    @ExceptionHandler({br.edu.ifpb.combinado.service.EmailEmUsoException.class,
            org.springframework.dao.DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>> emailEmUso(Exception exception) {
        return ResponseEntity.status(409).body(Map.of("message", "Este e-mail já está em uso."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }

    @PutMapping("/senha")
    @io.swagger.v3.oas.annotations.Operation(summary = "Alterar senha do perfil",
            description = "Exige senha atual correta, nova senha e confirmação idêntica. Nova senha com pelo menos 6 caracteres e no máximo 72 bytes UTF-8. Persiste hash BCrypt.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Senha alterada")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Credenciais, senha ou confirmação inválidas",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<Void> atualizarSenha(@Valid @RequestBody AtualizarSenhaDTO dto) {
        service.atualizarSenha(dto);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacaoInvalida(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("message", message == null ? "Dados inválidos." : message));
    }
}
