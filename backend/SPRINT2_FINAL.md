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

## Convites em lote e recebidos (#31079, #32243)

`POST /api/combinados/{combinadoId}/convites/lote` recebe
`{"participantesIds":[2,3],"mensagem":"Opcional"}`. Exige criador, 1 a 100 IDs distintos,
existentes, sem o próprio criador. Aplica as regras do envio individual para estado do combinado,
pendências existentes e aceite anterior. Todos os participantes são validados antes de `saveAll`.
Convites PENDENTE, eventos ENVIO e notificações são persistidos na mesma transação; falha em
qualquer etapa desfaz todo o lote. Não publica rascunhos nem altera o estado global do combinado.

`GET /api/convites/recebidos?pagina=0&limite=20` usa o ID do JWT, nunca um ID enviado pelo cliente.
O limite aceito é de 1 a 100 e a página começa em zero. Retorna convites de todos os status,
do mais recente para o mais antigo, com título, categoria, criador, prazo, estado do combinado,
status do convite e datas. Exclui rascunhos. ADMIN também vê apenas os seus convites recebidos.
Reenvios aparecem como convites distintos, preservando o histórico de cada envio.

## Swagger e timeline (#31427, #31428)

`GET /api/convites/{id}/historico` expõe a timeline de eventos já persistidos, em ordem
cronológica, com autorId, autorNome e ocorridoEm UTC. Exige destinatário ou criador do convite.
O histórico também permanece disponível nos detalhes. Não inventa eventos legados.

Swagger UI em `/swagger-ui/index.html`, OpenAPI JSON em `/v3/api-docs`, ambos públicos.
As operações protegidas indicam bearerAuth e documentam envio individual/lote, recebidos,
detalhes, aceite/recusa, histórico, respostas de erro e esquemas. O evento documenta os tipos
ENVIO, ACEITE e RECUSA. RemetenteId do envio individual é legado e ignorado; respostas
de aceite/recusa não recebem corpo, autor ou data fornecidos pelo cliente.

Atualizado springdoc de 2.5.0 para 3.1.1: a linha 3 suporta Spring Boot 4, conforme a
[matriz oficial](https://springdoc.org/#what-is-the-compatibility-matrix-of-springdoc-openapi-with-spring-boot).
Os testes consultam o JSON gerado e a página Swagger para verificar a configuração em execução.
