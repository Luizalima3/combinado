# Edição de perfil — sprint 2

## #30940 — atualização do nome

`PUT /api/usuarios/perfil/nome`, com `Authorization: Bearer <token>`, recebe
`{"nome":"Novo nome"}` e retorna os dados públicos atualizados do usuário (200).
O usuário é obtido da autenticação; IDs, e-mail e perfil enviados no JSON não
alteram o alvo nem outros dados da conta.

Espaços nas extremidades são removidos. O nome deve ter entre 2 e 150 caracteres,
seguindo a validação existente na entidade `Usuario`. Nomes nulos, vazios, curtos
ou longos retornam 400; falta de autenticação retorna 401. O nome é persistido
em `tb_usuarios`, com atualização de `data_atualizacao` pelo Hibernate.

Não são necessárias migrações adicionais. Os nomes históricos armazenados nos
combinados permanecem como foram registrados; as permissões continuam usando IDs.
O endpoint é de backend; a tela de edição de perfil ainda precisa ser integrada.

## #30942 — testes da atualização do nome

Os testes unitários verificam atualização e persistência no usuário autenticado,
preservação de e-mail/senha/perfil, normalização de espaços, nomes inválidos e os
limites de 2 e 150 caracteres. Os testes HTTP complementam essa cobertura com
persistência real em H2, bloqueio sem JWT e tentativa de alterar outro usuário.

## #30948 — alteração da senha

`PUT /api/usuarios/perfil/senha` exige JWT e recebe `senhaAtual`, `novaSenha` e
`confirmacaoSenha`. O backend verifica a senha atual com `PasswordEncoder.matches`,
valida a nova senha e persiste um novo hash BCrypt. A resposta de sucesso é 204,
sem corpo. Senhas incorretas, fracas ou confirmação divergente retornam 400.

A nova senha segue o padrão do cadastro: pelo menos 6 caracteres, não vazia e no
máximo 72 bytes em UTF-8 (limite BCrypt). A confirmação precisa coincidir.
Espaços de uma senha válida são preservados; senhas não são normalizadas.
A conta alvo sempre é a autenticada. O DTO oculta credenciais em `toString` e
os erros de validação do endpoint não incluem os valores rejeitados.

A senha antiga deixa de autenticar novos logins. JWTs já emitidos continuam
válidos até expirar ou realizar logout; esta task não implementa revogação de
todas as sessões ao trocar senha. Nome, e-mail e perfil são preservados.

## #30950 — testes de credenciais e hash

Testes unitários com BCrypt real verificam sucesso, rejeição da senha atual
incorreta e de senhas novas fora do padrão, confirmação, limites em bytes e
preservação do hash quando a alteração é recusada. Os testes HTTP verificam
persistência real e login com a nova senha, rejeição da antiga e isolamento
entre contas. Nenhum teste altera o PostgreSQL local.

## #30944 — alteração de e-mail

`PUT /api/usuarios/perfil/email`, com JWT e corpo `{"email":"novo@exemplo.com","senhaAtual":"..."}`,
altera somente a conta autenticada. Exige senha atual correta e formato de e-mail válido
com até 150 caracteres. Remove espaços nas extremidades e normaliza para minúsculas.
Rejeita e-mail de outra conta sem distinguir maiúsculas/minúsculas (409); entradas inválidas
ou senha incorreta retornam 400; sem autenticação, 401. Manter o próprio e-mail é permitido.

O retorno 200 contém resumo do usuário, sem senha/hash. Nome, senha e perfil são preservados.
Depois da troca, novos logins usam o novo e-mail. O ID do JWT continua sendo a identidade;
tokens anteriores permanecem válidos e o filtro carrega o e-mail atual do banco.
Esta task não envia mensagens nem implementa confirmação por e-mail.
As regras de formato, unicidade e senha atual foram confirmadas pelo responsável da task.

## Executar testes

No diretório `backend`, com JDK 21 configurado:

```powershell
.\mvnw.cmd '-Dtest=br.edu.ifpb.combinado.service.*Test,br.edu.ifpb.combinado.security.*Test' test
```
