# Detalhes do combinado (#32298, #32301, #32304)

GET /api/combinados/{id} mantém todos os campos anteriores do resumo e acrescenta:

- participantes: resumos com id, nome, email e status do convite mais recente.
  Inclui participantes vinculados e destinatários de convites. Sem convite, PENDENTE.
- tarefas: coleção completa ordenada por ID, com descrição e resumos dos responsáveis.
  Não expõe entidades de usuário, senhas, hashes ou perfis.
- acoesPermitidas: ações do combinado para o usuário autenticado e o estado atual.

EDITAR e ADICIONAR_TAREFAS exigem criador e estado RASCUNHO/AGUARDANDO_CONFIRMACAO.
PUBLICAR exige criador e RASCUNHO. Os dados obrigatórios continuam sendo validados
na publicação. CANCELAR exige criador e estado não final.
CONCLUIR exige ATIVO, papel autorizado pela regra atual (criador ou participante
aceito) e confirmação de todos os participantes vinculados. O cálculo usa a mesma
regra de confirmação aplicada pela conclusão efetiva.
VER_HISTORICO aparece para quem pode consultar o combinado.

Os valores são informativos para a interface. Os endpoints continuam verificando
as permissões e os dados a cada ação. Convites mantêm suas rotas próprias de resposta.
ADMIN sem vínculo tem leitura global, sem ações adicionais de alteração.
Rascunhos de terceiros permanecem restritos ao criador e à consulta global ADMIN.
Esta entrega altera apenas o backend, sem retomar a investigação adiada da interface.
