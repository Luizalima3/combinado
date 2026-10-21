# Complementos da sprint 2

## Tarefas e responsabilidades (#31014, #31020)

`POST /api/combinados/{id}/responsabilidades` recebe descrição e `usuarioId` (um responsável)
ou `responsaveisIds` (vários). Usa a entidade Tarefa e sua relação N:N já existente (#31026),
sem criar uma segunda entidade incompatível. Os responsáveis precisam estar vinculados ao
combinado (criador, participante explícito ou destinatário de convite).

`POST /api/combinados/{id}/tarefas` recebe um array de 1 a 100 itens no mesmo formato.
Valida todos antes de persistir; falhas desfazem o lote inteiro. Chamadas sucessivas acrescentam
itens, sem substituir anteriores. Só o criador pode adicionar, antes de finalizar/cancelar.
`GET /api/combinados/{id}/tarefas` (alias `/responsabilidades`) retorna toda a coleção,
ordenada por ID, com resumos dos responsáveis sem credenciais. Exige vínculo ou ADMIN.

## Atualização de rascunhos (#31064)

`PUT /api/combinados/rascunhos/{id}` recebe CombinadoDTO. Exige criador e estado RASCUNHO.
Somente título é obrigatório; descrição, categoria, prazo e responsabilidades podem ficar vazios.
`participantesIds` omitido mantém os atuais; lista vazia remove vínculos. IDs precisam existir.
Identidade do criador e estado não são controlados pelo JSON. Salvar não publica nem envia convites.
Entrada inválida/registro publicado retorna 400; outra pessoa, inclusive ADMIN, recebe 403.

## Consulta de administrador (#30960)

ROLE_ADMIN pode listar e consultar todos os combinados, inclusive rascunhos, seus participantes
e tarefas. Usuário comum continua limitado aos vínculos. A permissão global é somente de leitura:
não concede edição, publicação, envio ou resposta de convites de terceiros. O filtro carrega
o perfil atual do banco, em vez de confiar somente no perfil gravado no JWT.
