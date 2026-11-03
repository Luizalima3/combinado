# Histórico (#31231, #31232, #31233)

CombinadoHistoricoRepository permanece como repositório JPA dos eventos, com consulta
por combinado, ordenação crescente por ocorridoEm e desempate por ID. Carrega o autor
para produzir DTOs sem expor entidades.

HistoricoService centraliza os registros de criação, ativação, conclusão e cancelamento.
Os serviços de negócio fornecem o autor autenticado, a descrição e o motivo opcional.
O timestamp vem do servidor. A gravação exige uma transação existente (MANDATORY):
se a auditoria falhar, o estado da operação de negócio também é desfeito.

HistoricoController expõe a rota existente GET /api/combinados/{id}/historico, sem
duplicação de mapeamento. Preserva id, autorNome, descricao, acao e ocorridoEm, e inclui
autorId e motivo. A consulta exige vínculo ou ADMIN e protege rascunhos de terceiros.
O nome do autor é o snapshot da data do evento, mesmo após alterações no perfil.

Testes verificam ordem cronológica, desempate, campos persistidos, permissões e
rollback real da conclusão se a gravação do histórico falhar.

Adaptação à main atual: preserva os filtros por autorId, tipo e datas inclusivas em UTC, além dos eventos ALTERACAO_STATUS da equipe. A ativação continua passando pelo CombinadoService.
