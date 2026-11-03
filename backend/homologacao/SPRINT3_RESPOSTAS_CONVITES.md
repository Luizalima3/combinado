# Respostas aos convites (#32268, #32271, #32274)

Aceitar um convite PENDENTE registra o aceite, mas só muda AGUARDANDO_CONFIRMACAO
para ATIVO quando todos os participantes confirmarem. A regra considera os vínculos
e os destinatários dos últimos convites de cada pessoa. Participante sem convite,
pendente ou recusado impede a ativação. A ativação grava autor e data no histórico
do combinado com o tipo ALTERACAO_STATUS. Convites enviados individualmente ou em lote
também vinculam os destinatários ao combinado.

Recusar não avança nem rebaixa o estado global. Cria uma notificação para o criador,
na mesma transação da resposta e do histórico. Repetições continuam retornando 409.
PATCH /api/convites/{id}/recusar aceita corpo opcional:
{"motivo":"Não poderei participar."}. Motivo tem até 500 caracteres, remove espaços
nas extremidades e vazio/nulo vira null. O motivo permanece no convite e no evento
RECUSA e aparece na resposta e nos detalhes. A migration V13 cria colunas nullable.

As respostas acrescentam motivoRecusa e statusCombinado, preservando os campos antigos.
O aceite legado PATCH /api/combinados/{id}/aceitar usa a mesma regra e responde
somente o último convite do usuário autenticado. Não aceita automaticamente todos
os participantes nem usa a data informada pelo cliente como data da confirmação.
A rota legada de recusa do combinado continua sendo cancelamento exclusivo do criador.

Envios e respostas adquirem bloqueio no combinado antes do convite. Isso serializa
respostas simultâneas de participantes diferentes e evita perder a última confirmação.
Os testes usam transações reais separadas para concorrência e rollback da notificação.
Esta entrega é do backend. A investigação da interface continua adiada.
