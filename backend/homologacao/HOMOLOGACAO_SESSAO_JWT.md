# Homologação — Persistência da sessão e segurança do token

**Task:** #30929 — [po/validação] Homologar persistência da sessão e segurança (#31446)
**Data:** 2026-10-22
**Escopo:** validar se o token gerado cumpre as regras de negócio e os parâmetros
definidos no critério CA03 (especificado em `backend/COMBINADO_TODOS_OS_ITENS.md`).

## Critério avaliado

**CA03 (#31446) — Emissão de Token JWT:** ao realizar login com sucesso, o sistema
deve gerar um token JWT com tempo de expiração e dados de identificação do usuário
para autenticação das requisições subsequentes.

## Evidências

### 1. Testes unitários do JwtService

`.\mvnw.cmd -Dtest=JwtServiceTest test` → **11/11 aprovados** (`BUILD SUCCESS`),
cobrindo emissão, claims, algoritmo, validação e expiração (H2 em memória,
sem PostgreSQL).

### 2. Validação de token real (ponta a ponta, backend em execução em :8080)

Login com usuário recém-cadastrado retornou:

- `tipo`: `Bearer`
- `expiraEm`: `2026-10-23T01:10:54Z` (24 horas após a emissão, conforme
  `JWT_EXPIRATION = 86400000`)

Payload decodificado do token emitido:

```json
{"iss":"combinado","sub":"3","email":"homolog.2102192639@combinado.app",
 "perfil":"ROLE_USUARIO","jti":"630c4b39-3cd2-4057-b991-88a6f4d5add1",
 "iat":1792631229,"exp":1792717629}
```

- `exp - iat = 86400` segundos = **24 horas** (tempo de expiração presente e correto).
- Dados de identificação presentes: `sub` (ID), `email` e `perfil`.
- A senha não aparece no payload (conforme `JWT.md`).

### 3. Uso do token em requisições subsequentes

- `GET /api/usuarios/3` com `Authorization: Bearer <token>` → **200 OK**,
  retornando o perfil do usuário autenticado (id, nome, e-mail, perfil).
- Sem token, a mesma rota é bloqueada (401) — rotas privadas protegidas.

### 4. Persistência e encerramento seguro

- `POST /api/auth/logout` com o token → **204** (revogação persistida em
  `tokens_revogados`).
- Reuso do mesmo token após o logout → **401** (token revogado deixa de autenticar),
  conforme descrito em `AUTH.md` (#30930).

## Conclusão

**APROVADO — CONFORME.** O token emitido no login atende integralmente ao CA03:
algoritmo HS256, emissor `combinado`, identificação do usuário (`sub`, `email`,
`perfil`), tempo de expiração de 24 horas e autenticação válida para requisições
subsequentes, com revogação persistida funcionando corretamente.
