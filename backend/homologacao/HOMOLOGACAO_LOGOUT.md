# Homologação — Encerramento seguro de sessão

**Task:** #30933 — [po/validação] Homologar encerramento seguro de sessão (#31447)
**Data:** 2026-10-22
**Escopo:** homologar o fluxo de saída do usuário e confirmar redirecionamento
seguro para a tela pública inicial (especificação em
`backend/COMBINADO_TODOS_OS_ITENS.md`).

## Critério avaliado

**CA04 (#31447) — Encerramento de sessão (Logout):** o sistema deve permitir que o
usuário realize logout a qualquer momento, invalidando a sessão e removendo o
token armazenado no cliente.

## Evidências

### Teste E2E (Cypress, `cypress/e2e/logout-seguro.cy.ts`) — 1/1 aprovado

Executado contra backend (8080, via proxy `/api` do Vite) e frontend (5173):

1. **Logout a qualquer momento:** usuário autenticado via token real (cadastro +
   login pela API), clique no botão "Sair" do cabeçalho (`AppLayout`).
2. **Invalidação da sessão no backend:** `POST /api/auth/logout` retorna **204**
   (revogação persistida em `tokens_revogados`, task #30930).
3. **Remoção do token no cliente:** `combinado.jwt` e `combinado.authUser`
   removidos do `localStorage` (confirmado por asserção).
4. **Reuso do token revogado bloqueado:** `GET /api/usuarios/1` com o token
   revogado retorna **401** — a sessão ficou realmente invalidada no servidor.
5. **Redirecionamento seguro para a tela pública:** após o logout, a URL é
   `/login`; novo acesso à rota privada `/perfil` volta a redirecionar para
   `/login` (roteamento em `AppLayout.handleLogout` e guarda de rotas).

### Camadas validadas

- **Frontend:** `AuthContext.logout` (chama `logoutRequest` e limpa credenciais),
  `AppLayout.handleLogout` (redireciona para `/login` com `replace: true`).
- **Backend:** `AuthController` logout 204 + revogação persistida (validado
  também em API na homologação anterior, `HOMOLOGACAO_SESSAO_JWT.md`).

## Conclusão

**APROVADO — CONFORME.** O fluxo de saída permite logout a qualquer momento,
invalida a sessão no servidor, remove o token do cliente e redireciona
seguramente para a tela pública inicial, em conformidade com o CA04 (#31447).
