# Homologação — Confirmação de senha e formulário de cadastro

**Task:** #30921 — [po/validação] Homologar confirmação de senha e formulário (#31439)
**Data:** 2026-10-22
**Escopo:** validação funcional de ponta a ponta do cadastro completo, atestando
conformidade com os critérios CA05, CA06 e CA07 (conforme especificados em
`backend/COMBINADO_TODOS_OS_ITENS.md`).

## Critérios avaliados

| Critério | ID OpenProject | Especificação |
| --- | --- | --- |
| CA05 | #31439 | O sistema deve permitir a confirmação da senha e impedir o cadastro quando as senhas forem diferentes. |
| CA06 | #31440 | Quando todos os dados forem válidos, o sistema deve criar o usuário e informar que o cadastro foi realizado com sucesso. |
| CA07 | #31442 | Quando houver dados inválidos, o sistema deve apresentar mensagens de erro claras ao usuário. |

## Evidências

### Frontend — testes unitários (Vitest)

- `npm test` → **14/14 aprovados**, incluindo `src/utils/validation.test.ts`
  (força da senha e `passwordsMatch` para senhas iguais/divergentes).

### Backend — testes unitários (JUnit/Maven, H2 em memória)

- `mvnw -Dtest=UsuarioConfirmacaoSenhaTest,UsuarioAlteracaoSenhaTest test`
  → **25/25 aprovados**:
  - `UsuarioConfirmacaoSenhaTest` (3): rejeita senhas divergentes, aceita senhas
    coincidentes e rejeita confirmação nula — sem persistir (`save` nunca chamado).
  - `UsuarioAlteracaoSenhaTest` (22): confirmação obrigatória na troca de senha.

### Frontend — ponta a ponta (Cypress, `cypress/e2e/cadastro.cy.ts`)

Executado contra backend (8080) e frontend Vite (5173) em execução — **3/3 aprovados**:

1. **CA05** — ao informar senha e confirmação diferentes, o formulário exibe
   "As senhas não coincidem." em tempo real e, ao submeter, bloqueia o envio com
   "A confirmação deve ser igual à senha."; o cadastro não é realizado.
2. **CA07** — ao submeter o formulário vazio, são exibidas mensagens de erro
   claras para nome, e-mail, senha e confirmação.
3. **CA06** — com dados válidos e senhas coincidentes ("Senhas conferem." exibido),
   o `POST /api/usuarios` retorna 201, o usuário é autenticado automaticamente
   (token persistido) e o toast "Conta criada com sucesso." é exibido.

## Camadas validadas

- **Frontend:** `RegisterPage.tsx` (validação inline e mensagens),
  `utils/validation.ts` (`passwordsMatch`, `getPasswordStrength`),
  `components/auth/PasswordStrengthBar.tsx`.
- **Backend:** `UsuarioService.cadastrar` ("A confirmação de senha não confere
  com a senha informada."), `UsuarioRequestDTO.isSenhasIguais`.

## Resultado

**HOMOLOGADO** — CA05, CA06 e CA07 conformes em todas as camadas (unitária frontend,
unitária backend e ponta a ponta).
