# Autenticação — sprint 2

## #30922 — login

`POST /api/auth/login` recebe JSON com `email` e `senha`.
O Spring Security busca o usuário por e-mail e compara a senha com BCrypt.
E-mails são normalizados para minúsculas na busca.

Resposta de sucesso (200): `token`, `tipo` (`Bearer`), `expiraEm` e `usuario`
(ID, nome, e-mail, perfil e data de criação). Nenhuma senha ou hash é retornado.
Senha incorreta e usuário inexistente retornam 401 com a mesma mensagem.
JSON inválido ou campos obrigatórios inválidos retornam 400.

O cadastro existente (`POST /api/usuarios`) passa a salvar hashes BCrypt.
A migração Flyway V2 converte as senhas antigas em texto simples e preserva
valores que já têm o formato de um hash BCrypt. A migração é executada na
inicialização do backend, dentro da transação do Flyway.
O starter Flyway do Spring Boot 4 foi incluído para habilitar essa execução.
Se um banco existente foi criado apenas pelo Hibernate e não possui histórico
Flyway, confirme que `tb_usuarios` corresponde à migração V1 antes de executar
o baseline na versão 1. Não habilite baseline automático em produção sem essa
verificação. Nenhum banco local existente foi alterado durante a implementação.

Os testes HTTP usam banco H2 em memória, isolado do PostgreSQL local.
Os testes da migração executam o Flyway de verdade sobre esse banco de teste.

## #30934 — rotas privadas

As chamadas privadas exigem `Authorization: Bearer <token>`.
O filtro verifica assinatura e expiração e carrega o usuário pelo ID (`sub`).
As permissões são obtidas do perfil atual no banco, com o prefixo `ROLE_`.
Tokens de usuários removidos deixam de autenticar.

São públicos apenas `POST /api/auth/login`, `POST /api/usuarios` (cadastro),
Swagger UI e OpenAPI. As demais rotas exigem autenticação, incluindo consultas
de usuários, combinados e convites. Logout padrão, formulário e HTTP Basic
estão desabilitados. A API não cria nem reutiliza sessão HTTP para autenticar.
Falhas de autenticação retornam 401 JSON; permissões insuficientes, 403 JSON.

O frontend atual usa sessão simulada e não envia JWT. Suas chamadas privadas
serão bloqueadas até integrar o login real; essas tasks alteram somente o backend.
A verificação de vínculo entre usuário e combinado pertence à task #30968.

## #30930 — logout e revogação

`POST /api/auth/logout` exige o token atual no cabeçalho Authorization.
Retorna 204 após persistir o encerramento da sessão e revogar o token.
A tabela `tokens_revogados`, criada pela migração V3, registra o `jti`, ID do
usuário, horário do encerramento no servidor e expiração original do token.
O token completo não é armazenado.

O filtro consulta essa tabela em cada requisição autenticada. Reutilizar um
token revogado retorna 401, inclusive para um novo logout. A revogação é
persistida no banco e compartilhada entre instâncias do backend. Ela afeta
apenas aquele token: outras sessões do mesmo usuário continuam válidas.
O registro é preservado após a expiração para manter a evidência do logout.

O cliente também deve apagar sua cópia do token após o logout.
Login e logout não encerram nem alteram a sessão simulada do frontend atual.

## Validação local

Execute a partir de `backend`, com JDK 21 configurado:

```powershell
.\mvnw.cmd '-Dtest=br.edu.ifpb.combinado.service.*Test,br.edu.ifpb.combinado.security.*Test' test
```

Os testes usam H2 em memória; não alteram o PostgreSQL local.

## #30956 — perfis de acesso

`PerfilUsuario` define `ROLE_USUARIO` e `ROLE_ADMIN`, persistidos como texto na
entidade `Usuario`. O cadastro sempre atribui `ROLE_USUARIO`; o cliente não pode
se promover a administrador. Não foi criado endpoint de alteração de perfil.
A migração V4 converte os valores antigos `USUARIO`/`ADMIN`, atualiza o padrão
do banco. O enum define os perfis aceitos pela aplicação. Os JWTs novos usam esses mesmos nomes;
tokens antigos continuam sendo autenticados pelo perfil atual do banco.
