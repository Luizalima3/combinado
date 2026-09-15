# Combinado

Guia de setup e especificações do projeto completo Combinado, uma aplicação web para criação e gerenciamento de combinados entre usuários.

## Estrutura

- `backend/`: API REST em Spring Boot, Java 21, JPA e PostgreSQL.
- `frontend/`: interface React executada com Vite.
- `docker-compose.yml`: alternativa opcional para PostgreSQL local.

O front-end consome a API do back-end, e o back-end persiste os dados no PostgreSQL. O Flyway executa as migrations do banco durante a inicialização da API.

## Pré-requisitos

- JDK 17 ou superior (o projeto utiliza Java 21)
- Node.js 18 ou superior e npm
- PostgreSQL instalado localmente e em execução

Confira as versões instaladas:

```powershell
java --version
node --version
npm --version
psql --version
```

## Setup rápido sem Docker

Instale o PostgreSQL pelo instalador oficial ou pelo gerenciador de pacotes do Windows. Durante a instalação, crie ou mantenha:

| Parâmetro | Valor |
| --- | --- |
| Porta | `5432` |
| Banco | `combinado_db` |
| Usuário | `postgres` |
| Senha | `1234` |

Crie o banco pelo pgAdmin ou pelo `psql`:

```powershell
psql -U postgres -h localhost -p 5432 -c "CREATE DATABASE combinado_db;"
```

Se o serviço do PostgreSQL estiver parado, inicie-o pelo Windows Services ou pelo PowerShell como administrador:

```powershell
Get-Service postgresql*
Start-Service postgresql-x64-16
```

O nome do serviço pode variar conforme a versão instalada.

## Comandos de execução

Com o PostgreSQL em execução e o banco `combinado_db` criado, abra dois terminais na raiz do projeto.

No primeiro terminal, execute a API:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

No segundo terminal, execute o front-end:

```powershell
cd frontend
npm install
npm start
```

Para executar o front-end em modo de desenvolvimento, também é possível usar:

```powershell
cd frontend
npm run dev
```

A aplicação ficará disponível em `http://localhost:5173` e a API em `http://localhost:8080`.

## Banco de dados local

O back-end espera um PostgreSQL local com os seguintes parâmetros:

| Parâmetro | Valor |
| --- | --- |
| Host | `localhost` |
| Porta | `5432` |
| Banco | `combinado_db` |
| Usuário | `postgres` |
| Senha | `1234` |

As migrations do Flyway ficam em `backend/src/main/resources/db/migration` e são executadas quando o back-end inicia.

Para verificar a conexão:

```powershell
psql -U postgres -h localhost -p 5432 -d combinado_db -c "SELECT version();"
```

## Configuração do back-end

Os valores padrão estão em `backend/src/main/resources/application.properties`. Eles podem ser substituídos por variáveis de ambiente:

| Variável | Padrão |
| --- | --- |
| `DB_URL` | `jdbc:postgresql://localhost:5432/combinado_db` |
| `DB_USER` | `postgres` |
| `DB_PASSWORD` | `1234` |
| `JWT_SECRET` | valor local definido na configuração |
| `JWT_EXPIRATION` | `86400000` ms |

Em ambientes compartilhados ou de produção, defina credenciais e segredo JWT fora do código-fonte.

## Comandos úteis

### Back-end

```powershell
cd backend
.\mvnw.cmd clean package
.\mvnw.cmd test
```

### Front-end

```powershell
cd frontend
npm run dev
npm run build
npm run lint
npm run preview
```

## Documentação da API

Com o back-end em execução:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`

## Troubleshooting

Se a API não conectar ao banco, confirme se o serviço do PostgreSQL está em execução e teste:

```powershell
Get-Service postgresql*
psql -U postgres -h localhost -p 5432 -d combinado_db -c "SELECT 1;"
```

Se a porta `5432` estiver ocupada, altere a porta configurada no PostgreSQL e ajuste `DB_URL` para apontar para a nova porta.
