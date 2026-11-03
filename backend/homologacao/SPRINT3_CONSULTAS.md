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
