# Consultas da sprint 3

## Busca e ordenação de combinados (#32289, #32283, #32286)

GET /api/combinados preserva a consulta de todos os registros permitidos ao usuário.
GET /api/combinados/criados limita sempre ao criador autenticado, inclusive para ADMIN.
Ambas retornam o array de DTOs existente e aceitam titulo (trecho, sem diferenciar caixa).
Espaços nas extremidades são removidos e %, _ e ! são literais. Limite de 255 caracteres.

Ordenação padrão: criadoEm desc, id desc. Aceita sort=criadoEm,asc,
sort=prazo,asc (alias dataPrazo) e sort=status,desc. Para múltiplos critérios,
repita sort, até três vezes. Status segue a ordem textual dos valores do enum.
Campos e direções são validados. Um desempate por id desc evita ordem instável.
A busca e a ordenação ocorrem no banco, juntamente com as permissões.

Usuários comuns só consultam registros vinculados. Rascunhos de terceiros ficam
privados. ADMIN tem consulta global, mas não recebe permissões adicionais de escrita.

## Busca, status e ordem dos convites (#32307, #32295, #32292)

GET /api/convites/recebidos aceita titulo com as mesmas regras da busca em criados.
status aceita PENDENTE, ACEITO ou RECUSADO e filtra o convite, não o estado do combinado.
Sem status, mantém todos. Os filtros ocorrem no banco antes de pagina/limite.

O padrão continua dataEnvio desc, id desc. sort=criadoEm,desc é um alias para a data
do envio do convite, como solicitado pela task. sort=dataEnvio,asc também é aceito.
Nunca usa a data de criação do combinado para essa ordenação. Desempates usam id desc.
Mesmo com filtros e como ADMIN, o usuário consulta somente os próprios convites
recebidos. Rascunhos de terceiros não aparecem. Resultado sem correspondências é [].

## Categoria, estado e período (#32310, #32313, #32316)

As consultas /api/combinados e /api/combinados/criados aceitam status do combinado,
como RASCUNHO, AGUARDANDO_CONFIRMACAO, ATIVO, CONCLUIDO e CANCELADO.
/api/convites/recebidos mantém status do convite (PENDENTE, ACEITO ou RECUSADO).

As três listagens aceitam categoria. Preservam os enums atuais, como EMPRESTIMO_OBJETO
e DIVISAO_DESPESA, com aliases EMPRESTIMO e DESPESA. Categoria inválida retorna 400.
Os filtros são combináveis com título, status e ordenação, antes de paginar recebidos.

Conforme regra confirmada pelo usuário, dataInicio é o limite inferior da criação
do combinado (a partir de 00:00 do dia informado) e dataFim é o limite superior
do prazo de vencimento (inclusive). Formato: YYYY-MM-DD. Podem ser usados separados.
Intervalo invertido e datas inválidas retornam 400. Sem prazo, o registro não aparece
quando dataFim é informado. Em recebidos, dataInicio continua sendo a criação do
combinado, enquanto sort=criadoEm ordena pela data do convite.
