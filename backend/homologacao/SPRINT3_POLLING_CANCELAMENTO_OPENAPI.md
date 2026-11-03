# Polling, cancelamento e OpenAPI (#32277, #32319, #31423)

GET /api/combinados/{id}/status permite polling, sugerido a cada 10 segundos pelo
cliente. Retorna id, status global, status dos participantes, ações permitidas e
consultadoEm UTC. A resposta usa Cache-Control: no-store e não carrega tarefas.
É somente leitura e compartilha permissões e cálculo de ações com os detalhes:
sem autenticação 401, sem vínculo 403 e ADMIN com leitura global. Não implementa
WebSocket nem altera a interface, conforme o escopo adiado pelo usuário.

A regra existente de cancelamento foi conferida: o criador pode cancelar em
RASCUNHO, AGUARDANDO_CONFIRMACAO ou ATIVO. CONCLUIDO/CANCELADO bloqueiam a ação.
O histórico mantém autor, timestamp e motivo opcional. Os testes cobrem os cinco
estados, permissões e ausência de novo evento em falhas.

AuthController, UsuarioController e UsuarioPerfilController documentam @Operation,
@ApiResponses, schemas e códigos de erro. Cadastro e login declaram segurança
vazia (públicos), e logout e demais rotas usam o bearerAuth global.
Parâmetros internos do servidor são ocultos. O JSON real de /v3/api-docs é testado.
