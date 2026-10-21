# Gestão de combinados — sprint 2

## #31042 — busca preditiva por nome

`GET /api/usuarios/buscar?q=ana&pagina=0&limite=10` exige JWT e retorna uma lista
resumida com `id`, `nome` e `email`. Busca trechos do nome sem diferenciar
maiúsculas/minúsculas, usando LIKE com os curingas do usuário escapados.
São necessários ao menos 2 caracteres; termos menores retornam lista vazia.
O limite aceito é de 1 a 50, com página inicial 0 e ordenação por nome e ID.
A busca por e-mail não faz parte deste endpoint.
# Participantes e responsáveis (#31026, #32246)

`Tarefa` pertence a um combinado e permite vários usuários responsáveis pela tabela
`tarefa_responsaveis`, com chave composta que impede duplicatas. Remover uma associação
ou tarefa não exclui usuários. Esta task modela a persistência; não adiciona CRUD de tarefas
nem converte automaticamente o texto legado `responsabilidades`.

`GET /api/combinados/{id}/participantes` exige vínculo com o combinado e retorna
somente id, nome, email e status do convite. Em reenvios prevalece o convite mais recente
(data e id); participantes cadastrados sem convite aparecem como PENDENTE.
O criador não é incluído automaticamente como participante.

## DTOs e consultas (#32249, #32252, #32228)

`GET /api/combinados` entrega DTOs com título, status, prazo e nome atual do criador
(JOIN com usuário). `nomeCriador`/`prazo` coexistem com `criadorNome`/`dataPrazo`
para compatibilidade. Registros legados sem vínculo de criador usam o nome armazenado.

`GET /api/convites/{id}` permite acesso ao destinatário ou criador do combinado.
Retorna categoria, título, resumos dos envolvidos e `participantesConfirmados`,
considerando apenas o último convite ACEITO de cada pessoa. Não expõe entidades,
senhas ou perfis. IDs inexistentes e convites de terceiros retornam 403.

## Estados, rascunhos e detalhes (#32258, #31071, #32255)

Transições permitidas: RASCUNHO → AGUARDANDO_CONFIRMACAO ou CANCELADO;
AGUARDANDO_CONFIRMACAO → ATIVO ou CANCELADO; ATIVO → CONCLUIDO ou CANCELADO.
CONCLUIDO e CANCELADO são terminais. Repetir o estado atual também é inválido.
Aceitar/recusar aplica a validação antes de alterar estado e timestamps.

`PATCH /api/combinados/{id}/concluir` e `PATCH /api/combinados/{id}/cancelar`
exigem autenticação do criador. Concluir só é permitido para combinados ATIVO;
cancelar segue as transições acima. A recusa individual de convite deve usar
`PATCH /api/convites/{id}/recusar`; a rota legada do combinado não substitui essa ação.

`GET /api/combinados/meus-rascunhos` lista somente rascunhos do usuário autenticado.
Rascunhos são privados ao criador, inclusive na listagem geral e nos detalhes.
`POST /api/combinados/{id}/publicar` exige criador e estado RASCUNHO.
Sem corpo, valida os dados armazenados; com corpo `CombinadoDTO`, permite completar
título, descrição, categoria, dataPrazo, responsabilidades e participantesIds.
São obrigatórios título, categoria, prazo atual/futuro e ao menos um participante
cadastrado diferente do criador. Nomes e estado enviados não controlam a identidade
do criador nem o estado final. Erros não alteram o registro.

A publicação mantém AGUARDANDO_CONFIRMACAO, conforme decisão de compatibilidade;
não dispara convites automaticamente (serviço de convites é uma task separada).
O marcador JSON legado `rascunho` é limpo sem perder os demais dados.
A migration V7 permite categoria nula enquanto o registro é rascunho.

`GET /api/combinados/{id}` retorna os dados detalhados em DTO compatível com a lista,
sem entidades de usuário. Permite criador, participante vinculado ou destinatário
de convite; rascunhos são exceção, restritos ao criador. Sem vínculo ou ID inexistente:
403; sem autenticação: 401. Status dos participantes fica no endpoint específico.

## Respostas e histórico de convites (#32231, #31229, #32393)

`PATCH /api/convites/{id}/aceitar` e `/recusar` exigem JWT e o destinatário do convite.
Retornam id, combinadoId, status (ACEITO/RECUSADO) e dataResposta em UTC.
O cliente não define autor nem data da resposta. Convites inexistentes ou de terceiros
retornam 403; sem autenticação, 401. Respostas repetidas, convites antigos substituídos
por reenvio e combinados em RASCUNHO/CONCLUIDO/CANCELADO retornam 409.

O envio continua em `POST /api/combinados/{id}/convites`, restrito ao criador.
Não permite convite para si, duplicação de pendência ou reenvio para quem já aceitou.
Após uma recusa, um novo convite pode ser enviado, mantendo o anterior e seu histórico.
Bloqueios de banco serializam envios por combinado e respostas por convite.

A migration V8 adiciona dataResposta e `convite_historico`. Envio, aceite e recusa
geram eventos com autorId, nome do autor no momento da ação e ocorridoEm.
Resposta e evento são gravados na mesma transação: uma falha no histórico desfaz
a alteração do status. `GET /api/convites/{id}` inclui a timeline em `historico`,
em ordem cronológica, com a mesma permissão de detalhes. Datas de respostas antigas
não são fabricadas; registros legados sem evidência permanecem sem data/histórico.

Estes endpoints alteram o estado de cada convite. Não alteram automaticamente o
estado global do combinado nem substituem os endpoints legados de resposta do combinado.

