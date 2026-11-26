# Segurança — senhas, autorização e erros JSON

Tasks #31356, #31358, #31366 e #31374. Implementações já existentes na main,
conferidas sem duplicação de classes ou configuração.

| Task | Implementação | Evidência |
| --- | --- | --- |
| #31356 | AuthenticationConfig expõe PasswordEncoder com BCryptPasswordEncoder | SegurancaJwtSprint3Test verifica o bean real e o hash persistido |
| #31358 | Cadastro usa encode; DaoAuthenticationProvider usa o encoder no login | Fluxo real confirma hash diferente do texto, matches, rejeição de senha incorreta e login correto; AuthServiceTest e LoginEndpointTest |
| #31366 | @EnableMethodSecurity e @PreAuthorize nos serviços | CombinadoVinculoTest verifica acesso direto ao serviço e via HTTP; permissões dos demais endpoints preservadas |
| #31374 | ApiAuthenticationEntryPoint e AccessDeniedHandler JSON | SegurancaAutorizacaoSprint3Test verifica 401/403, tipo JSON, mensagem pública e ausência de stackTrace |

O cadastro não aceita elevar o perfil para ADMIN pelo JSON. O teste de cadastro,
login e revogação usa HTTP real com MockMvc e persistência H2, sem sessão HTTP.
A validação não inclui PostgreSQL real nem execução de ponta a ponta pelo navegador.
Validação em 26/11/2026: 35 testes direcionados (SegurancaJwtSprint3Test, SegurancaAutorizacaoSprint3Test, CombinadoVinculoTest, AuthServiceTest, LoginEndpointTest e UsuarioCadastroTest), sem falhas, erros ou casos ignorados.
