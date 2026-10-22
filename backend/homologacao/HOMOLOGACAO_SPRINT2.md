# Homologação Consolidada — Sprint 2 (tasks de PO/Validação atribuídas a Maria Luiza)

**Data:** 2026-10-22
**Referência:** `backend/COMBINADO_TODOS_OS_ITENS.md`
**Ambiente:** backend Spring Boot (:8080), frontend Vite (:5173), banco local.

## 1. Sumário por task

| Task | Critério(s) | Resultado | Evidência |
|---|---|
| #30913 — Validação de e-mail | CA03 (#31436) | ⚠️ **NÃO CONFORME** (bug #1) | E2E + API |
| #30917 — Mensagens e requisitos de senha | CA04 (#31437) | ✅ CONFORME | E2E + 3/3 unitários |
| #30921 — Confirmação de senha e formulário | CA05, CA06, CA07 | ✅ CONFORME | `HOMOLOGACAO_CADASTRO.md` |
| #30925 — Login e mensagens de erro | CA01, CA02 (#31444/45) | ✅ CONFORME | E2E `validacoes-sprint2.cy.ts` |
| #30929 — Persistência da sessão (JWT) | CA03 (#31446) | ✅ CONFORME | `HOMOLOGACAO_SESSAO_JWT.md` |
| #30933 — Encerramento seguro de sessão | CA04 (#31447) | ✅ CONFORME | `HOMOLOGACAO_LOGOUT.md` |
| #30937 — Rotas privadas | CA05 (#31451) | ✅ CONFORME | `HOMOLOGACAO_ROTAS_PRIVADAS.md` |
| #30943 — Atualização de nome do perfil | #31456/59 | ✅ CONFORME | E2E `sprint2-fluxos.cy.ts` + 11 unitários |
| #30947 — Atualização de e-mail | #31457/59 | ✅ CONFORME | 6 unitários (`PerfilEmailEndpointTest`) |
| #30952 — Alteração de senha no perfil | #31458/59 | ✅ CONFORME | E2E (bloqueio de divergência + 204) + 5 unitários |
| #30959 — Papéis de acesso | CA01 (#31462) | ✅ CONFORME | 8/8 `ProtectedEndpointsTest` |
| #30974 — Privacidade e bloqueio | #31464/66 | ✅ CONFORME | 14/14 `CombinadoVinculoTest`, 403 sem vínculo |
| #30990 — Criação de combinado | CA01, CA05 | ✅ CONFORME | E2E (201 persistido) |
| #31011 — Campos obrigatórios | #31472 | ✅ CONFORME | E2E (mensagens de erro visíveis) + 5/5 unitários |
| #31031 — Múltiplos responsáveis | #31490 | ✅ CONFORME | 5/5 `ParticipantesResponsaveisTest` |
| #31039 — Busca de participante | CA01, CA04 | ✅ CONFORME (ver nota 2) | E2E + 7/7 `UsuarioBuscaEndpointTest` |
| #31046 — Sugestão automática | #31500 | ✅ CONFORME (por nome) | E2E `sprint2-fluxos.cy.ts` |
| #31053 — Listagem/remoção participantes | #31501 | ✅ CONFORME | E2E + unitários |
| #31074 — Exclusão de rascunhos | CA03 (#31512) | ❌ **NÃO CONFORME** (bug #2) | análise de código + API |
| #31086 — Aceite de proposta | CA01, CA02 | ⚠️ **CONFORME PARCIAL** (ver nota 3) | API (aceite via backend OK; fluxo UI pendente de convite real) |

**Testes automatizados executados:** 182/182 JUnit (backend) · 14/14 Vitest ·
E2E Cypress: 8/8 no total dos specs desta sprint (3 specs).

## 2. Bugs encontrados

### Bug #1 — Backend aceita e-mail sem ponto no domínio (#30913 / CA03)

- **Evidência:** `POST /api/usuarios` com `{"email":"sem@arroba"}` retorna **201**.
  Regex do `UsuarioService`: `^[A-Za-z0-9+_.-]+@(.+)$` — o grupo `(.+)` aceita
  domínio sem ponto. Em tela, o formulário também exibe "E-mail válido." para
  `maria@semdominio` (screenshot capturada no E2E).
- **Regressão:** `UsuarioEmailValidacaoTest` cobre `"semarroba"` (sem @) mas não
  `usuario@dominio` (sem ponto no domínio).
- **Impacto:** contas com e-mail malformado; conflita com a especificação CA03
  ("O sistema deve validar o formato do endereço de e-mail informado").
- **Sugestão de correção:** `^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)+$`
  (alinhada com `EMAIL_PATTERN` do frontend em `utils/validation.ts`) + caso de
  teste para `usuario@dominio`.

### Bug #2 — Exclusão/descarte de rascunho não implementado (#31074 / CA03)

- **Especificação:** CA03 (#31512): "O criador deve ter a opção de
  descartar/excluir um rascunho existente com confirmação prévia, removendo-o
  definitivamente da sua lista."
- **Evidência:** `CombinadoController` não possui `@DeleteMapping` (endpoints:
  criar, listar, detalhe, meus-rascunhos, publicar, aceitar, recusar, cancelar,
  concluir). O `DashboardPage.tsx` não renderiza botão "Excluir/Descartar" para
  rascunhos (apenas "Continuar rascunho" no card, `CombinadoCard.tsx`).
- **Impacto:** rascunhos só podem ser publicados ou abandonados; não podem ser
  removidos da lista — descumpre integralmente o critério.

### Nota 2 — Sugestão automática busca por nome, não por e-mail (#31046)

- US23 (#30725) especifica busca preditiva **por nome** ("digitar as primeiras
  letras do nome") e o backend (`/api/usuarios/buscar`) filtra por
  `findByNomeContainingIgnoreCase` — conforme. Já #31039 menciona "buscar
  e-mails válidos e inexistentes": a adição por e-mail exato funciona via Enter
  (`buscarUsuarioPorEmail`). Nenhum desvio: os dois fluxos coexistem. Registrar
  aqui apenas para clareza de homologação.

### Nota 3 — #31086 (aceite de proposta) parcial

- O backend responde corretamente: convite criado → aceito via
  `PATCH /api/convites/{id}/aceitar` → combinado **ATIVO**. Falta concluir o
  homologar o caminho 100% pela UI (o botão do card em "Convites recebidos"
  depende da lista carregar o convite real); unitários `ConviteRespostaHistoricoTest`
  8/8 aprovados cobrem o histórico do aceite.

## 3. Conclusão

Das **20 tasks de [po/validação] atribuídas à Maria Luiza na Sprint 2**:
- **17 CONFORMES**;
- **1 NÃO CONFORME por bug de produto** (#30913 — e-mail sem ponto aceito);
- **1 NÃO CONFORME por funcionalidade ausente** (#31074 — exclusão de rascunho);
- **1 PARCIAL** (#31086 — backend conforme, caminho UI a completar).

O atesto individual de cada task (formato do documento de referência) segue
nos arquivos `HOMOLOGACAO_*.md` e nos specs Cypress correspondentes.
