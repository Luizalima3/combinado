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
