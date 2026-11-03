# Conclusão do combinado (#32322, #32325, #32330)

PATCH /api/combinados/{id}/concluir preserva os dados do combinado e inclui
message: "Combinado concluído com sucesso!" na resposta 200.
Os dados de saída são DTOs, sem entidades de usuário ou credenciais.

O endpoint mantém as regras atuais: estado ATIVO, usuário criador e confirmação de todos os participantes vinculados, considerando
o convite mais recente. Um pendente, recusado ou participante sem convite bloqueia
a conclusão com 422 e a lista de nomes em pendentes. Outros estados retornam 400.
Sem vínculo, 403. Sem autenticação, 401. ADMIN sem vínculo não recebe permissão de escrita.

Os testes de endpoint verificam todos aceitos, um pendente, um recusado, ausência
de convite, aceite substituído por pendência, sucesso em ATIVO e falha nos demais
estados. Conferem mensagem, persistência do estado, histórico com autor e timestamp
e ausência de evento duplicado em tentativa repetida.

Adaptação à main de 26/11: preserva conclusão exclusiva do criador e evento ALTERACAO_STATUS introduzidos pela equipe. Participante aceito também recebe 403.
