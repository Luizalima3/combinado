package br.edu.ifpb.combinado.controller;

import br.edu.ifpb.combinado.dto.LoginRequestDTO;
import br.edu.ifpb.combinado.dto.LoginResponseDTO;
import br.edu.ifpb.combinado.service.AuthService;
import br.edu.ifpb.combinado.service.LogoutService;
import br.edu.ifpb.combinado.security.JwtAuthenticationFilter;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Autenticação")
public class AuthController {
    private final AuthService service;
    private final LogoutService logoutService;

    public AuthController(AuthService service, LogoutService logoutService) {
        this.service = service;
        this.logoutService = logoutService;
    }

    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.Operation(summary = "Autenticar usuário",
            description = "Rota pública. Aceita e-mail válido e senha cadastrada (até 72 bytes UTF-8). Retorna JWT Bearer, instante de expiração UTC e resumo do usuário. Resposta não deve ser armazenada em cache.")
    @io.swagger.v3.oas.annotations.security.SecurityRequirements
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Autenticação concluída",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginResponseDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "E-mail ausente ou em formato inválido",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Credenciais inválidas ou senha acima do limite aceito",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok().header(HttpHeaders.CACHE_CONTROL, "no-store").body(service.login(dto));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> credenciaisInvalidas() {
        return ResponseEntity.status(401).body(Map.of("message", "E-mail ou senha inválidos."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<br.edu.ifpb.combinado.dto.MensagemErroDTO> dadosInvalidos(
            MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity.badRequest().body(new br.edu.ifpb.combinado.dto.MensagemErroDTO(
                message == null ? "Dados inválidos." : message));
    }

    @PostMapping("/logout")
    @io.swagger.v3.oas.annotations.Operation(summary = "Encerrar sessão", description = "Exige Bearer válido e registra a revogação do JWT. Não recebe token no corpo.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Token revogado")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token ausente, inválido ou revogado",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = br.edu.ifpb.combinado.dto.MensagemErroDTO.class)))
    public ResponseEntity<Void> logout(@io.swagger.v3.oas.annotations.Parameter(hidden = true)
            @RequestAttribute(JwtAuthenticationFilter.TOKEN_ATTRIBUTE) DecodedJWT token) {
        logoutService.revogarToken(token);
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
