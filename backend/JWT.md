# Tokens JWT — task #30926

O `JwtService` gera tokens assinados com HS256 a partir de um usuário persistido.
O método `gerarToken(usuario)` inclui:

- `iss`: `combinado`;
- `sub`: ID do usuário;
- `email` e `perfil`: identificação e perfil do usuário;
- `iat` e `exp`: emissão e expiração em UTC;
- `jti`: identificador único por token, disponível para futura revogação.

A senha não é incluída. O payload de um JWT é legível: assinatura não é criptografia.

## Configuração

As propriedades existentes em `application.properties` são utilizadas:

- `JWT_SECRET`: chave de assinatura com pelo menos 32 bytes. Em produção, defina um segredo aleatório fora do código.
- `JWT_EXPIRATION`: duração em milissegundos, no mínimo 1000. O padrão é 86400000 (24 horas). JWT representa os horários em segundos.

## Validação e integração

`validarToken(token)` recebe o token puro, sem o prefixo `Bearer`, e retorna um
`DecodedJWT` somente após verificar assinatura, algoritmo, emissor, presença dos
claims obrigatórios e validade temporal. Tokens inválidos lançam
`JWTVerificationException`; tokens expirados lançam `TokenExpiredException`.

A integração com login, filtros de autenticação e revogação pertence às próximas
tasks. O serviço não consulta o banco nem verifica se a conta foi desativada.

## Testes

No diretório `backend`, com JDK 21 configurado:

```powershell
.\mvnw.cmd '-Dtest=JwtServiceTest' test
```

Os testes são unitários e não precisam de PostgreSQL.
