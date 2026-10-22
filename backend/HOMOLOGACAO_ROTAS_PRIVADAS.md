# Homologação — Restrições de acesso a rotas privadas

**Task:** #30937 — [po/validação] Homologar restrições de acesso a rotas privadas (#31451)
**Data:** 2026-10-22
**Escopo:** homologar o fluxo de bloqueio em rotas privadas, garantindo que o
comportamento segue o critério CA05 (especificação em
`backend/COMBINADO_TODOS_OS_ITENS.md`).

## Critério avaliado

**CA05 (#31451) — Proteção de rotas privadas:** o sistema deve impedir que
usuários não autenticados acessem rotas protegidas da aplicação,
redirecionando-os automaticamente para a tela de login.

## Evidências

### Teste E2E (Cypress, `cypress/e2e/rotas-privadas.cy.ts`) — 3/3 aprovados

Executado contra backend (8080, via proxy `/api` do Vite) e frontend (5173):

1. **Bloqueio sem autenticação:** com o `localStorage` limpo, os acessos a `/`,
   `/combinados/novo` e `/perfil` foram redirecionados automaticamente para
   `/login` (guarda `PrivateRoute` em `App.tsx`).
2. **Acesso legítimo preservado:** usuário autenticado com token real (cadastro +
   login via API) acessou `/perfil` normalmente (200, conteúdo renderizado) —
   o bloqueio não impede o usuário legítimo.
3. **Sessão inválida em tempo de execução:** com token inexistente no backend,
   a primeira chamada à API retornou **401** e a interface redirecionou
   automaticamente para `/login` (listener `combinado:unauthorized` em `App.tsx`,
   disparado pelo interceptor HTTP).

### Camadas validadas

- **Frontend:** `PrivateRoute`/`GuestRoute` (`components/auth/PrivateRoute.tsx`),
  interceptor de 401/403 no cliente HTTP e listener em `App.tsx`.
- **Backend:** proteção equivalente já atestada por API na homologação
  `HOMOLOGACAO_SESSAO_JWT.md` (requisição sem/revogado token → 401).

## Conclusão

**APROVADO — CONFORME.** Rotas privadas são bloqueadas para não autenticados
com redirecionamento automático para a tela de login, em conformidade com o
CA05 (#31451). Acesso legítimo e expiração de sessão em runtime também
comportam-se corretamente.
