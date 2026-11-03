# Responsabilidades (#31235, #31237, #31238)

Reutiliza a entidade Responsabilidade e o ResponsabilidadeRepository já existentes, com vínculo ao combinado
e relação N:N com usuários responsáveis. Não cria tabela ou entidade duplicada.
O repositório acrescenta busca por ID da tarefa e ID do combinado, com os responsáveis
carregados, impedindo edição de tarefa de outro combinado pela troca do ID na URL.

Mantém POST /api/combinados/{id}/responsabilidades para um item, POST /tarefas para
adição múltipla e GET /tarefas ou /responsabilidades para a listagem completa.
Acrescenta PUT /api/combinados/{id}/tarefas/{tarefaId}, com alias
/api/combinados/{id}/responsabilidades/{tarefaId}.

O PUT recebe descricao e usuarioId ou responsaveisIds. Substitui descrição e conjunto
completo de responsáveis. Exige ao menos um responsável existente e vinculado ao
combinado. IDs repetidos não duplicam vínculos. Toda validação precede a alteração.
A mesma regra é usada na criação. Remover um vínculo não exclui o usuário nem a tarefa.

Somente o criador pode escrever, em RASCUNHO ou AGUARDANDO_CONFIRMACAO.
Demais estados retornam 400, usuários sem permissão 403 e sem autenticação 401.
Tarefa inexistente ou pertencente a outro combinado retorna 404.
ADMIN continua tendo consulta global, sem permissão adicional de escrita.
Rotas e DTOs estão documentados no Swagger e cobertos por testes de persistência e acesso.
