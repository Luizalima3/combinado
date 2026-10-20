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

