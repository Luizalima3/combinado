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
