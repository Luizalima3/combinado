# Conferência das 43 tasks — Sprint 3

Data da verificação: 26/11/2026. Escopo: backend.

| Grupo | Tasks | Entrega |
| --- | --- | --- |
| Tarefas, rascunhos e ADMIN | #31014, #31020, #31064, #30960 | PR #11 |
| Convites e documentação | #31079, #32243, #31427, #31428 | PR #11 |
| Busca e ordenação de combinados | #32289, #32283, #32286 | 8ab60a1 |
| Consulta de convites | #32307, #32295, #32292 | 45b0573 |
| Filtros de combinados | #32310, #32313, #32316 | a2f8f79 |
| Detalhes e ações permitidas | #32298, #32301, #32304 | f5dadcd |
| Conclusão | #32322, #32325, #32330 | 9a2005b |
| Respostas aos convites | #32268, #32271, #32274 | 517b82e |
| Responsáveis e edição de tarefas | #31235, #31237, #31238 | 16a0c27 |
| Segurança e contratos existentes | #31325, #31342, #31346, #31350, #31356, #31358, #31366, #31374 | 04cb108 (local, ainda não enviado) |
| Histórico transacional | #31231, #31232, #31233 | 875dd03 |
| Polling, cancelamento e documentação | #32277, #32319, #31423 | Commit que inclui este relatório |

Total: 43 tasks conferidas localmente. Após este envio: 35 integradas à main, incluindo as 8 do PR #11; faltam publicar as 8 tasks do commit de segurança 04cb108.
Alguns itens já existiam e foram conferidos com testes e documentação; o total não
representa 43 implementações novas. Não altera automaticamente o OpenProject.

## Validação

Regressão deste conjunto: 296 testes de serviços e segurança/endpoints, sem falhas, erros ou casos ignorados. Inclui integração HTTP e persistência em H2, migrações, permissões,
concorrência, rollback de auditoria e contrato OpenAPI. Não é somente teste unitário.
Não foi feita homologação com PostgreSQL real e navegador. A investigação da exibição
dos convites ao destinatário na interface permanece adiada por solicitação do usuário.

A tabela identifica os commits publicados e o grupo que permanece local. O registro de horas deve seguir o esforço
real e a data do trabalho; a data de publicação no GitHub é independente.