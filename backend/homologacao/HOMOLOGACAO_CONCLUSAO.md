# Homologação — Fluxo de Conclusão do Combinado (Task #32326)

**Responsável:** Felipe Macedo  
**Sprint:** 3  
**User Stories cobertas:** US62, US63, US64, US66, US71, US72  

---

## 1. Critérios de Aceitação Homologados

| Critério | Descrição | Resultado | Evidência |
|---|---|---|---|
| **CA01 — Pré-condição de aceite total (US62)** | O combinado só pode ser concluído quando todos os participantes convidados tiverem aceitado (`StatusConvite.ACEITO`). Caso haja pendentes/recusados, retorna `422 Unprocessable Entity` com a lista `pendentes`. | ✅ **CONFORME** | `CombinadoVinculoTest`, `CombinadoConclusaoPermissaoParticipanteTest`, `CombinadoDetailPage.tsx` |
| **CA02 — Permissão de conclusão** | A rota `PATCH /api/combinados/{id}/concluir` valida o vínculo e papel do usuário autenticado, bloqueando usuários sem permissão com `403 Forbidden`. | ✅ **CONFORME** | `CombinadoConclusaoPermissaoParticipanteTest`, `CombinadoController.java` |
| **CA03 — Transição para `CONCLUIDO` e bloqueio de edição (US63)** | Após concluído, o status muda para `CONCLUIDO` e qualquer tentativa de edição (`PATCH /api/combinados/{id}`) é bloqueada com `409 Conflict`. | ✅ **CONFORME** | `CombinadoBloqueioEdicaoTest`, `CombinadoFormPage.tsx` |
| **CA04 — Registro no histórico com autor e data/hora (US64 / US72)** | A conclusão registra automaticamente um evento na timeline (`combinado_historico`) contendo o autor autenticado e o timestamp UTC (`ocorridoEm`). | ✅ **CONFORME** | `HistoricoRegistroConclusaoTest`, `HistoricoGravacaoObrigatoriaAutorDataTest` |
| **CA05 — Feedback visual de sucesso (US66)** | Ao concluir o combinado, o sistema exibe mensagem de confirmação (`SuccessToast` / toast de sucesso) e atualiza a linha do tempo na tela de detalhes. | ✅ **CONFORME** | `SuccessToast.tsx`, `SuccessToast.test.ts`, `timeline-status-concluido.cy.ts` |

---

## 2. Parecer Final de Homologação

O fluxo de conclusão do combinado atende integralmente aos critérios de negócio, segurança, auditoria na timeline e feedback visual definidos para a Sprint 3. **Homologado sem ressalvas.**
