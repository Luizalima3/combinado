package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.LoginRequestDTO;
import br.edu.ifpb.combinado.dto.LoginResponseDTO;
import br.edu.ifpb.combinado.dto.UsuarioResponseDTO;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO dto) {
        if (dto.senha() == null || dto.senha().getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new org.springframework.security.authentication.BadCredentialsException("Credenciais inválidas.");
        }
        var authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(dto.email(), dto.senha()));
        var usuario = ((UsuarioPrincipal) authentication.getPrincipal()).usuario();
        String token = jwtService.gerarToken(usuario);
        return new LoginResponseDTO(token, "Bearer", jwtService.validarToken(token).getExpiresAtAsInstant(),
                UsuarioResponseDTO.fromEntity(usuario));
    }
}
