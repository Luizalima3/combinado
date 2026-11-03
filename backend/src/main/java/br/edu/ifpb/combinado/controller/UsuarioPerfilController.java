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
@io.swagger.v3.oas.annotations.tags.Tag(name = "Perfil")
@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Autenticação necessária")
public class UsuarioPerfilController {
    private final UsuarioPerfilService service;

    public UsuarioPerfilController(UsuarioPerfilService service) {
        this.service = service;
    }

    @PutMapping("/nome")
    @io.swagger.v3.oas.annotations.Operation(summary = "Atualizar nome do perfil", description = "Somente a conta autenticada. Nome entre 2 e 150 caracteres.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Perfil atualizado")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Nome inválido")
    public ResponseEntity<UsuarioResponseDTO> atualizarNome(@Valid @RequestBody AtualizarNomeDTO dto) {
        return ResponseEntity.ok(service.atualizarNome(dto));
    }

    @PutMapping("/email")
    @io.swagger.v3.oas.annotations.Operation(summary = "Atualizar e-mail do perfil", description = "Exige senha atual e e-mail válido e disponível. Normaliza espaços nas extremidades e caixa.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Perfil atualizado")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "E-mail ou senha atual inválidos")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "E-mail já utilizado por outra conta")
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
    @io.swagger.v3.oas.annotations.Operation(summary = "Alterar senha do perfil", description = "Exige senha atual correta, nova senha e confirmação. Nova senha com pelo menos 6 caracteres e no máximo 72 bytes UTF-8. Persiste hash BCrypt.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Senha alterada")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Credenciais ou confirmação inválidas")
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
