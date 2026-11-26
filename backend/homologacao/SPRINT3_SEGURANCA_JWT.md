# Segurança JWT — primeiro grupo da Sprint 3

Tasks #31325, #31342, #31346 e #31350. As implementações já existem na main;
esta entrega confere os contratos e acrescenta um teste integrado sem duplicar configuração.

| Task | Implementação | Evidência |
| --- | --- | --- |
| #31325 | SecurityConfig com SecurityFilterChain e @EnableWebSecurity | ProtectedEndpointsTest: rotas públicas e privadas |
| #31342 | STATELESS, CSRF desabilitado e ausência de sessão de autenticação | ProtectedEndpointsTest: sessão ignorada; SegurancaJwtSprint3Test: fluxo sem sessão/CSRF |
| #31346 | JwtService com assinatura, identidade, emissor, identificador e expiração | JwtServiceTest: assinatura, adulteração, emissor e validade; fluxo real de login |
| #31350 | JwtAuthenticationFilter com validação Bearer e identidade atual do banco | ProtectedEndpointsTest: token válido/inválido/expirado, usuário excluído e e-mail alterado; fluxo com revogação |

SegurancaJwtSprint3Test percorre cadastro público, login, validação dos claims,
consulta autenticada sem sessão HTTP, recusa de requisição posterior sem Bearer,
logout e rejeição do token revogado. Executa endpoints reais com H2 em memória.
Não é validação de ponta a ponta pelo navegador ou PostgreSQL real.

As tasks #31356, #31358, #31366 e #31374 permanecem no grupo local para o próximo envio.
Validação de 26/11/2026: 21 testes direcionados (SegurancaJwtSprint3Test, ProtectedEndpointsTest e JwtServiceTest), sem falhas, erros ou casos ignorados. Como esta entrega altera somente teste e documentação, não foi repetida a regressão anterior de 296 testes.
