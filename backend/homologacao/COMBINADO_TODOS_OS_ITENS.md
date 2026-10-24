# Projeto COMBINADO — Exportação estruturada

COMBINADO - EXPORTAÇÃO COMPLETA
================================================================================
COMBINADO - EXPORTAÇÃO COMPLETA
Exportado em: 24/10/2026, 14:36:12
Total informado pela API: 942
Total coletado: 942
Filtros de status, tipo e responsável: NENHUM
CONTAGEM POR TIPO
Epic: 41
Feature: 34
User story: 123
Task: 437
Critério de Aceitação: 293
Bug: 12
Melhoria: 2
CONTAGEM POR STATUS
In progress: 14
New: 727
Developed: 9
Closed: 191
On hold: 1
================================================================================

## Hierarquia


### [EPIC] #30564 — 01 — Autenticação e Usuários

- Pai: Não informado
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30564/activity)

**Descrição:**

Criado: 2026-09-02T18:54:02.752Z
Atualizado: 2026-10-22T04:52:15.084Z
Este EPIC contempla as funcionalidades relacionadas ao cadastro, autenticação e gerenciamento dos usuários da plataforma Combinado, incluindo cadastro, login, logout, edição de perfil e controle de acesso aos dados.

#### [EPIC] #30609 — 01.1 — Cadastro de Usuário

- Pai: 30564
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30609/activity)

**Descrição:**

Criado: 2026-09-02T21:34:19.617Z
Atualizado: 2026-10-22T04:52:18.681Z
Tela e lógica para novos usuários se registrarem com nome, e-mail e senha.

##### [FEATURE] #30668 — F01 — Cadastro de Usuário

- Pai: 30609
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30668/activity)

**Descrição:**

Criado: 2026-09-02T22:26:01.477Z
Atualizado: 2026-10-22T04:52:23.087Z
F01 — Cadastro de Usuário

Funcionalidade responsável por permitir que novos usuários realizem seu cadastro na plataforma Combinado, informando nome, e-mail e senha.

Critérios de Aceitação:

CA01 — Dados básicos:
O sistema deve permitir o cadastro de um usuário informando nome, e-mail e senha.

CA02 — Campos obrigatórios:
O sistema deve impedir o cadastro quando os campos obrigatórios não forem preenchidos.

CA03 — E-mail:
O sistema deve validar o formato do endereço de e-mail informado.

CA04 — Força da senha:
O sistema deve validar se a senha atende aos requisitos mínimos definidos para o cadastro.

CA05 — Confirmação de senha:
O sistema deve permitir a confirmação da senha e impedir o cadastro quando as senhas forem diferentes.

CA06 — Cadastro realizado:
Quando todos os dados forem válidos, o sistema deve criar o usuário e informar que o cadastro foi realizado com sucesso.

CA07 — Mensagens de erro:
Quando houver dados inválidos, o sistema deve apresentar mensagens de erro claras ao usuário.

###### [USER STORY] #30703 — US01 - Cadastro básico de usuário com nome, e-mail e senha

- Pai: 30668
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30703/activity)

**Descrição:**

Criado: 2026-09-03T00:18:38.781Z
Atualizado: 2026-10-22T04:52:27.327Z
História do Usuário

Como usuário não cadastrado,
quero realizar meu cadastro informando nome, e-mail e senha,
para poder acessar a plataforma Combinado.

BDD

Cenário: Cadastro de usuário com dados válidos

Dado que estou na tela de cadastro
E informo um nome válido
E informo um e-mail válido
E informo uma senha válida
Quando confirmar o cadastro
Então o sistema deve criar o usuário
E deve informar que o cadastro foi realizado com sucesso.

Critérios de Aceitação Associados
#31431, #31434, #31440, #31442

###### [TASK] #30897 — [back-end] Criar entidade Usuario e mapeamentos JPA

- Pai: 30703
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30897/activity)

**Descrição:**

Criado: 2026-09-04T17:44:07.852Z
Atualizado: 2026-09-16T01:00:59.120Z
Criar a entidade Usuário no backend da aplicação, contemplando os atributos necessários para o cadastro básico do usuário: nome, e-mail e senha.

###### [TASK] #30898 — [banco] Criar migration/script DDL para tabela de usuários

- Pai: 30703
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30898/activity)

**Descrição:**

Criado: 2026-09-04T17:44:49.840Z
Atualizado: 2026-09-16T01:00:59.093Z
Criar a tabela de Usuário no banco de dados PostgreSQL para armazenar os dados necessários ao cadastro, incluindo nome, e-mail e senha.

###### [TASK] #30899 — [back-end] Implementar service e endpoint de cadastro de usuário

- Pai: 30703
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30899/activity)

**Descrição:**

Criado: 2026-09-04T17:45:38.179Z
Atualizado: 2026-09-16T01:00:58.975Z
Implementar no backend o serviço e o endpoint responsáveis pelo cadastro de usuários, recebendo os dados do formulário e persistindo o novo usuário no banco de dados.

###### [TASK] #30900 — [front-end] Implementar tela e formulário de cadastro de usuário

- Pai: 30703
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30900/activity)

**Descrição:**

Criado: 2026-09-04T17:46:21.468Z
Atualizado: 2026-10-22T02:11:18.311Z
Implementar a tela de cadastro no frontend, permitindo que o usuário informe nome, e-mail e senha e envie os dados para o backend.

###### [TASK] #30901 — [testes] Testar fluxo de cadastro e validações de dados (#31431, #31434)

- Pai: 30703
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30901/activity)

**Descrição:**

Criado: 2026-09-04T17:47:09.103Z
Atualizado: 2026-10-22T01:05:00.058Z
Realizar testes do fluxo de cadastro com dados válidos e inválidos, verificando a criação do usuário e o comportamento esperado da aplicação.

###### [USER STORY] #30704 — US02 - Cadastro de usuário com validação de formato de e-mail

- Pai: 30668
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30704/activity)

**Descrição:**

Criado: 2026-09-03T00:19:37.228Z
Atualizado: 2026-10-22T04:52:32.357Z
História do Usuário

Como usuário não cadastrado,
quero que o sistema valide o e-mail informado no cadastro,
para evitar o uso de endereços inválidos na plataforma Combinado.

BDD

Cenário: Validação de e-mail inválido no cadastro

Dado que estou na tela de cadastro
E informo um nome válido
E informo uma senha válida
E informo um e-mail em formato inválido
Quando tentar realizar o cadastro
Então o sistema deve impedir o cadastro
E deve apresentar uma mensagem informando que o e-mail é inválido.

Critérios de Aceitação Associados
#31436, #31442

###### [TASK] #30910 — [back-end] Implementar validação de formato e unicidade de e-mail

- Pai: 30704
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30910/activity)

**Descrição:**

Criado: 2026-09-04T19:16:01.033Z
Atualizado: 2026-09-16T01:01:28.127Z
Adicionar anotação de Bean Validation (@Email) e regra de integridade no DTO/serviço de cadastro para validar formato de e-mail.

###### [TASK] #30911 — [front-end] Implementar validação client-side e feedback de e-mail

- Pai: 30704
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30911/activity)

**Descrição:**

Criado: 2026-09-04T19:16:50.817Z
Atualizado: 2026-10-22T02:02:45.922Z
Criar validação com regex no formulário React e exibir mensagem de erro visual caso o e-mail seja inválido.

###### [TASK] #30912 — [testes] Testar validações de e-mail duplicado e formato inválido

- Pai: 30704
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30912/activity)

**Descrição:**

Criado: 2026-09-04T19:17:34.546Z
Atualizado: 2026-10-22T01:05:16.808Z
Criar testes unitários com JUnit/Mockito cobrindo cenários de e-mails válidos e formatos inválidos.

###### [TASK] #30913 — [po/validação] Homologar regras de validação de e-mail (#31436)

- Pai: 30704
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30913/activity)

**Descrição:**

Criado: 2026-09-04T19:18:09.877Z
Atualizado: 2026-10-22T02:49:14.974Z
Testar em tela o bloqueio de envio e a exibição correta da mensagem de feedback de e-mail inválido.

###### [USER STORY] #30705 — US03 - Cadastro de usuário com validação de força de senha

- Pai: 30668
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30705/activity)

**Descrição:**

Criado: 2026-09-03T00:20:15.908Z
Atualizado: 2026-10-22T04:52:41.579Z
História do Usuário
Como usuário não cadastrado,
quero que o sistema valide os requisitos mínimos de segurança da senha (mínimo de 6 caracteres),
para garantir a proteção da minha conta na plataforma Combinado.

BDD
Cenário: Senha com menos de 6 caracteres informada no cadastro
Dado que estou na tela de cadastro
E informo um nome válido
E informo um e-mail válido
E informo uma senha com menos de 6 caracteres
Quando tentar realizar o cadastro
Então o sistema deve impedir o cadastro
E deve exibir mensagem informando que a senha deve conter no mínimo 6 caracteres.

Critérios de Aceitação Associados
#31437, #31442

###### [TASK] #30914 — [back-end] Implementar validação de requisitos mínimos de senha

- Pai: 30705
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30914/activity)

**Descrição:**

Criado: 2026-09-04T19:28:51.082Z
Atualizado: 2026-09-16T01:01:32.395Z
Adicionar anotação @Size(min = 6) no DTO de cadastro no Spring Boot e tratar a mensagem de erro de validação.

###### [TASK] #30915 — [front-end] Implementar indicador visual de força de senha

- Pai: 30705
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30915/activity)

**Descrição:**

Criado: 2026-09-04T19:29:31.344Z
Atualizado: 2026-10-22T02:12:00.073Z
Criar validação de tamanho mínimo de 6 caracteres no formulário React com feedback visual imediato ao digitar a senha.

###### [TASK] #30916 — [testes] Testar validações de complexidade de senha

- Pai: 30705
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30916/activity)

**Descrição:**

Criado: 2026-09-04T19:30:11.894Z
Atualizado: 2026-10-22T01:25:42.558Z
Criar testes unitários com JUnit validando rejeição de senhas curtas (&lt; 6 caracteres) e aceitação de senhas válidas.

###### [TASK] #30917 — [po/validação] Homologar mensagens e requisitos de senha (#31437)

- Pai: 30705
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30917/activity)

**Descrição:**

Criado: 2026-09-04T19:30:46.308Z
Atualizado: 2026-10-22T00:06:47.931Z
Validar se as mensagens de erro exibidas atendem aos critérios CA04 e CA07 e homologar o comportamento no fluxo de cadastro.

###### [USER STORY] #30706 — US04 - Cadastro de usuário com confirmação de senha

- Pai: 30668
- Status: In progress
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30706/activity)

**Descrição:**

Criado: 2026-09-03T00:22:03.100Z
Atualizado: 2026-10-22T04:52:50.195Z
História do Usuário
Como usuário não cadastrado,
quero confirmar minha senha digitando-a novamente no formulário de cadastro,
para garantir que não cometi erros de digitação e acessar a plataforma sem problemas.

BDD
Cenário: Senhas divergentes informadas no cadastro
Dado que estou na tela de cadastro
E preencho nome e e-mail válidos
E informo uma senha válida
E informo uma confirmação de senha diferente da senha digitada
Quando tentar realizar o cadastro
Então o sistema deve impedir o cadastro
E deve exibir uma mensagem informando que as senhas não conferem.

Critérios de Aceitação Associados
#31439, #31442

###### [TASK] #30918 — [back-end] Implementar validação de correspondência de senha

- Pai: 30706
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30918/activity)

**Descrição:**

Criado: 2026-09-04T19:32:20.077Z
Atualizado: 2026-09-16T01:01:38.235Z
Criar validação de correspondência entre os campos &#39;senha&#39; e &#39;confirmacaoSenha&#39; no DTO de registro antes de prosseguir com a criação da conta.

###### [TASK] #30919 — [front-end] Implementar campo de confirmação de senha com validação

- Pai: 30706
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30919/activity)

**Descrição:**

Criado: 2026-09-04T19:32:52.963Z
Atualizado: 2026-10-22T02:12:39.562Z
Adicionar o input &#39;Confirmar Senha&#39; no formulário React, com verificação de igualdade e alerta visual antes da submissão.

###### [TASK] #30920 — [testes] Testar validação de senhas divergentes e coincidentes

- Pai: 30706
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30920/activity)

**Descrição:**

Criado: 2026-09-04T19:33:17.653Z
Atualizado: 2026-10-22T01:05:22.971Z
Criar testes unitários com JUnit validando que o backend rejeita payloads com senhas incompatíveis e aceita senhas idênticas.

###### [TASK] #30921 — [po/validação] Homologar confirmação de senha e formulário (#31439)

- Pai: 30706
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30921/activity)

**Descrição:**

Criado: 2026-09-04T19:33:46.287Z
Atualizado: 2026-10-22T01:07:09.278Z
Realizar validação funcional de ponta a ponta do cadastro completo, atestando conformidade com os critérios CA05, CA06 e CA07.

###### [CRITÉRIO DE ACEITAÇÃO] #31449 — CA-F01.1 - Cadastro de Usuário

- Pai: 30668
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31449/activity)

**Descrição:**

Criado: 2026-09-05T20:52:48.922Z
Atualizado: 2026-09-16T02:07:43.336Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31431 — CA01 - Dados básicos

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31431/activity)

**Descrição:**

Criado: 2026-09-05T20:25:09.028Z
Atualizado: 2026-09-05T20:53:45.624Z
O sistema deve permitir o cadastro de um usuário informando nome, e-mail e senha.

###### [CRITÉRIO DE ACEITAÇÃO] #31434 — CA02 - Campos obrigatórios

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31434/activity)

**Descrição:**

Criado: 2026-09-05T20:26:33.866Z
Atualizado: 2026-09-05T20:54:49.742Z
O sistema deve impedir o cadastro quando os campos obrigatórios não forem preenchidos.

###### [CRITÉRIO DE ACEITAÇÃO] #31436 — CA03 - E-mail

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31436/activity)

**Descrição:**

Criado: 2026-09-05T20:26:53.937Z
Atualizado: 2026-09-05T20:55:11.457Z
O sistema deve validar o formato do endereço de e-mail informado.

###### [CRITÉRIO DE ACEITAÇÃO] #31437 — CA04 - Força da senha

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31437/activity)

**Descrição:**

Criado: 2026-09-05T20:27:13.014Z
Atualizado: 2026-09-05T20:55:32.330Z
O sistema deve validar se a senha atende aos requisitos mínimos definidos para o cadastro.

###### [CRITÉRIO DE ACEITAÇÃO] #31439 — CA05 - Confirmação de senha

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31439/activity)

**Descrição:**

Criado: 2026-09-05T20:27:30.614Z
Atualizado: 2026-09-05T20:55:54.632Z
O sistema deve permitir a confirmação da senha e impedir o cadastro quando as senhas forem diferentes.

###### [CRITÉRIO DE ACEITAÇÃO] #31440 — CA06 - Cadastro realizado

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31440/activity)

**Descrição:**

Criado: 2026-09-05T20:27:48.297Z
Atualizado: 2026-09-05T20:56:17.062Z
Quando todos os dados forem válidos, o sistema deve criar o usuário e informar que o cadastro foi realizado com sucesso.

###### [CRITÉRIO DE ACEITAÇÃO] #31442 — CA07 - Mensagens de erro

- Pai: 31449
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31442/activity)

**Descrição:**

Criado: 2026-09-05T20:28:06.549Z
Atualizado: 2026-09-05T20:56:41.541Z
Quando houver dados inválidos, o sistema deve apresentar mensagens de erro claras ao usuário.

#### [EPIC] #30610 — 01.2 — Login e Logout

- Pai: 30564
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30610/activity)

**Descrição:**

Criado: 2026-09-02T21:38:53.234Z
Atualizado: 2026-10-22T04:49:10.209Z
Autenticação com JWT, gerenciamento de sessão e logout.

##### [FEATURE] #30671 — F02 — Entrar e Sair

- Pai: 30610
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30671/activity)

**Descrição:**

Criado: 2026-09-02T23:58:05.016Z
Atualizado: 2026-10-22T04:49:17.003Z
F02 — Entrar e Sair
Funcionalidade responsável por gerenciar o acesso autenticado de usuários à plataforma Combinado através de login com e-mail e senha, geração de tokens JWT seguros, invalidação de sessão no logout e proteção das rotas privadas.

Critérios de Aceitação:
CA01 — Autenticação com credenciais válidas:
O sistema deve autenticar o usuário quando o e-mail e a senha informados coincidirem com os dados cadastrados.

CA02 — Bloqueio de credenciais inválidas:
O sistema deve impedir o login caso o e-mail não exista ou a senha esteja incorreta, retornando mensagem de erro sem expor detalhes sensíveis.

CA03 — Emissão de Token JWT:
Ao realizar login com sucesso, o sistema deve gerar e retornar um token JWT com tempo de expiração e dados de identificação do usuário.

CA04 — Logout e encerramento de sessão:
O sistema deve permitir que o usuário faça logout a qualquer momento, invalidando a sessão e removendo o token no cliente.

CA05 — Proteção de rotas privadas:
O sistema deve impedir o acesso a rotas restritas por usuários não autenticados, redirecionando para a tela de login.

###### [USER STORY] #30707 — US05 - Login básico com e-mail e senha

- Pai: 30671
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30707/activity)

**Descrição:**

Criado: 2026-09-03T00:22:53.848Z
Atualizado: 2026-10-22T04:49:30.618Z
História do Usuário
Como usuário cadastrado,
quero realizar login informando meu e-mail e minha senha,
para acessar meus combinados e utilizar a plataforma.

BDD
Cenário 1: Login com credenciais válidas
Dado que estou na tela de login
E informo um e-mail cadastrado
E informo a senha correta
Quando confirmar o login
Então o sistema deve autenticar meu acesso
E deve redirecionar para a página principal dos combinados.

Cenário 2: Login com senha incorreta
Dado que estou na tela de login
E informo um e-mail cadastrado
E informo uma senha incorreta
Quando confirmar o login
Então o sistema deve impedir o acesso
E deve exibir uma mensagem amigável de erro de autenticação.

Critérios de Aceitação Associados
#31444, #31445

###### [TASK] #30922 — [back-end] Implementar serviço de autenticação com Spring Security

- Pai: 30707
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30922/activity)

**Descrição:**

Criado: 2026-09-04T19:41:07.528Z
Atualizado: 2026-10-20T22:01:32.450Z
Criar endpoint de autenticação (/api/auth/login) e serviço para buscar usuário por e-mail e validar senha criptografada.

###### [TASK] #30923 — [front-end] Implementar tela e formulário de autenticação

- Pai: 30707
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30923/activity)

**Descrição:**

Criado: 2026-09-04T19:41:48.871Z
Atualizado: 2026-10-22T02:03:38.029Z
Desenvolver formulário de login em React com campos de e-mail e senha, botão de confirmação e exibição de alertas de erro.

###### [TASK] #30924 — [testes] Testar credenciais válidas e inválidas no login

- Pai: 30707
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30924/activity)

**Descrição:**

Criado: 2026-09-04T19:42:27.484Z
Atualizado: 2026-10-22T01:05:29.742Z
Criar testes unitários com JUnit/Mockito validando cenários de credenciais válidas, senha incorreta e e-mail inexistente.

###### [TASK] #30925 — [po/validação] Homologar fluxo de login e mensagens de erro (#31444, #31445)

- Pai: 30707
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30925/activity)

**Descrição:**

Criado: 2026-09-04T19:43:01.262Z
Atualizado: 2026-10-22T02:15:38.166Z
Homologar o comportamento funcional do login em conformidade com os critérios CA01 e CA02.

###### [USER STORY] #30708 — US06 - Login com geração de token JWT

- Pai: 30671
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30708/activity)

**Descrição:**

Criado: 2026-09-03T00:23:22.510Z
Atualizado: 2026-10-22T04:49:33.975Z
História do Usuário
Como usuário autenticado,
quero que o sistema gere um token JWT após o login bem-sucedido,
para que minhas requisições subsequentes sejam autenticadas com segurança e sem necessidade de reenviar minhas credenciais.

BDD
Cenário: Emissão de token JWT após autenticação com sucesso
Dado que informei credenciais válidas na tela de login
Quando o sistema confirmar minha autenticação
Então deve ser gerado um token JWT assinado contendo minha identificação e prazo de expiração
E esse token deve ser retornado para o cliente para autenticar as próximas requisições.

Critérios de Aceitação Associados
#31446

###### [TASK] #30926 — [back-end] Implementar geração e assinatura de Token JWT

- Pai: 30708
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30926/activity)

**Descrição:**

Criado: 2026-09-04T19:44:44.962Z
Atualizado: 2026-10-20T21:46:32.711Z
Criar classe JwtService com métodos para gerar e assinar tokens com chave secreta, definindo claims de usuário e tempo de expiração no Spring Boot.

###### [TASK] #30927 — front-end] Armazenar token JWT e configurar interceptor HTTP

- Pai: 30708
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30927/activity)

**Descrição:**

Criado: 2026-09-04T19:45:19.586Z
Atualizado: 2026-10-22T02:08:45.698Z
Salvar o token retornado (em memória/storage) no client React e configurar interceptor HTTP (Axios) para injetar o header &#39;Authorization: Bearer &lt;token&gt;&#39; nas chamadas.

###### [TASK] #30928 — [testes] Testar ciclo de vida e validação do token JWT

- Pai: 30708
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30928/activity)

**Descrição:**

Criado: 2026-09-04T19:45:49.278Z
Atualizado: 2026-10-22T01:05:36.326Z
Criar testes unitários e de integração com JUnit validando integridade da assinatura, extração de claims e recusa de tokens expirados.

###### [TASK] #30929 — [po/validação] Homologar persistência da sessão e segurança (#31446)

- Pai: 30708
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30929/activity)

**Descrição:**

Criado: 2026-09-04T19:46:16.367Z
Atualizado: 2026-10-22T01:14:27.885Z
Validar se o token gerado cumpre as regras de negócio e os parâmetros definidos no critério CA03.

###### [USER STORY] #30709 — US07 - Logout com invalidação de token

- Pai: 30671
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30709/activity)

**Descrição:**

Criado: 2026-09-03T00:23:46.896Z
Atualizado: 2026-10-22T04:49:43.272Z
História do Usuário
Como usuário autenticado,
quero realizar logout da plataforma a qualquer momento,
para encerrar minha sessão com segurança e garantir que ninguém acesse meus combinados pelo meu dispositivo.

BDD
Cenário: Encerramento de sessão com sucesso
Dado que estou autenticado na plataforma
Quando acionar a opção &quot;Sair&quot; / &quot;Logout&quot;
Então o sistema deve limpar as credenciais e o token JWT armazenados no cliente
E deve redirecionar para a tela de login impedindo o retorno pelo botão de voltar do navegador.

Critérios de Aceitação Associados
#31447

###### [TASK] #30930 — [back-end] Implementar endpoint de logout e revogação de token

- Pai: 30709
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30930/activity)

**Descrição:**

Criado: 2026-09-04T19:47:31.698Z
Atualizado: 2026-10-20T22:02:07.445Z
Criar endpoint /api/auth/logout no Spring Boot para registro do encerramento de sessão e suporte a blacklist de tokens, se aplicável.

###### [TASK] #30931 — [front-end] Implementar botão de logout e limpeza de estado local

- Pai: 30709
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30931/activity)

**Descrição:**

Criado: 2026-09-04T19:48:05.462Z
Atualizado: 2026-10-22T02:08:25.659Z
Adicionar ação de logout no cabeçalho/menu, limpando o token JWT do localStorage/sessionStorage e do contexto global da aplicação React.

###### [TASK] #30932 — [testes] Testar encerramento de sessão e descarte de credenciais

- Pai: 30709
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30932/activity)

**Descrição:**

Criado: 2026-09-04T19:48:45.290Z
Atualizado: 2026-10-22T01:05:45.072Z
Criar testes unitários e funcionais validando que, após o logout, requisições subsequentes sem token ou com token invalidado são recusadas.

###### [TASK] #30933 — [po/validação] Homologar encerramento seguro de sessão (#31447)

- Pai: 30709
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30933/activity)

**Descrição:**

Criado: 2026-09-04T19:49:19.767Z
Atualizado: 2026-10-22T01:35:27.702Z
Homologar o fluxo de saída do usuário e confirmar redirecionamento seguro para a tela pública inicial.

###### [USER STORY] #30710 — US08 - Proteção de rotas para usuários não autenticados

- Pai: 30671
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30710/activity)

**Descrição:**

Criado: 2026-09-03T00:24:17.708Z
Atualizado: 2026-10-22T04:49:46.409Z
História do Usuário
Como usuário não autenticado,
quero ser impedido de acessar páginas ou dados privados da plataforma sem realizar login,
para garantir a segurança, privacidade e integridade das informações dos combinados.

BDD
Cenário: Tentativa de acesso direto a rota restrita sem autenticação
Dado que não realizei login no sistema (não possuo token JWT válido)
Quando tentar acessar diretamente a URL de uma rota protegida (ex: &quot;/combinados&quot;)
Então o sistema deve bloquear a visualização do conteúdo privado
E deve me redirecionar automaticamente para a tela de login.

Critérios de Aceitação Associados
#31451

###### [TASK] #30934 — [back-end] Configurar filtros de autorização no Spring Security

- Pai: 30710
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30934/activity)

**Descrição:**

Criado: 2026-09-04T19:54:03.838Z
Atualizado: 2026-10-20T22:01:50.346Z
Configurar o SecurityFilterChain no backend para exigir autenticação em todas as rotas privadas (/api/combinados/\*\*), liberando apenas endpoints públicos (/api/auth/\*\*).

###### [TASK] #30935 — [front-end] Implementar proteção de rotas com PrivateRoute

- Pai: 30710
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30935/activity)

**Descrição:**

Criado: 2026-09-04T19:55:20.951Z
Atualizado: 2026-10-22T02:01:47.253Z
Criar componente de proteção de rotas no React Router que verifica a existência de token válido e redireciona para a tela de login caso ausente.

###### [TASK] #30936 — [testes] Testar bloqueio de rotas privadas sem credenciais

- Pai: 30710
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30936/activity)

**Descrição:**

Criado: 2026-09-04T19:56:24.736Z
Atualizado: 2026-10-22T01:06:00.207Z
Criar testes automatizados validando resposta HTTP 401/403 no backend e o redirecionamento correto na interface web.

###### [TASK] #30937 — [po/validação] Homologar restrições de acesso a rotas privadas (#31451)

- Pai: 30710
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30937/activity)

**Descrição:**

Criado: 2026-09-04T19:57:01.453Z
Atualizado: 2026-10-22T01:43:07.014Z
Homologar o fluxo de bloqueio em rotas privadas, garantindo que o comportamento segue o critério CA05.

###### [CRITÉRIO DE ACEITAÇÃO] #31450 — CA-F02.1 - Login e Logout

- Pai: 30671
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31450/activity)

**Descrição:**

Criado: 2026-09-05T20:57:15.447Z
Atualizado: 2026-09-16T02:07:44.530Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31444 — CA01 - Autenticação com credenciais válidas

- Pai: 31450
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31444/activity)

**Descrição:**

Criado: 2026-09-05T20:44:48.977Z
Atualizado: 2026-09-05T20:57:43.500Z
O sistema deve permitir a autenticação do usuário mediante fornecimento de e-mail e senha cadastrados e válidos.

###### [CRITÉRIO DE ACEITAÇÃO] #31445 — CA02 - Bloqueio de acesso com credenciais inválidas

- Pai: 31450
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31445/activity)

**Descrição:**

Criado: 2026-09-05T20:45:15.587Z
Atualizado: 2026-09-05T20:58:14.761Z
O sistema deve bloquear a tentativa de login quando o e-mail não existir ou a senha estiver incorreta, exibindo mensagem genérica de erro sem expor detalhes sensíveis.

###### [CRITÉRIO DE ACEITAÇÃO] #31446 — CA03 - Emissão de Token JWT

- Pai: 31450
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31446/activity)

**Descrição:**

Criado: 2026-09-05T20:45:42.434Z
Atualizado: 2026-09-05T20:58:49.326Z
Ao realizar login com sucesso, o sistema deve gerar um token JWT com tempo de expiração e dados de identificação do usuário para autenticação das requisições subsequentes.

###### [CRITÉRIO DE ACEITAÇÃO] #31447 — CA04 - Encerramento de sessão (Logout)

- Pai: 31450
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31447/activity)

**Descrição:**

Criado: 2026-09-05T20:48:43.518Z
Atualizado: 2026-09-05T20:59:25.305Z
O sistema deve permitir que o usuário realize logout a qualquer momento, invalidando a sessão e removendo o token armazenado no cliente.

###### [CRITÉRIO DE ACEITAÇÃO] #31451 — CA05 - Proteção de rotas privadas

- Pai: 31450
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31451/activity)

**Descrição:**

Criado: 2026-09-05T21:00:15.350Z
Atualizado: 2026-09-05T21:00:15.398Z
O sistema deve impedir que usuários não autenticados acessem rotas protegidas da aplicação, redirecionando-os automaticamente para a tela de login.

#### [EPIC] #30611 — 01.3 — Edição de Perfil

- Pai: 30564
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-14
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30611/activity)

**Descrição:**

Criado: 2026-09-02T21:39:37.562Z
Atualizado: 2026-10-22T04:49:50.398Z
Permitir que o usuário edite seus dados básicos (nome, e-mail, senha).

##### [FEATURE] #30672 — F03 — Edição de Perfil

- Pai: 30611
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30672/activity)

**Descrição:**

Criado: 2026-09-02T23:58:45.811Z
Atualizado: 2026-10-22T04:49:58.327Z
F03 — Edição de Perfil
Funcionalidade responsável por permitir que o usuário autenticado visualize e atualize suas informações cadastrais (nome, e-mail e senha) com validação de integridade e segurança.

Critérios de Aceitação:
CA01 — Atualização de nome:
O sistema deve permitir que o usuário altere seu nome de exibição, persistindo a alteração no banco de dados.

CA02 — Atualização de e-mail com garantia de unicidade:
O sistema deve permitir a alteração do e-mail cadastrado, garantindo que o novo endereço seja válido e não pertença a outro usuário.

CA03 — Alteração segura de senha:
O sistema deve permitir a troca de senha somente após a confirmação da senha atual e a validação dos requisitos mínimos da nova senha.

CA04 — Feedback visual e mensagens:
O sistema deve exibir confirmação de sucesso ao salvar alterações e mensagens amigáveis em caso de dados inválidos ou falha de validação.Permitir que o usuário edite seus dados básicos (nome, e-mail, senha).

###### [USER STORY] #30711 — US09 - Edição básica do nome do usuário

- Pai: 30672
- Status: Developed
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30711/activity)

**Descrição:**

Criado: 2026-09-03T00:24:45.801Z
Atualizado: 2026-10-22T04:50:02.503Z
História do Usuário
Como usuário autenticado,
quero editar meu nome de exibição no meu perfil,
para manter meus dados de identificação corretos e atualizados na plataforma Combinado.

BDD
Cenário: Alteração de nome com sucesso
Dado que estou autenticado e acesso a tela de edição de perfil
Quando informar um novo nome válido
E confirmar a alteração
Então o sistema deve atualizar meu nome no banco de dados
E exibir uma mensagem informando que o perfil foi atualizado com sucesso.

Critérios de Aceitação Associados
#31456, #31459

###### [TASK] #30940 — [back-end] Implementar endpoint de atualização do nome de perfil

- Pai: 30711
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30940/activity)

**Descrição:**

Criado: 2026-09-04T20:05:52.082Z
Atualizado: 2026-10-20T23:08:21.286Z
Criar endpoint PUT /api/usuarios/perfil/nome no Spring Boot com DTO de validação e persistência do novo nome no banco de dados.

###### [TASK] #30941 — [front-end] Implementar formulário para alteração de nome

- Pai: 30711
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30941/activity)

**Descrição:**

Criado: 2026-09-04T20:06:18.099Z
Atualizado: 2026-10-22T01:59:39.303Z
Desenvolver tela/modal de perfil em React com campo de edição do nome, botão de salvar e exibição de feedback de confirmação.

###### [TASK] #30942 — [testes] Testar atualização de nome e persistência no banco

- Pai: 30711
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30942/activity)

**Descrição:**

Criado: 2026-09-04T20:06:43.533Z
Atualizado: 2026-10-21T01:17:08.862Z
Criar testes unitários com JUnit validando a atualização correta do nome e rejeição de entradas vazias ou excessivamente longas.

###### [TASK] #30943 — [po/validação] Homologar atualização de nome do perfil (#31456, #31459)

- Pai: 30711
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30943/activity)

**Descrição:**

Criado: 2026-09-04T20:07:30.539Z
Atualizado: 2026-10-22T02:16:42.067Z
Homologar a atualização de nome em tela e certificar cumprimento dos critérios CA01 e CA04.

###### [USER STORY] #30712 — US10 - Edição do e-mail do usuário

- Pai: 30672
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30712/activity)

**Descrição:**

Criado: 2026-09-03T00:25:16.209Z
Atualizado: 2026-10-22T01:59:12.834Z
História do Usuário
Como usuário autenticado,
quero editar o endereço de e-mail associado à minha conta,
para manter meu canal de contato atualizado e garantir o recebimento de notificações na plataforma Combinado.

BDD
Cenário 1: Alteração de e-mail com sucesso
Dado que estou autenticado e acesso a tela de edição de perfil
Quando informar um novo e-mail em formato válido
E esse e-mail não estiver em uso por outro usuário
E confirmar a alteração
Então o sistema deve atualizar meu e-mail de acesso
E deve exibir uma mensagem informando que o e-mail foi alterado com sucesso.

Cenário 2: Tentativa de alteração para e-mail já cadastrado
Dado que estou na tela de edição de perfil
Quando informar um e-mail que já pertence a outra conta
E tentar salvar
Então o sistema deve impedir a alteração
E deve apresentar mensagem informando que o e-mail já está em uso.

Critérios de Aceitação Associados
#31457, #31459

###### [TASK] #30944 — [back-end] Implementar validação e alteração de e-mail no perfil

- Pai: 30712
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30944/activity)

**Descrição:**

Criado: 2026-09-04T20:08:39.668Z
Atualizado: 2026-10-21T01:46:09.424Z
(Sem descrição)

###### [TASK] #30945 — [front-end] Implementar formulário de alteração de e-mail

- Pai: 30712
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30945/activity)

**Descrição:**

Criado: 2026-09-04T20:09:03.520Z
Atualizado: 2026-10-22T01:59:21.088Z
Adicionar campo de edição de e-mail no formulário React, com validação de formato e tratamento visual de erros de duplicidade retornados pela API.

###### [TASK] #30946 — [testes] Testar unicidade e integridade na troca de e-mail

- Pai: 30712
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30946/activity)

**Descrição:**

Criado: 2026-09-04T20:09:27.234Z
Atualizado: 2026-10-21T23:33:38.666Z
Criar testes unitários com JUnit validando sucesso na troca de e-mail, rejeição de formato inválido e bloqueio de e-mail já existente.

###### [TASK] #30947 — [po/validação] Homologar fluxo de atualização de e-mail (#31457, #31459)

- Pai: 30712
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30947/activity)

**Descrição:**

Criado: 2026-09-04T20:09:49.943Z
Atualizado: 2026-10-22T02:17:05.787Z
Homologar o comportamento funcional do formulário garantindo mensagens claras para e-mail duplicado e confirmação de sucesso.

###### [USER STORY] #30713 — US11 - Edição de senha do usuário com validação da senha atual

- Pai: 30672
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30713/activity)

**Descrição:**

Criado: 2026-09-03T00:25:47.886Z
Atualizado: 2026-10-22T01:58:24.225Z
História do Usuário
Como usuário autenticado,
quero alterar minha senha informando a senha atual e a nova senha com confirmação,
para manter minha conta segura e garantir que apenas eu possa realizar essa alteração.

BDD
Cenário 1: Alteração de senha com senha atual correta
Dado que estou na tela de alteração de senha do perfil
E informo minha senha atual correta
E informo uma nova senha válida (mínimo de 6 caracteres)
E confirmo a nova senha corretamente
Quando acionar a confirmação de alteração
Então o sistema deve criptografar e atualizar minha senha
E deve apresentar mensagem de sucesso informando que a senha foi alterada.

Cenário 2: Tentativa de alteração informando senha atual incorreta
Dado que estou na tela de alteração de senha do perfil
Quando informar uma senha atual divergente da cadastrada
E tentar salvar
Então o sistema deve impedir a troca da senha
E deve apresentar mensagem de erro informando que a senha atual é inválida.

Critérios de Aceitação Associados
#31458, #31459

###### [TASK] #30948 — [back-end] Implementar validação de senha atual e criptografia da nova

- Pai: 30713
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30948/activity)

**Descrição:**

Criado: 2026-09-04T20:11:02.471Z
Atualizado: 2026-10-20T23:08:55.379Z
Criar endpoint PUT /api/usuarios/perfil/senha no Spring Boot que valida a senha atual via PasswordEncoder.matches(), verifica os critérios da nova senha e salva o novo hash BCrypt.

###### [TASK] #30949 — [front-end] Implementar tela de alteração de senha de perfil

- Pai: 30713
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30949/activity)

**Descrição:**

Criado: 2026-09-04T20:11:28.654Z
Atualizado: 2026-10-22T01:58:24.188Z
Desenvolver formulário em React contendo os campos &#39;Senha Atual&#39;, &#39;Nova Senha&#39; e &#39;Confirmar Nova Senha&#39;, com validações de tamanho mínimo, coincidência e alertas visuais.

###### [TASK] #30950 — [testes] Testar redefinição de credenciais e integridade de hash

- Pai: 30713
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30950/activity)

**Descrição:**

Criado: 2026-09-04T20:11:53.321Z
Atualizado: 2026-10-20T23:09:12.692Z
Criar testes unitários com JUnit validando sucesso na troca de senha, recusa para senha atual incorreta e bloqueio para nova senha fora do padrão mínimo.

###### [TASK] #30952 — [po/validação] Homologar alteração de senha no perfil (#31458, #31459)

- Pai: 30713
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30952/activity)

**Descrição:**

Criado: 2026-09-04T20:12:19.308Z
Atualizado: 2026-10-22T02:17:32.543Z
Homologar o fluxo completo de alteração de senha e verificar se as mensagens de erro e sucesso estão aderentes aos critérios CA03 e CA04.

###### [CRITÉRIO DE ACEITAÇÃO] #31455 — CA-F03.1 - Edição de Perfil

- Pai: 30672
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31455/activity)

**Descrição:**

Criado: 2026-09-05T21:06:44.486Z
Atualizado: 2026-09-16T02:07:45.693Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31456 — CA01 - Atualização do nome de exibição

- Pai: 31455
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31456/activity)

**Descrição:**

Criado: 2026-09-05T21:07:09.966Z
Atualizado: 2026-09-05T21:07:10.020Z
O sistema deve permitir que o usuário autenticado atualize seu nome no perfil, persistindo os dados corretamente.

###### [CRITÉRIO DE ACEITAÇÃO] #31457 — CA02 - Validação e unicidade do novo e-mail

- Pai: 31455
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31457/activity)

**Descrição:**

Criado: 2026-09-05T21:07:36.965Z
Atualizado: 2026-09-05T21:07:37.010Z
O sistema deve validar o formato do novo e-mail e impedir alterações para endereços já em uso por outra conta.

###### [CRITÉRIO DE ACEITAÇÃO] #31458 — CA03 - Alteração de senha com validação da atual

- Pai: 31455
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31458/activity)

**Descrição:**

Criado: 2026-09-05T21:08:00.340Z
Atualizado: 2026-09-05T21:08:00.398Z
A alteração de senha exige a conferência da senha atual e a validação das regras de complexidade da nova senha.

###### [CRITÉRIO DE ACEITAÇÃO] #31459 — CA04 - Feedback e mensagens de validação

- Pai: 31455
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31459/activity)

**Descrição:**

Criado: 2026-09-05T21:08:22.614Z
Atualizado: 2026-09-05T21:08:22.683Z
O sistema deve exibir confirmação visual em caso de sucesso ou alertas claros caso os dados informados sejam inválidos.

#### [EPIC] #30612 — 01.4 — Controle de Acesso

- Pai: 30564
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-13
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30612/activity)

**Descrição:**

Criado: 2026-09-02T21:40:25.341Z
Atualizado: 2026-10-22T01:57:51.065Z
Autorização baseada em roles e permissões (ex: só o criador pode editar o combinado).

##### [FEATURE] #30673 — F04 — Controle de Acesso

- Pai: 30612
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-13
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30673/activity)

**Descrição:**

Criado: 2026-09-02T23:59:20.493Z
Atualizado: 2026-10-22T01:57:51.040Z
F04 — Controle de Acesso
Funcionalidade responsável por gerenciar regras de autorização baseadas em perfis (Roles) e níveis de permissão, garantindo a privacidade dos combinados e a governança administrativa na plataforma.

Critérios de Aceitação:
CA01 — Definição de Perfis de Acesso:
O sistema deve suportar os perfis de acesso USUÁRIO e ADMIN, atribuídos a cada conta cadastrada.

CA02 — Permissão de Administrador:
Usuários com perfil ADMIN devem possuir permissão para visualizar todos os combinados cadastrados na plataforma para fins de auditoria e moderação.

CA03 — Restrição de Acesso aos Combinados:
Usuários comuns só podem visualizar e interagir com combinados nos quais figurem explicitamente como criador ou participante convidado.

CA04 — Bloqueio de Acesso Não Autorizado:
Tentativas de acesso a combinados alheios por usuários comuns devem ser bloqueadas com retorno de status de erro de permissão (HTTP 403 Forbidden).

###### [USER STORY] #30714 — US12 - Definição de perfis de usuário (USUÁRIO e ADMIN)

- Pai: 30673
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30714/activity)

**Descrição:**

Criado: 2026-09-03T00:26:31.356Z
Atualizado: 2026-10-22T01:57:51.021Z
História do Usuário
Como administrador da plataforma,
quero que o sistema defina os perfis de acesso USUÁRIO e ADMIN para as contas cadastradas,
para estruturar os níveis de permissão e autorização adequados no sistema Combinado.

BDD
Cenário: Atribuição de perfil padrão no cadastro
Dado que um novo usuário realiza cadastro na plataforma
Quando a conta for criada com sucesso
Então o sistema deve atribuir por padrão o perfil &quot;USUÁRIO&quot;
E deve permitir a promoção para o perfil &quot;ADMIN&quot; apenas por procedimentos administrativos autorizados.

Critérios de Aceitação Associados
#31462

###### [TASK] #30956 — [back-end] Modelar enum Role e persistência de perfis de acesso

- Pai: 30714
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30956/activity)

**Descrição:**

Criado: 2026-09-04T20:14:26.231Z
Atualizado: 2026-10-20T22:02:26.633Z
Criar enum PerfilUsuario (ROLE\_USUARIO, ROLE\_ADMIN) e mapear o relacionamento na entidade Usuario no Spring Boot e JPA.

###### [TASK] #30957 — [front-end] Mapear perfis de usuário no contexto de autenticação

- Pai: 30714
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30957/activity)

**Descrição:**

Criado: 2026-09-04T20:14:54.476Z
Atualizado: 2026-10-22T01:57:50.986Z
Extrair e armazenar o papel do usuário logado a partir do token JWT e disponibilizar via contexto global no React.

###### [TASK] #30958 — [testes] Testar atribuição e consistência dos perfis de acesso

- Pai: 30714
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30958/activity)

**Descrição:**

Criado: 2026-09-04T20:15:23.460Z
Atualizado: 2026-10-22T01:06:16.784Z
Criar testes unitários com JUnit validando a atribuição automática de ROLE\_USUARIO no cadastro e regras de serialização no token JWT.

###### [TASK] #30959 — [po/validação] Homologar conformidade dos papéis de acesso (#31462)

- Pai: 30714
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30959/activity)

**Descrição:**

Criado: 2026-09-04T20:16:03.516Z
Atualizado: 2026-10-22T02:18:40.999Z
Homologar o mapeamento de perfis no sistema atestando conformidade com a especificação do critério CA01.

###### [USER STORY] #30715 — US13 - Permissão de ADMIN para visualizar todos os combinados

- Pai: 30673
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30715/activity)

**Descrição:**

Criado: 2026-09-03T00:27:11.970Z
Atualizado: 2026-09-05T21:15:33.596Z
História do Usuário
Como administrador da plataforma,
quero ter permissão para visualizar todos os combinados cadastrados na aplicação,
para realizar auditoria, moderação e acompanhamento global do sistema Combinado.

BDD
Cenário: Visualização global de combinados por administrador
Dado que estou autenticado com um perfil que possui a permissão &quot;ROLE\_ADMIN&quot;
Quando acessar a listagem geral de combinados ou consultar qualquer combinado por ID
Então o sistema deve permitir o acesso completo aos dados do combinado
E não deve aplicar o filtro restritivo de participação.

Critérios de Aceitação Associados
#31463

###### [TASK] #30960 — [back-end] Implementar controle de acesso e consulta geral para ADMIN

- Pai: 30715
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30960/activity)

**Descrição:**

Criado: 2026-09-04T20:17:14.720Z
Atualizado: 2026-09-11T20:07:07.762Z
Configurar anotação @PreAuthorize(&quot;hasRole(&#39;ADMIN&#39;) or ...&quot;) nos endpoints de consulta de combinados no Spring Boot para liberar visualização global a administradores.

###### [TASK] #30961 — [front-end] Implementar visão administrativa e painel de combinados (CA02)

- Pai: 30715
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30961/activity)

**Descrição:**

Criado: 2026-09-04T20:17:52.207Z
Atualizado: 2026-10-24T17:13:21.938Z
Criar painel ou filtro na interface React que identifica o perfil ADMIN e exibe a opção de visualização de todos os acordos da plataforma.

###### [TASK] #30964 — [testes] Testar autorização e consultas com perfil de ADMIN

- Pai: 30715
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30964/activity)

**Descrição:**

Criado: 2026-09-04T20:18:27.765Z
Atualizado: 2026-10-24T16:59:05.773Z
Criar testes unitários e de integração com JUnit/Mockito validando que usuários com perfil ADMIN recebem status 200 OK ao acessar combinados dos quais não são criadores nem participantes.

###### [TASK] #30965 — [po/validação] Homologar regras de visualização e permissão de ADMIN (#31463)

- Pai: 30715
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30965/activity)

**Descrição:**

Criado: 2026-09-04T20:19:24.201Z
Atualizado: 2026-10-24T17:08:13.245Z
Homologar as regras de acesso do perfil administrativo e atestar cumprimento integral do critério CA02.

###### [USER STORY] #30716 — US14 - Permissão de acesso apenas para criador e participantes do combinado

- Pai: 30673
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-13
- Prazo: 2026-10-13
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30716/activity)

**Descrição:**

Criado: 2026-09-03T00:27:47.773Z
Atualizado: 2026-10-22T01:57:12.278Z
História do Usuário
Como usuário comum da plataforma,
quero que o acesso aos detalhes e dados de um combinado seja restrito unicamente ao seu criador e aos participantes convidados,
para preservar o sigilo e a privacidade dos acordos firmados.

BDD
Cenário 1: Usuário envolvido acessa o combinado
Dado que sou o criador ou um participante convidado de um determinado combinado
Quando solicitar a visualização dos dados desse combinado
Então o sistema deve autorizar a requisição
E deve exibir os detalhes completos do acordo.

Cenário 2: Usuário não participante tenta acessar combinado alheio
Dado que sou um usuário comum autenticado
E tento acessar os detalhes de um combinado no qual não sou criador nem participante
Quando enviar a requisição
Então o sistema deve bloquear a visualização
E deve retornar erro de acesso negado (HTTP 403 Forbidden).

Critérios de Aceitação Associados
#31464, #31466

###### [TASK] #30968 — [back-end] Implementar verificação de vínculo criador e participante

- Pai: 30716
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30968/activity)

**Descrição:**

Criado: 2026-09-04T20:21:20.171Z
Atualizado: 2026-10-20T22:02:45.535Z
Criar método no repositório/serviço ou validação com @PreAuthorize que verifica se o usuário autenticado é criador ou participante do ID informado antes de retornar a entidade Combinado.

###### [TASK] #30969 — [front-end] Implementar tela de acesso negado e interceptação de erro 403

- Pai: 30716
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-13
- Prazo: 2026-10-13
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30969/activity)

**Descrição:**

Criado: 2026-09-04T20:21:54.325Z
Atualizado: 2026-10-22T01:57:12.228Z
Adicionar tratamento no interceptor HTTP do React para capturar status 403 Forbidden e exibir tela ou alerta amigável de &#39;Acesso Não Autorizado&#39;.

###### [TASK] #30972 — [testes] Testar bloqueio de acesso a combinados não vinculados

- Pai: 30716
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30972/activity)

**Descrição:**

Criado: 2026-09-04T20:22:38.114Z
Atualizado: 2026-10-22T01:06:24.840Z
Criar testes automatizados com JUnit validando que requisições de usuários alheios ao combinado recebem HTTP 403 Forbidden e requisições legítimas recebem HTTP 200 OK.

###### [TASK] #30974 — [po/validação] Homologar privacidade e bloqueio de acesso (#31464, #31466)

- Pai: 30716
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30974/activity)

**Descrição:**

Criado: 2026-09-04T20:23:19.292Z
Atualizado: 2026-10-22T02:21:44.731Z
Homologar os fluxos de bloqueio de acesso entre diferentes perfis de usuários atestando cumprimento dos critérios CA03 e CA04.

###### [CRITÉRIO DE ACEITAÇÃO] #31461 — CA-F04.1 - Controle de Acesso

- Pai: 30673
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31461/activity)

**Descrição:**

Criado: 2026-09-05T21:11:31.726Z
Atualizado: 2026-09-16T02:07:46.896Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31462 — CA01 - Segregação de papéis de acesso (USUÁRIO e ADMIN)

- Pai: 31461
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31462/activity)

**Descrição:**

Criado: 2026-09-05T21:12:06.187Z
Atualizado: 2026-09-05T21:12:06.242Z
O sistema deve manter papéis distintos (USUÁRIO e ADMIN), restringindo telas e operações administrativas apenas a administradores.

###### [CRITÉRIO DE ACEITAÇÃO] #31463 — CA02 - Visualização irrestrita para ADMIN

- Pai: 31461
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31463/activity)

**Descrição:**

Criado: 2026-09-05T21:12:44.103Z
Atualizado: 2026-09-05T21:12:44.155Z
Usuários com perfil ADMIN possuem permissão para auditar e visualizar todos os combinados registrados na plataforma.

###### [CRITÉRIO DE ACEITAÇÃO] #31464 — CA03 - Restrição de privacidade entre usuários comuns

- Pai: 31461
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31464/activity)

**Descrição:**

Criado: 2026-09-05T21:13:20.427Z
Atualizado: 2026-09-05T21:13:20.471Z
Usuários padrão só podem visualizar e interagir com acordos em que constem expressamente como criador ou participante.

###### [CRITÉRIO DE ACEITAÇÃO] #31466 — CA04 - Tratamento e bloqueio de acesso não autorizado (HTTP 403)

- Pai: 31461
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31466/activity)

**Descrição:**

Criado: 2026-09-05T21:13:56.571Z
Atualizado: 2026-09-05T21:13:56.761Z
Tentativas de acesso direto a combinados alheios devem ser bloqueadas com código HTTP 403 e tela de acesso restrito.

### [EPIC] #30568 — 02 — Criação de Combinados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30568/activity)

**Descrição:**

Criado: 2026-09-02T18:54:34.370Z
Atualizado: 2026-10-21T01:08:23.530Z
Este EPIC contempla as funcionalidades necessárias para criação e preparação de combinados, permitindo informar título, descrição, categoria, prazo, responsabilidades e participantes, além de salvar e editar combinados em rascunho.

#### [EPIC] #30613 — 02.1 — Criação com Dados Básicos

- Pai: 30568
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-04
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30613/activity)

**Descrição:**

Criado: 2026-09-02T21:40:58.422Z
Atualizado: 2026-10-22T02:03:07.817Z
Tela e lógica para criar combinado com título, descrição, categoria e prazo.

##### [FEATURE] #30674 — F05 — Criar Combinado

- Pai: 30613
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30674/activity)

**Descrição:**

Criado: 2026-09-02T23:59:49.484Z
Atualizado: 2026-10-22T02:03:07.791Z
F05 — Criar Combinado
Funcionalidade responsável por viabilizar o cadastro e a estruturação inicial de acordos cotidianos, permitindo definir título, descrição detalhada, categoria e data limite de cumprimento com validação de dados obrigatórios.

Critérios de Aceitação:
CA01 — Dados Essenciais:
O sistema deve permitir a criação de um combinado informando obrigatoriamente título, descrição, categoria e prazo de vigência/cumprimento.

CA02 — Seleção de Categoria:
O sistema deve disponibilizar a seleção de uma das categorias predefinidas da plataforma (Empréstimo de objeto, Divisão de despesa, Prestação de serviço, Cuidado de animal, Uso de espaço, Entrega/devolução ou Outro).

CA03 — Definição de Prazo Válido:
O sistema deve exigir uma data limite válida e posterior à data atual de criação do combinado.

CA04 — Validação de Campos Obrigatórios:
O sistema deve impedir a criação do combinado se algum dos campos obrigatórios não for preenchido, apresentando alertas visuais claros.

CA05 — Confirmação de Cadastro:
Após o preenchimento válido, o sistema deve salvar o combinado com o status inicial correspondente e exibir confirmação ao criador.

###### [USER STORY] #30717 — US15 - Criação básica de combinação com título e descrição

- Pai: 30674
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30717/activity)

**Descrição:**

Criado: 2026-09-03T00:28:23.558Z
Atualizado: 2026-10-22T02:03:07.756Z
História do Usuário
Como criador de um acordo,
quero cadastrar um novo combinado informando seu título e sua descrição detalhada,
para formalizar com clareza o objetivo e os termos do acordo na plataforma Combinado.

BDD
Cenário: Criação de combinado com dados básicos válidos
Dado que estou autenticado e acesso o formulário de criação de combinados
Quando preencher um título válido
E preencher uma descrição detalhada do acordo
E acionar o salvamento
Então o sistema deve registrar o novo combinado associado ao meu usuário
E deve exibir uma mensagem informando que o combinado foi criado com sucesso.

Critérios de Aceitação Associados
#31468, #31472, #31473

###### [TASK] #30983 — [back-end] Implementar entidade e endpoint de criação de combinado (CA01)

- Pai: 30717
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30983/activity)

**Descrição:**

Criado: 2026-09-04T20:26:28.885Z
Atualizado: 2026-09-16T01:00:17.789Z
Criar entidade Combinado, repositório JPA e endpoint POST /api/combinados no Spring Boot vinculando o usuário autenticado como criador.

###### [TASK] #30987 — [front-end] Construir tela e formulário de criação de combinado (CA01)

- Pai: 30717
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30987/activity)

**Descrição:**

Criado: 2026-09-04T20:27:15.117Z
Atualizado: 2026-10-22T02:03:07.706Z
Construir formulário em React com campos de título e descrição, validações de preenchimento e integração com a API.

###### [TASK] #30989 — [testes] Testar persistência e fluxo de criação do combinado (CA01)

- Pai: 30717
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30989/activity)

**Descrição:**

Criado: 2026-09-04T20:28:17.445Z
Atualizado: 2026-09-16T01:00:17.890Z
Criar testes unitários com JUnit/Mockito validando o endpoint POST /api/combinados e testes de integração com o banco PostgreSQL.

###### [TASK] #30990 — [po/validação] Homologar criação de combinado e persistência (#31468, #31473)

- Pai: 30717
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30990/activity)

**Descrição:**

Criado: 2026-09-04T20:28:39.595Z
Atualizado: 2026-10-22T02:22:23.919Z
Homologar a jornada de criação do combinado em tela atestando conformidade com os critérios CA01 e CA05.

###### [USER STORY] #30718 — US16 - Seleção de categoria do combinado (empréstimo, despesa, serviço, cuidado, espaço, entrega, outro)

- Pai: 30674
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30718/activity)

**Descrição:**

Criado: 2026-09-03T00:28:50.935Z
Atualizado: 2026-09-16T22:41:22.118Z
História do Usuário
Como criador de um acordo,
quero selecionar uma categoria para o combinado entre as opções disponíveis (empréstimo de objeto, divisão de despesa, prestação de serviço, cuidado de animal, uso de espaço, entrega/devolução ou outro),
para classificar adequadamente o tipo de acordo e facilitar sua organização na plataforma.

BDD
Cenário: Seleção de categoria válida no cadastro
Dado que estou na tela de criação de combinados
Quando selecionar uma das categorias predefinidas da lista
E confirmar o salvamento do combinado
Então o sistema deve associar a categoria selecionada ao registro do acordo
E deve exibi-la de forma destacada nos dados do combinado.

Critérios de Aceitação Associados
#31469, #31472

###### [TASK] #30991 — [back-end] Implementar enum e mapeamento de Categoria no combinado (CA02)

- Pai: 30718
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-09-04
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30991/activity)

**Descrição:**

Criado: 2026-09-04T20:30:52.192Z
Atualizado: 2026-09-16T22:41:22.065Z
Implementar o enum CategoriaCombinado no Spring Boot contendo as opções definidas no escopo e mapear o campo na entidade Combinado com anotação @Enumerated(EnumType.STRING).

###### [TASK] #30994 — [front-end] Construir componente de seleção de categorias (CA02)

- Pai: 30718
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30994/activity)

**Descrição:**

Criado: 2026-09-04T20:31:13.679Z
Atualizado: 2026-09-16T02:50:58.205Z
Criar componente de Select/Dropdown ou conjunto de chips interativos no formulário React contendo todas as categorias do projeto e suporte a ícones visuais.

###### [TASK] #30995 — [testes] Testar validação e persistência de categorias válidas (CA02)

- Pai: 30718
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30995/activity)

**Descrição:**

Criado: 2026-09-04T20:31:35.630Z
Atualizado: 2026-09-16T18:55:18.631Z
Desenvolver testes unitários com JUnit validando que apenas valores válidos do enum de categoria são aceitos pelo backend e persistidos corretamente no banco.

###### [TASK] #30996 — [po/validação] Homologar seleção de categorias pré-definidas (#31469)

- Pai: 30718
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30996/activity)

**Descrição:**

Criado: 2026-09-04T20:32:00.723Z
Atualizado: 2026-09-16T21:27:14.704Z
Homologar o preenchimento do formulário garantindo que todas as categorias listadas no critério CA02 estão acessíveis e selecionáveis.

###### [USER STORY] #30719 — US17 -  Definição de prazo para o combinado

- Pai: 30674
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30719/activity)

**Descrição:**

Criado: 2026-09-03T00:29:15.090Z
Atualizado: 2026-09-16T22:06:53.669Z
História do Usuário
Como criador de um acordo,
quero definir um prazo limite para o combinado informando uma data futura,
para estabelecer claramente a vigência e o momento esperado para o cumprimento do acordo.

BDD
Cenário 1: Definição de prazo válido
Dado que estou na tela de criação de combinados
Quando informar uma data e hora limite no futuro
E salvar o combinado
Então o sistema deve registrar o prazo com sucesso
E exibir a data limite formatada nos detalhes do combinado.

Cenário 2: Tentativa de informar data retroativa
Dado que estou na tela de criação de combinados
Quando selecionar uma data anterior à data e hora atual
E tentar avançar com o salvamento
Então o sistema deve impedir a operação
E deve apresentar mensagem informando que o prazo deve ser posterior à data atual.

Critérios de Aceitação Associados
#31470, #31472

###### [TASK] #31000 — [back-end] Implementar validação temporal de prazo no DTO

- Pai: 30719
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31000/activity)

**Descrição:**

Criado: 2026-09-04T20:36:32.238Z
Atualizado: 2026-09-15T00:00:37.278Z
Adicionar anotação @Future e @NotNull no atributo prazo (LocalDateTime) no CombinadoDTO e tratar exceção de data inválida retornando mensagem amigável (atende CA03).

###### [TASK] #31001 — [front-end] Implementar componente DatePicker com bloqueio de datas passadas

- Pai: 30719
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31001/activity)

**Descrição:**

Criado: 2026-09-04T20:36:57.712Z
Atualizado: 2026-09-17T02:15:47.005Z
Construir componente de seleção de data/hora no formulário React, definindo minDate como a data/hora atual para impedir seleção retroativa e exibindo feedback visual (atende CA03).

###### [TASK] #31004 — [testes] Testar validações de data retroativa e data futura

- Pai: 30719
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31004/activity)

**Descrição:**

Criado: 2026-09-04T20:37:27.914Z
Atualizado: 2026-09-16T18:55:35.146Z
Criar testes unitários com JUnit validando que requisições com datas passadas são rejeitadas com HTTP 400 e datas futuras válidas são persistidas com sucesso (atende CA03).

###### [TASK] #31005 — [po/validação] Homologar validação de data limite futura (#31470)

- Pai: 30719
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31005/activity)

**Descrição:**

Criado: 2026-09-04T20:37:47.328Z
Atualizado: 2026-09-16T22:06:53.631Z
Realizar validação funcional de ponta a ponta garantindo bloqueio visual de datas passadas e formatação correta do prazo nos detalhes do combinado.

###### [USER STORY] #30720 — US18 - Validação de campos obrigatórios não combinados

- Pai: 30674
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30720/activity)

**Descrição:**

Criado: 2026-09-03T00:29:43.457Z
Atualizado: 2026-09-15T00:01:16.497Z
História do Usuário
Como criador de um acordo,
quero que o sistema valide o preenchimento de todos os campos obrigatórios (título, descrição, categoria e prazo),
para impedir a criação de combinados incompletos ou inconsistentes na plataforma.

BDD
Cenário: Tentativa de submissão com campos obrigatórios vazios
Dado que estou na tela de criação de combinados
E deixo de preencher um ou mais campos obrigatórios (título, categoria ou prazo)
Quando acionar o botão de salvar/criar
Então o sistema deve impedir a submissão
E deve destacar visualmente os campos pendentes com mensagens de erro claras.

Critérios de Aceitação Associados
#31472

###### [TASK] #31007 — [back-end] Implementar validações Bean Validation nos campos do Combinado

- Pai: 30720
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31007/activity)

**Descrição:**

Criado: 2026-09-04T20:38:56.274Z
Atualizado: 2026-09-15T00:01:26.987Z
Adicionar anotações @NotBlank, @NotNull e @Future no CombinadoDTO e configurar tratamento global de MethodArgumentNotValidException para retornar erros amigáveis (atende CA04).

###### [TASK] #31008 — [front-end] Implementar validação e alertas visuais no formulário

- Pai: 30720
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31008/activity)

**Descrição:**

Criado: 2026-09-04T20:39:28.692Z
Atualizado: 2026-09-17T02:28:42.613Z
Criar validação client-side no formulário React, destacando bordas em vermelho nos inputs obrigatórios vazios e exibindo texto de apoio (atende CA04).

###### [TASK] #31009 — [testes] Testar validações de campos obrigatórios no Backend

- Pai: 30720
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31009/activity)

**Descrição:**

Criado: 2026-09-04T20:41:07.368Z
Atualizado: 2026-09-16T18:55:50.423Z
Criar testes unitários com JUnit validando que requisições com título em branco, prazo nulo ou categoria ausente retornam HTTP 400 Bad Request com payload descritivo (atende CA04).

###### [TASK] #31011 — [po/validação] Homologar bloqueio e mensagens de campos obrigatórios (#31472)

- Pai: 30720
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31011/activity)

**Descrição:**

Criado: 2026-09-04T20:41:46.165Z
Atualizado: 2026-10-22T02:22:47.423Z
Validar comportamento funcional da tela e clareza das mensagens de feedback em conformidade com o critério CA04.

###### [CRITÉRIO DE ACEITAÇÃO] #31467 — CA-F05.1 - Criar Combinado

- Pai: 30674
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31467/activity)

**Descrição:**

Criado: 2026-09-05T21:20:34.440Z
Atualizado: 2026-09-16T02:07:48.076Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31468 — CA01 - Preenchimento de dados essenciais

- Pai: 31467
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31468/activity)

**Descrição:**

Criado: 2026-09-05T21:21:01.930Z
Atualizado: 2026-09-05T21:21:01.994Z
O sistema deve permitir a criação de um combinado informando obrigatoriamente título, descrição, categoria e prazo de vigência/cumprimento.

###### [CRITÉRIO DE ACEITAÇÃO] #31469 — CA02 - Seleção de categorias pré-definidas

- Pai: 31467
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31469/activity)

**Descrição:**

Criado: 2026-09-05T21:21:23.364Z
Atualizado: 2026-09-05T21:21:23.398Z
O sistema deve disponibilizar as categorias pré-definidas da plataforma (ex.: Empréstimo, Despesa Compartilhada, Tarefa, Meta) para classificação do acordo.

###### [CRITÉRIO DE ACEITAÇÃO] #31470 — CA03 - Validação de data limite futura

- Pai: 31467
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31470/activity)

**Descrição:**

Criado: 2026-09-05T21:21:44.623Z
Atualizado: 2026-09-05T21:21:44.680Z
O sistema deve exigir uma data limite válida e obrigatoriamente posterior à data e hora atual da criação do combinado.

###### [CRITÉRIO DE ACEITAÇÃO] #31472 — CA04 - Bloqueio de campos obrigatórios não preenchidos

- Pai: 31467
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31472/activity)

**Descrição:**

Criado: 2026-09-05T21:22:44.107Z
Atualizado: 2026-09-05T21:22:44.146Z
O sistema deve impedir a criação caso campos obrigatórios não sejam preenchidos, apresentando alertas visuais claros nos respectivos campos.

###### [CRITÉRIO DE ACEITAÇÃO] #31473 — CA05 - Confirmação e persistência do combinado

- Pai: 31467
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31473/activity)

**Descrição:**

Criado: 2026-09-05T21:23:41.529Z
Atualizado: 2026-09-05T21:23:41.581Z
Com todos os dados válidos, o sistema deve salvar o combinado com seu status inicial e exibir mensagem de confirmação de sucesso ao usuário criador.

#### [EPIC] #30614 — 02.2 — Definição de Responsabilidades

- Pai: 30568
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30614/activity)

**Descrição:**

Criado: 2026-09-02T21:41:42.941Z
Atualizado: 2026-10-21T01:15:24.822Z
Associar responsáveis e tarefas específicas dentro do combinado.

##### [FEATURE] #30675 — F06 — Definir Responsabilidades

- Pai: 30614
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30675/activity)

**Descrição:**

Criado: 2026-09-03T00:01:29.268Z
Atualizado: 2026-10-21T01:15:24.775Z
F06 — Definir Responsabilidades
Funcionalidade responsável por permitir a divisão e atribuição de tarefas e responsabilidades específicas dentro de um combinado, definindo claramente as obrigações de cada participante envolvido.

Critérios de Aceitação:
CA01 — Associação de Tarefa a Participante:
O sistema deve permitir criar tarefas associadas a um combinado e vinculá-las diretamente a um ou mais participantes confirmados/selecionados.

CA02 — Múltiplas Tarefas por Combinado:
O sistema deve permitir o cadastro de múltiplas tarefas/responsabilidades dentro de um único combinado, mantendo a listagem organizada.

CA03 — Múltiplos Responsáveis por Tarefa:
O sistema deve possibilitar a atribuição de mais de um responsável para uma mesma tarefa quando a atividade for compartilhada.

CA04 — Validação de Descrição da Tarefa:
O sistema deve exigir uma descrição obrigatória para cada responsabilidade cadastrada, impedindo o salvamento de tarefas vazias.

###### [USER STORY] #30721 — US19 - Associação de uma tarefa a um participante

- Pai: 30675
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30721/activity)

**Descrição:**

Criado: 2026-09-03T00:30:10.842Z
Atualizado: 2026-09-05T21:34:00.805Z
História do Usuário
Como criador de um combinado,
quero associar uma tarefa específica a um participante,
para deixar explícito quem é a pessoa responsável por cumprir aquela obrigação dentro do acordo.

BDD
Cenário: Vinculação de tarefa a um participante com sucesso
Dado que estou na etapa de definição de responsabilidades de um combinado
Quando cadastrar uma nova tarefa informando sua descrição
E selecionar um participante da lista de envolvidos
E salvar a responsabilidade
Então o sistema deve registrar a tarefa vinculada ao participante
E deve exibir a atribuição na listagem de responsabilidades do combinado.

Critérios de Aceitação Associados
#31487, #31491

###### [TASK] #31014 — [back-end] Implementar entidade e endpoints de Responsabilidade

- Pai: 30721
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31014/activity)

**Descrição:**

Criado: 2026-09-04T20:43:43.926Z
Atualizado: 2026-09-11T20:24:50.966Z
Criar entidade Responsabilidade/Tarefa, mapeando o relacionamento ManyToOne com Combinado e Usuario, além do endpoint POST /api/combinados/{id}/responsabilidades (atende CA01).

###### [TASK] #31017 — [front-end] Implementar formulário de inclusão de tarefa e seleção de responsável

- Pai: 30721
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31017/activity)

**Descrição:**

Criado: 2026-09-04T20:44:45.828Z
Atualizado: 2026-10-24T17:13:50.249Z
Construir componente React para adicionar tarefa com campo de texto e select box com os participantes disponíveis para vinculação (atende CA01).

###### [TASK] #31018 — [testes] Testar criação e vínculo de tarefas a participantes

- Pai: 30721
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31018/activity)

**Descrição:**

Criado: 2026-09-04T20:45:17.586Z
Atualizado: 2026-10-24T16:59:16.540Z
Criar testes unitários com JUnit validando persistência da tarefa, obrigatoriedade de descrição e associação correta da chave estrangeira do usuário (atende CA01 e CA04).

###### [TASK] #31019 — [po/validação] Homologar associação de tarefa a participante (#31487, #31491)

- Pai: 30721
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31019/activity)

**Descrição:**

Criado: 2026-09-04T20:45:46.746Z
Atualizado: 2026-10-24T17:08:23.076Z
Homologar o fluxo visual de inclusão de tarefa, verificando se a lista reflete com fidelidade o participante atribuído conforme o critério CA01.

###### [USER STORY] #30722 — US20 - Definição de múltiplas tarefas em um combinado

- Pai: 30675
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30722/activity)

**Descrição:**

Criado: 2026-09-03T00:30:32.047Z
Atualizado: 2026-09-05T21:34:20.704Z
História do Usuário
Como criador de um combinado,
quero cadastrar várias tarefas e responsabilidades em um mesmo acordo,
para organizar todas as ações necessárias para o cumprimento do combinado em um único lugar.

BDD
Cenário: Inclusão de múltiplas tarefas em um combinado
Dado que estou estruturando as responsabilidades de um combinado
E já incluí uma ou mais tarefas na lista
Quando cadastrar uma nova tarefa com descrição e responsável válidos
Então o sistema deve adicionar o novo item mantendo os itens anteriores intactos
E deve exibir a listagem completa e ordenada de todas as tarefas cadastradas.

Critérios de Aceitação Associados
#31489, <mention class="mention" data-id="31491" data-type="work_package" data-text="#31491">#31491</mention>

###### [TASK] #31020 — [back-end] Implementar endpoint de listagem e adição múltipla de tarefas

- Pai: 30722
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31020/activity)

**Descrição:**

Criado: 2026-09-04T20:46:51.331Z
Atualizado: 2026-09-11T20:25:04.771Z
Ajustar a API no Spring Boot para retornar a coleção completa de tarefas associadas ao ID do combinado e suportar persistência sequencial de itens (atende CA02).

###### [TASK] #31021 — [front-end] Implementar tabela/lista dinâmica de tarefas no formulário

- Pai: 30722
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31021/activity)

**Descrição:**

Criado: 2026-09-04T20:47:11.856Z
Atualizado: 2026-10-24T17:14:00.903Z
Construir componente visual em React com botão &quot;+ Adicionar Nova Tarefa&quot;, renderizando dinamicamente a grade de itens cadastrados com descrição e responsável (atende CA02).

###### [TASK] #31022 — [testes] Testar integridade da coleção de tarefas por combinado

- Pai: 30722
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31022/activity)

**Descrição:**

Criado: 2026-09-04T20:47:36.405Z
Atualizado: 2026-10-24T16:59:25.831Z
Criar testes unitários com JUnit validando que combinados com 2 ou mais tarefas associadas são recuperados integralmente sem perdas ou duplicidades no banco de dados (atende CA02).

###### [TASK] #31024 — [po/validação] Homologar listagem e inclusão de múltiplas tarefas (#31489)

- Pai: 30722
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31024/activity)

**Descrição:**

Criado: 2026-09-04T20:48:01.356Z
Atualizado: 2026-10-24T17:08:33.947Z
Homologar o comportamento da interface ao adicionar múltiplas responsabilidades sucessivas, garantindo estabilidade e visualização correta da lista.

###### [USER STORY] #30723 — US21 - Atribuição de múltiplos responsáveis por uma tarefa

- Pai: 30675
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30723/activity)

**Descrição:**

Criado: 2026-09-03T00:31:10.578Z
Atualizado: 2026-10-21T01:15:24.734Z
História do Usuário
Como criador de um combinado,
quero atribuir mais de um responsável a uma mesma tarefa quando a atividade for compartilhada,
para que todos os membros encarregados tenham ciência da sua obrigação conjunta no acordo.

BDD
Cenário: Atribuição de múltiplos participantes em uma tarefa compartilhada
Dado que estou cadastrando uma responsabilidade no combinado
Quando selecionar dois ou mais participantes na lista de responsáveis
E confirmar o salvamento da tarefa
Então o sistema deve associar todos os participantes selecionados àquela tarefa
E deve exibir os nomes de todos os encarregados no card da responsabilidade.

Critérios de Aceitação Associados
#31490, #31491

###### [TASK] #31026 — [back-end] Modelar relação N:N entre Tarefa e Usuários responsáveis

- Pai: 30723
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31026/activity)

**Descrição:**

Criado: 2026-09-04T20:49:03.640Z
Atualizado: 2026-10-21T01:15:24.691Z
Ajustar a entidade Tarefa/Responsabilidade no Spring Boot para utilizar relacionamento ManyToMany com a entidade Usuario, criando a tabela associativa tarefa\_responsaveis (atende CA03).

###### [TASK] #31028 — [front-end] Implementar componente Multi-Select para seleção de múltiplos responsáveis

- Pai: 30723
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31028/activity)

**Descrição:**

Criado: 2026-09-04T20:49:28.418Z
Atualizado: 2026-10-21T22:53:37.064Z
Construir componente de seleção múltipla com caixas de seleção/chips no React para permitir marcar mais de um participante para a mesma atividade (atende CA03).

###### [TASK] #31029 — [testes] Testar vinculação e persistência de múltiplos responsáveis por tarefa

- Pai: 30723
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31029/activity)

**Descrição:**

Criado: 2026-09-04T20:49:53.434Z
Atualizado: 2026-10-21T23:37:53.326Z
Criar testes unitários e de integração com JUnit validando que tarefas com múltiplos responsáveis são salvas e recuperadas corretamente com todos os IDs vinculados (atende CA03).

###### [TASK] #31031 — [po/validação] Homologar múltiplos responsáveis por tarefa (#31490)

- Pai: 30723
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31031/activity)

**Descrição:**

Criado: 2026-09-04T20:50:14.645Z
Atualizado: 2026-10-22T02:23:23.160Z
Homologar o preenchimento em tela e verificar se os chips visuais de todos os participantes selecionados são apresentados corretamente no resumo da tarefa.

###### [CRITÉRIO DE ACEITAÇÃO] #31486 — CA-F06.1 - Definir Responsabilidades

- Pai: 30675
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31486/activity)

**Descrição:**

Criado: 2026-09-05T21:32:05.824Z
Atualizado: 2026-09-16T02:07:49.190Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31487 — CA01 - Associação de tarefa a participante

- Pai: 31486
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31487/activity)

**Descrição:**

Criado: 2026-09-05T21:32:26.284Z
Atualizado: 2026-09-05T21:32:26.325Z
O sistema deve permitir criar tarefas associadas a um combinado e vinculá-las diretamente a um participante confirmado ou selecionado.

###### [CRITÉRIO DE ACEITAÇÃO] #31489 — CA02 - Cadastro de múltiplas tarefas por combinado

- Pai: 31486
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31489/activity)

**Descrição:**

Criado: 2026-09-05T21:32:46.985Z
Atualizado: 2026-09-05T21:32:47.040Z
O sistema deve possibilitar a inclusão de múltiplas tarefas e responsabilidades dentro de um único acordo, exibindo-as em lista ordenada.

###### [CRITÉRIO DE ACEITAÇÃO] #31490 — CA03 - Atribuição de múltiplos responsáveis por tarefa

- Pai: 31486
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31490/activity)

**Descrição:**

Criado: 2026-09-05T21:33:07.182Z
Atualizado: 2026-09-05T21:33:07.226Z
O sistema deve possibilitar a seleção de dois ou mais participantes para uma mesma tarefa quando a atividade for compartilhada.

###### [CRITÉRIO DE ACEITAÇÃO] #31491 — CA04 - Obrigatoriedade da descrição da tarefa

- Pai: 31486
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31491/activity)

**Descrição:**

Criado: 2026-09-05T21:33:25.915Z
Atualizado: 2026-09-05T21:33:25.955Z
O sistema deve exigir um texto descritivo para cada responsabilidade cadastrada, bloqueando o registro de itens sem descrição.

#### [EPIC] #30615 — 02.3 — Seleção de Participantes

- Pai: 30568
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-04
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30615/activity)

**Descrição:**

Criado: 2026-09-02T21:42:11.783Z
Atualizado: 2026-10-22T02:04:12.863Z
Buscar e adicionar outros usuários como participantes do combinado.

##### [FEATURE] #30676 — F07 — Selecionar Participantes

- Pai: 30615
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30676/activity)

**Descrição:**

Criado: 2026-09-03T00:01:54.524Z
Atualizado: 2026-10-22T02:04:12.846Z
F07 — Selecionar Participantes
Funcionalidade responsável pela busca, identificação e adição de outros usuários cadastrados na plataforma como participantes do combinado, garantindo transparência e integridade antes do envio dos convites formais.

Critérios de Aceitação:
CA01 — Busca por E-mail:
O sistema deve permitir pesquisar usuários cadastrados através do seu endereço exato de e-mail.

CA02 — Busca Preditiva por Nome:
O sistema deve fornecer recurso de preenchimento automático (autocomplete) ao digitar o nome do usuário, facilitando sua localização.

CA03 — Lista de Participantes Selecionados:
O sistema deve exibir visualmente a lista de participantes já adicionados ao combinado, permitindo visualizar nome, e-mail e remover qualquer membro antes do envio.

CA04 — Prevenção de Autoadição e Duplicidade:
O sistema deve impedir que o próprio criador se adicione como participante convidado e evitar a duplicação do mesmo usuário na lista de convidados.

###### [USER STORY] #30724 — US22 - Busca de usuário por e-mail para adição ao combinado

- Pai: 30676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-10-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30724/activity)

**Descrição:**

Criado: 2026-09-03T00:32:50.927Z
Atualizado: 2026-10-22T02:04:12.783Z
História do Usuário
Como criador de um combinado,
quero buscar usuários pelo endereço de e-mail exato,
para localizá-los e adicioná-los como participantes do acordo de maneira precisa e confiável.

BDD
Cenário 1: Busca e adição de usuário por e-mail com sucesso
Dado que estou no formulário de criação/edição de combinados
Quando digitar um e-mail cadastrado no campo de busca de participantes
E confirmar a inclusão
Então o sistema deve localizar o usuário correspondente
E adicioná-lo à lista de participantes do combinado.

Cenário 2: Busca por e-mail inexistente
Dado que estou na tela de seleção de participantes
Quando pesquisar por um e-mail que não está registrado na base de dados
Então o sistema não deve incluir nenhum participante
E deve apresentar mensagem informando que o usuário não foi encontrado.

Critérios de Aceitação Associados
#31499, #31501, #31502

###### [TASK] #31034 — [back-end] Implementar endpoint de consulta de usuário por e-mail

- Pai: 30724
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31034/activity)

**Descrição:**

Criado: 2026-09-04T20:52:14.743Z
Atualizado: 2026-09-16T01:02:23.290Z
Criar endpoint GET /api/usuarios/buscar-email?email=... no Spring Boot retornando ID, nome e e-mail do usuário, bloqueando retorno caso seja o próprio usuário autenticado (atende CA01 e CA04).

###### [TASK] #31035 — [front-end] Implementar campo de busca por e-mail e ação de adicionar

- Pai: 30724
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31035/activity)

**Descrição:**

Criado: 2026-09-04T20:52:40.327Z
Atualizado: 2026-10-22T02:04:12.753Z
Construir campo de input com botão &quot;Adicionar&quot; no formulário React, disparando consulta na API e tratando alertas de &quot;Usuário não encontrado&quot; ou &quot;E-mail duplicado&quot; (atende CA01).

###### [TASK] #31036 — [testes] Testar busca por e-mail e bloqueio de autoadição

- Pai: 30724
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31036/activity)

**Descrição:**

Criado: 2026-09-04T20:53:08.465Z
Atualizado: 2026-10-22T01:18:13.638Z
Criar testes unitários com JUnit validando retorno 200 OK para e-mail existente, 404 Not Found para não cadastrado e bloqueio de inclusão do próprio criador (atende CA01 e CA04).

###### [TASK] #31039 — [po/validação] Homologar busca de participante por e-mail (#31499, #31502)

- Pai: 30724
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31039/activity)

**Descrição:**

Criado: 2026-09-04T20:53:46.936Z
Atualizado: 2026-10-22T02:24:04.407Z
Homologar o comportamento da interface ao buscar e-mails válidos e inexistentes, atestando conformidade com os critérios CA01 e CA04.

###### [USER STORY] #30725 — US23 - Busca de usuário por nome com preenchimento automático

- Pai: 30676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30725/activity)

**Descrição:**

Criado: 2026-09-03T00:33:39.117Z
Atualizado: 2026-09-05T21:38:27.944Z
História do Usuário
Como criador de um combinado,
quero que o sistema sugira automaticamente nomes de usuários cadastrados enquanto digito,
para agilizar a busca e facilitar a adição de participantes ao acordo.

BDD
Cenário: Busca preditiva por nome com preenchimento automático
Dado que estou na tela de seleção de participantes
Quando digitar as primeiras letras do nome de um usuário no campo de busca
Então o sistema deve exibir uma lista de sugestões correspondentes em tempo real
E, ao clicar em uma sugestão, deve preencher os dados do usuário e adicioná-lo à lista de participantes.

Critérios de Aceitação Associados
#31500, #31501, #31502

###### [TASK] #31042 — [back-end] Implementar endpoint de busca preditiva por nome com LIKE

- Pai: 30725
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31042/activity)

**Descrição:**

Criado: 2026-09-04T20:55:32.189Z
Atualizado: 2026-10-21T01:01:28.677Z
Criar consulta no UsuarioRepository utilizando LIKE/ILIKE (contendo termo) com paginação/limite de resultados e retorno via DTO resumido (atende CA02).

###### [TASK] #31043 — [front-end] Implementar componente de Autocomplete com debounce

- Pai: 30725
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31043/activity)

**Descrição:**

Criado: 2026-09-04T20:55:57.976Z
Atualizado: 2026-10-22T01:31:28.214Z
Construir campo com sugestões dinâmicas no React, aplicando debounce nas requisições e permitindo seleção rápida por clique ou teclado (atende CA02).

###### [TASK] #31045 — [testes] Testar busca preditiva e performance da consulta

- Pai: 30725
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31045/activity)

**Descrição:**

Criado: 2026-09-04T20:56:24.539Z
Atualizado: 2026-10-21T23:45:00.550Z
Criar testes automatizados com JUnit validando que termos parciais retornam apenas usuários correspondentes e desconsideram o usuário logado (atende CA02 e CA04).

###### [TASK] #31046 — [po/validação] Homologar sugestão automática de participantes (#31500)

- Pai: 30725
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31046/activity)

**Descrição:**

Criado: 2026-09-04T20:56:53.003Z
Atualizado: 2026-10-22T02:24:39.207Z
Validar a fluidez visual da listagem suspensa de usuários e a seleção rápida de participantes conforme o critério CA02.

###### [USER STORY] #30726 — US24 - Visualização da lista de participantes selecionados

- Pai: 30676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-04
- Prazo: 2026-09-04
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30726/activity)

**Descrição:**

Criado: 2026-09-03T01:08:53.693Z
Atualizado: 2026-09-16T22:41:52.830Z
História do Usuário
Como criador de um combinado,
quero visualizar a lista de todos os participantes selecionados antes de finalizar a criação,
para conferir os membros adicionados e ter a opção de remover qualquer usuário incorreto.

BDD
Cenário 1: Visualização dos membros adicionados
Dado que selecionei um ou mais participantes para o combinado
Quando acessar a área de participantes do formulário
Então o sistema deve exibir a listagem dos participantes com nome e e-mail
E deve disponibilizar uma opção visual para remoção de cada participante.

Cenário 2: Remoção de um participante da listagem
Dado que estou visualizando a lista de participantes selecionados
Quando acionar o botão de exclusão/remoção em um membro da lista
Então o sistema deve desvincular aquele usuário da seleção do combinado
E atualizar a exibição da lista imediatamente.

Critérios de Aceitação Associados
#31501

###### [TASK] #31049 — [back-end] Implementar endpoint de gerenciamento da lista de convidados

- Pai: 30726
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-09-04
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31049/activity)

**Descrição:**

Criado: 2026-09-04T20:58:07.552Z
Atualizado: 2026-09-16T22:41:52.779Z
Criar suporte na camada de serviço e endpoints no Spring Boot para receber e validar o conjunto de IDs de participantes vinculados ao combinado, garantindo a integridade dos dados (atende CA03 e CA04).

###### [TASK] #31050 — [front-end] Implementar componente de lista/chips de participantes com remoção

- Pai: 30726
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31050/activity)

**Descrição:**

Criado: 2026-09-04T20:58:29.640Z
Atualizado: 2026-10-20T19:03:04.023Z
Construir componente React que renderiza a lista de participantes selecionados com avatar, nome, e-mail e botão de exclusão rápida (atende CA03).

###### [TASK] #31051 — [testes] Testar integridade da seleção e remoção de participantes

- Pai: 30726
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31051/activity)

**Descrição:**

Criado: 2026-09-04T20:58:51.183Z
Atualizado: 2026-10-22T01:06:31.855Z
Criar testes unitários com JUnit validando que a lista de participantes é atualizada corretamente ao remover um membro e impedindo itens duplicados no payload (atende CA03 e CA04).

###### [TASK] #31053 — [po/validação] Homologar listagem e remoção de participantes (#31501)

- Pai: 30726
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31053/activity)

**Descrição:**

Criado: 2026-09-04T20:59:15.806Z
Atualizado: 2026-10-22T02:25:17.531Z
Validar funcionalmente a adição e remoção de membros na interface, garantindo conformidade total com o critério CA03.

###### [CRITÉRIO DE ACEITAÇÃO] #31498 — CA-F07.1 - Selecionar Participantes

- Pai: 30676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31498/activity)

**Descrição:**

Criado: 2026-09-05T21:35:42.450Z
Atualizado: 2026-09-16T02:07:50.351Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31499 — CA01 - Busca exata de usuário por e-mail

- Pai: 31498
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31499/activity)

**Descrição:**

Criado: 2026-09-05T21:36:07.928Z
Atualizado: 2026-09-05T21:36:07.961Z
O sistema deve permitir pesquisar usuários cadastrados através do seu endereço exato de e-mail.

###### [CRITÉRIO DE ACEITAÇÃO] #31500 — CA02 - Busca preditiva por nome (Autocomplete)

- Pai: 31498
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31500/activity)

**Descrição:**

Criado: 2026-09-05T21:36:32.438Z
Atualizado: 2026-09-05T21:36:32.473Z
O sistema deve fornecer recurso de busca e preenchimento automático ao digitar as primeiras letras do nome do usuário.

###### [CRITÉRIO DE ACEITAÇÃO] #31501 — CA03 - Visualização e remoção de membros na lista

- Pai: 31498
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31501/activity)

**Descrição:**

Criado: 2026-09-05T21:36:57.951Z
Atualizado: 2026-09-05T21:36:57.987Z
O sistema deve exibir a listagem dos participantes adicionados com nome, e-mail e opção para remoção individual antes da conclusão.

###### [CRITÉRIO DE ACEITAÇÃO] #31502 — CA04 - Prevenção de duplicidade e autoadição

- Pai: 31498
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31502/activity)

**Descrição:**

Criado: 2026-09-05T21:37:20.357Z
Atualizado: 2026-09-05T21:37:20.394Z
O sistema deve impedir que o criador se adicione como participante convidado e evitar a duplicidade de um mesmo usuário na lista.

#### [EPIC] #30616 — 02.4 — Salvar e Editar Rascunho

- Pai: 30568
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30616/activity)

**Descrição:**

Criado: 2026-09-02T21:42:48.946Z
Atualizado: 2026-10-21T01:08:23.510Z
Permitir salvar o combinado como rascunho e editá-lo antes de enviar.

##### [FEATURE] #30677 — F08 — Salvar e Editar Rascunho

- Pai: 30616
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30677/activity)

**Descrição:**

Criado: 2026-09-03T00:02:28.539Z
Atualizado: 2026-10-21T01:08:23.485Z
F08 — Salvar e Editar Rascunho
Funcionalidade responsável por permitir ao criador salvar acordos em estado preliminar (rascunho) para posterior edição, complementação de informações ou envio definitivo aos participantes.

Critérios de Aceitação:
CA01 — Salvamento em Estado Rascunho:
O sistema deve permitir salvar um combinado com preenchimento parcial no estado &quot;Rascunho&quot;, sem disparar convites aos participantes.

CA02 — Edição de Rascunho:
O sistema deve permitir ao criador editar quaisquer campos e dados previamente informados de um combinado que esteja no estado &quot;Rascunho&quot;.

CA03 — Listagem de Rascunhos:
O sistema deve disponibilizar ao criador uma listagem filtrada contendo exclusivamente os seus combinados em estado de rascunho.

CA04 — Publicação e Envio do Combinado:
Ao finalizar o preenchimento dos campos obrigatórios, o sistema deve permitir ao criador alterar o estado do combinado de &quot;Rascunho&quot; para &quot;Ativo/Pendente de Aceite&quot;, formalizando o envio aos participantes.Permitir salvar o combinado como rascunho e editá-lo antes de enviar.

###### [USER STORY] #30727 — US25 - Salvamento de combinado como rascunho

- Pai: 30677
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30727/activity)

**Descrição:**

Criado: 2026-09-03T01:10:49.491Z
Atualizado: 2026-09-16T22:49:32.277Z
História do Usuário
Como criador de um combinado,
quero salvar um acordo como rascunho mesmo com dados incompletos,
para continuar o preenchimento em outro momento sem perder o progresso realizado.

BDD
Cenário: Salvamento de combinado preliminar como rascunho
Dado que estou na tela de criação de combinados preenchendo as informações
Quando acionar o botão &quot;Salvar como Rascunho&quot;
Então o sistema deve registrar o combinado associado ao meu usuário com o status &quot;RASCUNHO&quot;
E não deve enviar notificações ou convites aos participantes selecionados.

Critérios de Aceitação Associados
#31510

###### [TASK] #31058 — [back-end] Implementar persistência de combinado com status RASCUNHO

- Pai: 30727
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31058/activity)

**Descrição:**

Criado: 2026-09-04T21:03:34.430Z
Atualizado: 2026-09-16T22:49:32.231Z
Ajustar entidade e endpoint de criação para aceitar status RASCUNHO, flexibilizando regras de validação obrigatórias no salvamento parcial (atende CA01).

###### [TASK] #31059 — [front-end] Implementar botão e ação de "Salvar como Rascunho"

- Pai: 30727
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31059/activity)

**Descrição:**

Criado: 2026-09-04T21:04:00.642Z
Atualizado: 2026-09-17T02:16:09.101Z
Adicionar botão secundário de salvamento no formulário React que envia o payload com flag/status de rascunho sem bloquear por campos vazios opcionais (atende CA01).

###### [TASK] #31061 — [testes] Testar salvamento parcial e ausência de disparo de notificações

- Pai: 30727
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31061/activity)

**Descrição:**

Criado: 2026-09-04T21:04:22.202Z
Atualizado: 2026-09-16T18:56:01.179Z
Criar testes unitários com JUnit validando que combinados salvos com status RASCUNHO não acionam o serviço de mensageria nem criam pendências para participantes (atende CA01).

###### [TASK] #31062 — [po/validação] Homologar salvamento de rascunho incompleto (#31510)

- Pai: 30727
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31062/activity)

**Descrição:**

Criado: 2026-09-04T21:04:45.755Z
Atualizado: 2026-09-16T22:01:52.349Z
Homologar o fluxo de salvamento como rascunho na interface e certificar conformidade com a especificação do critério CA01.

###### [USER STORY] #30728 — US26 - Edição de rascunho do combinado

- Pai: 30677
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30728/activity)

**Descrição:**

Criado: 2026-09-03T01:18:08.101Z
Atualizado: 2026-09-05T21:45:02.552Z
História do Usuário
Como criador de um combinado,
quero editar as informações de um acordo salvo como rascunho,
para atualizar termos, adicionar detalhes pendentes e ajustar prazos antes do envio formal.

BDD
Cenário 1: Edição de dados em combinado no estado rascunho
Dado que possuo um combinado com status &quot;RASCUNHO&quot;
Quando abrir o combinado para edição
E alterar dados como título, descrição, prazo, categorias ou participantes
E salvar as alterações
Então o sistema deve atualizar os dados do combinado
E deve mantê-lo no status &quot;RASCUNHO&quot; até a publicação definitiva.

Cenário 2: Tentativa de edição de rascunho por usuário não criador
Dado que um usuário autenticado não é o criador do rascunho
Quando tentar acessar a rota de edição do rascunho
Então o sistema deve bloquear o acesso
E retornar erro de permissão (HTTP 403 Forbidden).

Critérios de Aceitação Associados
#31511, #31513

###### [TASK] #31064 — [back-end] Implementar endpoint de atualização de rascunho com validação de criador

- Pai: 30728
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31064/activity)

**Descrição:**

Criado: 2026-09-04T21:05:53.295Z
Atualizado: 2026-09-11T20:26:06.282Z
Criar endpoint PUT /api/combinados/rascunhos/{id} no Spring Boot, validando se o status atual é RASCUNHO e se o usuário autenticado é o criador do registro (atende CA02).

###### [TASK] #31065 — [front-end] Construir tela de edição de rascunho pré-preenchida

- Pai: 30728
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31065/activity)

**Descrição:**

Criado: 2026-09-04T21:06:23.378Z
Atualizado: 2026-10-24T17:14:20.763Z
Carregar dados existentes do rascunho no formulário React, permitindo modificação dos campos e disparando requisição de atualização para a API (atende CA02).

###### [TASK] #31068 — [testes] Testar regras de atualização de rascunho e bloqueio de não criadores

- Pai: 30728
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31068/activity)

**Descrição:**

Criado: 2026-09-04T21:06:59.172Z
Atualizado: 2026-10-24T16:59:37.990Z
Criar testes unitários com JUnit validando que alterações em rascunhos são persistidas com sucesso e que tentativas de modificação por outros usuários retornam HTTP 403 (atende CA02).

###### [TASK] #31069 — [po/validação] Homologar retomada e publicação a partir de rascunho (#31511, #31513)

- Pai: 30728
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31069/activity)

**Descrição:**

Criado: 2026-09-04T21:07:27.451Z
Atualizado: 2026-10-24T16:59:54.400Z
Homologar funcionalmente o fluxo de abertura, alteração e salvamento de um rascunho, certificando que nenhum dado foi corrompido ou perdido conforme o critério CA02.

###### [USER STORY] #30729 — US27 - Exclusão de rascunho do combinado

- Pai: 30677
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30729/activity)

**Descrição:**

Criado: 2026-09-03T01:24:08.964Z
Atualizado: 2026-10-21T01:08:23.455Z
História do Usuário
Como criador de um combinado,
quero visualizar meus combinados salvos como rascunho e ter a opção de publicá-los definitivamente,
para gerenciar meus acordos pendentes e enviá-los formalmente para aceite dos participantes.

BDD
Cenário 1: Visualização da lista de rascunhos do usuário
Dado que possuo combinados salvos com status &quot;RASCUNHO&quot;
Quando acessar a aba ou filtro &quot;Meus Rascunhos&quot;
Então o sistema deve exibir apenas os acordos em rascunho criados por mim
E deve fornecer ações para editar, excluir ou publicar cada item.

Cenário 2: Publicação definitiva do rascunho com dados válidos
Dado que estou visualizando ou editando um combinado em &quot;RASCUNHO&quot;
E todos os campos obrigatórios (título, categoria, prazo e ao menos um participante) estão preenchidos
Quando acionar o botão &quot;Publicar Combinado&quot;
Então o sistema deve alterar o status do acordo de &quot;RASCUNHO&quot; para &quot;PENDENTE\_ACEITE&quot;
E deve disparar os convites formais para os participantes selecionados.

Critérios de Aceitação Associados
#31512

###### [TASK] #31071 — [back-end] Implementar endpoint de listagem de rascunhos e transição de status para publicação

- Pai: 30729
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31071/activity)

**Descrição:**

Criado: 2026-09-04T21:09:11.951Z
Atualizado: 2026-10-21T01:08:23.393Z
Criar endpoint GET /api/combinados/meus-rascunhos e POST /api/combinados/{id}/publicar no Spring Boot, validando campos obrigatórios antes da mudança de status para PENDENTE\_ACEITE (atende CA03 e CA04).

###### [TASK] #31072 — [front-end] Implementar aba de rascunhos e fluxo de publicação no painel

- Pai: 30729
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31072/activity)

**Descrição:**

Criado: 2026-09-04T21:09:38.036Z
Atualizado: 2026-10-21T22:56:12.576Z
Construir visualização de cards/tabela no React para exibir os rascunhos do usuário com botão de ação rápida &quot;Publicar&quot;, exibindo modal de confirmação (atende CA03 e CA04).

###### [TASK] #31073 — [testes] Testar transição de estado de Rascunho para Publicado e validação de pendências

- Pai: 30729
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31073/activity)

**Descrição:**

Criado: 2026-09-04T21:10:00.542Z
Atualizado: 2026-10-22T01:06:38.881Z
Criar testes unitários com JUnit validando que combinados com dados faltantes não podem ser publicados e que a publicação com sucesso altera o status e aciona o serviço de convites (atende CA04).

###### [TASK] #31074 — [po/validação] Homologar exclusão e descarte de rascunhos (#31512)

- Pai: 30729
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31074/activity)

**Descrição:**

Criado: 2026-09-04T21:10:23.006Z
Atualizado: 2026-10-22T02:46:27.533Z
Validar de ponta a ponta a listagem filtrada de rascunhos e o fluxo de transição definitiva para o estado ativo/pendente conforme os critérios CA03 e CA04.

###### [CRITÉRIO DE ACEITAÇÃO] #31506 — CA-F08.1 - Salvar e Editar Rascunho

- Pai: 30677
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31506/activity)

**Descrição:**

Criado: 2026-09-05T21:40:23.260Z
Atualizado: 2026-09-16T02:07:51.455Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31510 — CA01 - Salvamento parcial sem validação de campos obrigatórios

- Pai: 31506
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31510/activity)

**Descrição:**

Criado: 2026-09-05T21:40:59.473Z
Atualizado: 2026-09-05T21:40:59.512Z
O sistema deve permitir salvar o progresso da criação do combinado com status RASCUNHO, mesmo que campos obrigatórios ainda não estejam preenchidos.

###### [CRITÉRIO DE ACEITAÇÃO] #31511 — CA02 - Edição e retomada de dados salvos

- Pai: 31506
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31511/activity)

**Descrição:**

Criado: 2026-09-05T21:41:35.405Z
Atualizado: 2026-09-05T21:41:35.513Z
O sistema deve permitir reabrir um rascunho salvo, carregando todas as informações previamente preenchidas para continuação da edição.

###### [CRITÉRIO DE ACEITAÇÃO] #31512 — CA03 - Exclusão definitiva de rascunho

- Pai: 31506
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31512/activity)

**Descrição:**

Criado: 2026-09-05T21:42:08.426Z
Atualizado: 2026-09-05T21:42:08.472Z
O criador deve ter a opção de descartar/excluir um rascunho existente com confirmação prévia, removendo-o definitivamente da sua lista.

###### [CRITÉRIO DE ACEITAÇÃO] #31513 — CA04 - Validação completa na publicação final

- Pai: 31506
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31513/activity)

**Descrição:**

Criado: 2026-09-05T21:42:39.635Z
Atualizado: 2026-09-05T21:42:39.673Z
Ao optar por publicar e finalizar o combinado a partir de um rascunho, o sistema deve exigir o preenchimento de todos os dados obrigatórios e atualizar seu status para ATIVO/ABERTO.

### [EPIC] #30570 — 03 — Participantes e Convites

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30570/activity)

**Descrição:**

Criado: 2026-09-02T18:55:06.973Z
Atualizado: 2026-10-22T02:17:03.492Z
Este EPIC contempla o gerenciamento dos participantes e do processo de convite e confirmação dos combinados, permitindo enviar convites, visualizar os acordos recebidos, aceitar ou recusar a participação e registrar as respostas.

#### [EPIC] #30617 — 03.1 — Envio de Convite

- Pai: 30570
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-04
- Prazo: 2026-10-13
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30617/activity)

**Descrição:**

Criado: 2026-09-02T21:43:42.847Z
Atualizado: 2026-10-22T01:56:46.481Z
Enviar notificação/convite para o participante selecionado.

##### [FEATURE] #30678 — F09 — Enviar Convite

- Pai: 30617
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-10-13
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30678/activity)

**Descrição:**

Criado: 2026-09-03T00:02:59.889Z
Atualizado: 2026-10-22T01:56:46.451Z
F09 — Enviar Convite
Funcionalidade responsável por gerenciar o disparo e a formalização de convites para os participantes selecionados em um combinado, viabilizando o envio individual ou em lote com suporte a mensagens personalizadas do criador.

Critérios de Aceitação:
CA01 — Disparo de Convite com Notificação:
O sistema deve enviar o convite formal ao participante selecionado e gerar uma notificação no painel da aplicação informando o novo convite recebido.

CA02 — Envio de Convites em Lote:
O sistema deve permitir o envio simultâneo de convites para todos os participantes vinculados ao combinado em uma única ação.

CA03 — Inclusão de Mensagem Personalizada:
O sistema deve disponibilizar um campo opcional para que o criador insira uma mensagem introdutória ou observação personalizada no momento do envio do convite.

CA04 — Atualização de Status do Convite:
Após o envio, o status de participação de cada usuário convidado deve ser registrado como &quot;Pendente&quot; até sua manifestação (aceite ou recusa).

###### [USER STORY] #30730 — US28 - Envio de convite para um participante

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-14
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30730/activity)

**Descrição:**

Criado: 2026-09-03T01:25:16.879Z
Atualizado: 2026-09-16T21:35:15.221Z
História do Usuário
Como criador de um combinado,
quero enviar o convite com uma mensagem personalizada para os participantes selecionados,
para formalizar a proposta de acordo e garantir que os envolvidos sejam notificados de imediato.

BDD
Cenário 1: Envio de convite com mensagem personalizada
Dado que estou na etapa de envio de convites de um combinado válido
Quando informar uma mensagem personalizada no campo específico
E acionar o botão de disparo de convites
Então o sistema deve registrar o convite com status &quot;PENDENTE&quot; para cada participante
E deve gerar uma notificação interna no painel de cada convidado contendo a mensagem informada.

Cenário 2: Envio sem mensagem personalizada
Dado que estou disparando os convites de um combinado
Quando deixar o campo de mensagem personalizada em branco
E confirmar o envio
Então o sistema deve processar o envio utilizando o modelo de texto padrão da plataforma
E registrar o status &quot;PENDENTE&quot; normalmente.

Critérios de Aceitação Associados
\- CA01 — O sistema deve enviar o convite formal ao participante selecionado e gerar uma notificação no painel da aplicação.
\- CA03 — O sistema deve disponibilizar um campo opcional para inserção de mensagem personalizada no momento do envio.
\- CA04 — O status de participação de cada usuário convidado deve ser registrado como &quot;Pendente&quot;.

###### [TASK] #31075 — [back-end] Implementar endpoint de envio de convite e persistência de notificação

- Pai: 30730
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31075/activity)

**Descrição:**

Criado: 2026-09-04T21:12:57.058Z
Atualizado: 2026-09-15T18:47:11.450Z
Criar entidade Convite (status PENDENTE, mensagem, dataEnvio), mapear relacionamentos e implementar endpoint POST /api/combinados/{id}/convites gerando registro na tabela de notificações (atende CA01, CA03 e CA04).

###### [TASK] #31076 — [front-end] Implementar modal de envio de convites com campo de mensagem

- Pai: 30730
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31076/activity)

**Descrição:**

Criado: 2026-09-04T21:13:22.234Z
Atualizado: 2026-09-16T03:17:45.780Z
Construir modal em React com prévia dos participantes, textarea opcional para mensagem personalizada e botão de confirmação com loading (atende CA01 e CA03).

###### [TASK] #31077 — [testes] Testar criação de convites, status inicial e persistência de notificações

- Pai: 30730
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31077/activity)

**Descrição:**

Criado: 2026-09-04T21:13:45.121Z
Atualizado: 2026-09-16T18:59:29.505Z
Criar testes unitários com JUnit validando que convites são gerados com status PENDENTE, com ou sem mensagem customizada, e que registros de notificação são criados no banco (atende CA01 e CA04).

###### [TASK] #31078 — [po/validação] Homologar fluxo de envio de convite e notificações (CA01 e CA03)

- Pai: 30730
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31078/activity)

**Descrição:**

Criado: 2026-09-04T21:14:12.313Z
Atualizado: 2026-09-16T21:35:15.179Z
Validar o envio de convites em tela, garantindo que o status pendente e a mensagem personalizada cheguem corretamente ao painel do convidado conforme os critérios CA01, CA03 e CA04.

###### [USER STORY] #30731 — US29 - Envio de convites para múltiplos participantes

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30731/activity)

**Descrição:**

Criado: 2026-09-03T01:26:38.860Z
Atualizado: 2026-09-04T21:17:51.213Z
História do Usuário
Como criador de um combinado,
quero disparar os convites de forma simultânea para todos os participantes selecionados em uma única ação,
para economizar tempo e formalizar o acordo com o grupo de forma unificada.

BDD
Cenário 1: Envio em lote com múltiplos participantes válidos
Dado que configurei um combinado com dois ou mais participantes na lista
Quando acionar o botão &quot;Convidar Todos&quot;
Então o sistema deve processar o disparo para toda a lista em lote
E deve registrar o status &quot;PENDENTE&quot; para cada membro
E exibir mensagem informando a quantidade total de convites enviados com sucesso.

Cenário 2: Tentativa de envio em lote sem participantes vinculados
Dado que estou na tela de envio de convites
E não selecionei nenhum participante para o acordo
Quando tentar acionar o botão de envio
Então o sistema deve desabilitar o botão ou bloquear a ação
E apresentar alerta visual informando a necessidade de adicionar ao menos um participante.

Critérios de Aceitação Associados
\- CA02 — O sistema deve permitir o envio simultâneo de convites para todos os participantes vinculados ao combinado em uma única ação.
\- CA04 — Após o envio, o status de participação de cada usuário convidado deve ser registrado como &quot;Pendente&quot;.

###### [TASK] #31079 — [back-end] Implementar processamento transacional de convites em lote

- Pai: 30731
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31079/activity)

**Descrição:**

Criado: 2026-09-04T21:15:57.706Z
Atualizado: 2026-09-11T20:26:37.500Z
Criar método com @Transactional no Spring Boot para receber a lista de IDs de participantes e persistir os convites com status PENDENTE em batch no banco de dados (atende CA02 e CA04).

###### [TASK] #31080 — [front-end] Implementar botão de ação em lote "Convidar Todos"

- Pai: 30731
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31080/activity)

**Descrição:**

Criado: 2026-09-04T21:16:59.894Z
Atualizado: 2026-10-24T17:14:43.890Z
Criar botão de disparo único na interface React com validação de lista não-vazia, animação de processamento em massa e feedback de total enviado (atende CA02).

###### [TASK] #31081 — [testes] Testar atomicidade e persistência do envio em lote

- Pai: 30731
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31081/activity)

**Descrição:**

Criado: 2026-09-04T21:17:23.232Z
Atualizado: 2026-10-24T17:03:44.114Z
Criar testes de integração com JUnit validando que uma lista de N participantes gera N registros de convites pendentes e que falhas parciais efetuam rollback de toda a operação (atende CA02 e CA04).

###### [TASK] #31082 — [po/validação] Validar disparo simultâneo e contadores de convite (CA02)

- Pai: 30731
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31082/activity)

**Descrição:**

Criado: 2026-09-04T21:17:51.147Z
Atualizado: 2026-10-24T17:08:52.207Z
Homologar o comportamento da interface no disparo em lote com múltiplos convidados, atestando conformidade com os critérios CA02 e CA04.

###### [USER STORY] #30732 — US30 - Mensagem personalizada no convite

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-04
- Prazo: 2026-10-13
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30732/activity)

**Descrição:**

Criado: 2026-09-03T01:30:35.181Z
Atualizado: 2026-10-22T01:56:46.433Z
História do Usuário
Como participante convidado,
quero visualizar os detalhes da proposta e aceitar o convite recebido,
para oficializar minha adesão ao combinado e assumir as responsabilidades definidas.

BDD
Cenário: Aceite de convite válido com sucesso
Dado que recebi uma notificação de convite pendente para um combinado
Quando acessar os detalhes do convite e acionar o botão &quot;Aceitar Convite&quot;
Então o sistema deve alterar meu status de participação para &quot;CONFIRMADO&quot;
E deve notificar o criador sobre a confirmação da minha participação
E liberar meu acesso total ao painel do combinado.

Critérios de Aceitação Associados
\- CA01 — O participante convidado deve ter acesso a todos os detalhes do combinado antes de tomar a decisão.
\- CA02 — O participante convidado deve poder aceitar o convite, alterando seu status de participação para &quot;Confirmado&quot;.
\- CA04 — O sistema deve emitir uma notificação automática para o criador do combinado quando houver manifestação.

###### [TASK] #31083 — [back-end] Implementar endpoint de aceite de convite e gatilho de notificação

- Pai: 30732
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-04
- Prazo: 2026-09-04
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31083/activity)

**Descrição:**

Criado: 2026-09-04T21:21:04.305Z
Atualizado: 2026-09-16T22:40:49.147Z
Criar endpoint POST /api/convites/{id}/aceitar no Spring Boot validando se o usuário autenticado é o destinatário, atualizando status para CONFIRMADO e persistindo notificação para o criador (atende CA02 e CA04).

###### [TASK] #31084 — [front-end] Implementar tela de convite com botão de aceite

- Pai: 30732
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-13
- Prazo: 2026-10-13
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31084/activity)

**Descrição:**

Criado: 2026-09-04T21:21:28.223Z
Atualizado: 2026-10-22T01:56:46.394Z
Construir visualização detalhada do convite no React com dados da proposta, termos acordados e botão destacado &quot;Aceitar Convite&quot; com feedback imediato (atende CA01 e CA02).

###### [TASK] #31085 — [testes] Testar transição de status para CONFIRMADO e segurança de acesso

- Pai: 30732
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31085/activity)

**Descrição:**

Criado: 2026-09-04T21:21:54.659Z
Atualizado: 2026-10-22T01:06:46.134Z
Criar testes automatizados com JUnit validando que convites aceitos mudam o status para ACEITO e que usuários alheios ao convite recebem HTTP 403 Forbidden ao tentar responder (atende CA02).

###### [TASK] #31086 — [po/validação] Validar fluxo funcional de aceite de proposta (CA01 e CA02)

- Pai: 30732
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31086/activity)

**Descrição:**

Criado: 2026-09-04T21:22:20.524Z
Atualizado: 2026-10-22T02:45:31.190Z
Homologar o percurso visual do participante convidado ao visualizar a proposta e confirmar o aceite em estrita conformidade com os critérios CA01 e CA02.

###### [CRITÉRIO DE ACEITAÇÃO] #31916 — CA - Visualizar Detalhes do Combinado

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31916/activity)

**Descrição:**

Criado: 2026-09-06T02:45:19.578Z
Atualizado: 2026-09-06T02:45:19.609Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #32367 — CA-F09.1 - O convite só pode ser enviado se o e-mail do participante for válido e cadastrado

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32367/activity)

**Descrição:**

Criado: 2026-09-16T02:05:00.683Z
Atualizado: 2026-09-16T02:05:00.727Z
Bloqueio de e-mail inválido ou não cadastrado no envio de convite.

###### [CRITÉRIO DE ACEITAÇÃO] #32368 — CA-F09.2 - Todos os campos obrigatórios do convite devem ser preenchidos antes do envio

- Pai: 30678
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32368/activity)

**Descrição:**

Criado: 2026-09-16T02:05:01.448Z
Atualizado: 2026-09-16T02:05:01.513Z
Campos marcados com asterisco devem ser validados antes de enviar.

#### [EPIC] #30618 — 03.2 — Visualização do Convite

- Pai: 30570
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30618/activity)

**Descrição:**

Criado: 2026-09-02T21:44:16.785Z
Atualizado: 2026-10-22T02:17:03.432Z
Tela para o participante visualizar os detalhes do combinado ao qual foi convidado.

##### [FEATURE] #30679 — F10 — Visualizar Convite

- Pai: 30618
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30679/activity)

**Descrição:**

Criado: 2026-09-03T00:03:38.160Z
Atualizado: 2026-10-22T02:17:03.418Z
Tela para o participante visualizar os detalhes do combinado ao qual foi convidado.

Interface e telas para que o usuário participante consiga visualizar todas as informações detalhadas referentes a um convite de combinado para o qual foi chamado.

**Critérios de Aceitação da Feature:**

1.  Exibir dados gerais do combinado: título, descrição, prazo e criador.

2.  Exibir categoria do combinado e lista de participantes com status de confirmação.

3.  Disponibilizar de forma acessível e visível as opções de aceite e recusa do convite.

###### [USER STORY] #30733 — US31 - Visualização básica do convite com título, descrição e prazo

- Pai: 30679
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30733/activity)

**Descrição:**

Criado: 2026-09-03T01:31:09.264Z
Atualizado: 2026-09-11T20:39:45.951Z
História do Usuário
Como participante convidado,
quero recusar um convite recebido podendo informar uma justificativa,
para formalizar a não adesão ao combinado e comunicar o motivo ao criador do acordo.

BDD
Cenário 1: Recusa de convite com justificativa preenchida
Dado que estou na visualização de um convite pendente
Quando acionar o botão &quot;Recusar Convite&quot;
E informar um motivo no campo de justificativa
E confirmar a recusa
Então o sistema deve registrar meu status de participação como &quot;RECUSADO&quot;
E deve armazenar a justificativa apresentada
E deve enviar uma notificação ao criador com o motivo informado.

Cenário 2: Recusa de convite sem preenchimento de justificativa
Dado que estou na visualização de um convite pendente
Quando acionar a recusa sem informar texto no campo opcional de justificativa
E confirmar a ação
Então o sistema deve alterar o status para &quot;RECUSADO&quot; com justificativa em branco
E deve notificar o criador sobre a recusa do convite.

Critérios de Aceitação Associados
\- CA01 — O participante convidado deve ter acesso a todos os detalhes do combinado antes de tomar a decisão.
\- CA03 — O participante convidado deve poder recusar o convite, alterando seu status para &quot;Recusado&quot; e podendo preencher um motivo/justificativa opcional.
\- CA04 — O sistema deve emitir uma notificação automática para o criador do combinado quando houver manifestação.

###### [TASK] #31087 — [back-end] Implementar endpoint de recusa de convite com justificativa

- Pai: 30733
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31087/activity)

**Descrição:**

Criado: 2026-09-04T21:25:54.599Z
Atualizado: 2026-09-11T20:39:55.052Z
Criar endpoint POST /api/convites/{id}/recusar recebendo DTO com justificativa opcional, alterando status para RECUSADO e gerando notificação para o criador (atende CA03 e CA04).

###### [TASK] #31088 — [front-end] Implementar modal de recusa de convite com campo de texto

- Pai: 30733
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31088/activity)

**Descrição:**

Criado: 2026-09-04T21:26:21.340Z
Atualizado: 2026-09-17T02:19:53.422Z
Construir modal em React com campo textarea opcional para inserção de justificativa, botão &quot;Confirmar Recusa&quot; e alerta de confirmação de ação irreversível (atende CA03).

###### [TASK] #31089 — [testes] Testar fluxo de recusa com e sem justificativa

- Pai: 30733
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31089/activity)

**Descrição:**

Criado: 2026-09-04T21:26:44.594Z
Atualizado: 2026-09-16T18:59:40.207Z
Criar testes unitários com JUnit validando persistência do status RECUSADO, armazenamento correto do texto da justificativa e envio da notificação ao criador (atende CA03 e CA04).

###### [TASK] #31090 — [po/validação] Validar registro de recusa e notificação com motivo (CA03 e CA04)

- Pai: 30733
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31090/activity)

**Descrição:**

Criado: 2026-09-04T21:27:12.710Z
Atualizado: 2026-09-16T22:02:14.412Z
Homologar o fluxo de recusa na interface, verificando se o criador recebe o aviso contendo a justificativa preenchida em conformidade com os critérios CA03 e CA04.

###### [USER STORY] #30734 — US32 - Visualização de categoria e participantes confirmados

- Pai: 30679
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-19
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30734/activity)

**Descrição:**

Criado: 2026-09-03T01:32:10.254Z
Atualizado: 2026-10-22T02:17:03.399Z
Funcionalidade: Visualizar categoria e participantes confirmados no convite

Cenário: Exibição dos dados do convite recebido

Dado que o participante recebeu um convite para um combinado

Quando ele acessar a tela de visualização do convite

Então a interface deve exibir a categoria do combinado (ex: empréstimo, despesa, serviço) e a lista dos participantes que já aceitaram o convite.

###### [TASK] #30966 — [Frontend] Criar componente/card visual para exibir a categoria do combinado

- Pai: 30734
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-21
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30966/activity)

**Descrição:**

Criado: 2026-09-04T20:19:36.368Z
Atualizado: 2026-10-22T02:17:19.302Z
(Sem descrição)

###### [TASK] #30967 — [Frontend] Implementar lista com avatares/nomes dos participantes confirmados

- Pai: 30734
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-21
- Prazo: 2026-10-21
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30967/activity)

**Descrição:**

Criado: 2026-09-04T20:19:54.459Z
Atualizado: 2026-10-22T02:17:03.361Z
(Sem descrição)

###### [TASK] #32228 — [back-end] Implementar endpoint de detalhes do convite com categoria e participantes

- Pai: 30734
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32228/activity)

**Descrição:**

Criado: 2026-09-16T01:50:23.364Z
Atualizado: 2026-10-21T01:11:48.820Z
Ajustar GET /convites/{id} para retornar categoria + lista de participantes confirmados (JOIN Convite→Combinado→Participante→Usuario)

###### [TASK] #32229 — [testes] Testar retorno de categoria e lista de participantes confirmados

- Pai: 30734
- Status: On hold
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32229/activity)

**Descrição:**

Criado: 2026-09-16T01:50:24.452Z
Atualizado: 2026-10-22T04:11:31.520Z
Testar 0, 1 e múltiplos participantes. Validar que só ACEITOS aparecem

###### [TASK] #32230 — [po/validação] Homologar exibição de categoria e participantes no convite

- Pai: 30734
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32230/activity)

**Descrição:**

Criado: 2026-09-16T01:50:25.520Z
Atualizado: 2026-09-23T22:55:52.486Z
Validar na tela se categoria e lista de confirmados aparecem corretamente

###### [USER STORY] #30735 — US33 - Botões de ação Aceitar/Recusar nenhum convite

- Pai: 30679
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30735/activity)

**Descrição:**

Criado: 2026-09-03T01:32:50.521Z
Atualizado: 2026-10-22T02:16:15.306Z
**Critérios de Aceitação:**

1.  A interface deve exibir botões claros e visíveis para as ações &quot;Aceitar&quot; e &quot;Recusar&quot; o convite.

2.  Os botões só devem estar habilitados se o status do convite do usuário for &quot;Pendente&quot;.

3.  Ao clicar em qualquer uma das opções, o sistema deve exibir uma confirmação ou alterar o estado da tela para evitar cliques duplos.


**Especificação BDD:**

**Dado que** o participante está visualizando a tela de detalhes de um convite recebido

**Quando** a página carregar com o status do convite como &quot;Pendente&quot;

**Então** a interface deve renderizar os botões &quot;Aceitar&quot; e &quot;Recusar&quot; para que o usuário possa interagir.

###### [TASK] #30977 — [Frontend] Criar componentes de botão "Aceitar" e "Recusar" com estados ativo/desabilitado

- Pai: 30735
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30977/activity)

**Descrição:**

Criado: 2026-09-04T20:23:52.326Z
Atualizado: 2026-10-22T02:16:42.190Z
(Sem descrição)

###### [TASK] #30979 — [Frontend] Implementar lógica de exibição condicional baseada no status "Pendente"

- Pai: 30735
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-20
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30979/activity)

**Descrição:**

Criado: 2026-09-04T20:24:15.988Z
Atualizado: 2026-10-22T02:16:15.271Z
(Sem descrição)

###### [TASK] #32231 — [back-end] Implementar endpoints de aceite e recusa de convite

- Pai: 30735
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32231/activity)

**Descrição:**

Criado: 2026-09-16T01:50:26.593Z
Atualizado: 2026-10-21T01:45:08.779Z
Criar PATCH /convites/{id}/aceitar e /recusar, alterando status e gravando data/hora

###### [TASK] #32232 — [testes] Testar endpoints de aceite e recusa (sucesso e erro)

- Pai: 30735
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32232/activity)

**Descrição:**

Criado: 2026-09-16T01:50:27.518Z
Atualizado: 2026-10-22T01:32:00.724Z
Testar aceite/recusa válidos, duplicados e convite inexistente (404)

###### [TASK] #32233 — [po/validação] Homologar botões e comportamento de ação no convite

- Pai: 30735
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32233/activity)

**Descrição:**

Criado: 2026-09-16T01:50:28.476Z
Atualizado: 2026-09-23T22:48:43.656Z
Validar exibição dos botões apenas em PENDENTE e feedback pós-ação

###### [CRITÉRIO DE ACEITAÇÃO] #32369 — CA-F10.1 - Botões Aceitar e Recusar devem aparecer apenas quando o convite estiver PENDENTE

- Pai: 30679
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32369/activity)

**Descrição:**

Criado: 2026-09-16T02:05:02.302Z
Atualizado: 2026-09-16T02:05:02.352Z
Botões desabilitados/ocultos após resposta do convite.

###### [CRITÉRIO DE ACEITAÇÃO] #32370 — CA-F10.2 - Lista de participantes confirmados deve exibir apenas quem aceitou

- Pai: 30679
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32370/activity)

**Descrição:**

Criado: 2026-09-16T02:05:03.141Z
Atualizado: 2026-09-16T02:05:03.179Z
Pendentes e recusados não aparecem na lista de confirmados.

#### [EPIC] #30619 — 03.3 — Aceitar Convite

- Pai: 30570
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-08
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30619/activity)

**Descrição:**

Criado: 2026-09-02T21:44:42.581Z
Atualizado: 2026-09-16T22:26:28.353Z
Lógica para registrar a aceitação, com data/hora.

##### [FEATURE] #30680 — F11 — Aceitar Convite

- Pai: 30619
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-08
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30680/activity)

**Descrição:**

Criado: 2026-09-03T00:04:06.424Z
Atualizado: 2026-09-16T22:26:28.340Z
Lógica para registrar a aceitação, com data/hora.

Funcionalidade que permite ao participante convidado confirmar sua entrada em um combinado, alterando o status do convite e notificando a interface do usuário.

**Critérios de Aceitação da Feature:**

1.  Permitir a confirmação de participação com registro de carimbo de data e hora.

2.  Atualizar automaticamente o status do combinado para o participante na interface.

3.  Exibir feedback e notificações visuais claras de confirmação para o usuário.

###### [USER STORY] #30736 — US34 - Aceitação de convite com registro de dados/hora

- Pai: 30680
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-08
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30736/activity)

**Descrição:**

Criado: 2026-09-03T01:33:21.464Z
Atualizado: 2026-09-16T22:26:28.323Z
**Critérios de Aceitação:**

1.  O clique no botão &quot;Aceitar&quot; deve acionar o serviço do backend para atualizar o status do convite.

2.  O registro do evento deve enviar o carimbo de data e hora exatos da confirmação.

3.  A tela deve exibir feedback visual de sucesso logo após a confirmação.


**Especificação BDD:**

**Dado que** o usuário está visualizando os botões de ação de um convite pendente

**Quando** ele clicar no botão &quot;Aceitar Convite&quot;

**Então** o sistema deve registrar a aceitação com data/hora e atualizar a interface informando a confirmação.

###### [TASK] #30985 — [Frontend] Integrar botão de aceite com o endpoint da API enviando timestamp

- Pai: 30736
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-09-15
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30985/activity)

**Descrição:**

Criado: 2026-09-04T20:26:42.226Z
Atualizado: 2026-09-16T03:23:16.288Z
(Sem descrição)

###### [TASK] #30986 — [Frontend] Implementar alerta/modal de sucesso após confirmação do aceite

- Pai: 30736
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30986/activity)

**Descrição:**

Criado: 2026-09-04T20:26:52.004Z
Atualizado: 2026-09-16T03:25:13.967Z
(Sem descrição)

###### [TASK] #32234 — [back-end] Implementar registro de timestamp no aceite

- Pai: 30736
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-08
- Prazo: 2026-09-08
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32234/activity)

**Descrição:**

Criado: 2026-09-16T01:50:29.520Z
Atualizado: 2026-09-16T22:27:07.914Z
Gravar respondido_em no servidor ao aceitar

###### [TASK] #32235 — [testes] Testar persistência de data/hora no aceite

- Pai: 30736
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32235/activity)

**Descrição:**

Criado: 2026-09-16T01:50:30.492Z
Atualizado: 2026-10-22T01:35:26.256Z
Verificar no banco o campo respondido_em e formato/fuso

###### [TASK] #32236 — [po/validação] Homologar registro de aceite no histórico

- Pai: 30736
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32236/activity)

**Descrição:**

Criado: 2026-09-16T01:50:32.404Z
Atualizado: 2026-09-16T22:01:18.343Z
Validar evento na timeline com autor e data

###### [USER STORY] #30737 — US35 - Atualização automática do status do combinado ao aceitar

- Pai: 30680
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30737/activity)

**Descrição:**

Criado: 2026-09-03T01:33:53.117Z
Atualizado: 2026-09-16T01:51:23.541Z
**Critérios de Aceitação:**

1.  Logo após o aceite do convite, o status do combinado exibido na tela deve mudar de &quot;Pendente&quot; para &quot;Ativo&quot; / &quot;Confirmado&quot; sem necessidade de recarregar a página (F5).

2.  O estado global/local da aplicação deve ser atualizado refletindo o novo status do usuário no combinado.

3.  Em caso de falha de conexão na atualização, o sistema deve exibir uma mensagem de erro apropriada e manter a tela no estado anterior.


**Especificação BDD:**

**Dado que** o usuário clicou para aceitar um convite com sucesso

**Quando** o backend retornar a confirmação do aceite

**Então** a interface deve atualizar automaticamente o indicador de status do combinado para refletir o novo estado na tela.

###### [TASK] #30992 — [Frontend] Implementar atualização em tempo real do badge/indicador de status do combinado

- Pai: 30737
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30992/activity)

**Descrição:**

Criado: 2026-09-04T20:30:57.341Z
Atualizado: 2026-09-06T01:10:55.080Z
(Sem descrição)

###### [TASK] #30993 — [Frontend] Tratar respostas da API e re-renderizar a tela com os dados atualizados do combinado

- Pai: 30737
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30993/activity)

**Descrição:**

Criado: 2026-09-04T20:31:06.289Z
Atualizado: 2026-09-06T01:10:58.396Z
(Sem descrição)

###### [TASK] #32268 — [back-end] Implementar gatilho de atualização de status do combinado

- Pai: 30737
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32268/activity)

**Descrição:**

Criado: 2026-09-16T01:51:21.392Z
Atualizado: 2026-10-24T17:04:01.393Z
Verificar todos aceitaram → AGUARDANDO vira ATIVO

###### [TASK] #32269 — [testes] Testar transição AGUARDANDO → ATIVO

- Pai: 30737
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32269/activity)

**Descrição:**

Criado: 2026-09-16T01:51:22.403Z
Atualizado: 2026-10-24T17:09:28.500Z
Testar 1/2 aceitou 2/2 aceitaram 1 recusou

###### [TASK] #32270 — [po/validação] Homologar mudança automática de status

- Pai: 30737
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32270/activity)

**Descrição:**

Criado: 2026-09-16T01:51:23.429Z
Atualizado: 2026-10-24T17:21:00.929Z
Validar mudança sem refresh

###### [TASK] #30741 — US36 - Mensagem de confirmação após aceitação

- Pai: 30680
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30741/activity)

**Descrição:**

Criado: 2026-09-03T01:43:00.570Z
Atualizado: 2026-09-04T20:31:42.856Z
**Critérios de Aceitação:**

1.  Um feedback visual (Toast, Banner ou Modal) deve ser exibido na tela informando que o convite foi aceito com sucesso.

2.  A notificação visual deve sumir automaticamente após alguns segundos ou possuir botão para fechar.

3.  A notificação deve ter um estilo claro de sucesso (ex: cor verde ou ícone de checagem).


**Especificação BDD:**

**Dado que** o usuário aceitou um convite

**Quando** a ação for processada com êxito

**Então** a interface deve exibir um alerta/toast notificando visualmente que a participação no combinado foi confirmada.

###### [TASK] #30997 — [Frontend] Criar componente de notificação visual (Toast/Banner) de sucesso para confirmação de aceite

- Pai: 30741
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30997/activity)

**Descrição:**

Criado: 2026-09-04T20:32:50.211Z
Atualizado: 2026-09-06T01:17:53.642Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #32371 — CA-F11.1 - Ao aceitar o status do combinado atualiza automaticamente para ATIVO quando todos aceitarem

- Pai: 30680
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32371/activity)

**Descrição:**

Criado: 2026-09-16T02:05:03.983Z
Atualizado: 2026-09-16T02:05:04.037Z
Regra de transição automática de status.

###### [CRITÉRIO DE ACEITAÇÃO] #32372 — CA-F11.2 - Deve ser exibida notificação visual de sucesso ao aceitar convite

- Pai: 30680
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32372/activity)

**Descrição:**

Criado: 2026-09-16T02:05:04.802Z
Atualizado: 2026-09-16T02:05:04.851Z
Toast/modal de confirmação ao aceitar.

#### [EPIC] #30621 — 03.4 — Recusar Convite

- Pai: 30570
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30621/activity)

**Descrição:**

Criado: 2026-09-02T21:45:11.616Z
Atualizado: 2026-09-16T22:27:52.556Z
Lógica para registrar a recusa, com data/hora.

##### [FEATURE] #30681 — F12 — Recusar Convite

- Pai: 30621
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-11
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30681/activity)

**Descrição:**

Criado: 2026-09-03T00:04:30.097Z
Atualizado: 2026-09-16T22:27:52.540Z
Lógica para registrar a recusa, com data/hora.

Permite que o participante convidado recuse a participação em um combinado, fornecendo feedback visual e atualizando o status do convite no sistema.

**Critérios de Aceitação da Feature:**

1.  Permitir que o usuário recuse o convite diretamente na interface de visualização.

2.  Solicitar/permitir justificativa opcional ou confirmação de recusa.

3.  Atualizar a lista e os detalhes do convite removendo ou alterando o status para &quot;Recusado&quot;.

###### [USER STORY] #30738 — US37 - Recusa de convite com registro de data/hora

- Pai: 30681
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30738/activity)

**Descrição:**

Criado: 2026-09-03T01:41:26.030Z
Atualizado: 2026-09-16T22:27:52.518Z
**Critérios de Aceitação:**

1.  O botão &quot;Recusar&quot; deve disparar a alteração do status do convite para &quot;Recusado&quot;.

2.  O sistema deve solicitar confirmação do usuário antes de efetivar a recusa para evitar cliques acidentais.

3.  Após a recusa, a tela deve ser atualizada impedindo novas ações no convite.


**Especificação BDD:**

**Dado que** o usuário recebeu um convite com status pendente

**Quando** ele clicar no botão &quot;Recusar&quot; e confirmar a ação

**Então** a interface deve enviar a solicitação ao sistema, alterar o status do convite para &quot;Recusado&quot; e atualizar a visualização.

###### [TASK] #31002 — [Frontend] Implementar modal/pop-up de confirmação ao clicar em "Recusar Convite"

- Pai: 30738
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31002/activity)

**Descrição:**

Criado: 2026-09-04T20:36:58.110Z
Atualizado: 2026-09-16T03:28:03.656Z
(Sem descrição)

###### [TASK] #31003 — [Frontend] Integrar o botão de recusa com a API e atualizar estado da tela para "Recusado"

- Pai: 30738
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31003/activity)

**Descrição:**

Criado: 2026-09-04T20:37:07.354Z
Atualizado: 2026-09-17T02:18:50.014Z
(Sem descrição)

###### [TASK] #32237 — [back-end] Implementar endpoint de recusa com timestamp

- Pai: 30738
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32237/activity)

**Descrição:**

Criado: 2026-09-16T01:50:34.459Z
Atualizado: 2026-09-16T22:28:17.694Z
PATCH /convites/{id}/recusar gravando status RECUSADO e respondido_em

###### [TASK] #32238 — [testes] Testar persistência de recusa e data/hora

- Pai: 30738
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32238/activity)

**Descrição:**

Criado: 2026-09-16T01:50:36.378Z
Atualizado: 2026-10-22T01:35:14.342Z
Validar status e timestamp no banco. Testar recusa duplicada

###### [TASK] #32239 — [po/validação] Homologar recusa de convite

- Pai: 30738
- Status: In progress
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32239/activity)

**Descrição:**

Criado: 2026-09-16T01:50:38.424Z
Atualizado: 2026-09-16T22:01:36.334Z
Validar exibição Recusado na lista e timeline

###### [USER STORY] #30739 — US38 - Atualização automática do status do combinado ao recusar

- Pai: 30681
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30739/activity)

**Descrição:**

Criado: 2026-09-03T01:41:48.703Z
Atualizado: 2026-09-16T01:51:27.461Z
**Critérios de Aceitação:**

1.  O modal/tela de recusa deve disponibilizar um campo de texto (textarea) para o usuário inserir uma justificativa opcional.

2.  O usuário deve conseguir concluir a recusa mesmo deixando o campo de justificativa em branco.

3.  A justificativa informada deve ser enviada junto com o status no momento da recusa.


**Especificação BDD:**

**Dado que** o usuário abriu a opção de recusar o convite

**Quando** ele preencher (ou não) o campo de justificativa opcional e clicar em enviar

**Então** o sistema deve processar a recusa registrando o motivo fornecido caso exista.

###### [TASK] #31006 — [Frontend] Adicionar campo de texto opcional para justificativa de recusa na interface

- Pai: 30739
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31006/activity)

**Descrição:**

Criado: 2026-09-04T20:38:25.337Z
Atualizado: 2026-09-06T01:19:20.492Z
(Sem descrição)

###### [TASK] #32271 — [back-end] Implementar regra de atualização de status após recusa

- Pai: 30739
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32271/activity)

**Descrição:**

Criado: 2026-09-16T01:51:25.390Z
Atualizado: 2026-10-24T17:20:21.812Z
Recusa não avança para ATIVO; notificar criador

###### [TASK] #32272 — [testes] Testar impacto da recusa no status do combinado

- Pai: 30739
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32272/activity)

**Descrição:**

Criado: 2026-09-16T01:51:26.428Z
Atualizado: 2026-10-24T17:25:33.511Z
1 de 3 recusa → status permanece AGUARDANDO

###### [TASK] #32273 — [po/validação] Homologar comportamento do combinado após recusa

- Pai: 30739
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32273/activity)

**Descrição:**

Criado: 2026-09-16T01:51:27.398Z
Atualizado: 2026-10-24T17:09:43.330Z
Validar aviso/notificação ao criador

###### [USER STORY] #30740 — US39 - Motivo opcional para recusa do convite

- Pai: 30681
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30740/activity)

**Descrição:**

Criado: 2026-09-03T01:42:43.924Z
Atualizado: 2026-09-16T01:51:32.486Z
**Critérios de Aceitação:**

1.  A interface deve exibir uma notificação visual (Toast/Alerta) informando que o convite foi recusado.

2.  O card ou elemento do convite deve ser atualizado para o estado &quot;Recusado&quot; ou removido da lista de pendências.

3.  A notificação deve desaparecer após alguns segundos.


**Especificação BDD:**

**Dado que** o usuário recusou um convite

**Quando** a requisição for concluída com sucesso

**Então** a interface deve exibir um alerta/toast confirmando a recusa e redirecionar ou atualizar a lista de convites.

###### [TASK] #31794 — [Frontend] Implementar notificação de feedback para recusa e redirecionamento na UI

- Pai: 30740
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31794/activity)

**Descrição:**

Criado: 2026-09-06T01:20:32.149Z
Atualizado: 2026-09-06T01:20:39.327Z
(Sem descrição)

###### [TASK] #32274 — [back-end] Adicionar campo opcional motivo na recusa

- Pai: 30740
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32274/activity)

**Descrição:**

Criado: 2026-09-16T01:51:29.371Z
Atualizado: 2026-10-24T17:20:35.189Z
Coluna nullable + aceitar no DTO

###### [TASK] #32275 — [testes] Testar recusa com e sem motivo

- Pai: 30740
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32275/activity)

**Descrição:**

Criado: 2026-09-16T01:51:30.417Z
Atualizado: 2026-10-24T17:22:16.160Z
Testar null preenchido e limite de caracteres

###### [TASK] #32276 — [po/validação] Homologar exibição do motivo no histórico

- Pai: 30740
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32276/activity)

**Descrição:**

Criado: 2026-09-16T01:51:32.384Z
Atualizado: 2026-10-24T17:25:46.384Z
Validar exibição no histórico

#### [EPIC] #30622 — 03.5 — Gerenciamento de Status do Convite

- Pai: 30570
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-16
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30622/activity)

**Descrição:**

Criado: 2026-09-02T21:45:50.496Z
Atualizado: 2026-10-22T02:15:46.977Z
Controle dos status: _pendente_, _aceito_, _recusado_.

##### [FEATURE] #30682 — F13 — Gerenciar Status do Convite

- Pai: 30622
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30682/activity)

**Descrição:**

Criado: 2026-09-03T00:05:19.309Z
Atualizado: 2026-10-22T02:15:46.948Z
Controle dos status: pendente, aceito, recusado.

Permite o acompanhamento e a gestão do ciclo de vida dos convites enviados e recebidos, exibindo os status atualizados de cada participante.

**Critérios de Aceitação da Feature:**

1.  Exibir claramente o status individual do convite de cada participante (Pendente, Aceito, Recusado).

2.  Permitir o cancelamento ou alteração do status do convite pelo criador, caso aplicável.

3.  Refletir as mudanças de status em tempo real ou mediante atualização da tela.

###### [USER STORY] #30742 — US40 - Visualização do status Pendente/Aceito/Recusado por participante

- Pai: 30682
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-16
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30742/activity)

**Descrição:**

Criado: 2026-09-03T01:44:49.012Z
Atualizado: 2026-10-22T02:15:46.923Z
**Critérios de Aceitação:**

1.  A interface deve exibir tags/badges visuais com cores distintas para cada status do convite (ex: Amarelo para Pendente, Verde para Aceito, Vermelho para Recusado).

2.  O indicador de status deve estar posicionado em local visível na tela de detalhes do convite.

3.  Caso o status do convite mude, o componente visual deve refletir a nova cor e rótulo imediatamente.


**Especificação BDD:**

**Dado que** o usuário está consultando um convite no sistema

**Quando** a tela carregar as informações do convite

**Então** a interface deve renderizar o badge com o texto e a cor correspondentes ao status atual (Pendente, Aceito ou Recusado).

###### [TASK] #31010 — [Frontend] Criar componente visual de Badge/Tag reutilizável para exibição dos status (Pendente, Aceito, Recusado)

- Pai: 30742
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31010/activity)

**Descrição:**

Criado: 2026-09-04T20:41:27.488Z
Atualizado: 2026-10-22T02:15:46.873Z
(Sem descrição)

###### [TASK] #32246 — [back-end] Implementar endpoint de listagem de status por participante

- Pai: 30742
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32246/activity)

**Descrição:**

Criado: 2026-09-16T01:50:48.403Z
Atualizado: 2026-10-21T01:14:45.825Z
GET /combinados/{id}/participantes retornando nome, email e status

###### [TASK] #32247 — [testes] Testar retorno de status por participante

- Pai: 30742
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32247/activity)

**Descrição:**

Criado: 2026-09-16T01:50:50.406Z
Atualizado: 2026-10-22T01:34:52.989Z
Testar 0, 1 e múltiplos com status diferentes

###### [TASK] #32248 — [po/validação] Homologar exibição de status por participante

- Pai: 30742
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32248/activity)

**Descrição:**

Criado: 2026-09-16T01:50:51.379Z
Atualizado: 2026-09-23T22:56:00.652Z
Validar status correto na tela de detalhes

###### [USER STORY] #30744 — US41 - Atualização em tempo real dos status dos convites

- Pai: 30682
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30744/activity)

**Descrição:**

Criado: 2026-09-03T01:45:15.138Z
Atualizado: 2026-09-17T11:28:11.063Z
**Critérios de Aceitação:**

1.  A tela deve disponibilizar uma seção/linha do tempo mostrando o histórico de eventos do convite (ex: &quot;Enviado em DD/MM&quot;, &quot;Aceito em DD/MM&quot;).

2.  Cada registro do histórico deve exibir a data, a hora e o usuário responsável pela alteração.

3.  A listagem do histórico deve estar organizada em ordem cronológica (mais recente ao mais antigo).


**Especificação BDD:**

**Dado que** o usuário acessou a tela de detalhes ou histórico do convite

**Quando** ele solicitar a visualização do histórico de status

**Então** a interface deve exibir uma lista com todas as mudanças de status ocorridas, com seus respectivos horários e responsáveis.

###### [TASK] #31012 — [Frontend] Implementar componente de Timeline/Linha do tempo para o histórico do convite

- Pai: 30744
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31012/activity)

**Descrição:**

Criado: 2026-09-04T20:42:41.729Z
Atualizado: 2026-09-06T01:22:33.421Z
(Sem descrição)

###### [TASK] #31013 — [Frontend] Formatar e exibir datas/horários dos registros do histórico na interface

- Pai: 30744
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31013/activity)

**Descrição:**

Criado: 2026-09-04T20:42:47.310Z
Atualizado: 2026-09-06T01:22:35.936Z
(Sem descrição)

###### [TASK] #32277 — [back-end] Implementar polling ou websocket para atualização de status

- Pai: 30744
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32277/activity)

**Descrição:**

Criado: 2026-09-16T01:51:33.397Z
Atualizado: 2026-10-24T17:20:47.246Z
Endpoint de polling (ex: 10s) ou WebSocket

###### [TASK] #32278 — [testes] Testar atualização automática de status

- Pai: 30744
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32278/activity)

**Descrição:**

Criado: 2026-09-16T01:51:34.393Z
Atualizado: 2026-10-24T17:19:22.242Z
2 navegadores: um aceita outro vê sem refresh

###### [TASK] #32279 — [po/validação] Homologar tempo real na tela de convites

- Pai: 30744
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32279/activity)

**Descrição:**

Criado: 2026-09-16T01:51:36.406Z
Atualizado: 2026-10-24T17:25:58.303Z
Validar atualização em até X segundos

###### [USER STORY] #30745 — US42 - Indicador visual (ícones/cores) para cada status

- Pai: 30682
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30745/activity)

**Descrição:**

Criado: 2026-09-03T01:46:25.836Z
Atualizado: 2026-09-16T01:51:42.462Z
**Critérios de Aceitação:**

1.  Para convites com status &quot;Pendente&quot;, o criador do combinado deve ver botões de ação para &quot;Reenviar Convite&quot; e &quot;Cancelar Convite&quot;.

2.  Ao clicar em cancelar, o sistema deve pedir confirmação antes de remover/inativar o convite.

3.  Ao clicar em reenviar, a interface deve disparar a ação de notificação e dar um feedback visual de sucesso ao criador.


**Especificação BDD:**

**Dado que** o criador do combinado está visualizando um convite com status &quot;Pendente&quot;

**Quando** ele clicar na opção de reenviar ou cancelar o convite

**Então** a interface deve executar a ação selecionada, exibindo modal de confirmação ou mensagem de sucesso e atualizando a tela.

###### [TASK] #31015 — [Frontend] Criar botões e modal de confirmação para ações de cancelamento e reenvio de convite

- Pai: 30745
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31015/activity)

**Descrição:**

Criado: 2026-09-04T20:43:48.683Z
Atualizado: 2026-09-06T01:25:15.375Z
(Sem descrição)

###### [TASK] #31016 — [Frontend] Integrar ações de reenviar e cancelar convite com os endpoints correspondentes

- Pai: 30745
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31016/activity)

**Descrição:**

Criado: 2026-09-04T20:44:01.577Z
Atualizado: 2026-09-06T01:25:18.364Z
(Sem descrição)

###### [TASK] #32280 — [front-end] Definir e implementar paleta de cores/ícones por status

- Pai: 30745
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32280/activity)

**Descrição:**

Criado: 2026-09-16T01:51:38.389Z
Atualizado: 2026-10-24T17:21:32.207Z
Pendente (amarelo) Aceito (verde) Recusado (vermelho)

###### [TASK] #32281 — [testes] Testar consistência visual dos indicadores

- Pai: 30745
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32281/activity)

**Descrição:**

Criado: 2026-09-16T01:51:40.412Z
Atualizado: 2026-10-24T17:26:20.301Z
Validar em todas as telas

###### [TASK] #32282 — [po/validação] Homologar indicadores visuais de status

- Pai: 30745
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32282/activity)

**Descrição:**

Criado: 2026-09-16T01:51:42.384Z
Atualizado: 2026-10-24T17:26:09.151Z
Validar acessibilidade (daltonismo) e consistência

### [EPIC] #30574 — 04 — Acompanhamento de Combinados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-09
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30574/activity)

**Descrição:**

Criado: 2026-09-02T18:55:31.066Z
Atualizado: 2026-10-21T01:05:25.701Z
Este EPIC contempla as funcionalidades de consulta e acompanhamento dos combinados, permitindo visualizar combinados criados e recebidos, consultar seus detalhes, participantes, prazos, status e responsabilidades.

#### [EPIC] #30623 — 04.1 — Listar Combinados Criados

- Pai: 30574
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-09
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30623/activity)

**Descrição:**

Criado: 2026-09-02T21:46:41.157Z
Atualizado: 2026-10-22T02:15:09.869Z
listagem dos combinados que o usuário criou.

##### [FEATURE] #30683 — F14 — Listar Combinados Criados

- Pai: 30623
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-09
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30683/activity)

**Descrição:**

Criado: 2026-09-03T00:05:48.085Z
Atualizado: 2026-10-22T02:15:09.845Z
Listagem dos combinados que o usuário criou.

Exibição estruturada de todos os combinados que foram criados pelo usuário logado, permitindo navegação rápida, acompanhamento do status geral e acesso aos detalhes de cada um.

**Critérios de Aceitação da Feature:**

1.  Listar em formato de cards ou tabela todos os combinados criados pelo usuário.

2.  Exibir informações resumidas de cada combinado (título, quantidade de participantes, status e data).

3.  Tratar cenários em que o usuário ainda não criou nenhum combinado (estado vazio).

###### [USER STORY] #30746 — US43 - Listagem básica de combinados criados pelo usuário

- Pai: 30683
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Felipe Macedo
- Início: 2026-09-09
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30746/activity)

**Descrição:**

Criado: 2026-09-03T01:48:01.801Z
Atualizado: 2026-09-16T22:29:05.972Z
**Critérios de Aceitação:**

1.  A página deve buscar e renderizar a lista de todos os combinados onde o usuário atual é o criador.

2.  Cada item da lista deve ser apresentado em formato de card/linha contendo título e categoria.

3.  A listagem deve possuir estado de carregamento (_skeleton_ ou _spinner_) enquanto busca os dados da API.


**Especificação BDD:**

**Dado que** o usuário está autenticado no sistema

**Quando** ele acessar a tela de &quot;Meus Combinados Criados&quot;

**Então** a interface deve exibir a lista contendo todos os combinados criados por ele.

###### [TASK] #31023 — [Frontend] Criar a estrutura e layout da página de listagem "Meus Combinados Criados"

- Pai: 30746
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31023/activity)

**Descrição:**

Criado: 2026-09-04T20:47:55.805Z
Atualizado: 2026-09-17T02:19:26.616Z
(Sem descrição)

###### [TASK] #31025 — [Frontend] Integrar listagem com a API e implementar estados de carregamento (loading)

- Pai: 30746
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31025/activity)

**Descrição:**

Criado: 2026-09-04T20:48:10.198Z
Atualizado: 2026-09-17T02:19:15.426Z
(Sem descrição)

###### [TASK] #32240 — [back-end] Implementar endpoint de listagem de combinados criados

- Pai: 30746
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-09
- Prazo: 2026-09-09
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32240/activity)

**Descrição:**

Criado: 2026-09-16T01:50:40.569Z
Atualizado: 2026-09-16T22:29:12.431Z
GET /combinados?criadorId={id}

###### [TASK] #32241 — [testes] Testar listagem de combinados criados

- Pai: 30746
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32241/activity)

**Descrição:**

Criado: 2026-09-16T01:50:42.388Z
Atualizado: 2026-10-22T01:34:36.204Z
Testar 0, 1 e múltiplos combinados; validar paginação

###### [TASK] #32242 — [po/validação] Homologar listagem de combinados criados

- Pai: 30746
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32242/activity)

**Descrição:**

Criado: 2026-09-16T01:50:43.383Z
Atualizado: 2026-09-16T21:25:52.207Z
Validar que só aparecem combinados do usuário logado

###### [USER STORY] #30747 — US44 - Listagem de combinados criados com título, status e prazo

- Pai: 30683
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-15
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30747/activity)

**Descrição:**

Criado: 2026-09-03T01:48:35.913Z
Atualizado: 2026-10-22T02:15:09.784Z
**Critérios de Aceitação:**

1.  O card do combinado deve exibir o título, a categoria, a data de criação/prazo e a quantidade de participantes.

2.  O card deve conter um badge visual simples com o status geral do combinado (ex: Ativo, Concluído, Pendente).

3.  O design do card deve ser responsivo e adaptável para dispositivos móveis e desktop.


**Especificação BDD:**

**Dado que** a lista de combinados criados foi carregada na tela

**Quando** os cards forem renderizados

**Então** cada card deve apresentar de forma clara e resumida o título, categoria, status e total de participantes.

###### [TASK] #31027 — [Frontend] Desenvolver componente reusável de Card para exibição resumida do combinado

- Pai: 30747
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31027/activity)

**Descrição:**

Criado: 2026-09-04T20:49:10.710Z
Atualizado: 2026-10-22T02:15:09.713Z
(Sem descrição)

###### [TASK] #32249 — [back-end] Ajustar DTO para incluir título status e prazo

- Pai: 30747
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32249/activity)

**Descrição:**

Criado: 2026-09-16T01:50:53.408Z
Atualizado: 2026-10-21T01:13:37.404Z
Garantir retorno dos 3 campos em cada item

###### [TASK] #32250 — [testes] Testar retorno dos campos obrigatórios na listagem

- Pai: 30747
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32250/activity)

**Descrição:**

Criado: 2026-09-16T01:50:54.406Z
Atualizado: 2026-10-22T01:34:23.600Z
Validar preenchimento correto dos 3 campos

###### [TASK] #32251 — [po/validação] Homologar exibição de título status e prazo

- Pai: 30747
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32251/activity)

**Descrição:**

Criado: 2026-09-16T01:50:56.386Z
Atualizado: 2026-09-23T22:51:27.975Z
Validar formatação de data e badge de status

###### [USER STORY] #30748 — US45 - Ordenação de combinados por data de criação

- Pai: 30683
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30748/activity)

**Descrição:**

Criado: 2026-09-03T01:48:59.753Z
Atualizado: 2026-09-16T01:51:46.466Z
**Critérios de Aceitação:**

1.  Caso o usuário possua muitos combinados criados, a lista deve carregar os dados em páginas ou via _infinite scroll_.

2.  Deve haver controles claros de navegação de página (Anterior/Próxima) ou indicador de carregamento ao rolar até o fim da página.

3.  O sistema deve manter a performance fluida sem travar a interface visual ao carregar mais itens.


**Especificação BDD:**

**Dado que** o usuário possui um volume de combinados criados maior que o limite por página

**Quando** ele navegar pelas páginas ou rolar a tela até o final

**Então** a interface deve carregar e exibir o próximo lote de combinados sem recarregar a página inteira.

###### [TASK] #31030 — [Frontend] Implementar componente ou lógica de paginação/scroll para a lista de combinados

- Pai: 30748
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31030/activity)

**Descrição:**

Criado: 2026-09-04T20:49:59.193Z
Atualizado: 2026-09-06T01:30:55.322Z
(Sem descrição)

###### [TASK] #32283 — [back-end] Implementar ordenação por data de criação

- Pai: 30748
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32283/activity)

**Descrição:**

Criado: 2026-09-16T01:51:43.383Z
Atualizado: 2026-10-24T17:24:44.008Z
sort=criadoEm desc

###### [TASK] #32284 — [testes] Testar ordenação crescente e decrescente

- Pai: 30748
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32284/activity)

**Descrição:**

Criado: 2026-09-16T01:51:44.382Z
Atualizado: 2026-10-24T17:26:31.920Z
ASC e DESC

###### [TASK] #32285 — [po/validação] Homologar ordenação padrão da listagem

- Pai: 30748
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32285/activity)

**Descrição:**

Criado: 2026-09-16T01:51:46.396Z
Atualizado: 2026-10-24T17:26:44.748Z
Mais recente primeiro por padrão

###### [USER STORY] #30749 — US46 - Ordenação de combinados por prazo e por status

- Pai: 30683
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30749/activity)

**Descrição:**

Criado: 2026-09-03T01:49:22.591Z
Atualizado: 2026-09-16T01:51:50.379Z
**Critérios de Aceitação:**

1.  A interface deve disponibilizar um campo de seleção (_select_) para ordenar a lista (ex: &quot;Mais recentes&quot;, &quot;Mais antigos&quot;, &quot;Por ordem alfabética&quot;).

2.  Ao alterar a opção de ordenação, a lista deve ser reorganizada na tela imediatamente.

3.  A ordenação padrão deve ser pelos combinados criados mais recentemente.


**Especificação BDD:**

**Dado que** o usuário está visualizando a lista de combinados criados

**Quando** ele selecionar um critério de ordenação no menu suspenso

**Então** a interface deve reordenar os cards exibidos de acordo com o filtro selecionado.

###### [TASK] #31032 — [Frontend] Criar componente Select de ordenação e integrar com a ordenação dos dados na UI

- Pai: 30749
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31032/activity)

**Descrição:**

Criado: 2026-09-04T20:50:44.850Z
Atualizado: 2026-09-06T01:31:25.493Z
(Sem descrição)

###### [TASK] #32286 — [back-end] Implementar ordenação por prazo e status

- Pai: 30749
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32286/activity)

**Descrição:**

Criado: 2026-09-16T01:51:48.381Z
Atualizado: 2026-10-24T17:24:06.521Z
sort=prazo asc sort=status desc etc

###### [TASK] #32287 — [testes] Testar múltiplos critérios de ordenação

- Pai: 30749
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32287/activity)

**Descrição:**

Criado: 2026-09-16T01:51:49.308Z
Atualizado: 2026-10-24T17:05:09.265Z
Todas as combinações

###### [TASK] #32288 — [po/validação] Homologar seletor de ordenação

- Pai: 30749
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32288/activity)

**Descrição:**

Criado: 2026-09-16T01:51:50.309Z
Atualizado: 2026-10-24T17:26:56.525Z
Dropdown funcional na UI

###### [USER STORY] #30750 — US47 - Busca de combinados por título

- Pai: 30683
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30750/activity)

**Descrição:**

Criado: 2026-09-03T01:49:51.994Z
Atualizado: 2026-09-16T01:51:53.313Z
**Critérios de Aceitação:**

1.  Quando o usuário não tiver criado nenhum combinado, a tela não deve ficar em branco, mas sim exibir uma ilustração/mensagem (_Empty State_).

2.  O _Empty State_ deve conter a frase: &quot;Você ainda não criou nenhum combinado&quot;.

3.  Deve haver um botão em destaque convidando o usuário a criar o seu primeiro combinado.


**Especificação BDD:**

**Dado que** o usuário acessou a tela de combinados criados

**Quando** o sistema retornar uma lista vazia de registros

**Então** a interface deve renderizar uma tela de estado vazio com instrução e botão para criar um novo combinado.

###### [TASK] #31033 — [Frontend] Implementar tela/componente de Empty State com ilustração e botão para 'Criar Combinado'

- Pai: 30750
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31033/activity)

**Descrição:**

Criado: 2026-09-04T20:51:29.190Z
Atualizado: 2026-09-06T01:32:20.888Z
(Sem descrição)

###### [TASK] #32289 — [back-end] Implementar busca textual por título

- Pai: 30750
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32289/activity)

**Descrição:**

Criado: 2026-09-16T01:51:51.249Z
Atualizado: 2026-10-24T17:24:57.690Z
LIKE %...% case-insensitive

###### [TASK] #32290 — [testes] Testar busca por parte do título e case-insensitive

- Pai: 30750
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32290/activity)

**Descrição:**

Criado: 2026-09-16T01:51:52.206Z
Atualizado: 2026-10-24T17:04:17.778Z
Acento maiúscula termo parcial

###### [TASK] #32291 — [po/validação] Homologar campo de busca por título

- Pai: 30750
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32291/activity)

**Descrição:**

Criado: 2026-09-16T01:51:53.239Z
Atualizado: 2026-10-24T17:27:08.315Z
Filtro em tempo real + empty state

#### [EPIC] #30625 — 04.2 — Listar Combinados Recebidos

- Pai: 30574
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30625/activity)

**Descrição:**

Criado: 2026-09-02T21:47:18.693Z
Atualizado: 2026-10-22T02:14:45.799Z
listagem dos combinados que o usuário recebeu por convite.

##### [FEATURE] #30684 — F15 — Listar Combinados Recebidos

- Pai: 30625
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-16
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30684/activity)

**Descrição:**

Criado: 2026-09-03T00:06:17.258Z
Atualizado: 2026-10-22T02:14:45.781Z
Listagem dos combinados que o usuário recebeu por convite.

Exibição e organização de todos os convites e combinados para os quais o usuário foi convidado por terceiros, permitindo rápida navegação e resposta.

**Critérios de Aceitação da Feature:**

1.  Listar em aba ou tela dedicada todos os combinados recebidos pelo usuário.

2.  Destacar o status atual do convite em cada card (Pendente, Aceito, Recusado).

3.  Disponibilizar filtros para separar convites pendentes de convites já respondidos.

###### [USER STORY] #30752 — US48 - Listagem básica de combinados recebidos

- Pai: 30684
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Felipe Macedo
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30752/activity)

**Descrição:**

Criado: 2026-09-03T01:51:15.107Z
Atualizado: 2026-09-17T11:03:02.947Z
**Critérios de Aceitação:**

1.  A interface deve renderizar a lista de todos os combinados em que o usuário logado foi adicionado como participante.

2.  Cada item deve indicar claramente quem foi o usuário criador do convite.

3.  A listagem deve tratar estados de carregamento (loading) durante a busca de dados.


**Especificação BDD:**

**Dado que** o usuário está na seção &quot;Combinados Recebidos&quot;

**Quando** a página for carregada

**Então** a interface deve exibir todos os combinados para os quais o usuário foi convidado, informando quem o convidou.

###### [TASK] #31037 — [Frontend] Implementar tela/aba de listagem para 'Combinados Recebidos'

- Pai: 30752
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31037/activity)

**Descrição:**

Criado: 2026-09-04T20:53:19.713Z
Atualizado: 2026-09-17T02:19:38.228Z
(Sem descrição)

###### [TASK] #31038 — [Frontend] Integrar listagem de convites recebidos com o endpoint da API

- Pai: 30752
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31038/activity)

**Descrição:**

Criado: 2026-09-04T20:53:26.777Z
Atualizado: 2026-09-17T02:19:44.771Z
(Sem descrição)

###### [TASK] #32243 — [back-end] Implementar endpoint de convites/combinados recebidos

- Pai: 30752
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32243/activity)

**Descrição:**

Criado: 2026-09-16T01:50:44.415Z
Atualizado: 2026-10-21T01:59:15.912Z
GET /convites/recebidos filtrando por usuário logado

###### [TASK] #32244 — [testes] Testar listagem de combinados recebidos

- Pai: 30752
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32244/activity)

**Descrição:**

Criado: 2026-09-16T01:50:46.391Z
Atualizado: 2026-10-22T01:34:11.689Z
Testar 0, 1 e múltiplos; validar que não vaza convites de outros

###### [TASK] #32245 — [po/validação] Homologar listagem de combinados recebidos

- Pai: 30752
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32245/activity)

**Descrição:**

Criado: 2026-09-16T01:50:47.422Z
Atualizado: 2026-09-16T21:36:38.693Z
Validar que só aparecem convites do usuário logado

###### [USER STORY] #30753 — US49 - Listagem de combinados recebidos com criador, status e prazo

- Pai: 30684
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-15
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30753/activity)

**Descrição:**

Criado: 2026-09-03T01:51:45.051Z
Atualizado: 2026-10-22T02:14:45.760Z
**Critérios de Aceitação:**

1.  Convites com status &quot;Pendente&quot; devem possuir um destaque visual (como borda colorida, selo &quot;Novo&quot; ou contador no menu).

2.  O destaque deve sumir assim que o usuário responder (aceitar ou recusar) ao convite.

3.  Deve haver um contador total de convites pendentes visível na barra superior ou de navegação.


**Especificação BDD:**

**Dado que** o usuário possui convites recebidos ainda não respondidos

**Quando** ele acessar a tela de listagem

**Então** a interface deve aplicar destaques visuais e exibir o badge com a contagem de pendências.

###### [TASK] #31040 — [Frontend] Criar badge de notificação e indicador visual para convites pendentes

- Pai: 30753
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31040/activity)

**Descrição:**

Criado: 2026-09-04T20:54:26.462Z
Atualizado: 2026-10-22T02:14:45.716Z
(Sem descrição)

###### [TASK] #32252 — [back-end] Incluir criador status e prazo no DTO

- Pai: 30753
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-15
- Prazo: 2026-10-15
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32252/activity)

**Descrição:**

Criado: 2026-09-16T01:50:57.486Z
Atualizado: 2026-10-21T01:13:03.924Z
JOIN com Usuário; retornar nomeCriador status prazo

###### [TASK] #32253 — [testes] Testar retorno dos campos criador/status/prazo

- Pai: 30753
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32253/activity)

**Descrição:**

Criado: 2026-09-16T01:50:59.413Z
Atualizado: 2026-10-22T01:33:59.018Z
Validar 3 campos preenchidos

###### [TASK] #32254 — [po/validação] Homologar exibição de criador status e prazo

- Pai: 30753
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32254/activity)

**Descrição:**

Criado: 2026-09-16T01:51:01.388Z
Atualizado: 2026-09-23T22:51:19.848Z
Validar nome do criador badge e prazo formatado

###### [USER STORY] #30754 — US50 - Ordenação de combinados por data do convite

- Pai: 30684
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30754/activity)

**Descrição:**

Criado: 2026-09-03T01:52:15.210Z
Atualizado: 2026-09-17T11:41:27.679Z
**Critérios de Aceitação:**

1.  Os cards de convites pendentes na lista devem possuir botões de atalho &quot;Aceitar&quot; e &quot;Recusar&quot; sem exigir que o usuário entre nos detalhes.

2.  Ao acionar o botão rápido, o card deve atualizar seu estado na própria lista com feedback visual imediato.

3.  Os botões rápidos devem solicitar confirmação no caso de recusa.


**Especificação BDD:**

**Dado que** o usuário está visualizando a lista de combinados recebidos

**Quando** ele clicar no botão de aceite ou recusa rápido direto no card

**Então** o sistema deve processar a resposta e atualizar o card na própria listagem.

###### [TASK] #31041 — [Frontend] Adicionar botões de ação rápida de aceite/recusa no card da listagem

- Pai: 30754
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31041/activity)

**Descrição:**

Criado: 2026-09-04T20:55:19.849Z
Atualizado: 2026-09-06T01:36:28.203Z
(Sem descrição)

###### [TASK] #32292 — [back-end] Implementar ordenação por data do convite

- Pai: 30754
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32292/activity)

**Descrição:**

Criado: 2026-09-16T01:51:54.240Z
Atualizado: 2026-10-24T17:23:49.472Z
sort=criadoEm desc

###### [TASK] #32293 — [testes] Testar ordenação por data do convite

- Pai: 30754
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32293/activity)

**Descrição:**

Criado: 2026-09-16T01:51:55.205Z
Atualizado: 2026-10-24T17:04:27.045Z
ASC/DESC

###### [TASK] #32294 — [po/validação] Homologar ordenação da aba de recebidos

- Pai: 30754
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32294/activity)

**Descrição:**

Criado: 2026-09-16T01:51:56.216Z
Atualizado: 2026-10-24T17:27:10.316Z
Mais recente primeiro

###### [USER STORY] #30755 — US51 - Filtro de combinados por status (Pendente/Aceito/Recusado)

- Pai: 30684
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Low
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30755/activity)

**Descrição:**

Criado: 2026-09-03T01:53:45.652Z
Atualizado: 2026-09-16T01:51:59.176Z
**Critérios de Aceitação:**

1.  Se o usuário não tiver recebido nenhum convite, a tela deve exibir uma mensagem amigável de estado vazio.

2.  A mensagem deve ser: &quot;Você não possui convites de combinados no momento&quot;.

3.  Deve conter uma ilustração visual adequada ao contexto.


**Especificação BDD:**

**Dado que** o usuário não possui convites recebidos cadastrados

**Quando** ele acessar a aba de combinados recebidos

**Então** a interface deve renderizar o componente de Empty State com a mensagem correspondente.

###### [TASK] #31044 — [Frontend] Criar componente de Empty State para a aba de convites recebidos

- Pai: 30755
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31044/activity)

**Descrição:**

Criado: 2026-09-04T20:56:07.785Z
Atualizado: 2026-09-06T01:37:03.154Z
(Sem descrição)

###### [TASK] #32295 — [back-end] Implementar filtro por status na listagem

- Pai: 30755
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32295/activity)

**Descrição:**

Criado: 2026-09-16T01:51:57.256Z
Atualizado: 2026-10-24T17:01:55.073Z
status=PENDENTE|ACEITO|RECUSADO

###### [TASK] #32296 — [testes] Testar filtro por cada status

- Pai: 30755
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32296/activity)

**Descrição:**

Criado: 2026-09-16T01:51:58.166Z
Atualizado: 2026-10-24T17:04:36.450Z
Isolado e combinado

###### [TASK] #32297 — [po/validação] Homologar filtro de status na UI

- Pai: 30755
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32297/activity)

**Descrição:**

Criado: 2026-09-16T01:51:59.101Z
Atualizado: 2026-10-24T17:27:18.990Z
Preservar estado na URL

#### [EPIC] #30626 — 04.3 — Consultar Detalhes

- Pai: 30574
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-19
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30626/activity)

**Descrição:**

Criado: 2026-09-02T21:47:55.016Z
Atualizado: 2026-10-21T01:05:25.677Z
Página de detalhes com todas as informações do combinado.

##### [FEATURE] #30685 — F16 — Consultar Detalhes

- Pai: 30626
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-19
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30685/activity)

**Descrição:**

Criado: 2026-09-03T00:06:48.994Z
Atualizado: 2026-10-21T01:05:25.635Z
Página de detalhes com todas as informações do combinado.

Exibição completa e centralizada de todas as informações de um combinado, agrupando dados gerais, regras, participantes e histórico.

**Critérios de Aceitação da Feature:**

1.  Apresentar uma tela exclusiva de detalhes acessível a partir das listas de combinados.

2.  Exibir informações organizadas em seções claras (Geral, Participantes, Prazos/Regras).

3.  Fornecer navegação de retorno simples para a tela anterior.

###### [USER STORY] #30756 — US52 - Consulta detalhada de combinado com título, descrição, categoria e prazo

- Pai: 30685
- Status: New
- Responsável: Carlos Silvio
- Prioridade: High
- Autor: Felipe Macedo
- Início: 2026-10-19
- Prazo: 2026-10-20
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30756/activity)

**Descrição:**

Criado: 2026-09-03T01:54:26.743Z
Atualizado: 2026-10-21T01:05:25.598Z
**Critérios de Aceitação:**

1.  A tela de detalhes deve exibir título, descrição completa, categoria, data de criação e criador do combinado.

2.  O layout deve organizar as informações em cards ou blocos bem estruturados visualmente.

3.  Deve haver um botão &quot;Voltar&quot; funcional no topo da tela.


**Especificação BDD:**

**Dado que** o usuário selecionou um combinado na listagem

**Quando** a página de detalhes for carregada

**Então** a interface deve exibir todos os dados gerais e informativos do combinado de forma organizada.

###### [TASK] #31047 — [Frontend] Criar o layout da página de Detalhes do Combinado com botão de retorno

- Pai: 30756
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31047/activity)

**Descrição:**

Criado: 2026-09-04T20:57:51.827Z
Atualizado: 2026-10-22T02:14:18.347Z
(Sem descrição)

###### [TASK] #31048 — [Frontend] Integrar a busca de detalhes do combinado por ID com o backend

- Pai: 30756
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-19
- Prazo: 2026-10-19
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31048/activity)

**Descrição:**

Criado: 2026-09-04T20:58:00.263Z
Atualizado: 2026-10-22T02:14:00.171Z
(Sem descrição)

###### [TASK] #32255 — [back-end] Implementar endpoint de detalhes do combinado

- Pai: 30756
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-19
- Prazo: 2026-10-20
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32255/activity)

**Descrição:**

Criado: 2026-09-16T01:51:03.392Z
Atualizado: 2026-10-21T01:05:25.546Z
GET /combinados/{id} com validação de permissão

###### [TASK] #32256 — [testes] Testar busca de detalhes e permissão de acesso

- Pai: 30756
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32256/activity)

**Descrição:**

Criado: 2026-09-16T01:51:05.391Z
Atualizado: 2026-10-22T01:33:05.174Z
Testar autorizado (200) e negado (403)

###### [TASK] #32257 — [po/validação] Homologar tela de detalhes do combinado

- Pai: 30756
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32257/activity)

**Descrição:**

Criado: 2026-09-16T01:51:06.400Z
Atualizado: 2026-09-23T22:45:50.731Z
Validar formatação de todos os campos

###### [USER STORY] #30757 — US53 - Consulta detalhada com lista de participantes e status

- Pai: 30685
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30757/activity)

**Descrição:**

Criado: 2026-09-03T01:57:20.173Z
Atualizado: 2026-09-16T01:52:02.082Z
**Critérios de Aceitação:**

1.  A página de detalhes deve apresentar uma seção dedicada com a lista de todos os convidados.

2.  Cada participante deve ser exibido com nome, foto/avatar e o respectivo status (Aceito, Pendente, Recusado).

3.  O criador do combinado deve ser identificado com uma tag especial (ex: &quot;Organizador&quot;).


**Especificação BDD:**

**Dado que** o usuário está visualizando os detalhes de um combinado

**Quando** ele rolar até a seção de participantes

**Então** a interface deve renderizar a lista de convidados exibindo seus avatares, nomes e status de resposta.

###### [TASK] #31052 — [Frontend] Implementar seção/lista de participantes com tags de status e destaque para o organizador

- Pai: 30757
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31052/activity)

**Descrição:**

Criado: 2026-09-04T20:58:54.208Z
Atualizado: 2026-09-06T01:49:27.275Z
(Sem descrição)

###### [TASK] #32298 — [back-end] Incluir lista de participantes no detalhe

- Pai: 30757
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32298/activity)

**Descrição:**

Criado: 2026-09-16T01:52:00.112Z
Atualizado: 2026-10-24T17:01:41.938Z
Nome + status no DTO

###### [TASK] #32299 — [testes] Testar lista de participantes no detalhe

- Pai: 30757
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32299/activity)

**Descrição:**

Criado: 2026-09-16T01:52:01.029Z
Atualizado: 2026-10-24T17:04:58.415Z
Todos aparecem com status correto

###### [TASK] #32300 — [po/validação] Homologar lista de participantes na tela

- Pai: 30757
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32300/activity)

**Descrição:**

Criado: 2026-09-16T01:52:02.004Z
Atualizado: 2026-10-24T17:28:39.396Z
Nome email status; destaque criador

###### [USER STORY] #30758 — US54 - Consulta detalhada com responsabilidades e tarefas

- Pai: 30685
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30758/activity)

**Descrição:**

Criado: 2026-09-03T01:57:50.092Z
Atualizado: 2026-09-16T01:52:05.024Z
**Critérios de Aceitação:**

1.  Se o combinado possuir regras, valores ou termos definidos, estes devem ser exibidos em uma área de destaque (card ou sanfona/accordion).

2.  O texto de termos deve ter boa legibilidade e formatação adequada para leitura.

3.  Caso não existam termos adicionais registrados, a seção deve ser ocultada ou exibir &quot;Sem regras adicionais&quot;.


**Especificação BDD:**

**Dado que** o combinado possui regras ou termos cadastrados

**Quando** a tela de detalhes for aberta

**Então** a interface deve exibir um bloco formatado contendo todas as regras e condições estabelecidas.

###### [TASK] #31054 — [Frontend] Criar componente visual para exibição das regras e termos do combinado

- Pai: 30758
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31054/activity)

**Descrição:**

Criado: 2026-09-04T20:59:40.271Z
Atualizado: 2026-09-06T01:56:45.401Z
(Sem descrição)

###### [TASK] #32301 — [back-end] Incluir responsabilidades/tarefas no detalhe

- Pai: 30758
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32301/activity)

**Descrição:**

Criado: 2026-09-16T01:52:03.010Z
Atualizado: 2026-10-24T17:01:30.440Z
Lista de tarefas + responsáveis

###### [TASK] #32302 — [testes] Testar retorno de responsabilidades e tarefas

- Pai: 30758
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32302/activity)

**Descrição:**

Criado: 2026-09-16T01:52:03.981Z
Atualizado: 2026-10-24T17:05:48.889Z
Com/sem tarefas; múltiplos responsáveis

###### [TASK] #32303 — [po/validação] Homologar exibição de responsabilidades

- Pai: 30758
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32303/activity)

**Descrição:**

Criado: 2026-09-16T01:52:04.957Z
Atualizado: 2026-10-24T17:28:44.594Z
Organização visual

###### [USER STORY] #30760 — US55 - Consulta detalhada com status atual e opções de ação

- Pai: 30685
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30760/activity)

**Descrição:**

Criado: 2026-09-03T02:00:15.881Z
Atualizado: 2026-09-16T01:52:07.951Z
**Critérios de Aceitação:**

1.  A tela de detalhes deve exibir botões de ação dinâmicos de acordo com o papel do usuário (ex: se for convidado pendente: &quot;Aceitar/Recusar&quot;; se for criador: &quot;Editar/Cancelar&quot;).

2.  O clique nos botões de ação deve acionar os fluxos sem necessidade de navegar para outra página.

3.  Botões de ações não permitidas para o perfil atual devem ficar ocultos.


**Especificação BDD:**

**Dado que** o usuário está na tela de detalhes de um combinado

**Quando** o sistema identificar o perfil do usuário (criador ou convidado)

**Então** a interface deve renderizar apenas os botões de ação permitidos para aquele perfil específico.

###### [TASK] #31055 — [Frontend] Implementar renderização condicional de botões de ação com base no papel do usuário

- Pai: 30760
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31055/activity)

**Descrição:**

Criado: 2026-09-04T21:00:49.808Z
Atualizado: 2026-09-06T01:57:14.407Z
(Sem descrição)

###### [TASK] #32304 — [back-end] Retornar status atual e ações permitidas

- Pai: 30760
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32304/activity)

**Descrição:**

Criado: 2026-09-16T01:52:05.946Z
Atualizado: 2026-10-24T17:02:24.598Z
Lista de ações conforme papel do usuário

###### [TASK] #32305 — [testes] Testar retorno de ações por papel

- Pai: 30760
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32305/activity)

**Descrição:**

Criado: 2026-09-16T01:52:06.879Z
Atualizado: 2026-10-24T17:05:28.212Z
Criador vs participante

###### [TASK] #32306 — [po/validação] Homologar opções de ação por papel

- Pai: 30760
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32306/activity)

**Descrição:**

Criado: 2026-09-16T01:52:07.877Z
Atualizado: 2026-10-24T17:28:50.062Z
Cada um vê só o que pode fazer

#### [EPIC] #30627 — 04.4 — Filtros e Busca Básica

- Pai: 30574
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30627/activity)

**Descrição:**

Criado: 2026-09-02T21:48:18.906Z
Atualizado: 2026-09-16T01:52:22.502Z
Filtrar por status, categoria ou participante.

##### [FEATURE] #30686 — F17 — Filtros e Busca

- Pai: 30627
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30686/activity)

**Descrição:**

Criado: 2026-09-03T00:07:17.113Z
Atualizado: 2026-09-16T01:52:22.482Z
Filtrar por status, categoria ou participante.

**Descrição do Recurso:**

Recursos de pesquisa e filtragem avançada para permitir que o usuário localize rapidamente combinados específicos por nome, categoria ou status.

**Critérios de Aceitação da Feature:**

1.  Disponibilizar campo de busca por texto funcional nas telas de listagem.

2.  Permitir a aplicação de filtros por categoria e status simultaneamente.

3.  Oferecer opção simples de limpar todos os filtros aplicados.

###### [USER STORY] #30762 — US56 - Busca de combinados por título

- Pai: 30686
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30762/activity)

**Descrição:**

Criado: 2026-09-03T02:01:00.015Z
Atualizado: 2026-09-16T01:52:10.852Z
**Critérios de Aceitação:**

1.  A interface deve apresentar um campo de texto (input de pesquisa) no topo da listagem.

2.  A busca deve filtrar a lista dinamicamente conforme o usuário digita (ou ao pressionar Enter).

3.  O campo deve conter um ícone de lupa e botão para limpar a busca digitada.


**Especificação BDD:**

**Dado que** o usuário está na tela de listagem de combinados

**Quando** ele digitar um termo no campo de busca por título

**Então** a interface deve atualizar a lista exibindo apenas os combinados cujos títulos correspondam ao texto pesquisado.

###### [TASK] #31056 — [Frontend] Criar componente de input de busca com filtro dinâmico de texto

- Pai: 30762
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31056/activity)

**Descrição:**

Criado: 2026-09-04T21:02:23.840Z
Atualizado: 2026-09-06T01:57:42.491Z
(Sem descrição)

###### [TASK] #32307 — [back-end] Implementar busca por título nos endpoints de listagem

- Pai: 30762
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32307/activity)

**Descrição:**

Criado: 2026-09-16T01:52:08.894Z
Atualizado: 2026-10-24T17:17:30.574Z
LIKE %...% em criados e recebidos

###### [TASK] #32308 — [testes] Testar busca textual nas duas listagens

- Pai: 30762
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32308/activity)

**Descrição:**

Criado: 2026-09-16T01:52:09.847Z
Atualizado: 2026-10-24T17:05:37.783Z
Case-insensitive e parcial

###### [TASK] #32309 — [po/validação] Homologar campo de busca unificado

- Pai: 30762
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32309/activity)

**Descrição:**

Criado: 2026-09-16T01:52:10.788Z
Atualizado: 2026-10-24T17:28:55.282Z
Ambas as abas + debounce

###### [USER STORY] #30763 — US57 - Filtro de combinados por categoria

- Pai: 30686
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30763/activity)

**Descrição:**

Criado: 2026-09-03T02:01:24.674Z
Atualizado: 2026-09-16T01:52:13.959Z
**Critérios de Aceitação:**

1.  A tela deve disponibilizar um componente (dropdown/chips) com as categorias disponíveis (ex: Empréstimo, Despesa, Serviço).

2.  Ao selecionar uma categoria, a listagem deve exibir apenas os itens associados a ela.

3.  Deve haver a opção &quot;Todas as categorias&quot; para redefinir o filtro.


**Especificação BDD:**

**Dado que** o usuário deseja visualizar apenas combinados de um tipo específico

**Quando** ele selecionar uma categoria no filtro

**Então** a interface deve filtrar e exibir somente os combinados pertencentes àquela categoria.

###### [TASK] #31057 — [Frontend] Implementar seletor/chips de filtro por categoria na interface

- Pai: 30763
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31057/activity)

**Descrição:**

Criado: 2026-09-04T21:03:11.510Z
Atualizado: 2026-09-06T01:58:25.882Z
(Sem descrição)

###### [TASK] #32310 — [back-end] Implementar filtro por categoria

- Pai: 30763
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32310/activity)

**Descrição:**

Criado: 2026-09-16T01:52:11.714Z
Atualizado: 2026-10-24T17:01:18.514Z
categoria=EMPRESTIMO|DESPESA|...

###### [TASK] #32311 — [testes] Testar filtro por cada categoria

- Pai: 30763
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32311/activity)

**Descrição:**

Criado: 2026-09-16T01:52:12.687Z
Atualizado: 2026-10-24T17:05:58.320Z
Isolado e combinado

###### [TASK] #32312 — [po/validação] Homologar chips de categoria na UI

- Pai: 30763
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32312/activity)

**Descrição:**

Criado: 2026-09-16T01:52:13.713Z
Atualizado: 2026-10-24T17:29:01.503Z
Seleção múltipla

###### [USER STORY] #30764 — US58 - Filtro de combinados por status

- Pai: 30686
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30764/activity)

**Descrição:**

Criado: 2026-09-03T02:01:51.805Z
Atualizado: 2026-09-16T01:52:18.448Z
**Critérios de Aceitação:**

1.  A interface deve fornecer filtros por status (ex: Pendentes, Aceitos, Recusados, Concluídos).

2.  A seleção de um status deve atualizar os resultados imediatamente na tela.

3.  Os filtros de status devem trabalhar de forma combinada com o campo de busca de texto.


**Especificação BDD:**

**Dado que** o usuário está pesquisando combinados na lista

**Quando** ele acionar um filtro de status específico

**Então** a interface deve renderizar apenas os combinados que possuem aquele status ativo.

###### [TASK] #31060 — [Frontend] Adicionar componente de filtragem por status na tela de listagem

- Pai: 30764
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31060/activity)

**Descrição:**

Criado: 2026-09-04T21:04:03.919Z
Atualizado: 2026-09-06T01:59:21.300Z
(Sem descrição)

###### [TASK] #32313 — [back-end] Implementar filtro por status nos endpoints

- Pai: 30764
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32313/activity)

**Descrição:**

Criado: 2026-09-16T01:52:15.416Z
Atualizado: 2026-10-24T17:23:14.578Z
status=RASCUNHO|...

###### [TASK] #32314 — [testes] Testar filtro por cada status

- Pai: 30764
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32314/activity)

**Descrição:**

Criado: 2026-09-16T01:52:16.574Z
Atualizado: 2026-10-24T17:06:10.079Z
Cada valor + combinação

###### [TASK] #32315 — [po/validação] Homologar filtro de status

- Pai: 30764
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32315/activity)

**Descrição:**

Criado: 2026-09-16T01:52:18.382Z
Atualizado: 2026-10-24T17:29:07.523Z
Refletir na URL

###### [USER STORY] #30765 — US59 - Filtro de combinados por período

- Pai: 30686
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Low
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30765/activity)

**Descrição:**

Criado: 2026-09-03T02:02:10.399Z
Atualizado: 2026-09-16T01:52:22.461Z
**Critérios de Aceitação:**

1.  Quando um ou mais filtros estiverem ativos, a interface deve exibir um botão &quot;Limpar filtros&quot;.

2.  Ao clicar no botão, o campo de busca e todos os seletores de categoria/status devem ser resetados para o estado padrão.

3.  A listagem deve recarregar mostrando todos os registros sem restrições.


**Especificação BDD:**

**Dado que** o usuário aplicou filtros na listagem de combinados

**Quando** ele clicar no botão &quot;Limpar Filtros&quot;

**Então** o sistema deve restaurar as seleções padrão e exibir a listagem completa novamente.

###### [TASK] #31063 — [Frontend] Implementar botão e lógica para resetar filtros ativos

- Pai: 30765
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31063/activity)

**Descrição:**

Criado: 2026-09-04T21:05:04.302Z
Atualizado: 2026-09-06T01:59:39.270Z
(Sem descrição)

###### [TASK] #32316 — [back-end] Implementar filtro por intervalo de datas

- Pai: 30765
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32316/activity)

**Descrição:**

Criado: 2026-09-16T01:52:19.421Z
Atualizado: 2026-10-24T17:21:20.978Z
dataInicio e dataFim

###### [TASK] #32317 — [testes] Testar filtros de período

- Pai: 30765
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32317/activity)

**Descrição:**

Criado: 2026-09-16T01:52:21.403Z
Atualizado: 2026-10-24T17:06:22.772Z
Só início só fim ambos início>fim

###### [TASK] #32318 — [po/validação] Homologar filtro de período

- Pai: 30765
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32318/activity)

**Descrição:**

Criado: 2026-09-16T01:52:22.382Z
Atualizado: 2026-10-24T17:29:15.283Z
Calendário + aplicação

### [EPIC] #30580 — 05 — Status e Encerramento

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30580/activity)

**Descrição:**

Criado: 2026-09-02T18:55:59.715Z
Atualizado: 2026-10-21T01:10:59.487Z
Este EPIC contempla o gerenciamento do ciclo de vida dos combinados, incluindo as transições entre rascunho, aguardando confirmação, ativo, concluído e cancelado, além das funcionalidades de conclusão e cancelamento.

#### [EPIC] #30629 — 05.1 — Máquina de Estados do Combinado

- Pai: 30580
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30629/activity)

**Descrição:**

Criado: 2026-09-02T21:50:59.258Z
Atualizado: 2026-10-21T01:10:59.462Z
Implementação das transições: _Rascunho → Aguardando → Ativo → Concluído/Cancelado_.

##### [FEATURE] #30687 — F18 — Controle de Status

- Pai: 30629
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30687/activity)

**Descrição:**

Criado: 2026-09-03T00:07:40.829Z
Atualizado: 2026-10-21T01:10:59.436Z
Implementação das transições: Rascunho → Aguardando → Ativo → Concluído/Cancelado.

Gerenciamento do estado global do combinado, permitindo acompanhar o ciclo de vida do acordo desde a criação até a sua conclusão ou cancelamento definitivo.

**Critérios de Aceitação da Feature:**

1.  Permitir a alteração do status do combinado conforme marcos de avanço (ex: Em Andamento, Concluído, Cancelado).

2.  Atualizar a interface do usuário refletindo visualmente o encerramento ou alteração de estado do combinado.

3.  Restringir ações de edição quando o combinado for finalizado ou cancelado.

###### [USER STORY] #30766 — US60 - Máquina de estados: Rascunho → Aguardando → Ativo → Concluído

- Pai: 30687
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30766/activity)

**Descrição:**

Criado: 2026-09-03T02:03:25.115Z
Atualizado: 2026-10-21T01:10:59.409Z
**Critérios de Aceitação:**

1.  A interface deve disponibilizar ao criador do combinado um botão &quot;Concluir Combinado&quot; ou &quot;Marcar como Finalizado&quot;.

2.  Ao acionar o botão, o sistema deve apresentar um modal de confirmação solicitando a validação da conclusão.

3.  Após a confirmação, o status do combinado deve mudar para &quot;Concluído&quot; e a interface deve bloquear edições adicionais.


**Especificação BDD:**

**Dado que** o criador está na tela do combinado ativo

**Quando** ele clicar na opção &quot;Concluir Combinado&quot; e confirmar a ação no modal

**Então** a interface deve atualizar o status do combinado para &quot;Concluído&quot; e exibir mensagem de sucesso.

###### [TASK] #31066 — [Frontend] Criar botão e modal de confirmação para conclusão do combinado

- Pai: 30766
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31066/activity)

**Descrição:**

Criado: 2026-09-04T21:06:47.940Z
Atualizado: 2026-10-22T02:13:33.679Z
(Sem descrição)

###### [TASK] #31067 — [Frontend] Integrar ação de finalizar combinado com o backend e atualizar estado visual

- Pai: 30766
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31067/activity)

**Descrição:**

Criado: 2026-09-04T21:06:55.249Z
Atualizado: 2026-10-22T02:13:06.798Z
(Sem descrição)

###### [TASK] #32258 — [back-end] Implementar máquina de estados no service

- Pai: 30766
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-16
- Prazo: 2026-10-16
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32258/activity)

**Descrição:**

Criado: 2026-09-16T01:51:08.386Z
Atualizado: 2026-10-21T01:10:59.360Z
Enum/classe com transições válidas; bloquear inválidas

###### [TASK] #32259 — [testes] Testar todas as transições válidas e inválidas

- Pai: 30766
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32259/activity)

**Descrição:**

Criado: 2026-09-16T01:51:09.429Z
Atualizado: 2026-10-22T01:32:51.832Z
Cobrir todos os pares origem→destino

###### [TASK] #32260 — [po/validação] Homologar fluxo de transições no sistema

- Pai: 30766
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32260/activity)

**Descrição:**

Criado: 2026-09-16T01:51:11.385Z
Atualizado: 2026-09-23T22:45:28.984Z
Validar manualmente cada transição

###### [USER STORY] #30767 — US61 - Transição para Cancelado em qualquer estado

- Pai: 30687
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30767/activity)

**Descrição:**

Criado: 2026-09-03T02:03:48.137Z
Atualizado: 2026-09-16T01:52:26.469Z
**Critérios de Aceitação:**

1.  Combinados com status &quot;Concluído&quot; ou &quot;Cancelado&quot; devem exibir uma marca d&#39;água ou badge destacado indicando o estado final.

2.  Todos os botões de ação e edição (como &quot;Aceitar&quot;, &quot;Recusar&quot;, &quot;Editar&quot;) devem ser desabilitados ou ocultados para esses combinados.

3.  A interface deve manter o histórico e as informações visíveis apenas para consulta/leitura.


**Especificação BDD:**

**Dado que** o usuário está consultando um combinado que foi finalizado ou cancelado

**Quando** a página de detalhes for renderizada

**Então** a interface deve apresentar o badge de status final e desabilitar todas as opções de alteração/interação.

###### [TASK] #31070 — [Frontend] Implementar bloqueio de edições e aplicação de estilo de leitura/inativo para combinados finalizados

- Pai: 30767
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31070/activity)

**Descrição:**

Criado: 2026-09-04T21:07:37.792Z
Atualizado: 2026-09-06T02:00:54.402Z
(Sem descrição)

###### [TASK] #32319 — [back-end] Permitir cancelamento em qualquer estado (exceto final)

- Pai: 30767
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32319/activity)

**Descrição:**

Criado: 2026-09-16T01:52:23.426Z
Atualizado: 2026-10-24T17:23:33.330Z
RASCUNHO AGUARDANDO ATIVO → OK; CONCLUIDO/CANCELADO → bloqueio

###### [TASK] #32320 — [testes] Testar cancelamento em cada estado

- Pai: 30767
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32320/activity)

**Descrição:**

Criado: 2026-09-16T01:52:25.400Z
Atualizado: 2026-10-24T17:24:27.451Z
5 cenários

###### [TASK] #32321 — [po/validação] Homologar cancelamento em estados variados

- Pai: 30767
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32321/activity)

**Descrição:**

Criado: 2026-09-16T01:52:26.400Z
Atualizado: 2026-10-24T17:29:13.089Z
Manualmente cada cenário

###### [USER STORY] #30768 — US62 - Validação para conclusão (todos os participantes aceitaram)

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30768/activity)

**Descrição:**

Criado: 2026-09-03T02:04:35.104Z
Atualizado: 2026-09-16T01:52:29.488Z
## Critérios de Aceitação — BDD

### Cenário

DADO que o combinado possui participantes com status pendente ou recusado
QUANDO o criador tentar concluir o combinado
ENTÃO o sistema deve impedir a conclusão e exibir mensagem informando quais participantes ainda não confirmaram

### Formato

DADO / QUANDO / ENTÃO

### BDD

```text
DADO que o combinado possui participantes com status pendente ou recusado
QUANDO o criador tentar concluir o combinado
ENTÃO o sistema deve impedir a conclusão e exibir mensagem informando quais participantes ainda não confirmaram
```

###### [TASK] #31670 — [back-end] Criar validação "todos aceitaram" no service de Combinado

- Pai: 30768
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31670/activity)

**Descrição:**

Criado: 2026-09-06T00:10:45.188Z
Atualizado: 2026-09-06T00:10:45.227Z
Task da US62 - Validação para conclusão (todos os participantes aceitaram).

###### [TASK] #31671 — [back-end] Retornar erro 422 com lista de participantes pendentes

- Pai: 30768
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31671/activity)

**Descrição:**

Criado: 2026-09-06T00:10:45.898Z
Atualizado: 2026-09-06T00:10:45.932Z
Task da US62 - Validação para conclusão (todos os participantes aceitaram).

###### [TASK] #31672 — [front-end] Exibir mensagem de erro ao tentar concluir com pendências

- Pai: 30768
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31672/activity)

**Descrição:**

Criado: 2026-09-06T00:10:46.602Z
Atualizado: 2026-09-06T00:10:46.645Z
Task da US62 - Validação para conclusão (todos os participantes aceitaram).

###### [TASK] #32322 — [testes] Testar validação todos aceitaram

- Pai: 30768
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32322/activity)

**Descrição:**

Criado: 2026-09-16T01:52:27.407Z
Atualizado: 2026-10-24T17:06:37.792Z
1 pendente 1 recusado todos aceitos

###### [TASK] #32323 — [po/validação] Homologar bloqueio de conclusão com pendências

- Pai: 30768
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32323/activity)

**Descrição:**

Criado: 2026-09-16T01:52:29.396Z
Atualizado: 2026-10-24T17:29:19.702Z
Mensagem lista pendentes

###### [USER STORY] #30769 — US63 - Bloqueio de edição após o combinado ficar ativo

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30769/activity)

**Descrição:**

Criado: 2026-09-03T02:05:02.177Z
Atualizado: 2026-09-16T01:52:31.442Z
### BDD

```text
DADO que o combinado está com status "Ativo"
QUANDO o usuário tentar editar título, descrição, categoria, prazo ou participantes
ENTÃO o sistema deve bloquear a edição e informar que o combinado não pode mais ser alterado
```

###### [TASK] #31673 — [back-end] Verificação de status antes de permitir update do Combinado

- Pai: 30769
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31673/activity)

**Descrição:**

Criado: 2026-09-06T00:10:49.515Z
Atualizado: 2026-09-06T00:10:49.557Z
Task da US63 - Bloqueio de edição após o combinado ficar ativo.

###### [TASK] #31674 — [front-end] Desabilitar campos de edição quando status = Ativo

- Pai: 30769
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31674/activity)

**Descrição:**

Criado: 2026-09-06T00:10:50.327Z
Atualizado: 2026-09-06T00:10:50.367Z
Task da US63 - Bloqueio de edição após o combinado ficar ativo.

###### [TASK] #31675 — [back-end] Teste unitário da regra de bloqueio de edição

- Pai: 30769
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31675/activity)

**Descrição:**

Criado: 2026-09-06T00:10:51.052Z
Atualizado: 2026-09-06T00:10:51.087Z
Task da US63 - Bloqueio de edição após o combinado ficar ativo.

###### [TASK] #32324 — [po/validação] Homologar bloqueio de edição em combinado ATIVO

- Pai: 30769
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32324/activity)

**Descrição:**

Criado: 2026-09-16T01:52:31.362Z
Atualizado: 2026-10-24T17:29:28.639Z
Campos desabilitados + API 403

###### [CRITÉRIO DE ACEITAÇÃO] #31618 — CA-F18.1 - Controle de Status

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31618/activity)

**Descrição:**

Criado: 2026-09-05T23:30:26.579Z
Atualizado: 2026-09-16T02:07:52.645Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31624 — CA-F18.2 - Controle de Status

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31624/activity)

**Descrição:**

Criado: 2026-09-06T00:01:39.939Z
Atualizado: 2026-09-16T02:07:53.797Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31625 — CA01 - A conclusão de um combinado só é permitida quando todos os participantes tiverem aceitado o convite.

- Pai: 31624
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31625/activity)

**Descrição:**

Criado: 2026-09-06T00:01:40.444Z
Atualizado: 2026-09-06T00:01:40.478Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31626 — CA02 - Após o combinado atingir o status "Ativo", os campos de título, descrição, categoria, prazo e participantes não podem mais ser editados.

- Pai: 31624
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31626/activity)

**Descrição:**

Criado: 2026-09-06T00:01:40.914Z
Atualizado: 2026-09-06T00:01:40.952Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31627 — CA03 - Tentativas de concluir um combinado com participantes pendentes ou que recusaram devem ser bloqueadas, com mensagem informando quem falta confirmar.

- Pai: 31624
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31627/activity)

**Descrição:**

Criado: 2026-09-06T00:01:41.380Z
Atualizado: 2026-09-06T00:01:41.422Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31628 — CA04 - O sistema deve validar o estado atual do combinado antes de permitir qualquer transição de status.

- Pai: 31624
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31628/activity)

**Descrição:**

Criado: 2026-09-06T00:01:41.869Z
Atualizado: 2026-09-06T00:01:41.918Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31665 — CA-F18.3 - Controle de Status

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31665/activity)

**Descrição:**

Criado: 2026-09-06T00:10:39.389Z
Atualizado: 2026-09-16T02:07:54.960Z
Critérios de aceitação da F18 — Controle de Status.

###### [CRITÉRIO DE ACEITAÇÃO] #31666 — CA01 - A conclusão de um combinado só é permitida quando todos os participantes tiverem aceitado o convite.

- Pai: 31665
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31666/activity)

**Descrição:**

Criado: 2026-09-06T00:10:39.843Z
Atualizado: 2026-09-06T00:10:39.883Z
A conclusão de um combinado só é permitida quando todos os participantes tiverem aceitado o convite.

###### [CRITÉRIO DE ACEITAÇÃO] #31667 — CA02 - Após o combinado atingir o status "Ativo", os campos de título, descrição, categoria, prazo e participantes não podem mais ser editados.

- Pai: 31665
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31667/activity)

**Descrição:**

Criado: 2026-09-06T00:10:40.343Z
Atualizado: 2026-09-06T00:10:40.392Z
Após o combinado atingir o status "Ativo", os campos de título, descrição, categoria, prazo e participantes não podem mais ser editados.

###### [CRITÉRIO DE ACEITAÇÃO] #31668 — CA03 - Tentativas de concluir um combinado com participantes pendentes ou que recusaram devem ser bloqueadas, com mensagem informando quem falta confirmar.

- Pai: 31665
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31668/activity)

**Descrição:**

Criado: 2026-09-06T00:10:40.801Z
Atualizado: 2026-09-06T00:10:40.841Z
Tentativas de concluir um combinado com participantes pendentes ou que recusaram devem ser bloqueadas, com mensagem informando quem falta confirmar.

###### [CRITÉRIO DE ACEITAÇÃO] #31669 — CA04 - O sistema deve validar o estado atual do combinado antes de permitir qualquer transição de status.

- Pai: 31665
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31669/activity)

**Descrição:**

Criado: 2026-09-06T00:10:41.290Z
Atualizado: 2026-09-06T00:10:41.335Z
O sistema deve validar o estado atual do combinado antes de permitir qualquer transição de status.

###### [CRITÉRIO DE ACEITAÇÃO] #31766 — CA-F18.4 - Controle de Status

- Pai: 30687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31766/activity)

**Descrição:**

Criado: 2026-09-06T00:30:26.203Z
Atualizado: 2026-09-16T02:07:56.140Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31767 — CA01 - A conclusão de um combinado só é permitida quando todos os participantes tiverem aceitado o convite.

- Pai: 31766
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31767/activity)

**Descrição:**

Criado: 2026-09-06T00:30:26.679Z
Atualizado: 2026-09-06T00:30:26.737Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31768 — CA02 - Após o combinado atingir o status "Ativo", os campos de título, descrição, categoria, prazo e participantes não podem mais ser editados.

- Pai: 31766
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31768/activity)

**Descrição:**

Criado: 2026-09-06T00:30:27.211Z
Atualizado: 2026-09-06T00:30:27.252Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31769 — CA03 - Tentativas de concluir um combinado com participantes pendentes ou que recusaram devem ser bloqueadas, com mensagem informando quem falta confirmar.

- Pai: 31766
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31769/activity)

**Descrição:**

Criado: 2026-09-06T00:30:27.686Z
Atualizado: 2026-09-06T00:30:27.740Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31770 — CA04 - O sistema deve validar o estado atual do combinado antes de permitir qualquer transição de status.

- Pai: 31766
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31770/activity)

**Descrição:**

Criado: 2026-09-06T00:30:28.168Z
Atualizado: 2026-09-06T00:30:28.207Z
(Sem descrição)

#### [EPIC] #30630 — 05.2 — Conclusão do Combinado

- Pai: 30580
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30630/activity)

**Descrição:**

Criado: 2026-09-02T21:51:14.408Z
Atualizado: 2026-09-16T01:52:41.522Z
Lógica e tela para finalizar o combinado.

##### [FEATURE] #30688 — F19 — Concluir Combinado

- Pai: 30630
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30688/activity)

**Descrição:**

Criado: 2026-09-03T00:08:08.094Z
Atualizado: 2026-09-16T01:52:41.509Z
Lógica e tela para finalizar o combinado.

###### [USER STORY] #30770 — US64 - Conclusão de combinado com botão "Concluir"

- Pai: 30688
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30770/activity)

**Descrição:**

Criado: 2026-09-03T02:05:33.430Z
Atualizado: 2026-09-16T01:52:34.473Z
### BDD

```text
DADO que o combinado está ativo e todos os participantes aceitaram
QUANDO o criador clicar no botão "Concluir"
ENTÃO o status do combinado deve mudar para "Concluído"
```

###### [TASK] #31681 — [front-end] Criar botão "Concluir" visível conforme status/permissão

- Pai: 30770
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31681/activity)

**Descrição:**

Criado: 2026-09-06T00:10:57.537Z
Atualizado: 2026-09-06T00:10:57.582Z
Task da US64 - Conclusão de combinado com botão "Concluir".

###### [TASK] #31682 — [back-end] Criar endpoint PATCH /combinados/{id}/concluir

- Pai: 30770
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31682/activity)

**Descrição:**

Criado: 2026-09-06T00:10:58.247Z
Atualizado: 2026-09-06T00:10:58.300Z
Task da US64 - Conclusão de combinado com botão "Concluir".

###### [TASK] #31683 — [back-end] Implementar transição de status para Concluído

- Pai: 30770
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31683/activity)

**Descrição:**

Criado: 2026-09-06T00:10:58.972Z
Atualizado: 2026-09-06T00:10:59.004Z
Task da US64 - Conclusão de combinado com botão "Concluir".

###### [TASK] #32325 — [testes] Testar endpoint de conclusão

- Pai: 30770
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32325/activity)

**Descrição:**

Criado: 2026-09-16T01:52:32.386Z
Atualizado: 2026-10-24T17:06:49.628Z
Sucesso (ATIVO) e falha (outros status)

###### [TASK] #32326 — [po/validação] Homologar fluxo de conclusão

- Pai: 30770
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32326/activity)

**Descrição:**

Criado: 2026-09-16T01:52:34.384Z
Atualizado: 2026-10-24T17:01:09.651Z
Botão + modal + status

###### [USER STORY] #30771 — US65 - Registro de conclusão no histórico com autor e data

- Pai: 30688
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30771/activity)

**Descrição:**

Criado: 2026-09-03T02:06:25.240Z
Atualizado: 2026-09-16T01:52:38.454Z
### BDD

```text
DADO que um combinado foi concluído
QUANDO a transição de status for realizada
ENTÃO o sistema deve registrar no histórico o autor e a data/hora da conclusão
```

###### [TASK] #31684 — [back-end] Criar registro de evento "Concluído" na tabela Histórico

- Pai: 30771
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31684/activity)

**Descrição:**

Criado: 2026-09-06T00:11:01.763Z
Atualizado: 2026-09-06T00:11:01.805Z
Task da US65 - Registro de conclusão no histórico com autor e data.

###### [TASK] #31685 — [back-end] Associar autor (usuário logado) e timestamp ao evento

- Pai: 30771
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31685/activity)

**Descrição:**

Criado: 2026-09-06T00:11:02.401Z
Atualizado: 2026-09-06T00:11:02.435Z
Task da US65 - Registro de conclusão no histórico com autor e data.

###### [TASK] #32327 — [front-end] Exibir registro de conclusão na timeline

- Pai: 30771
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32327/activity)

**Descrição:**

Criado: 2026-09-16T01:52:36.398Z
Atualizado: 2026-10-24T17:18:40.439Z
Concluído por [nome] em [data]

###### [TASK] #32328 — [testes] Testar registro de conclusão no histórico

- Pai: 30771
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32328/activity)

**Descrição:**

Criado: 2026-09-16T01:52:37.394Z
Atualizado: 2026-10-24T17:01:00.103Z
Autor + timestamp; não permitir dupla conclusão

###### [TASK] #32329 — [po/validação] Homologar visualização da conclusão no histórico

- Pai: 30771
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32329/activity)

**Descrição:**

Criado: 2026-09-16T01:52:38.371Z
Atualizado: 2026-10-24T17:29:30.957Z
Ícone + autor + data

###### [USER STORY] #30773 — US66 - Mensagem de sucesso ao concluir

- Pai: 30688
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30773/activity)

**Descrição:**

Criado: 2026-09-03T02:07:57.272Z
Atualizado: 2026-09-16T01:52:41.494Z
### BDD

```text
DADO que o combinado foi concluído com sucesso
QUANDO a ação for processada pelo backend
ENTÃO o front-end deve exibir uma mensagem de confirmação ao usuário
```

###### [TASK] #31686 — [front-end] Criar componente/toast de mensagem de sucesso

- Pai: 30773
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31686/activity)

**Descrição:**

Criado: 2026-09-06T00:11:05.240Z
Atualizado: 2026-09-06T00:11:05.276Z
Task da US66 - Mensagem de sucesso ao concluir.

###### [TASK] #32330 — [back-end] Retornar mensagem de sucesso no response

- Pai: 30773
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32330/activity)

**Descrição:**

Criado: 2026-09-16T01:52:39.385Z
Atualizado: 2026-10-24T17:00:27.415Z
Combinado concluído com sucesso!

###### [TASK] #32331 — [testes] Testar retorno da mensagem de sucesso

- Pai: 30773
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32331/activity)

**Descrição:**

Criado: 2026-09-16T01:52:40.384Z
Atualizado: 2026-10-24T17:00:52.436Z
Validar na API

###### [TASK] #32332 — [po/validação] Homologar mensagem de sucesso na UI

- Pai: 30773
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32332/activity)

**Descrição:**

Criado: 2026-09-16T01:52:41.405Z
Atualizado: 2026-10-24T17:29:36.612Z
Toast aparece/desaparece

###### [CRITÉRIO DE ACEITAÇÃO] #31629 — CA-F19.1 - Concluir Combinado

- Pai: 30688
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31629/activity)

**Descrição:**

Criado: 2026-09-06T00:01:42.343Z
Atualizado: 2026-09-16T02:07:57.304Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31630 — CA01 - O botão "Concluir" deve estar visível apenas quando o combinado estiver no status "Ativo".

- Pai: 31629
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31630/activity)

**Descrição:**

Criado: 2026-09-06T00:01:42.778Z
Atualizado: 2026-09-06T00:01:42.813Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31631 — CA02 - Ao concluir, o status deve mudar para "Concluído" e essa ação deve ficar registrada no histórico com autor e data/hora.

- Pai: 31629
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31631/activity)

**Descrição:**

Criado: 2026-09-06T00:01:43.282Z
Atualizado: 2026-09-06T00:01:43.321Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31632 — CA03 - Após a conclusão, o sistema deve exibir mensagem de sucesso ao usuário.

- Pai: 31629
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31632/activity)

**Descrição:**

Criado: 2026-09-06T00:01:43.789Z
Atualizado: 2026-09-06T00:01:43.837Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31633 — CA04 - Um combinado concluído não pode retornar a nenhum status anterior.

- Pai: 31629
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31633/activity)

**Descrição:**

Criado: 2026-09-06T00:01:44.293Z
Atualizado: 2026-09-06T00:01:44.337Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31676 — CA-F19.2 - Concluir Combinado

- Pai: 30688
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31676/activity)

**Descrição:**

Criado: 2026-09-06T00:10:51.854Z
Atualizado: 2026-09-16T02:07:58.427Z
Critérios de aceitação da F19 — Concluir Combinado.

###### [CRITÉRIO DE ACEITAÇÃO] #31677 — CA01 - O botão "Concluir" deve estar visível apenas quando o combinado estiver no status "Ativo".

- Pai: 31676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31677/activity)

**Descrição:**

Criado: 2026-09-06T00:10:52.399Z
Atualizado: 2026-09-06T00:10:52.449Z
O botão "Concluir" deve estar visível apenas quando o combinado estiver no status "Ativo".

###### [CRITÉRIO DE ACEITAÇÃO] #31678 — CA02 - Ao concluir, o status deve mudar para "Concluído" e essa ação deve ficar registrada no histórico com autor e data/hora.

- Pai: 31676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31678/activity)

**Descrição:**

Criado: 2026-09-06T00:10:52.892Z
Atualizado: 2026-09-06T00:10:52.958Z
Ao concluir, o status deve mudar para "Concluído" e essa ação deve ficar registrada no histórico com autor e data/hora.

###### [CRITÉRIO DE ACEITAÇÃO] #31679 — CA03 - Após a conclusão, o sistema deve exibir mensagem de sucesso ao usuário.

- Pai: 31676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31679/activity)

**Descrição:**

Criado: 2026-09-06T00:10:53.391Z
Atualizado: 2026-09-06T00:10:53.435Z
Após a conclusão, o sistema deve exibir mensagem de sucesso ao usuário.

###### [CRITÉRIO DE ACEITAÇÃO] #31680 — CA04 - Um combinado concluído não pode retornar a nenhum status anterior.

- Pai: 31676
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31680/activity)

**Descrição:**

Criado: 2026-09-06T00:10:53.848Z
Atualizado: 2026-09-06T00:10:53.896Z
Um combinado concluído não pode retornar a nenhum status anterior.

##### [TASK] #31443 — CA - Controle de Status

- Pai: 30630
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31443/activity)

**Descrição:**

Criado: 2026-09-05T20:43:16.777Z
Atualizado: 2026-09-05T20:43:37.123Z
(Sem descrição)

#### [EPIC] #30631 — 05.3 — Cancelamento do Combinado

- Pai: 30580
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30631/activity)

**Descrição:**

Criado: 2026-09-02T21:51:26.777Z
Atualizado: 2026-09-16T01:52:51.500Z
Lógica e tela para cancelar o combinado (com motivo, se necessário).

##### [FEATURE] #30689 — F20 — Cancelar Combinado

- Pai: 30631
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30689/activity)

**Descrição:**

Criado: 2026-09-03T00:08:30.940Z
Atualizado: 2026-09-16T01:52:51.489Z
Lógica e tela para cancelar o combinado (com motivo, se necessário).

###### [USER STORY] #30774 — US67 - Cancelamento de combinado com botão "Cancelar"

- Pai: 30689
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30774/activity)

**Descrição:**

Criado: 2026-09-03T02:08:24.234Z
Atualizado: 2026-09-16T01:52:43.483Z
### BDD

```text
DADO que o combinado não está concluído nem já cancelado
QUANDO o usuário autorizado clicar em "Cancelar"
ENTÃO o status do combinado deve mudar para "Cancelado"
```

###### [TASK] #31692 — [front-end] Criar botão "Cancelar" com modal de confirmação

- Pai: 30774
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31692/activity)

**Descrição:**

Criado: 2026-09-06T00:11:11.697Z
Atualizado: 2026-09-06T00:11:11.738Z
Task da US67 - Cancelamento de combinado com botão "Cancelar".

###### [TASK] #31693 — [back-end] Criar endpoint PATCH /combinados/{id}/cancelar

- Pai: 30774
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31693/activity)

**Descrição:**

Criado: 2026-09-06T00:11:12.397Z
Atualizado: 2026-09-06T00:11:12.445Z
Task da US67 - Cancelamento de combinado com botão "Cancelar".

###### [TASK] #32333 — [testes] Testar endpoint de cancelamento

- Pai: 30774
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32333/activity)

**Descrição:**

Criado: 2026-09-16T01:52:42.365Z
Atualizado: 2026-10-24T17:00:41.732Z
Criador (200) e não-criador (403)

###### [TASK] #32334 — [po/validação] Homologar fluxo de cancelamento

- Pai: 30774
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32334/activity)

**Descrição:**

Criado: 2026-09-16T01:52:43.405Z
Atualizado: 2026-10-24T17:29:42.949Z
Botão + modal + status

###### [USER STORY] #30775 — US68 - Registro de cancelamento no histórico com autor e data

- Pai: 30689
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30775/activity)

**Descrição:**

Criado: 2026-09-03T02:08:45.503Z
Atualizado: 2026-09-16T01:52:48.513Z
### BDD

```text
DADO que um combinado foi cancelado
QUANDO a transição de status for realizada
ENTÃO o sistema deve registrar no histórico o autor e a data/hora do cancelamento
```

###### [TASK] #31694 — [back-end] Criar registro de evento "Cancelado" na tabela Histórico

- Pai: 30775
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31694/activity)

**Descrição:**

Criado: 2026-09-06T00:11:15.268Z
Atualizado: 2026-09-06T00:11:15.302Z
Task da US68 - Registro de cancelamento no histórico com autor e data.

###### [TASK] #32335 — [front-end] Exibir registro de cancelamento na timeline

- Pai: 30775
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32335/activity)

**Descrição:**

Criado: 2026-09-16T01:52:45.401Z
Atualizado: 2026-10-24T17:15:26.415Z
Cancelado por [nome] em [data]

###### [TASK] #32336 — [testes] Testar registro de cancelamento no histórico

- Pai: 30775
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32336/activity)

**Descrição:**

Criado: 2026-09-16T01:52:46.390Z
Atualizado: 2026-10-24T17:27:54.415Z
Autor + timestamp

###### [TASK] #32337 — [po/validação] Homologar visualização do cancelamento no histórico

- Pai: 30775
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32337/activity)

**Descrição:**

Criado: 2026-09-16T01:52:48.404Z
Atualizado: 2026-10-24T17:29:45.921Z
Ícone + autor + data

###### [USER STORY] #30778 — US69 - Motivo opcional para cancelamento

- Pai: 30689
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30778/activity)

**Descrição:**

Criado: 2026-09-03T02:09:30.865Z
Atualizado: 2026-09-16T01:52:51.477Z
### BDD

```text
DADO que o usuário está cancelando um combinado
QUANDO ele preencher (ou não) o campo de motivo
ENTÃO o sistema deve aceitar o cancelamento com ou sem motivo informado
```

###### [TASK] #31695 — [front-end] Adicionar campo opcional "Motivo" no modal de cancelamento

- Pai: 30778
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31695/activity)

**Descrição:**

Criado: 2026-09-06T00:11:18.108Z
Atualizado: 2026-09-06T00:11:18.146Z
Task da US69 - Motivo opcional para cancelamento.

###### [TASK] #31696 — [back-end] Persistir motivo (nullable) junto ao evento de cancelamento

- Pai: 30778
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31696/activity)

**Descrição:**

Criado: 2026-09-06T00:11:18.772Z
Atualizado: 2026-09-06T00:11:18.809Z
Task da US69 - Motivo opcional para cancelamento.

###### [TASK] #32338 — [testes] Testar cancelamento com e sem motivo

- Pai: 30778
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32338/activity)

**Descrição:**

Criado: 2026-09-16T01:52:50.396Z
Atualizado: 2026-10-24T17:18:52.041Z
null / preenchido / muito longo

###### [TASK] #32339 — [po/validação] Homologar campo de motivo no cancelamento

- Pai: 30778
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32339/activity)

**Descrição:**

Criado: 2026-09-16T01:52:51.370Z
Atualizado: 2026-10-24T17:29:53.567Z
Opcional + exibição no histórico

###### [CRITÉRIO DE ACEITAÇÃO] #31634 — CA-F20.1 - Cancelar Combinado

- Pai: 30689
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31634/activity)

**Descrição:**

Criado: 2026-09-06T00:01:44.805Z
Atualizado: 2026-09-16T02:07:59.623Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31635 — CA01 - O botão "Cancelar" deve estar disponível em qualquer status, exceto "Concluído" e "Cancelado".

- Pai: 31634
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31635/activity)

**Descrição:**

Criado: 2026-09-06T00:01:45.267Z
Atualizado: 2026-09-06T00:01:45.300Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31636 — CA02 - O cancelamento deve registrar autor e data/hora no histórico.

- Pai: 31634
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31636/activity)

**Descrição:**

Criado: 2026-09-06T00:01:45.725Z
Atualizado: 2026-09-06T00:01:45.775Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31637 — CA03 - O campo de motivo do cancelamento é opcional.

- Pai: 31634
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31637/activity)

**Descrição:**

Criado: 2026-09-06T00:01:46.238Z
Atualizado: 2026-09-06T00:01:46.281Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31638 — CA04 - Um combinado cancelado não pode ser reativado.

- Pai: 31634
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31638/activity)

**Descrição:**

Criado: 2026-09-06T00:01:46.744Z
Atualizado: 2026-09-06T00:01:46.789Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31687 — CA-F20.2 - Cancelar Combinado

- Pai: 30689
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31687/activity)

**Descrição:**

Criado: 2026-09-06T00:11:06.037Z
Atualizado: 2026-09-16T02:08:00.797Z
Critérios de aceitação da F20 — Cancelar Combinado.

###### [CRITÉRIO DE ACEITAÇÃO] #31688 — CA01 - O botão "Cancelar" deve estar disponível em qualquer status, exceto "Concluído" e "Cancelado".

- Pai: 31687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31688/activity)

**Descrição:**

Criado: 2026-09-06T00:11:06.562Z
Atualizado: 2026-09-06T00:11:06.613Z
O botão "Cancelar" deve estar disponível em qualquer status, exceto "Concluído" e "Cancelado".

###### [CRITÉRIO DE ACEITAÇÃO] #31689 — CA02 - O cancelamento deve registrar autor e data/hora no histórico.

- Pai: 31687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31689/activity)

**Descrição:**

Criado: 2026-09-06T00:11:07.073Z
Atualizado: 2026-09-06T00:11:07.117Z
O cancelamento deve registrar autor e data/hora no histórico.

###### [CRITÉRIO DE ACEITAÇÃO] #31690 — CA03 - O campo de motivo do cancelamento é opcional.

- Pai: 31687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31690/activity)

**Descrição:**

Criado: 2026-09-06T00:11:07.541Z
Atualizado: 2026-09-06T00:11:07.583Z
O campo de motivo do cancelamento é opcional.

###### [CRITÉRIO DE ACEITAÇÃO] #31691 — CA04 - Um combinado cancelado não pode ser reativado.

- Pai: 31687
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31691/activity)

**Descrição:**

Criado: 2026-09-06T00:11:07.968Z
Atualizado: 2026-09-06T00:11:08.031Z
Um combinado cancelado não pode ser reativado.

#### [EPIC] #30632 — 05.4 — Validação de Permissões

- Pai: 30580
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30632/activity)

**Descrição:**

Criado: 2026-09-02T21:51:38.057Z
Atualizado: 2026-09-16T01:52:57.563Z
Garantir que apenas usuários autorizados possam alterar status.

##### [FEATURE] #30690 — F21 — Validação de Permissões

- Pai: 30632
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30690/activity)

**Descrição:**

Criado: 2026-09-03T00:08:58.736Z
Atualizado: 2026-09-16T01:52:57.548Z
Garantir que apenas usuários autorizados possam alterar status.

###### [USER STORY] #30780 — US70 - Permissão apenas para o criador concluir ou cancelar

- Pai: 30690
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30780/activity)

**Descrição:**

Criado: 2026-09-03T02:10:54.993Z
Atualizado: 2026-09-16T01:51:15.492Z
### BDD

```text
DADO que um usuário não é o criador do combinado
QUANDO ele tentar concluir ou cancelar o combinado
ENTÃO o sistema deve negar a ação e retornar erro de permissão
```

###### [TASK] #31702 — [back-end] Verificação de "criador" no service antes da transição

- Pai: 30780
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31702/activity)

**Descrição:**

Criado: 2026-09-06T00:11:25.456Z
Atualizado: 2026-10-21T21:10:47.374Z
Task da US70 - Permissão apenas para o criador concluir ou cancelar.

###### [TASK] #31703 — [back-end] Retornar HTTP 403 com mensagem clara

- Pai: 30780
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31703/activity)

**Descrição:**

Criado: 2026-09-06T00:11:26.160Z
Atualizado: 2026-10-21T22:07:59.175Z
Task da US70 - Permissão apenas para o criador concluir ou cancelar.

###### [TASK] #32261 — [front-end] Ocultar botão Cancelar para não-criadores

- Pai: 30780
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32261/activity)

**Descrição:**

Criado: 2026-09-16T01:51:12.399Z
Atualizado: 2026-10-22T01:32:39.341Z
Comparar usuario.id === combinado.criadorId

###### [TASK] #32262 — [testes] Testar permissão de cancelamento

- Pai: 30780
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32262/activity)

**Descrição:**

Criado: 2026-09-16T01:51:13.395Z
Atualizado: 2026-09-23T22:44:52.879Z
Testar API (403) e UI (botão oculto)

###### [TASK] #32263 — [po/validação] Homologar restrição de cancelamento

- Pai: 30780
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32263/activity)

**Descrição:**

Criado: 2026-09-16T01:51:15.388Z
Atualizado: 2026-09-23T22:44:37.198Z
Logar como criador e como participante

###### [USER STORY] #30783 — US71 - Permissão para qualquer participante concluir o combinado

- Pai: 30690
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30783/activity)

**Descrição:**

Criado: 2026-09-03T02:11:39.446Z
Atualizado: 2026-09-16T01:52:54.482Z
### BDD

```text
DADO que um usuário é participante (não necessariamente o criador) do combinado
QUANDO ele tentar concluir o combinado
ENTÃO o sistema deve permitir a ação, respeitando as demais regras de status
```

###### [TASK] #31704 — [back-end] Ajustar regra de autorização para aceitar qualquer participante na conclusão

- Pai: 30783
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31704/activity)

**Descrição:**

Criado: 2026-09-06T00:11:29.048Z
Atualizado: 2026-09-06T00:11:29.089Z
Task da US71 - Permissão para qualquer participante concluir o combinado.

###### [TASK] #31705 — [back-end] Teste unitário cobrindo participante x criador

- Pai: 30783
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31705/activity)

**Descrição:**

Criado: 2026-09-06T00:11:29.769Z
Atualizado: 2026-09-06T00:11:29.816Z
Task da US71 - Permissão para qualquer participante concluir o combinado.

###### [TASK] #32340 — [front-end] Exibir botão Concluir para participantes aceitos

- Pai: 30783
- Status: New
- Responsável: Carlos Silvio
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32340/activity)

**Descrição:**

Criado: 2026-09-16T01:52:52.361Z
Atualizado: 2026-10-24T17:15:08.613Z
Verificar status ACEITO

###### [TASK] #32341 — [testes] Testar permissão de conclusão por participante

- Pai: 30783
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32341/activity)

**Descrição:**

Criado: 2026-09-16T01:52:53.298Z
Atualizado: 2026-10-24T17:17:59.796Z
Criador (200) aceito (200) pendente (403) externo (403)

###### [TASK] #32342 — [po/validação] Homologar conclusão por participantes

- Pai: 30783
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32342/activity)

**Descrição:**

Criado: 2026-09-16T01:52:54.392Z
Atualizado: 2026-10-24T17:29:59.598Z
Múltiplos usuários

###### [USER STORY] #30784 — US72 - Registro do autor e da data em cada ação

- Pai: 30690
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30784/activity)

**Descrição:**

Criado: 2026-09-03T02:12:14.793Z
Atualizado: 2026-09-16T01:52:57.530Z
### BDD

```text
DADO que qualquer ação de mudança de status ocorre (concluir, cancelar)
QUANDO a ação for executada
ENTÃO o sistema deve registrar o usuário responsável e o timestamp da ação
```

###### [TASK] #31706 — [back-end] Garantir que todo evento de Histórico grave usuário_id e criado_em

- Pai: 30784
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31706/activity)

**Descrição:**

Criado: 2026-09-06T00:11:32.744Z
Atualizado: 2026-09-06T00:11:32.781Z
Task da US72 - Registro do autor e da data em cada ação.

###### [TASK] #32343 — [testes] Testar gravação obrigatória de autor/data

- Pai: 30784
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32343/activity)

**Descrição:**

Criado: 2026-09-16T01:52:55.396Z
Atualizado: 2026-10-24T17:17:46.685Z
Todos os tipos de evento

###### [TASK] #32344 — [po/validação] Homologar consistência de auditoria

- Pai: 30784
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32344/activity)

**Descrição:**

Criado: 2026-09-16T01:52:57.429Z
Atualizado: 2026-10-24T17:16:16.798Z
Todos eventos com autor/data

###### [CRITÉRIO DE ACEITAÇÃO] #31639 — CA-F21.1 - Validação de Permissões

- Pai: 30690
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31639/activity)

**Descrição:**

Criado: 2026-09-06T00:01:47.250Z
Atualizado: 2026-09-16T02:08:01.929Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31640 — CA01 - Apenas o criador do combinado pode concluir ou cancelar (regra padrão).

- Pai: 31639
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31640/activity)

**Descrição:**

Criado: 2026-09-06T00:01:47.732Z
Atualizado: 2026-09-06T00:01:47.778Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31641 — CA02 - Alternativamente, qualquer participante pode concluir o combinado (regra a confirmar com o Product Owner).

- Pai: 31639
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31641/activity)

**Descrição:**

Criado: 2026-09-06T00:01:48.287Z
Atualizado: 2026-09-06T00:01:48.334Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31642 — CA03 - Toda ação de mudança de status deve registrar o autor e a data/hora exata da execução.

- Pai: 31639
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31642/activity)

**Descrição:**

Criado: 2026-09-06T00:01:48.808Z
Atualizado: 2026-09-06T00:01:48.858Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31643 — CA04 - Usuários sem permissão devem receber erro 403 ao tentar executar uma ação restrita.

- Pai: 31639
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31643/activity)

**Descrição:**

Criado: 2026-09-06T00:01:49.323Z
Atualizado: 2026-09-06T00:01:49.365Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31697 — CA-F21.2 - Validação de Permissões

- Pai: 30690
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31697/activity)

**Descrição:**

Criado: 2026-09-06T00:11:19.610Z
Atualizado: 2026-09-16T02:08:03.101Z
Critérios de aceitação da F21 — Validação de Permissões.

###### [CRITÉRIO DE ACEITAÇÃO] #31698 — CA01 - Apenas o criador do combinado pode concluir ou cancelar (regra padrão).

- Pai: 31697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31698/activity)

**Descrição:**

Criado: 2026-09-06T00:11:20.088Z
Atualizado: 2026-09-06T00:11:20.127Z
Apenas o criador do combinado pode concluir ou cancelar (regra padrão).

###### [CRITÉRIO DE ACEITAÇÃO] #31699 — CA02 - Alternativamente, qualquer participante pode concluir o combinado (regra a confirmar com o Product Owner — ver observação abaixo).

- Pai: 31697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31699/activity)

**Descrição:**

Criado: 2026-09-06T00:11:20.571Z
Atualizado: 2026-09-06T00:11:20.617Z
Alternativamente, qualquer participante pode concluir o combinado (regra a confirmar com o Product Owner — ver observação abaixo).

###### [CRITÉRIO DE ACEITAÇÃO] #31700 — CA03 - Toda ação de mudança de status deve registrar o autor e a data/hora exata da execução.

- Pai: 31697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31700/activity)

**Descrição:**

Criado: 2026-09-06T00:11:21.165Z
Atualizado: 2026-09-06T00:11:21.222Z
Toda ação de mudança de status deve registrar o autor e a data/hora exata da execução.

###### [CRITÉRIO DE ACEITAÇÃO] #31701 — CA04 - Usuários sem permissão devem receber erro 403 ao tentar executar uma ação restrita.

- Pai: 31697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31701/activity)

**Descrição:**

Criado: 2026-09-06T00:11:21.683Z
Atualizado: 2026-09-06T00:11:21.741Z
Usuários sem permissão devem receber erro 403 ao tentar executar uma ação restrita.

### [EPIC] #30583 — 06 — Histórico e Linha do Tempo

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30583/activity)

**Descrição:**

Criado: 2026-09-02T18:56:52.433Z
Atualizado: 2026-10-21T22:45:58.345Z
Este EPIC contempla o registro e a consulta dos eventos relacionados aos combinados, permitindo acompanhar cronologicamente ações como criação, envio, aceite, recusa, alterações de status, conclusão e cancelamento, identificando o responsável e a data e hora de cada evento.

#### [EPIC] #30634 — 06.1 — Registro de Eventos

- Pai: 30583
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30634/activity)

**Descrição:**

Criado: 2026-09-02T21:52:36.253Z
Atualizado: 2026-10-21T22:45:58.326Z
Backend para registrar criação, envio, aceite, recusa, alterações de status e encerramento.

##### [FEATURE] #30691 — F22 — Registro de Eventos

- Pai: 30634
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30691/activity)

**Descrição:**

Criado: 2026-09-03T00:09:29.307Z
Atualizado: 2026-10-21T22:45:58.307Z
Backend para registrar criação, envio, aceite, recusa, alterações de status e encerramento.

###### [USER STORY] #30785 — US73 - Registro da criação do combinado no histórico

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30785/activity)

**Descrição:**

Criado: 2026-09-03T02:12:50.108Z
Atualizado: 2026-09-16T01:51:17.440Z
### BDD

```text
DADO que um novo combinado é criado
QUANDO o registro for salvo no banco
ENTÃO o sistema deve criar um evento de histórico do tipo "Criação" associado a esse combinado
```

###### [TASK] #31712 — [back-end] Criar entidade/tabela Histórico

- Pai: 30785
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31712/activity)

**Descrição:**

Criado: 2026-09-06T00:11:38.326Z
Atualizado: 2026-10-21T21:15:27.572Z
Task da US73 - Registro da criação do combinado no histórico.

###### [TASK] #31713 — [back-end] Disparar evento "Criação" no service de criação de Combinado

- Pai: 30785
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31713/activity)

**Descrição:**

Criado: 2026-09-06T00:11:39.040Z
Atualizado: 2026-10-21T22:10:36.992Z
Task da US73 - Registro da criação do combinado no histórico.

###### [TASK] #32264 — [testes] Testar evento de criação no histórico

- Pai: 30785
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32264/activity)

**Descrição:**

Criado: 2026-09-16T01:51:16.398Z
Atualizado: 2026-10-22T01:27:31.000Z
Verificar gravação CRIACAO com autor/data

###### [TASK] #32265 — [po/validação] Homologar registro de criação

- Pai: 30785
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32265/activity)

**Descrição:**

Criado: 2026-09-16T01:51:17.376Z
Atualizado: 2026-09-23T22:43:07.596Z
Validar evento na timeline com texto claro

###### [USER STORY] #30786 — US74 - Registro do envio de convite no histórico

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30786/activity)

**Descrição:**

Criado: 2026-09-03T02:13:49.447Z
Atualizado: 2026-10-21T22:45:58.282Z
### BDD

```text
DADO que um convite é enviado a um participante
QUANDO o convite for persistido
ENTÃO o sistema deve registrar um evento de histórico do tipo "Convite Enviado"
```

###### [TASK] #31714 — [back-end] Disparar evento "Convite Enviado" no service de Convite

- Pai: 30786
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31714/activity)

**Descrição:**

Criado: 2026-09-06T00:11:42.096Z
Atualizado: 2026-10-21T22:46:02.188Z
Task da US74 - Registro do envio de convite no histórico.

###### [TASK] #32266 — [testes] Testar evento de envio de convite no histórico

- Pai: 30786
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32266/activity)

**Descrição:**

Criado: 2026-09-16T01:51:18.388Z
Atualizado: 2026-10-22T01:27:39.855Z
Cada convite gera evento ENVIO_CONVITE

###### [TASK] #32267 — [po/validação] Homologar registro de envio de convite

- Pai: 30786
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32267/activity)

**Descrição:**

Criado: 2026-09-16T01:51:19.400Z
Atualizado: 2026-09-23T22:42:54.356Z
Múltiplos convites = múltiplos eventos

###### [USER STORY] #30787 — US75 - Registro de aceite e recusa no histórico

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30787/activity)

**Descrição:**

Criado: 2026-09-03T02:14:21.807Z
Atualizado: 2026-09-16T01:53:01.515Z
### BDD

```text
DADO que um participante responde a um convite
QUANDO ele aceitar ou recusar
ENTÃO o sistema deve registrar um evento de histórico com o tipo correspondente (Aceito/Recusado)
```

###### [TASK] #31715 — [back-end] Disparar evento "Aceito" ou "Recusado" conforme resposta do convite

- Pai: 30787
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31715/activity)

**Descrição:**

Criado: 2026-09-06T00:11:45.063Z
Atualizado: 2026-09-06T00:11:45.114Z
Task da US75 - Registro de aceite e recusa no histórico.

###### [TASK] #32345 — [testes] Testar eventos de aceite e recusa no histórico

- Pai: 30787
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32345/activity)

**Descrição:**

Criado: 2026-09-16T01:52:59.391Z
Atualizado: 2026-10-24T17:16:58.027Z
Autor + data

###### [TASK] #32346 — [po/validação] Homologar registro de aceite e recusa

- Pai: 30787
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32346/activity)

**Descrição:**

Criado: 2026-09-16T01:53:01.433Z
Atualizado: 2026-10-24T17:16:04.485Z
Ordem cronológica

###### [USER STORY] #30789 — US76 - Registro de alteração de status, conclusão e cancelamento

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30789/activity)

**Descrição:**

Criado: 2026-09-03T02:14:44.038Z
Atualizado: 2026-09-16T01:53:04.453Z
### BDD

```text
DADO que o status de um combinado é alterado (ativo, concluído ou cancelado)
QUANDO a transição ocorrer
ENTÃO o sistema deve registrar um evento de histórico específico para essa mudança
```

###### [TASK] #31716 — [back-end] Criar evento genérico de "Alteração de Status" reaproveitável pelas transições

- Pai: 30789
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31716/activity)

**Descrição:**

Criado: 2026-09-06T00:11:48.017Z
Atualizado: 2026-09-06T00:11:48.062Z
Task da US76 - Registro de alteração de status, conclusão e cancelamento.

###### [TASK] #32347 — [testes] Testar eventos de alteração de status

- Pai: 30789
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32347/activity)

**Descrição:**

Criado: 2026-09-16T01:53:03.409Z
Atualizado: 2026-10-24T17:16:42.175Z
Dados antigos e novos

###### [TASK] #32348 — [po/validação] Homologar registro de alterações de status

- Pai: 30789
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32348/activity)

**Descrição:**

Criado: 2026-09-16T01:53:04.376Z
Atualizado: 2026-10-24T17:15:49.012Z
Clareza na timeline

###### [CRITÉRIO DE ACEITAÇÃO] #31644 — CA-F22.1 - Registro de Eventos

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31644/activity)

**Descrição:**

Criado: 2026-09-06T00:01:49.803Z
Atualizado: 2026-09-16T02:08:04.274Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31645 — CA01 - Toda criação de combinado deve gerar um evento de histórico do tipo "Criação".

- Pai: 31644
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31645/activity)

**Descrição:**

Criado: 2026-09-06T00:01:50.276Z
Atualizado: 2026-09-06T00:01:50.325Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31646 — CA02 - Todo envio de convite deve gerar um evento de histórico do tipo "Convite Enviado".

- Pai: 31644
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31646/activity)

**Descrição:**

Criado: 2026-09-06T00:01:50.786Z
Atualizado: 2026-09-06T00:01:50.820Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31647 — CA03 - Toda resposta a convite (aceite/recusa) deve gerar um evento de histórico correspondente.

- Pai: 31644
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31647/activity)

**Descrição:**

Criado: 2026-09-06T00:01:51.204Z
Atualizado: 2026-09-06T00:01:51.238Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31648 — CA04 - Toda alteração de status (ativo, concluído, cancelado) deve gerar um evento de histórico.

- Pai: 31644
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31648/activity)

**Descrição:**

Criado: 2026-09-06T00:01:51.708Z
Atualizado: 2026-09-06T00:01:51.738Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31707 — CA-F22.2 - Registro de Eventos

- Pai: 30691
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31707/activity)

**Descrição:**

Criado: 2026-09-06T00:11:33.542Z
Atualizado: 2026-09-16T02:08:05.457Z
Critérios de aceitação da F22 — Registro de Eventos.

###### [CRITÉRIO DE ACEITAÇÃO] #31708 — CA01 - Toda criação de combinado deve gerar um evento de histórico do tipo "Criação".

- Pai: 31707
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31708/activity)

**Descrição:**

Criado: 2026-09-06T00:11:34.095Z
Atualizado: 2026-09-06T00:11:34.142Z
Toda criação de combinado deve gerar um evento de histórico do tipo "Criação".

###### [CRITÉRIO DE ACEITAÇÃO] #31709 — CA02 - Todo envio de convite deve gerar um evento de histórico do tipo "Convite Enviado".

- Pai: 31707
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31709/activity)

**Descrição:**

Criado: 2026-09-06T00:11:34.586Z
Atualizado: 2026-09-06T00:11:34.628Z
Todo envio de convite deve gerar um evento de histórico do tipo "Convite Enviado".

###### [CRITÉRIO DE ACEITAÇÃO] #31710 — CA03 - Toda resposta a convite (aceite/recusa) deve gerar um evento de histórico correspondente.

- Pai: 31707
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31710/activity)

**Descrição:**

Criado: 2026-09-06T00:11:35.098Z
Atualizado: 2026-09-06T00:11:35.132Z
Toda resposta a convite (aceite/recusa) deve gerar um evento de histórico correspondente.

###### [CRITÉRIO DE ACEITAÇÃO] #31711 — CA04 - Toda alteração de status (ativo, concluído, cancelado) deve gerar um evento de histórico.

- Pai: 31707
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31711/activity)

**Descrição:**

Criado: 2026-09-06T00:11:35.646Z
Atualizado: 2026-09-06T00:11:35.701Z
Toda alteração de status (ativo, concluído, cancelado) deve gerar um evento de histórico.

#### [EPIC] #30636 — 06.2 — Linha do Tempo

- Pai: 30583
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30636/activity)

**Descrição:**

Criado: 2026-09-02T21:52:49.579Z
Atualizado: 2026-09-16T01:53:12.502Z
Tela que exibe os eventos em ordem cronológica.

##### [FEATURE] #30692 — F23 — Linha do Tempo

- Pai: 30636
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30692/activity)

**Descrição:**

Criado: 2026-09-03T00:09:48.693Z
Atualizado: 2026-09-16T01:53:12.488Z
Tela que exibe os eventos em ordem cronológica.

###### [USER STORY] #30790 — US77 - Linha do tempo com eventos em ordem cronológica

- Pai: 30692
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30790/activity)

**Descrição:**

Criado: 2026-09-03T02:15:22.985Z
Atualizado: 2026-09-16T01:53:07.468Z
### BDD

```text
DADO que um combinado possui eventos registrados no histórico
QUANDO o usuário acessar a linha do tempo
ENTÃO os eventos devem ser exibidos em ordem cronológica
```

###### [TASK] #31721 — [back-end] Criar endpoint GET /combinados/{id}/historico ordenado por data

- Pai: 30790
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31721/activity)

**Descrição:**

Criado: 2026-09-06T00:11:52.998Z
Atualizado: 2026-09-06T00:11:53.034Z
Task da US77 - Linha do tempo com eventos em ordem cronológica.

###### [TASK] #31722 — [front-end] Criar componente de linha do tempo (timeline)

- Pai: 30790
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31722/activity)

**Descrição:**

Criado: 2026-09-06T00:11:53.682Z
Atualizado: 2026-09-06T00:11:53.722Z
Task da US77 - Linha do tempo com eventos em ordem cronológica.

###### [TASK] #32349 — [testes] Testar ordenação cronológica do endpoint de histórico

- Pai: 30790
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32349/activity)

**Descrição:**

Criado: 2026-09-16T01:53:06.402Z
Atualizado: 2026-10-24T17:12:52.918Z
Ordem crescente

###### [TASK] #32350 — [po/validação] Homologar ordem cronológica na timeline

- Pai: 30790
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32350/activity)

**Descrição:**

Criado: 2026-09-16T01:53:07.391Z
Atualizado: 2026-10-24T17:12:21.775Z
Mais antigo → mais recente

###### [USER STORY] #30791 — US78 - Linha do tempo com autor, data/hora e descrição do evento

- Pai: 30692
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30791/activity)

**Descrição:**

Criado: 2026-09-03T02:15:44.320Z
Atualizado: 2026-09-16T01:53:09.506Z
### BDD

```text
DADO que a linha do tempo está sendo exibida
QUANDO cada evento for renderizado
ENTÃO deve mostrar o nome do autor, a data/hora e a descrição do evento
```

###### [TASK] #31723 — [front-end] Exibir autor, data/hora e descrição em cada item da timeline

- Pai: 30791
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31723/activity)

**Descrição:**

Criado: 2026-09-06T00:11:56.664Z
Atualizado: 2026-09-06T00:11:56.709Z
Task da US78 - Linha do tempo com autor, data/hora e descrição do evento.

###### [TASK] #32351 — [testes] Testar campos obrigatórios na timeline

- Pai: 30791
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32351/activity)

**Descrição:**

Criado: 2026-09-16T01:53:08.395Z
Atualizado: 2026-10-24T17:12:02.115Z
Autor + data/hora + descrição

###### [TASK] #32352 — [po/validação] Homologar exibição de autor/data/descrição

- Pai: 30791
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32352/activity)

**Descrição:**

Criado: 2026-09-16T01:53:09.409Z
Atualizado: 2026-10-24T17:11:38.700Z
Formato e clareza

###### [USER STORY] #30792 — US79 - Linha do tempo com ícones por tipo de evento

- Pai: 30692
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30792/activity)

**Descrição:**

Criado: 2026-09-03T02:16:19.098Z
Atualizado: 2026-09-16T01:53:12.472Z
### BDD

```text
DADO que existem diferentes tipos de evento (criação, convite, aceite, recusa, status, conclusão, cancelamento)
QUANDO a linha do tempo for exibida
ENTÃO cada tipo de evento deve ter um ícone/cor visualmente distinto
```

###### [TASK] #31724 — [front-end] Mapear tipo de evento para ícone/cor e aplicar na timeline

- Pai: 30792
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31724/activity)

**Descrição:**

Criado: 2026-09-06T00:11:59.551Z
Atualizado: 2026-09-06T00:11:59.587Z
Task da US79 - Linha do tempo com ícones por tipo de evento.

###### [TASK] #32353 — [testes] Testar mapeamento de ícones

- Pai: 30792
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32353/activity)

**Descrição:**

Criado: 2026-09-16T01:53:10.434Z
Atualizado: 2026-10-24T17:11:15.790Z
Cada tipo com ícone correto

###### [TASK] #32354 — [po/validação] Homologar ícones da timeline

- Pai: 30792
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32354/activity)

**Descrição:**

Criado: 2026-09-16T01:53:12.386Z
Atualizado: 2026-10-24T17:11:04.976Z
Consistência + acessibilidade

###### [CRITÉRIO DE ACEITAÇÃO] #31649 — CA-F23.1 - Linha do Tempo

- Pai: 30692
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31649/activity)

**Descrição:**

Criado: 2026-09-06T00:01:52.175Z
Atualizado: 2026-09-16T02:08:06.743Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31650 — CA01 - A linha do tempo deve exibir os eventos do combinado em ordem cronológica.

- Pai: 31649
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31650/activity)

**Descrição:**

Criado: 2026-09-06T00:01:52.690Z
Atualizado: 2026-09-06T00:01:52.737Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31651 — CA02 - Cada item da linha do tempo deve exibir autor, data/hora e descrição do evento.

- Pai: 31649
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31651/activity)

**Descrição:**

Criado: 2026-09-06T00:01:53.206Z
Atualizado: 2026-09-06T00:01:53.252Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31652 — CA03 - Cada tipo de evento deve ter um ícone/cor distinto para facilitar a identificação visual.

- Pai: 31649
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31652/activity)

**Descrição:**

Criado: 2026-09-06T00:01:53.666Z
Atualizado: 2026-09-06T00:01:53.704Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31717 — CA-F23.2 - Linha do Tempo

- Pai: 30692
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31717/activity)

**Descrição:**

Criado: 2026-09-06T00:11:48.806Z
Atualizado: 2026-09-16T02:08:08.718Z
Critérios de aceitação da F23 — Linha do Tempo.

###### [CRITÉRIO DE ACEITAÇÃO] #31718 — CA01 - A linha do tempo deve exibir os eventos do combinado em ordem cronológica.

- Pai: 31717
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31718/activity)

**Descrição:**

Criado: 2026-09-06T00:11:49.315Z
Atualizado: 2026-09-06T00:11:49.352Z
A linha do tempo deve exibir os eventos do combinado em ordem cronológica.

###### [CRITÉRIO DE ACEITAÇÃO] #31719 — CA02 - Cada item da linha do tempo deve exibir autor, data/hora e descrição do evento.

- Pai: 31717
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31719/activity)

**Descrição:**

Criado: 2026-09-06T00:11:49.786Z
Atualizado: 2026-09-06T00:11:49.824Z
Cada item da linha do tempo deve exibir autor, data/hora e descrição do evento.

###### [CRITÉRIO DE ACEITAÇÃO] #31720 — CA03 - Cada tipo de evento deve ter um ícone/cor distinto para facilitar a identificação visual.

- Pai: 31717
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31720/activity)

**Descrição:**

Criado: 2026-09-06T00:11:50.358Z
Atualizado: 2026-09-06T00:11:50.404Z
Cada tipo de evento deve ter um ícone/cor distinto para facilitar a identificação visual.

#### [EPIC] #30637 — 06.3 — Exibição de Autor e Data

- Pai: 30583
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30637/activity)

**Descrição:**

Criado: 2026-09-02T21:53:05.651Z
Atualizado: 2026-09-16T01:53:20.530Z
Cada evento deve mostrar quem fez e quando.

##### [FEATURE] #30693 — F24 — Exibir Autor e Data

- Pai: 30637
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30693/activity)

**Descrição:**

Criado: 2026-09-03T00:10:12.641Z
Atualizado: 2026-09-16T01:53:20.505Z
Cada evento deve mostrar quem fez e quando.

###### [USER STORY] #30794 — US80 - Exibição do nome do autor e da data em cada evento

- Pai: 30693
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30794/activity)

**Descrição:**

Criado: 2026-09-03T02:16:56.401Z
Atualizado: 2026-09-16T01:53:15.462Z
### BDD

```text
DADO que um evento de histórico é exibido
QUANDO o item for renderizado na tela
ENTÃO deve mostrar o nome do autor e a data do evento
```

###### [TASK] #31729 — [back-end] Incluir nome do autor no retorno da API de histórico (join com Usuário)

- Pai: 30794
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31729/activity)

**Descrição:**

Criado: 2026-09-06T00:12:04.585Z
Atualizado: 2026-09-06T00:12:04.621Z
Task da US80 - Exibição do nome do autor e da data em cada evento.

###### [TASK] #31730 — [front-end] Exibir nome do autor no item de evento

- Pai: 30794
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31730/activity)

**Descrição:**

Criado: 2026-09-06T00:12:05.411Z
Atualizado: 2026-09-06T00:12:05.499Z
Task da US80 - Exibição do nome do autor e da data em cada evento.

###### [TASK] #32355 — [testes] Testar exibição de nome do autor

- Pai: 30794
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32355/activity)

**Descrição:**

Criado: 2026-09-16T01:53:14.366Z
Atualizado: 2026-10-24T17:10:49.732Z
Nome completo

###### [TASK] #32356 — [po/validação] Homologar nome do autor na timeline

- Pai: 30794
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32356/activity)

**Descrição:**

Criado: 2026-09-16T01:53:15.375Z
Atualizado: 2026-10-24T17:10:47.564Z
Não usar login/email

###### [USER STORY] #30795 — US81 - Exibição da hora em cada evento

- Pai: 30693
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30795/activity)

**Descrição:**

Criado: 2026-09-03T02:17:19.692Z
Atualizado: 2026-09-16T01:53:18.466Z
### BDD

```text
DADO que um evento de histórico é exibido
QUANDO o item for renderizado na tela
ENTÃO deve mostrar a hora exata em que o evento ocorreu
```

###### [TASK] #31731 — [front-end] Formatar e exibir hora (HH:mm) junto à data do evento

- Pai: 30795
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31731/activity)

**Descrição:**

Criado: 2026-09-06T00:12:08.719Z
Atualizado: 2026-09-06T00:12:08.767Z
Task da US81 - Exibição da hora em cada evento.

###### [TASK] #32357 — [testes] Testar exibição de hora

- Pai: 30795
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32357/activity)

**Descrição:**

Criado: 2026-09-16T01:53:17.381Z
Atualizado: 2026-10-24T17:10:07.142Z
Formato HH:mm

###### [TASK] #32358 — [po/validação] Homologar exibição de hora

- Pai: 30795
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32358/activity)

**Descrição:**

Criado: 2026-09-16T01:53:18.375Z
Atualizado: 2026-10-24T17:10:32.995Z
Consistência

###### [USER STORY] #30796 — US82 - Exibição da data em formato amigável (exemplo: "há 2 dias")

- Pai: 30693
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30796/activity)

**Descrição:**

Criado: 2026-09-03T02:17:36.563Z
Atualizado: 2026-09-16T01:53:20.488Z
### BDD

```text
DADO que um evento possui uma data de criação
QUANDO exibido na linha do tempo
ENTÃO a data deve ser apresentada em formato relativo amigável (ex: "há 2 dias", "há 1 hora")
```

###### [TASK] #31732 — [front-end] Implementar função/lib de data relativa (ex: date-fns)

- Pai: 30796
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31732/activity)

**Descrição:**

Criado: 2026-09-06T00:12:11.628Z
Atualizado: 2026-09-06T00:12:11.664Z
Task da US82 - Exibição da data em formato amigável (exemplo: "há 2 dias").

###### [TASK] #32359 — [testes] Testar função de data relativa

- Pai: 30796
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32359/activity)

**Descrição:**

Criado: 2026-09-16T01:53:19.391Z
Atualizado: 2026-10-24T17:07:57.202Z
Hoje ontem há X dias

###### [TASK] #32360 — [po/validação] Homologar data amigável

- Pai: 30796
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32360/activity)

**Descrição:**

Criado: 2026-09-16T01:53:20.406Z
Atualizado: 2026-10-24T17:10:31.035Z
Clareza e limites

###### [CRITÉRIO DE ACEITAÇÃO] #31653 — CA-F24.1 - Exibir Autor e Data

- Pai: 30693
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31653/activity)

**Descrição:**

Criado: 2026-09-06T00:01:54.161Z
Atualizado: 2026-09-16T02:08:10.734Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31654 — CA01 - Cada evento deve exibir o nome do autor que executou a ação.

- Pai: 31653
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31654/activity)

**Descrição:**

Criado: 2026-09-06T00:01:54.650Z
Atualizado: 2026-09-06T00:01:54.709Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31655 — CA02 - Cada evento deve exibir a hora exata em que ocorreu.

- Pai: 31653
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31655/activity)

**Descrição:**

Criado: 2026-09-06T00:01:55.149Z
Atualizado: 2026-09-06T00:01:55.192Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31656 — CA03 - A data deve ser exibida em formato amigável (ex: "há 2 dias") quando aplicável.

- Pai: 31653
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31656/activity)

**Descrição:**

Criado: 2026-09-06T00:01:55.643Z
Atualizado: 2026-09-06T00:01:55.681Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31725 — CA-F24.2 - Exibir Autor e Data

- Pai: 30693
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31725/activity)

**Descrição:**

Criado: 2026-09-06T00:12:00.339Z
Atualizado: 2026-09-16T02:08:12.719Z
Critérios de aceitação da F24 — Exibir Autor e Data.

###### [CRITÉRIO DE ACEITAÇÃO] #31726 — CA01 - Cada evento deve exibir o nome do autor que executou a ação.

- Pai: 31725
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31726/activity)

**Descrição:**

Criado: 2026-09-06T00:12:00.825Z
Atualizado: 2026-09-06T00:12:00.875Z
Cada evento deve exibir o nome do autor que executou a ação.

###### [CRITÉRIO DE ACEITAÇÃO] #31727 — CA02 - Cada evento deve exibir a hora exata em que ocorreu.

- Pai: 31725
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31727/activity)

**Descrição:**

Criado: 2026-09-06T00:12:01.385Z
Atualizado: 2026-09-06T00:12:01.440Z
Cada evento deve exibir a hora exata em que ocorreu.

###### [CRITÉRIO DE ACEITAÇÃO] #31728 — CA03 - A data deve ser exibida em formato amigável (ex: "há 2 dias") quando aplicável.

- Pai: 31725
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31728/activity)

**Descrição:**

Criado: 2026-09-06T00:12:01.902Z
Atualizado: 2026-09-06T00:12:01.945Z
A data deve ser exibida em formato amigável (ex: "há 2 dias") quando aplicável.

#### [EPIC] #30638 — 06.4 — Filtros no Histórico

- Pai: 30583
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30638/activity)

**Descrição:**

Criado: 2026-09-02T21:53:16.635Z
Atualizado: 2026-09-16T01:53:29.386Z
Permitir filtrar eventos por tipo ou data.

##### [FEATURE] #30694 — F25 — Filtros no Histórico

- Pai: 30638
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30694/activity)

**Descrição:**

Criado: 2026-09-03T00:10:50.551Z
Atualizado: 2026-09-16T01:53:29.375Z
Permitir filtrar eventos por tipo ou data.

###### [USER STORY] #30799 — US83 - Filtro do histórico por tipo de evento

- Pai: 30694
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30799/activity)

**Descrição:**

Criado: 2026-09-03T02:18:40.292Z
Atualizado: 2026-09-16T01:53:23.491Z
### BDD

```text
DADO que o usuário está na tela de histórico
QUANDO selecionar um tipo de evento no filtro
ENTÃO a lista deve exibir apenas eventos daquele tipo
```

###### [TASK] #31737 — [back-end] Adicionar parâmetro de filtro "tipo" no endpoint de histórico

- Pai: 30799
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31737/activity)

**Descrição:**

Criado: 2026-09-06T00:12:16.448Z
Atualizado: 2026-09-06T00:12:16.490Z
Task da US83 - Filtro do histórico por tipo de evento.

###### [TASK] #31738 — [front-end] Criar select/dropdown de filtro por tipo de evento

- Pai: 30799
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31738/activity)

**Descrição:**

Criado: 2026-09-06T00:12:17.209Z
Atualizado: 2026-09-06T00:12:17.250Z
Task da US83 - Filtro do histórico por tipo de evento.

###### [TASK] #32361 — [testes] Testar filtro por tipo

- Pai: 30799
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32361/activity)

**Descrição:**

Criado: 2026-09-16T01:53:22.408Z
Atualizado: 2026-10-24T17:07:46.883Z
Cada tipo e combinações

###### [TASK] #32362 — [po/validação] Homologar filtro de tipo

- Pai: 30799
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32362/activity)

**Descrição:**

Criado: 2026-09-16T01:53:23.419Z
Atualizado: 2026-10-24T17:10:17.770Z
Dropdown funcional

###### [USER STORY] #30800 — US84 - Filtro do histórico por período

- Pai: 30694
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30800/activity)

**Descrição:**

Criado: 2026-09-03T02:19:00.963Z
Atualizado: 2026-09-16T01:53:27.468Z
### BDD

```text
DADO que o usuário está na tela de histórico
QUANDO informar uma data inicial e final
ENTÃO a lista deve exibir apenas eventos ocorridos dentro desse período
```

###### [TASK] #31739 — [back-end] Adicionar filtro por intervalo de datas no endpoint de histórico

- Pai: 30800
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31739/activity)

**Descrição:**

Criado: 2026-09-06T00:12:20.809Z
Atualizado: 2026-09-06T00:12:20.857Z
Task da US84 - Filtro do histórico por período.

###### [TASK] #31740 — [front-end] Criar seletor de período (date range)

- Pai: 30800
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31740/activity)

**Descrição:**

Criado: 2026-09-06T00:12:21.520Z
Atualizado: 2026-09-06T00:12:21.567Z
Task da US84 - Filtro do histórico por período.

###### [TASK] #32363 — [testes] Testar filtro por intervalo de datas

- Pai: 30800
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32363/activity)

**Descrição:**

Criado: 2026-09-16T01:53:25.391Z
Atualizado: 2026-10-24T17:07:25.983Z
Válido inválido vazio

###### [TASK] #32364 — [po/validação] Homologar filtro de período no histórico

- Pai: 30800
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32364/activity)

**Descrição:**

Criado: 2026-09-16T01:53:27.395Z
Atualizado: 2026-10-24T17:10:15.496Z
Date range

###### [USER STORY] #30801 — US85 - Busca no histórico por autor

- Pai: 30694
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30801/activity)

**Descrição:**

Criado: 2026-09-03T02:20:35.997Z
Atualizado: 2026-09-16T01:53:29.363Z
### BDD

```text
DADO que o usuário está na tela de histórico
QUANDO buscar por nome de um autor
ENTÃO a lista deve exibir apenas eventos realizados por esse autor
```

###### [TASK] #31741 — [back-end] Adicionar filtro por autor (usuário) no endpoint de histórico

- Pai: 30801
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31741/activity)

**Descrição:**

Criado: 2026-09-06T00:12:24.376Z
Atualizado: 2026-09-06T00:12:24.413Z
Task da US85 - Busca no histórico por autor.

###### [TASK] #31742 — [front-end] Criar campo de busca por autor

- Pai: 30801
- Status: New
- Responsável: Maria Luiza
- Prioridade: Low
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31742/activity)

**Descrição:**

Criado: 2026-09-06T00:12:25.059Z
Atualizado: 2026-09-06T00:12:25.097Z
Task da US85 - Busca no histórico por autor.

###### [TASK] #32365 — [testes] Testar busca por autor

- Pai: 30801
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32365/activity)

**Descrição:**

Criado: 2026-09-16T01:53:28.337Z
Atualizado: 2026-10-24T17:07:16.123Z
Parcial e case-insensitive

###### [TASK] #32366 — [po/validação] Homologar busca por autor

- Pai: 30801
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32366/activity)

**Descrição:**

Criado: 2026-09-16T01:53:29.295Z
Atualizado: 2026-10-24T17:00:11.199Z
Campo funcional

###### [CRITÉRIO DE ACEITAÇÃO] #31657 — CA-F25.1 - Filtros no Histórico

- Pai: 30694
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31657/activity)

**Descrição:**

Criado: 2026-09-06T00:01:56.124Z
Atualizado: 2026-09-16T02:08:14.753Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31658 — CA01 - O usuário deve poder filtrar os eventos do histórico por tipo de evento.

- Pai: 31657
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31658/activity)

**Descrição:**

Criado: 2026-09-06T00:01:56.599Z
Atualizado: 2026-09-06T00:01:56.642Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31659 — CA02 - O usuário deve poder filtrar os eventos por período (data inicial e final).

- Pai: 31657
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31659/activity)

**Descrição:**

Criado: 2026-09-06T00:01:57.114Z
Atualizado: 2026-09-06T00:01:57.160Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31660 — CA03 - O usuário deve poder buscar eventos por autor.

- Pai: 31657
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31660/activity)

**Descrição:**

Criado: 2026-09-06T00:01:57.607Z
Atualizado: 2026-09-06T00:01:57.653Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31733 — CA-F25.2 - Filtros no Histórico

- Pai: 30694
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31733/activity)

**Descrição:**

Criado: 2026-09-06T00:12:12.394Z
Atualizado: 2026-09-16T02:08:16.681Z
Critérios de aceitação da F25 — Filtros no Histórico.

###### [CRITÉRIO DE ACEITAÇÃO] #31734 — CA01 - O usuário deve poder filtrar os eventos do histórico por tipo de evento.

- Pai: 31733
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31734/activity)

**Descrição:**

Criado: 2026-09-06T00:12:12.930Z
Atualizado: 2026-09-06T00:12:12.976Z
O usuário deve poder filtrar os eventos do histórico por tipo de evento.

###### [CRITÉRIO DE ACEITAÇÃO] #31735 — CA02 - O usuário deve poder filtrar os eventos por período (data inicial e final).

- Pai: 31733
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31735/activity)

**Descrição:**

Criado: 2026-09-06T00:12:13.361Z
Atualizado: 2026-09-06T00:12:13.396Z
O usuário deve poder filtrar os eventos por período (data inicial e final).

###### [CRITÉRIO DE ACEITAÇÃO] #31736 — CA03 - O usuário deve poder buscar eventos por autor.

- Pai: 31733
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31736/activity)

**Descrição:**

Criado: 2026-09-06T00:12:13.872Z
Atualizado: 2026-09-06T00:12:13.917Z
O usuário deve poder buscar eventos por autor.

### [EPIC] #30584 — 07 — Qualidade, Segurança e Infraestrutura

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-03
- Prazo: 2026-10-08
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30584/activity)

**Descrição:**

Criado: 2026-09-02T18:57:08.406Z
Atualizado: 2026-10-21T22:47:14.888Z
Este EPIC contempla as atividades auxiliares necessárias para garantir a qualidade, segurança e confiabilidade da aplicação, incluindo controle de acesso, testes unitários, testes funcionais, testes da API, correções e documentação.

#### [EPIC] #30641 — 07.1 — Modelagem e Banco de Dados

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-03
- Prazo: 2026-10-08
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30641/activity)

**Descrição:**

Criado: 2026-09-02T21:54:16.447Z
Atualizado: 2026-10-21T22:47:14.875Z
Criação do modelo relacional, migrations e configuração do PostgreSQL.

##### [FEATURE] #30695 — F26 — Modelagem de Dados

- Pai: 30641
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-03
- Prazo: 2026-10-08
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30695/activity)

**Descrição:**

Criado: 2026-09-03T00:11:21.864Z
Atualizado: 2026-10-21T22:47:14.857Z
Criação do modelo relacional, migrations e configuração do PostgreSQL.

###### [USER STORY] #30802 — US86 - Criação da tabela Usuário

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30802/activity)

**Descrição:**

Criado: 2026-09-03T02:21:21.339Z
Atualizado: 2026-09-15T20:48:01.877Z
### BDD

```text
DADO que o banco de dados está configurado
QUANDO a migration for executada
ENTÃO a tabela Usuário deve ser criada com os campos de nome, e-mail, senha e perfil
```

###### [TASK] #31747 — [back-end] Criar migration da tabela usuarios

- Pai: 30802
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31747/activity)

**Descrição:**

Criado: 2026-09-06T00:12:32.444Z
Atualizado: 2026-09-15T20:49:35.158Z
Task da US86 - Criação da tabela Usuário.

###### [TASK] #31748 — [back-end] Criar entidade JPA Usuario

- Pai: 30802
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31748/activity)

**Descrição:**

Criado: 2026-09-06T00:12:33.108Z
Atualizado: 2026-09-15T20:50:11.645Z
Task da US86 - Criação da tabela Usuário.

###### [USER STORY] #30804 — US87 - Criação da tabela Combinado

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-03
- Prazo: 2026-09-10
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30804/activity)

**Descrição:**

Criado: 2026-09-03T02:21:45.725Z
Atualizado: 2026-09-16T22:45:33.892Z
### BDD

```text
DADO que o banco de dados está configurado
QUANDO a migration for executada
ENTÃO a tabela Combinado deve ser criada com título, descrição, categoria, prazo e status
```

###### [TASK] #31749 — [back-end] Criar migration da tabela combinados

- Pai: 30804
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-10
- Prazo: 2026-09-10
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31749/activity)

**Descrição:**

Criado: 2026-09-06T00:12:36.792Z
Atualizado: 2026-09-16T22:45:33.847Z
Task da US87 - Criação da tabela Combinado.

###### [TASK] #31750 — [back-end] Criar entidade JPA Combinado

- Pai: 30804
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-03
- Prazo: 2026-09-03
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31750/activity)

**Descrição:**

Criado: 2026-09-06T00:12:37.473Z
Atualizado: 2026-09-16T22:39:04.349Z
Task da US87 - Criação da tabela Combinado.

###### [USER STORY] #30805 — US88 - Criação da tabela Participante

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30805/activity)

**Descrição:**

Criado: 2026-09-03T02:22:34.212Z
Atualizado: 2026-10-21T22:45:17.874Z
### BDD

```text
DADO que as tabelas Usuário e Combinado existem
QUANDO a migration for executada
ENTÃO a tabela Participante deve ser criada relacionando usuário e combinado
```

###### [TASK] #31751 — [back-end] Criar migration da tabela participantes com FKs

- Pai: 30805
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-10-07
- Prazo: 2026-10-07
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31751/activity)

**Descrição:**

Criado: 2026-09-06T00:12:41.254Z
Atualizado: 2026-10-21T22:47:49.883Z
Task da US88 - Criação da tabela Participante.

###### [TASK] #31752 — [back-end] Criar entidade JPA Participante

- Pai: 30805
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31752/activity)

**Descrição:**

Criado: 2026-09-06T00:12:41.947Z
Atualizado: 2026-10-21T22:26:33.352Z
Task da US88 - Criação da tabela Participante.

###### [USER STORY] #30807 — US89 - Criação da tabela Convite

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-10-08
- Prazo: 2026-10-08
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30807/activity)

**Descrição:**

Criado: 2026-09-03T02:22:53.512Z
Atualizado: 2026-10-21T22:47:14.838Z
### BDD

```text
DADO que a tabela Participante existe
QUANDO a migration for executada
ENTÃO a tabela Convite deve ser criada com status (pendente/aceito/recusado) e datas
```

###### [TASK] #31753 — [back-end] Criar migration da tabela convites

- Pai: 30807
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-10-08
- Prazo: 2026-10-08
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31753/activity)

**Descrição:**

Criado: 2026-09-06T00:12:45.788Z
Atualizado: 2026-10-21T22:47:23.555Z
Task da US89 - Criação da tabela Convite.

###### [TASK] #31754 — [back-end] Criar entidade JPA Convite

- Pai: 30807
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31754/activity)

**Descrição:**

Criado: 2026-09-06T00:12:46.524Z
Atualizado: 2026-10-21T22:44:37.258Z
Task da US89 - Criação da tabela Convite.

###### [USER STORY] #30809 — US90 - Criação da tabela Histórico

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30809/activity)

**Descrição:**

Criado: 2026-09-03T02:23:40.484Z
Atualizado: 2026-09-06T00:12:51.256Z
### BDD

```text
DADO que a tabela Combinado existe
QUANDO a migration for executada
ENTÃO a tabela Histórico deve ser criada com tipo de evento, autor, data e descrição
```

###### [TASK] #31755 — [back-end] Criar migration da tabela historicos

- Pai: 30809
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31755/activity)

**Descrição:**

Criado: 2026-09-06T00:12:50.454Z
Atualizado: 2026-10-21T22:31:06.153Z
Task da US90 - Criação da tabela Histórico.

###### [TASK] #31756 — [back-end] Criar entidade JPA Historico

- Pai: 30809
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31756/activity)

**Descrição:**

Criado: 2026-09-06T00:12:51.133Z
Atualizado: 2026-10-21T22:32:30.151Z
Task da US90 - Criação da tabela Histórico.

###### [USER STORY] #30810 — US91 - Criação da tabela Responsabilidade

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30810/activity)

**Descrição:**

Criado: 2026-09-03T02:24:19.048Z
Atualizado: 2026-09-06T00:12:55.800Z
### BDD

```text
DADO que as tabelas Combinado e Participante existem
QUANDO a migration for executada
ENTÃO a tabela Responsabilidade deve ser criada relacionando tarefa e responsável(is)
```

###### [TASK] #31757 — [back-end] Criar migration da tabela responsabilidades

- Pai: 30810
- Status: New
- Responsável: Maria Luiza
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31757/activity)

**Descrição:**

Criado: 2026-09-06T00:12:55.010Z
Atualizado: 2026-09-06T00:12:55.067Z
Task da US91 - Criação da tabela Responsabilidade.

###### [TASK] #31758 — [back-end] Criar entidade JPA Responsabilidade

- Pai: 30810
- Status: New
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31758/activity)

**Descrição:**

Criado: 2026-09-06T00:12:55.727Z
Atualizado: 2026-09-06T00:12:55.765Z
Task da US91 - Criação da tabela Responsabilidade.

###### [CRITÉRIO DE ACEITAÇÃO] #31661 — CA-F26.1 - Modelagem de Dados

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31661/activity)

**Descrição:**

Criado: 2026-09-06T00:01:58.127Z
Atualizado: 2026-09-16T02:08:18.740Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31662 — CA01 - As tabelas devem seguir a modelagem relacional definida (chaves primárias, estrangeiras e tipos de dados adequados).

- Pai: 31661
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31662/activity)

**Descrição:**

Criado: 2026-09-06T00:01:58.634Z
Atualizado: 2026-09-06T00:01:58.679Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31663 — CA02 - As migrations devem ser versionadas e executáveis em ambiente limpo.

- Pai: 31661
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31663/activity)

**Descrição:**

Criado: 2026-09-06T00:01:59.082Z
Atualizado: 2026-09-06T00:01:59.129Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31664 — CA03 - O banco de dados deve estar configurado em PostgreSQL, coerente com o ambiente do projeto.

- Pai: 31661
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31664/activity)

**Descrição:**

Criado: 2026-09-06T00:01:59.517Z
Atualizado: 2026-09-06T00:01:59.568Z
(Sem descrição)

###### [CRITÉRIO DE ACEITAÇÃO] #31743 — CA-F26.2 - Modelagem de Dados

- Pai: 30695
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31743/activity)

**Descrição:**

Criado: 2026-09-06T00:12:26.072Z
Atualizado: 2026-09-16T02:08:20.721Z
Critérios de aceitação da F26 — Modelagem de Dados.

###### [CRITÉRIO DE ACEITAÇÃO] #31744 — CA01 - As tabelas devem seguir a modelagem relacional definida (chaves primárias, estrangeiras e tipos de dados adequados).

- Pai: 31743
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31744/activity)

**Descrição:**

Criado: 2026-09-06T00:12:26.762Z
Atualizado: 2026-09-06T00:12:26.822Z
As tabelas devem seguir a modelagem relacional definida (chaves primárias, estrangeiras e tipos de dados adequados).

###### [CRITÉRIO DE ACEITAÇÃO] #31745 — CA02 - As migrations devem ser versionadas e executáveis em ambiente limpo.

- Pai: 31743
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31745/activity)

**Descrição:**

Criado: 2026-09-06T00:12:27.307Z
Atualizado: 2026-09-06T00:12:27.358Z
As migrations devem ser versionadas e executáveis em ambiente limpo.

###### [CRITÉRIO DE ACEITAÇÃO] #31746 — CA03 - O banco de dados deve estar configurado em PostgreSQL, coerente com o ambiente do projeto.

- Pai: 31743
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31746/activity)

**Descrição:**

Criado: 2026-09-06T00:12:27.779Z
Atualizado: 2026-09-06T00:12:27.832Z
O banco de dados deve estar configurado em PostgreSQL, coerente com o ambiente do projeto.

#### [EPIC] #30642 — 07.2 — Arquitetura e Setup do Backend

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-03
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30642/activity)

**Descrição:**

Criado: 2026-09-02T21:54:31.428Z
Atualizado: 2026-09-16T22:47:26.630Z
Configuração do Spring Boot, estrutura de pacotes, controllers, services, repositories.

##### [FEATURE] #30696 — F27 — Arquitetura Backend

- Pai: 30642
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-03
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30696/activity)

**Descrição:**

Criado: 2026-09-03T00:11:51.640Z
Atualizado: 2026-09-16T22:47:26.608Z
Configuração do Spring Boot, estrutura de pacotes, controllers, services, repositories.

###### [USER STORY] #30829 — US92 - Configuração do Spring Boot com estrutura de pastas

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-03
- Prazo: 2026-09-03
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30829/activity)

**Descrição:**

Criado: 2026-09-03T02:46:41.857Z
Atualizado: 2026-09-16T22:38:08.474Z
História do Usuário
Como desenvolvedor da equipe,
quero configurar a base da aplicação Spring Boot com a estrutura de pacotes padronizada,
para permitir o desenvolvimento organizado e desacoplado das regras de negócio do Combinado.

BDD
Cenário: Inicialização do projeto Spring Boot e validação da arquitetura
Dado que o projeto backend foi clonado e aberto na IDE
E todas as dependências do pom.xml foram baixadas com sucesso
Quando eu executar o comando de inicialização da aplicação
Então a aplicação Spring Boot deve subir localmente na porta configurada sem erros
E a estrutura de pacotes em camadas (controller, service, repository, model, dto, config) deve estar disponível.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).

###### [TASK] #31148 — [back-end] Inicializar projeto Spring Boot e configurar dependências base

- Pai: 30829
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-03
- Prazo: 2026-09-03
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31148/activity)

**Descrição:**

Criado: 2026-09-05T12:02:41.710Z
Atualizado: 2026-09-16T22:36:51.824Z
Criar a base do Spring Boot (Web, DevTools, JPA, PostgreSQL Driver) via Spring Initializr.

###### [TASK] #31149 — [back-end] Organizar arquitetura de pacotes e camadas base

- Pai: 30829
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-03
- Prazo: 2026-09-03
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31149/activity)

**Descrição:**

Criado: 2026-09-05T12:03:36.458Z
Atualizado: 2026-09-16T22:38:08.421Z
Criar os pacotes controller, service, repository, model, dto e config.

###### [USER STORY] #30830 — US93 - Criação de Controller, Service e Repository para Usuário

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-07
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30830/activity)

**Descrição:**

Criado: 2026-09-03T02:47:11.922Z
Atualizado: 2026-09-16T22:47:26.593Z
História do Usuário
Como desenvolvedor backend,
quero estruturar as camadas de Controller, Service e Repository da entidade Usuário,
para viabilizar as operações de cadastro, autenticação e gerenciamento de usuários no sistema.

BDD
Cenário: Integração das camadas de usuário com sucesso
Dado que a requisição de usuário é enviada para a API
E os dados chegam através do UsuarioController
Quando o UsuarioService processar as regras de validação e chamar o UsuarioRepository
Então a operação no banco de dados deve ser executada com persistência correta
E o sistema deve retornar a resposta no formato DTO apropriado com código HTTP correspondente.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).
\- CA02 — A API deve disponibilizar serviços e endpoints REST para cadastro, consulta e manipulação de usuários.

###### [TASK] #31150 — [back-end] Criar UsuarioRepository com métodos derivados Spring Data

- Pai: 30830
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31150/activity)

**Descrição:**

Criado: 2026-09-05T12:08:30.524Z
Atualizado: 2026-09-16T22:50:05.267Z
Implementar interface de persistência herdando JpaRepository com consultas por e-mail

###### [TASK] #31151 — [back-end] Implementar estrutura e métodos no UsuarioService

- Pai: 30830
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-07
- Prazo: 2026-09-07
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31151/activity)

**Descrição:**

Criado: 2026-09-05T12:09:17.651Z
Atualizado: 2026-09-16T22:43:55.145Z
Desenvolver métodos de negócio de criação, validação de unicidade e conversão para DTO.

###### [TASK] #31152 — [back-end] Implementar UsuarioController com rotas REST

- Pai: 30830
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31152/activity)

**Descrição:**

Criado: 2026-09-05T12:10:37.120Z
Atualizado: 2026-09-16T22:47:30.089Z
Criar rotas `@RestController` com validação `@Valid` e retornos via `ResponseEntity`.

###### [USER STORY] #30831 — US94 - Criação de Controller, Service e Repository para Combinado

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-09
- Prazo: 2026-09-11
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30831/activity)

**Descrição:**

Criado: 2026-09-03T02:47:55.401Z
Atualizado: 2026-09-16T22:44:46.622Z
História do Usuário
Como desenvolvedor backend,
quero estruturar o Controller, Service e Repository para a entidade Combinado,
para prover as operações de criação, salvamento de rascunho e listagem de acordos na API.

BDD
Cenário: Criação de combinado no backend
Dado que uma requisição com dados válidos de um combinado chega na API
Quando o CombinadoController repassar a chamada para o CombinadoService
Então os dados devem ser validados e persistidos através do CombinadoRepository
E o sistema deve retornar o código HTTP 201 Created com o DTO do combinado criado.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).
\- CA03 — A API deve expor endpoints REST para criação, salvamento de rascunho e consulta de acordos e combinados.

###### [TASK] #31224 — [back-end] Criar CombinadoRepository e métodos de busca

- Pai: 30831
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31224/activity)

**Descrição:**

Criado: 2026-09-05T17:59:06.407Z
Atualizado: 2026-09-11T20:17:07.428Z
Implementar interface herdando JpaRepository com métodos de busca por criador e participante.

###### [TASK] #31225 — [back-end] Implementar regras de negócio no CombinadoService

- Pai: 30831
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-09
- Prazo: 2026-09-09
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31225/activity)

**Descrição:**

Criado: 2026-09-05T17:59:54.143Z
Atualizado: 2026-09-16T22:44:46.557Z
Criar métodos de criação, salvamento de rascunho, edição e validação de prazos.

###### [TASK] #31226 — [back-end] Implementar CombinadoController com rotas REST

- Pai: 30831
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31226/activity)

**Descrição:**

Criado: 2026-09-05T18:00:53.695Z
Atualizado: 2026-09-11T20:20:23.177Z
Criar endpoints POST, PUT e GET protegidos por autenticação para manipulação de combinados.

###### [USER STORY] #30832 — US95 - Criação de Controller, Service e Repository para Participante e Convite

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30832/activity)

**Descrição:**

Criado: 2026-09-03T02:48:13.672Z
Atualizado: 2026-09-05T18:04:02.035Z
História do Usuário
Como desenvolvedor backend,
quero construir as camadas de Controller, Service e Repository de Participante e Convite,
para gerenciar o envio, aceite e recusa de convites na plataforma Combinado.

BDD
Cenário: Processamento de resposta de convite
Dado que um usuário participante responde a um convite pendente
Quando a chamada atingir o ConviteController
Então o ConviteService deve registrar a resposta (aceito ou recusado) com timestamp via ConviteRepository
E deve atualizar a situação do participante no combinado correspondente.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).
\- CA04 — O backend deve gerenciar os estados de convites e respostas (aceite/recusa) com atualização de data/hora.

###### [TASK] #31228 — [back-end] Implementar Repositories para Participante e Convite

- Pai: 30832
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31228/activity)

**Descrição:**

Criado: 2026-09-05T18:02:41.805Z
Atualizado: 2026-10-22T01:25:50.195Z
Criar interfaces Spring Data JPA para as entidades Participante e Convite.

###### [TASK] #31229 — [back-end] Implementar regras de convites no ConviteService

- Pai: 30832
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31229/activity)

**Descrição:**

Criado: 2026-09-05T18:03:28.890Z
Atualizado: 2026-10-21T01:45:29.829Z
Desenvolver métodos para registrar envio de convite, aceite, recusa e salvar data/hora.

###### [TASK] #31230 — [back-end] Expor endpoints de convite no ConviteController

- Pai: 30832
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31230/activity)

**Descrição:**

Criado: 2026-09-05T18:04:01.961Z
Atualizado: 2026-10-22T01:31:45.207Z
Implementar rotas REST para envio, consulta e atualização de status dos convites.

###### [USER STORY] #30833 — US96 - Criação de Controller, Service e Repository para Histórico

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30833/activity)

**Descrição:**

Criado: 2026-09-03T02:48:31.504Z
Atualizado: 2026-09-05T18:06:07.243Z
História do Usuário
Como desenvolvedor backend,
quero criar as camadas de Controller, Service e Repository para a entidade Histórico,
para persistir e consultar todos os eventos que ocorrem ao longo da vida de um combinado.

BDD
Cenário: Registro automático de evento de combinado
Dado que uma ação relevante (ex: aceite de convite) ocorre em um combinado
Quando o serviço correspondente invocar o HistoricoService
Então um novo registro deve ser inserido no banco de dados via HistoricoRepository com data/hora e autor
E a rota de consulta deve listar os eventos ordenados cronologicamente.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).
\- CA05 — A API deve persistir eventos em tabela de histórico e disponibilizar consulta cronológica da linha do tempo.

###### [TASK] #31231 — [back-end] Criar HistoricoRepository com busca ordenada por data

- Pai: 30833
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31231/activity)

**Descrição:**

Criado: 2026-09-05T18:04:39.590Z
Atualizado: 2026-10-24T17:19:33.508Z
Implementar repositório JPA com consulta de eventos filtrados por combinado e ordenados cronologicamente.

###### [TASK] #31232 — [back-end] Implementar serviço de auditoria e registro no HistoricoService

- Pai: 30833
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31232/activity)

**Descrição:**

Criado: 2026-09-05T18:05:25.777Z
Atualizado: 2026-10-24T17:19:45.001Z
Criar método reutilizável para gravar logs com autor, descrição do evento e timestamp.

###### [TASK] #31233 — [back-end] Implementar HistoricoController para consulta da linha do tempo

- Pai: 30833
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31233/activity)

**Descrição:**

Criado: 2026-09-05T18:06:07.159Z
Atualizado: 2026-10-24T17:19:55.437Z
Expor rota GET /api/combinados/{id}/historico com DTOs dos eventos.

###### [USER STORY] #30834 — US97 - Criação de Controller, Service e Repository para Responsabilidade

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30834/activity)

**Descrição:**

Criado: 2026-09-03T02:48:51.608Z
Atualizado: 2026-09-05T18:10:43.644Z
História do Usuário
Como desenvolvedor backend,
quero estruturar o Controller, Service e Repository da entidade Responsabilidade,
para permitir o cadastro de tarefas e a atribuição de responsáveis dentro de cada combinado.

BDD
Cenário: Atribuição de responsabilidade no combinado
Dado que o criador inclui uma tarefa com um participante vinculado
Quando a requisição atingir o ResponsabilidadeController
Então o ResponsabilidadeService deve validar os dados e persistir a tarefa via ResponsabilidadeRepository
E associar a responsabilidade aos usuários designados.

Critérios de Aceitação Associados
\- CA01 — O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).
\- CA06 — O sistema deve permitir a persistência de tarefas atreladas a combinados e vinculadas a um ou múltiplos participantes.

###### [TASK] #31235 — [back-end] Criar ResponsabilidadeRepository e mapeamentos JPA

- Pai: 30834
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31235/activity)

**Descrição:**

Criado: 2026-09-05T18:07:13.577Z
Atualizado: 2026-10-24T17:03:14.882Z
Criar interface de persistência para as tarefas vinculadas a combinados e participantes.

###### [TASK] #31237 — [back-end] Implementar lógica de responsabilidades no backend

- Pai: 30834
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31237/activity)

**Descrição:**

Criado: 2026-09-05T18:08:33.478Z
Atualizado: 2026-10-24T17:20:06.093Z
Desenvolver métodos para adicionar, editar e validar atribuição de múltiplos responsáveis por tarefa.

###### [TASK] #31238 — [back-end] Expor rotas de responsabilidade no Controller

- Pai: 30834
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31238/activity)

**Descrição:**

Criado: 2026-09-05T18:10:43.557Z
Atualizado: 2026-10-24T17:03:32.320Z
Expor rotas para criação e listagem de responsabilidades associadas a um combinado.

###### [CRITÉRIO DE ACEITAÇÃO] #31771 — CA-F27.1 - Arquitetura Backend

- Pai: 30696
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31771/activity)

**Descrição:**

Criado: 2026-09-06T00:34:19.361Z
Atualizado: 2026-09-16T02:08:22.707Z
Agrupador dos critérios de aceitação referentes à estruturação em camadas, serviços REST, persistência e regras do backend da aplicação.

###### [CRITÉRIO DE ACEITAÇÃO] #31157 — CA01 — Inicialização e Camadas da Aplicação

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31157/activity)

**Descrição:**

Criado: 2026-09-05T12:55:30.687Z
Atualizado: 2026-09-06T00:34:37.652Z
O projeto deve compilar e rodar em Spring Boot mantendo a arquitetura em camadas desacopladas (controller, service, repository, model, dto, config).

###### [CRITÉRIO DE ACEITAÇÃO] #31158 — CA02 — CRUD e Operações de Usuários

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31158/activity)

**Descrição:**

Criado: 2026-09-05T13:14:48.079Z
Atualizado: 2026-09-06T00:34:52.695Z
A API deve disponibilizar serviços e endpoints REST para cadastro, consulta e manipulação de usuários.

###### [CRITÉRIO DE ACEITAÇÃO] #31159 — CA03 — Persistência e Regras de Combinados

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31159/activity)

**Descrição:**

Criado: 2026-09-05T13:18:40.856Z
Atualizado: 2026-09-06T00:35:03.107Z
A API deve expor endpoints REST para criação, salvamento de rascunho e consulta de acordos e combinados.

###### [CRITÉRIO DE ACEITAÇÃO] #31160 — CA04 — Gestão de Participantes e Convites

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31160/activity)

**Descrição:**

Criado: 2026-09-05T13:19:08.063Z
Atualizado: 2026-09-06T00:35:14.088Z
O backend deve gerenciar os estados de convites e respostas (aceite/recusa) com atualização de data/hora.

###### [CRITÉRIO DE ACEITAÇÃO] #31161 — CA05 — Rastreabilidade e Linha do Tempo

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31161/activity)

**Descrição:**

Criado: 2026-09-05T13:19:39.513Z
Atualizado: 2026-09-06T00:35:26.620Z
A API deve persistir eventos em tabela de histórico e disponibilizar consulta cronológica da linha do tempo.

###### [CRITÉRIO DE ACEITAÇÃO] #31162 — CA06 — Divisão e Vínculo de Responsabilidades

- Pai: 31771
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31162/activity)

**Descrição:**

Criado: 2026-09-05T13:20:42.712Z
Atualizado: 2026-09-06T00:35:37.120Z
O sistema deve permitir a persistência de tarefas atreladas a combinados e vinculadas a um ou múltiplos participantes.

#### [EPIC] #30643 — 07.3 — Arquitetura e Setup do Frontend

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30643/activity)

**Descrição:**

Criado: 2026-09-02T21:54:41.954Z
Atualizado: 2026-09-05T19:54:32.028Z
Configuração do React, roteamento, gerenciamento de estado, consumo da API.

##### [FEATURE] #30697 — F28 — Arquitetura Frontend

- Pai: 30643
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30697/activity)

**Descrição:**

Criado: 2026-09-03T00:12:13.721Z
Atualizado: 2026-09-05T19:54:31.995Z
Configuração do React, roteamento, gerenciamento de estado, consumo da API.

###### [USER STORY] #30814 — US98 - Configuração do React com estrutura de pastas

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30814/activity)

**Descrição:**

Criado: 2026-09-03T02:35:25.355Z
Atualizado: 2026-09-05T17:39:52.692Z
História do Usuário
Como desenvolvedor frontend,
quero inicializar a aplicação React com TypeScript e organizar os diretórios do projeto,
para fornecer a estrutura base necessária para a construção das interfaces do Combinado.

BDD
Cenário: Inicialização e estrutura do projeto frontend
Dado que o projeto frontend foi configurado com React e TypeScript
E todas as dependências do package.json foram instaladas
Quando o script de desenvolvimento for executado no terminal
Então a aplicação deve ser compilada sem erros e aberta no navegador
E a árvore de pastas (components, pages, services, routes, types) deve estar estruturada.

Critérios de Aceitação Associados
\- CA01 — O projeto frontend deve ser inicializado com React e TypeScript, contendo pastas separadas para components, pages, services, routes e types.

###### [TASK] #31153 — [front-end] Inicializar aplicação React com TypeScript

- Pai: 30814
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31153/activity)

**Descrição:**

Criado: 2026-09-05T12:13:04.330Z
Atualizado: 2026-09-05T12:13:04.386Z
Criar a estrutura base do projeto React TS e dependências centrais.

###### [TASK] #31154 — [front-end] Organizar diretórios modulares e estilos base

- Pai: 30814
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31154/activity)

**Descrição:**

Criado: 2026-09-05T12:17:25.827Z
Atualizado: 2026-09-05T12:17:25.862Z
Criar pastas components, pages, services, routes, types e folha de estilos global.

###### [USER STORY] #30815 — US99 - Criação de rotas para as principais telas

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30815/activity)

**Descrição:**

Criado: 2026-09-03T02:36:22.781Z
Atualizado: 2026-09-05T17:41:06.450Z
História do Usuário
Como usuário da plataforma,
quero navegar entre as diferentes telas do sistema através de URLs dedicadas,
para acessar com clareza as telas de login, cadastro e visualização principal.

BDD
Cenário: Navegação entre rotas públicas
Dado que o usuário acessa a aplicação pelo navegador
Quando ele digitar ou navegar para a rota /login ou /cadastro
Então a página correspondente deve ser renderizada corretamente sem recarregar a aplicação inteira
E tentativas de acesso a páginas inexistentes devem ser tratadas de forma amigável.

Critérios de Aceitação Associados
\- CA02 — O sistema deve gerenciar a navegação por rotas públicas e privadas via SPA utilizando react-router-dom sem recarregar o navegador.

###### [TASK] #31155 — [front-end] Instalar e configurar react-router-dom

- Pai: 30815
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31155/activity)

**Descrição:**

Criado: 2026-09-05T12:24:19.959Z
Atualizado: 2026-09-05T12:24:20.000Z
Configurar BrowserRouter e centralizar o roteador no arquivo AppRoutes.

###### [TASK] #31156 — [front-end] Mapear rotas públicas para páginas de login e cadastro

- Pai: 30815
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31156/activity)

**Descrição:**

Criado: 2026-09-05T12:24:59.891Z
Atualizado: 2026-09-05T12:24:59.929Z
Vincular as rotas `/login` e `/cadastro` aos respectivos componentes visuais.

###### [USER STORY] #30816 — US100 - Criação de componentes reutilizáveis (botões, insumos, cartões)

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30816/activity)

**Descrição:**

Criado: 2026-09-03T02:36:53.980Z
Atualizado: 2026-09-05T19:52:26.057Z
História do Usuário
Como desenvolvedor frontend,
quero desenvolver uma biblioteca de componentes reutilizáveis (Button, Input, Card),
para acelerar a criação das interfaces mantendo consistência visual e facilidade de manutenção.

BDD
Cenário: Reutilização de componentes na montagem de tela
Dado que uma tela da aplicação precisa exibir formulários e cartões
Quando os componentes Button, Input e Card forem importados e instanciados
Então eles devem renderizar de acordo com as propriedades fornecidas
E refletir visualmente estados de hover, disabled e mensagens de erro inline.

Critérios de Aceitação Associados
\- CA03 — Devem ser implementados componentes de interface compartilhados (botões, inputs e cards) mantendo padronização visual e suporte a estados dinâmicos.

###### [TASK] #31306 — [front-end] Construir componentes reutilizáveis de Botão e Input

- Pai: 30816
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31306/activity)

**Descrição:**

Criado: 2026-09-05T19:51:46.400Z
Atualizado: 2026-09-05T19:51:46.565Z
Desenvolver componentes com tipagem TypeScript e variantes de estilo no padrão da interface.

###### [TASK] #31309 — [front-end] Construir componente de Card para listagem de combinados

- Pai: 30816
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31309/activity)

**Descrição:**

Criado: 2026-09-05T19:52:25.957Z
Atualizado: 2026-09-05T19:52:26.016Z
Criar container reutilizável para exibir resumo de título, prazo e badges de status.

###### [USER STORY] #30817 — US101 - Criação de serviços de API para comunicação com o backend

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30817/activity)

**Descrição:**

Criado: 2026-09-03T02:37:28.955Z
Atualizado: 2026-09-05T19:54:31.976Z
História do Usuário
Como desenvolvedor frontend,
quero centralizar as chamadas HTTP em módulos de serviços com Axios,
para comunicar o cliente web com a API backend com tratamento unificado e injeção automática de tokens.

BDD
Cenário: Injeção de cabeçalho de autenticação nas requisições
Dado que o usuário está autenticado com token JWT salvo no navegador
Quando qualquer método de serviço disparar uma requisição para a API
Então o interceptador HTTP deve injetar o cabeçalho Authorization com o Bearer Token
E a requisição deve ser enviada para a baseURL configurada.

Critérios de Aceitação Associados
\- CA04 — O frontend deve dispor de cliente HTTP centralizado (Axios) com interceptador automático para injeção do token JWT nas requisições autenticadas.

###### [TASK] #31317 — [front-end] Configurar instância base do Axios com interceptadores

- Pai: 30817
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31317/activity)

**Descrição:**

Criado: 2026-09-05T19:54:02.432Z
Atualizado: 2026-09-05T19:54:02.469Z
Definir baseUrl e injetar token Bearer JWT automaticamente nas requisições autenticadas.

###### [TASK] #31323 — [front-end] Modularizar módulos de serviço (authService, combinadoService)

- Pai: 30817
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31323/activity)

**Descrição:**

Criado: 2026-09-05T19:54:31.858Z
Atualizado: 2026-09-05T19:54:31.919Z
Criar funções tipadas para chamar endpoints de combinados, autenticação e convites.

###### [CRITÉRIO DE ACEITAÇÃO] #31781 — CA-F28.1 - Arquitetura Frontend

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31781/activity)

**Descrição:**

Criado: 2026-09-06T00:39:53.652Z
Atualizado: 2026-09-16T02:08:24.755Z
Agrupador dos critérios de aceitação referentes ao setup do React, roteamento SPA, componentes reutilizáveis e centralização de serviços com Axios.

###### [CRITÉRIO DE ACEITAÇÃO] #31782 — CA-F28.2 - Arquitetura Frontend

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31782/activity)

**Descrição:**

Criado: 2026-09-06T00:40:46.592Z
Atualizado: 2026-09-16T02:08:26.732Z
Agrupador dos critérios de aceitação referentes ao setup do React, roteamento SPA, componentes reutilizáveis e centralização de serviços com Axios.

###### [CRITÉRIO DE ACEITAÇÃO] #31783 — CA-F28.3 - Arquitetura Frontend

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31783/activity)

**Descrição:**

Criado: 2026-09-06T00:41:51.924Z
Atualizado: 2026-09-16T02:08:28.776Z
Agrupador dos critérios de aceitação referentes ao setup do React, roteamento SPA, componentes reutilizáveis e centralização de serviços com Axios.

###### [CRITÉRIO DE ACEITAÇÃO] #31784 — CA-F28.4 - Arquitetura Frontend

- Pai: 30697
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31784/activity)

**Descrição:**

Criado: 2026-09-06T00:44:36.353Z
Atualizado: 2026-09-16T02:08:31.236Z
Agrupador dos critérios de aceitação referentes ao setup do React, roteamento SPA, componentes reutilizáveis e centralização de serviços com Axios.

###### [CRITÉRIO DE ACEITAÇÃO] #31163 — CA01 — Setup e Estrutura Modular

- Pai: 31784
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31163/activity)

**Descrição:**

Criado: 2026-09-05T13:33:37.393Z
Atualizado: 2026-09-06T00:45:00.096Z
O projeto frontend deve ser inicializado com React e TypeScript, contendo pastas separadas para components, pages, services, routes e types.

###### [CRITÉRIO DE ACEITAÇÃO] #31164 — CA02 — Roteamento da Aplicação

- Pai: 31784
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31164/activity)

**Descrição:**

Criado: 2026-09-05T13:34:27.690Z
Atualizado: 2026-09-06T00:45:08.621Z
O sistema deve gerenciar a navegação por rotas públicas e privadas via SPA utilizando react-router-dom sem recarregar o navegador.

###### [CRITÉRIO DE ACEITAÇÃO] #31165 — CA03 — Biblioteca de Componentes Reutilizáveis

- Pai: 31784
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31165/activity)

**Descrição:**

Criado: 2026-09-05T13:35:16.719Z
Atualizado: 2026-09-06T00:45:16.051Z
Devem ser implementados componentes de interface compartilhados (botões, inputs e cards) mantendo padronização visual e suporte a estados dinâmicos.

###### [CRITÉRIO DE ACEITAÇÃO] #31166 — CA04 — Centralização de Serviços de API

- Pai: 31784
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31166/activity)

**Descrição:**

Criado: 2026-09-05T13:38:24.865Z
Atualizado: 2026-09-06T00:45:25.420Z
O frontend deve dispor de cliente HTTP centralizado (Axios) com interceptador automático para injeção do token JWT nas requisições autenticadas.

#### [EPIC] #30644 — 07.4 — Segurança e Autenticação

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30644/activity)

**Descrição:**

Criado: 2026-09-02T21:54:52.852Z
Atualizado: 2026-09-05T20:04:26.211Z
Spring Security, BCrypt, JWT, filtros de autorização.

##### [FEATURE] #30698 — F29 — Segurança e JWT

- Pai: 30644
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30698/activity)

**Descrição:**

Criado: 2026-09-03T00:12:36.805Z
Atualizado: 2026-09-05T20:04:26.198Z
*   F29 — Segurança e JWT

*   Descrição:
Funcionalidade responsável por implementar a camada de segurança da aplicação utilizando Spring Security, criptografia de senhas com algoritmo BCrypt, emissão e validação de tokens JWT (JSON Web Token) e proteção de endpoints contra acessos não autorizados.

*   Critérios de Aceitação:
CA01 — Arquitetura de Segurança:
O sistema deve integrar o Spring Security configurando filtros de segurança sem estado (stateless) para API REST.

*   CA02 — Criptografia de Credenciais:
Todas as senhas de usuários devem ser obrigatoriamente criptografadas com o algoritmo BCrypt antes da persistência no banco.

*   CA03 — Emissão de Token JWT:
O backend deve gerar e assinar digitalmente um token JWT contendo tempo de expiração e claims de identificação após autenticação bem-sucedida.

*   CA04 — Validação de Requisições:
O sistema deve interceptar requisições para rotas protegidas, validando a integridade e expiração do Bearer Token informado no cabeçalho Authorization.

*   CA05 — Controle de Acesso e Erros de Permissão:
Tentativas de requisições a rotas privadas sem token válido devem retornar HTTP 401 Unauthorized, e requisições sem privilégios suficientes devem retornar HTTP 403 Forbidden.

###### [USER STORY] #30818 — US102 - Configuração do Spring Security com autenticação básica

- Pai: 30698
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30818/activity)

**Descrição:**

Criado: 2026-09-03T02:38:42.289Z
Atualizado: 2026-09-05T19:58:28.367Z
História do Usuário
Como desenvolvedor do Combinado,
quero configurar o Spring Security na aplicação backend,
para definir a política de segurança, desabilitar CSRF e liberar apenas as rotas públicas de acesso.

BDD
Cenário: Bloqueio de acesso a rota privada sem autenticação
Dado que a API está em execução com o Spring Security configurado
Quando um cliente não autenticado tentar acessar uma rota privada
Então a cadeia de filtros de segurança deve bloquear a requisição
E deve retornar o código de erro HTTP 401 Unauthorized.

Critérios de Aceitação Associados
\- CA01 — O sistema deve integrar o Spring Security configurando filtros de segurança sem estado (stateless) para a API REST.
\- CA04 — O sistema deve interceptar requisições para rotas protegidas, retornando HTTP 401 para requisições sem token válido e HTTP 403 para acessos sem permissão suficiente.

###### [TASK] #31325 — [back-end] Criar classe SecurityConfig e SecurityFilterChain

- Pai: 30818
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31325/activity)

**Descrição:**

Criado: 2026-09-05T19:55:13.225Z
Atualizado: 2026-09-05T19:55:13.283Z
Implementar classe anotada com @EnableWebSecurity e configurar bean SecurityFilterChain com rotas públicas e privadas.

###### [TASK] #31342 — [back-end] Configurar política de sessão STATELESS e desabilitar CSRF

- Pai: 30818
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31342/activity)

**Descrição:**

Criado: 2026-09-05T19:58:28.274Z
Atualizado: 2026-09-05T19:58:28.325Z
Desativar proteção CSRF e configurar SessionCreationPolicy.STATELESS no Spring Security para API REST.

###### [USER STORY] #30819 — US103 - Implementação de geração e validação de JWT

- Pai: 30698
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30819/activity)

**Descrição:**

Criado: 2026-09-03T02:39:31.524Z
Atualizado: 2026-09-05T20:00:12.633Z
História do Usuário
Como usuário autenticado,
quero que o sistema emita um token JWT assinado digitalmente,
para autenticar minhas requisições subsequentes sem a necessidade de enviar login e senha repetidamente.

BDD
Cenário: Emissão e validação de Bearer Token JWT
Dado que forneci credenciais corretas no endpoint de login
Quando o sistema autenticar meu acesso com sucesso
Então deve gerar e retornar um token JWT com data de expiração e assunto
E o filtro de autorização deve autenticar as próximas chamadas que apresentarem esse token no header Authorization.

Critérios de Aceitação Associados
\- CA02 — O backend deve gerar e assinar digitalmente um token JWT contendo tempo de expiração e claims de identificação após autenticação bem-sucedida.

###### [TASK] #31346 — [back-end] Implementar componente JwtService para geração e extração de claims

- Pai: 30819
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31346/activity)

**Descrição:**

Criado: 2026-09-05T19:59:20.907Z
Atualizado: 2026-09-05T19:59:20.952Z
Desenvolver métodos para gerar token assinado com chave secreta e extrair claims de usuário e expiração.

###### [TASK] #31350 — [back-end] Criar filtro JwtAuthenticationFilter para interceptar requisições

- Pai: 30819
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31350/activity)

**Descrição:**

Criado: 2026-09-05T20:00:12.471Z
Atualizado: 2026-09-05T20:00:12.548Z
Implementar filtro OncePerRequestFilter para autenticar o usuário no contexto do Spring a partir do cabeçalho Bearer.

###### [USER STORY] #30820 — US104 - Implementação de BCrypt para criptografia de senhas

- Pai: 30698
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30820/activity)

**Descrição:**

Criado: 2026-09-03T02:39:58.498Z
Atualizado: 2026-09-05T20:01:35.582Z
História do Usuário
Como usuário cadastrado,
quero que minha senha seja gravada como hash criptográfico no banco de dados,
para garantir o sigilo das minhas credenciais contra visualizações indevidas.

BDD
Cenário: Criptografia da senha no cadastro de usuário
Dado que preenchi minha senha em texto puro no formulário de cadastro
Quando o backend processar os dados para inserção na base
Então deve gerar o hash irreversível da senha utilizando o algoritmo BCrypt
E deve persistir exclusivamente o hash na coluna de senha da tabela de usuários.

Critérios de Aceitação Associados
\- CA03 — Todas as senhas de usuários devem ser obrigatoriamente criptografadas com o algoritmo BCrypt antes da persistência no banco de dados.

###### [TASK] #31356 — [back-end] Disponibilizar bean BCryptPasswordEncoder no contexto do Spring

- Pai: 30820
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31356/activity)

**Descrição:**

Criado: 2026-09-05T20:01:04.152Z
Atualizado: 2026-09-05T20:01:04.187Z
Instanciar e exportar o bean PasswordEncoder utilizando a implementação BCryptPasswordEncoder na configuração.

###### [TASK] #31358 — [back-end] Integrar criptografia BCrypt no fluxo de cadastro e login

- Pai: 30820
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31358/activity)

**Descrição:**

Criado: 2026-09-05T20:01:35.426Z
Atualizado: 2026-09-05T20:01:35.492Z
Chamar passwordEncoder.encode() antes do salvamento e passwordEncoder.matches() no momento da autenticação.

###### [USER STORY] #30821 — US105 - Implementação de filtros de autorização para endpoints

- Pai: 30698
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30821/activity)

**Descrição:**

Criado: 2026-09-03T02:40:35.034Z
Atualizado: 2026-09-05T20:04:26.179Z
História do Usuário
Como administrador de segurança do sistema,
quero que os endpoints possuam filtros de autorização granulares,
para garantir que apenas usuários com permissões legítimas executem ações restritas.

BDD
Cenário: Rejeição de requisição sem permissão suficiente
Dado que um usuário comum autenticado tenta acionar um endpoint administrativo
Quando o filtro de autorização avaliar os privilégios da sessão
Então o acesso ao recurso deve ser impedido
E a API deve retornar a resposta com código HTTP 403 Forbidden.

Critérios de Aceitação Associados
\- CA01 — O sistema deve integrar o Spring Security configurando filtros de segurança sem estado (stateless) para a API REST.
\- CA04 — O sistema deve interceptar requisições para rotas protegidas, retornando HTTP 401 para requisições sem token válido e HTTP 403 para acessos sem permissão suficiente.

###### [TASK] #31366 — [back-end] Configurar anotações de pré-autorização (@PreAuthorize)

- Pai: 30821
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31366/activity)

**Descrição:**

Criado: 2026-09-05T20:02:19.568Z
Atualizado: 2026-09-05T20:02:19.604Z
Habilitar anotações de segurança por método para proteger endpoints específicos conforme perfil de usuário.

###### [TASK] #31374 — [back-end] Tratar respostas de erro 401 e 403 com JSON customizado

- Pai: 30821
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31374/activity)

**Descrição:**

Criado: 2026-09-05T20:04:26.046Z
Atualizado: 2026-09-05T20:04:26.110Z
Configurar handlers de AuthenticationEntryPoint e AccessDeniedHandler para padronizar respostas de erro de segurança.

###### [CRITÉRIO DE ACEITAÇÃO] #31785 — CA-F29.1 - Segurança e JWT

- Pai: 30698
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31785/activity)

**Descrição:**

Criado: 2026-09-06T00:47:40.833Z
Atualizado: 2026-09-16T02:08:32.742Z
Agrupador dos critérios de aceitação referentes à integração com Spring Security, BCrypt, emissão/validação de tokens JWT e controle de acesso a rotas.

###### [CRITÉRIO DE ACEITAÇÃO] #31167 — CA01 — Arquitetura de Segurança

- Pai: 31785
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31167/activity)

**Descrição:**

Criado: 2026-09-05T13:54:22.929Z
Atualizado: 2026-09-06T00:47:55.139Z
O sistema deve integrar o Spring Security configurando filtros de segurança sem estado (stateless) para a API REST.

###### [CRITÉRIO DE ACEITAÇÃO] #31168 — CA02 — Emissão de Token JWT

- Pai: 31785
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31168/activity)

**Descrição:**

Criado: 2026-09-05T13:55:02.774Z
Atualizado: 2026-09-06T00:48:05.606Z
O backend deve gerar e assinar digitalmente um token JWT contendo tempo de expiração e claims de identificação após autenticação bem-sucedida.

###### [CRITÉRIO DE ACEITAÇÃO] #31169 — CA03 — Criptografia de Credenciais

- Pai: 31785
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31169/activity)

**Descrição:**

Criado: 2026-09-05T13:55:45.039Z
Atualizado: 2026-09-06T00:48:12.807Z
Todas as senhas de usuários devem ser obrigatoriamente criptografadas com o algoritmo BCrypt antes da persistência no banco de dados.

###### [CRITÉRIO DE ACEITAÇÃO] #31170 — CA04 — Controle de Acesso e Erros de Permissão

- Pai: 31785
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31170/activity)

**Descrição:**

Criado: 2026-09-05T13:56:20.614Z
Atualizado: 2026-09-06T00:48:20.690Z
O sistema deve interceptar requisições para rotas protegidas, retornando HTTP 401 para requisições sem token válido e HTTP 403 para acessos sem permissão suficiente.

#### [EPIC] #30645 — 07.5 — Testes Unitários (Backend)

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30645/activity)

**Descrição:**

Criado: 2026-09-02T21:55:04.912Z
Atualizado: 2026-09-05T20:13:10.689Z
JUnit e Mockito para regras de negócio, status e permissões.

##### [FEATURE] #30699 — F30 — Testes Unitários

- Pai: 30645
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30699/activity)

**Descrição:**

Criado: 2026-09-03T00:13:07.145Z
Atualizado: 2026-09-05T20:13:10.665Z
JUnit e Mockito para regras de negócio, status e permissões.

###### [USER STORY] #30822 — US106 - Teste unitário de criação de usuário

- Pai: 30699
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30822/activity)

**Descrição:**

Criado: 2026-09-03T02:41:25.760Z
Atualizado: 2026-09-05T20:07:44.765Z
História do Usuário
Como desenvolvedor e analista de QA,
quero implementar testes unitários automatizados para o serviço de usuário,
para comprovar a integridade das validações de cadastro e garantir que regressões não ocorram.

BDD
Cenário: Validação unitária do fluxo de criação de usuário com sucesso
Dado que a camada de serviço de usuário é instanciada com os mocks do repositório
Quando o método de criação de usuário for acionado com dados válidos
Então a senha deve ser codificada via PasswordEncoder
E o método save do repositório mockado deve ser executado exatamente uma vez.

Critérios de Aceitação Associados
\- CA01 — A camada de serviço de usuário deve possuir cobertura com testes unitários cobrindo cadastro com dados válidos e bloqueio de e-mails duplicados com Mockito.

###### [TASK] #31378 — [testes] Implementar teste unitário de sucesso para criação de usuário

- Pai: 30822
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31378/activity)

**Descrição:**

Criado: 2026-09-05T20:05:03.249Z
Atualizado: 2026-09-05T20:05:03.283Z
Criar suite UsuarioServiceTest com JUnit 5 validando fluxo de cadastro positivo com repositório mockado.

###### [TASK] #31393 — [testes] Implementar teste unitário para bloqueio de e-mail duplicado

- Pai: 30822
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31393/activity)

**Descrição:**

Criado: 2026-09-05T20:07:44.693Z
Atualizado: 2026-09-05T20:07:44.727Z
Escrever cenário simulando e-mail já existente no mock e checar lançamento da exceção tratada.

###### [USER STORY] #30823 — US107 - Teste unitário de regras de status e transições do Combinado

- Pai: 30699
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30823/activity)

**Descrição:**

Criado: 2026-09-03T02:42:16.114Z
Atualizado: 2026-09-05T20:08:51.521Z
História do Usuário
Como analista de QA,
quero implementar testes unitários para a máquina de estados do Combinado,
para certificar que transições de estado inválidas sejam bloqueadas pelas regras de negócio.

BDD
Cenário: Bloqueio de transição ilegal de status
Dado que um combinado está em estado de rascunho
Quando for solicitada a alteração direta para o estado Concluído
Então o serviço de validação deve lançar uma exceção de estado ilegal
E o registro não deve ter seu status alterado no banco de dados.

Critérios de Aceitação Associados
\- CA02 — As regras de negócio da máquina de estados do combinado devem ser validadas unitariamente, garantindo transições válidas e bloqueio de estados ilegais.

###### [TASK] #31395 — [testes] Criar testes unitários para a máquina de estados do Combinado

- Pai: 30823
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31395/activity)

**Descrição:**

Criado: 2026-09-05T20:08:27.896Z
Atualizado: 2026-09-05T20:08:27.995Z
Testar transições legais: Rascunho -&gt; Aguardando Confirmação -&gt; Ativo.

###### [TASK] #31399 — [testes] Testar cenários de transição de status proibida no backend

- Pai: 30823
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31399/activity)

**Descrição:**

Criado: 2026-09-05T20:08:51.431Z
Atualizado: 2026-09-05T20:08:51.480Z
Cobrir bloqueios de transição inválida (ex: Rascunho para Concluído) com asserção de exceções de negócio.

###### [USER STORY] #30824 — US108 - Teste unitário de regras de Convite (aceite, recusa, atualização de status)

- Pai: 30699
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30824/activity)

**Descrição:**

Criado: 2026-09-03T02:42:51.008Z
Atualizado: 2026-09-05T20:10:44.261Z
História do Usuário
Como analista de QA,
quero criar testes unitários para os métodos de aceite e recusa de convites,
para validar o comportamento esperado nas mudanças de situação dos participantes.

BDD
Cenário: Teste unitário de recusa de convite com justificativa
Dado que o serviço de convite é executado com as dependências mockadas
Quando o método de recusa for chamado com uma justificativa válida
Então o status do convite deve ser atualizado para Recusado
E o repositório deve salvar a data/hora e o texto da recusa fornecido.

Critérios de Aceitação Associados
\- CA03 — Os serviços de manipulação de convites devem ser testados unitariamente para operações de aceite, recusa e atualização de timestamps.

###### [TASK] #31400 — [testes] Escrever testes unitários para aceite de convite com Mockito

- Pai: 30824
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31400/activity)

**Descrição:**

Criado: 2026-09-05T20:09:28.492Z
Atualizado: 2026-09-05T20:09:28.539Z
Testar alterações de estado e persistência correta de data/hora no ConviteService.

###### [TASK] #31404 — [testes] Escrever testes unitários para recusa de convite

- Pai: 30824
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31404/activity)

**Descrição:**

Criado: 2026-09-05T20:10:44.185Z
Atualizado: 2026-09-05T20:10:44.225Z
Validar armazenamento de motivo opcional e mudança de status para Recusado.

###### [USER STORY] #30825 — US109 - Teste unitário de regras de permissão

- Pai: 30699
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30825/activity)

**Descrição:**

Criado: 2026-09-03T02:43:20.930Z
Atualizado: 2026-09-05T20:13:10.652Z
História do Usuário
Como analista de QA,
quero testar unitariamente as regras de autorização de métodos,
para garantir que operações sensíveis só sejam executadas pelos proprietários legítimos dos dados.

BDD
Cenário: Teste de autorização de cancelamento por não criador
Dado um usuário participante tentando invocar o método de cancelamento do combinado
Quando a validação de propriedade do acordo for executada
Então deve ser lançada uma exceção de acesso negado
E nenhuma modificação de cancelamento deve ser persistida.

Critérios de Aceitação Associados
\- CA04 — As regras de autorização devem possuir testes de unidade comprovando o bloqueio de ações restritas a usuários sem vínculo ao combinado.

###### [TASK] #31406 — [testes] Testar bloqueio de cancelamento para usuários não criadores

- Pai: 30825
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31406/activity)

**Descrição:**

Criado: 2026-09-05T20:12:38.831Z
Atualizado: 2026-09-05T20:12:38.898Z
Escrever cenários de teste unitário validando lançamento de AccessDeniedException para usuários comuns.

###### [TASK] #31407 — [testes] Testar isolamento de visualização entre combinados de outros usuários

- Pai: 30825
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31407/activity)

**Descrição:**

Criado: 2026-09-05T20:13:10.553Z
Atualizado: 2026-09-05T20:13:10.622Z
Cobrir com JUnit o bloqueio de consulta de detalhes para quem não faz parte do acordo.

###### [CRITÉRIO DE ACEITAÇÃO] #31786 — CA-F30.1 - Testes Unitários

- Pai: 30699
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31786/activity)

**Descrição:**

Criado: 2026-09-06T00:49:59.316Z
Atualizado: 2026-09-16T02:08:34.687Z
Agrupador dos critérios de aceitação referentes aos testes unitários com JUnit e Mockito cobrindo serviços de usuários, transições de status, convites e permissões de acesso.

###### [CRITÉRIO DE ACEITAÇÃO] #31171 — CA01 — Testes Unitários de Usuários

- Pai: 31786
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31171/activity)

**Descrição:**

Criado: 2026-09-05T14:08:52.090Z
Atualizado: 2026-09-06T00:50:09.468Z
A camada de serviço de usuário deve possuir cobertura com testes unitários cobrindo cadastro com dados válidos e bloqueio de e-mails duplicados com Mockito.

###### [CRITÉRIO DE ACEITAÇÃO] #31172 — CA02 — Testes Unitários de Transições de Status

- Pai: 31786
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31172/activity)

**Descrição:**

Criado: 2026-09-05T14:27:31.052Z
Atualizado: 2026-09-06T00:50:19.411Z
As regras de negócio da máquina de estados do combinado devem ser validadas unitariamente, garantindo transições válidas e bloqueio de estados ilegais.

###### [CRITÉRIO DE ACEITAÇÃO] #31173 — CA03 — Testes Unitários do Ciclo de Convites

- Pai: 31786
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31173/activity)

**Descrição:**

Criado: 2026-09-05T14:27:56.720Z
Atualizado: 2026-09-06T00:50:28.461Z
Os serviços de manipulação de convites devem ser testados unitariamente para operações de aceite, recusa e atualização de timestamps.

###### [CRITÉRIO DE ACEITAÇÃO] #31174 — CA04 — Testes Unitários de Permissões de Acesso

- Pai: 31786
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31174/activity)

**Descrição:**

Criado: 2026-09-05T14:28:19.087Z
Atualizado: 2026-09-06T00:50:37.311Z
As regras de autorização devem possuir testes de unidade comprovando o bloqueio de ações restritas a usuários sem vínculo ao combinado.

#### [EPIC] #30646 — 07.6 — Testes Funcionais (Frontend)

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30646/activity)

**Descrição:**

Criado: 2026-09-02T21:55:16.624Z
Atualizado: 2026-09-05T20:17:54.136Z
Cypress para automação dos fluxos principais no navegador.

##### [FEATURE] #30700 — F31 — Testes Funcionais

- Pai: 30646
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30700/activity)

**Descrição:**

Criado: 2026-09-03T00:13:26.618Z
Atualizado: 2026-09-05T20:17:54.123Z
Cypress para automação dos fluxos principais no navegador.

###### [USER STORY] #30826 — US110 - Teste funcional do fluxo de cadastro e login

- Pai: 30700
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30826/activity)

**Descrição:**

Criado: 2026-09-03T02:44:22.391Z
Atualizado: 2026-09-05T20:14:15.326Z
História do Usuário
Como analista de QA,
quero criar especificações de teste automatizado no Cypress cobrindo cadastro e login,
para assegurar que o fluxo de entrada do usuário na aplicação ocorra perfeitamente no navegador.

BDD
Cenário: Automação completa de novo cadastro seguido de login
Dado que o Cypress acessa a tela de cadastro no navegador
E preenche todos os campos com dados válidos
Quando submeter o formulário de cadastro e efetuar o login com o mesmo usuário
Então o sistema deve realizar a autenticação
E deve redirecionar o navegador para o painel principal exibindo as boas-vindas.

Critérios de Aceitação Associados
\- CA01 — O fluxo completo de criação de conta no frontend seguido de login e redirecionamento para o dashboard deve ser coberto por testes automatizados com Cypress.

###### [TASK] #31409 — [testes] Configurar Cypress e comandos customizados no frontend

- Pai: 30826
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31409/activity)

**Descrição:**

Criado: 2026-09-05T20:13:40.734Z
Atualizado: 2026-09-05T20:13:40.796Z
Instalar dependências do Cypress no frontend e configurar baseUrl apontando para o servidor local.

###### [TASK] #31410 — [testes] Escrever especificação Cypress para o fluxo de cadastro e login

- Pai: 30826
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31410/activity)

**Descrição:**

Criado: 2026-09-05T20:14:15.249Z
Atualizado: 2026-09-05T20:14:15.293Z
Criar arquivo de teste E2E executando o formulário de cadastro, submissão e autenticação na sequência.

###### [USER STORY] #30827 — US111 - Teste funcional do fluxo de criação de combinado com rascunho e envio

- Pai: 30700
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30827/activity)

**Descrição:**

Criado: 2026-09-03T02:45:12.265Z
Atualizado: 2026-09-05T20:15:20.973Z
História do Usuário
Como analista de QA,
quero automatizar via Cypress os cenários de rascunho e publicação de combinados,
para garantir a confiabilidade no preenchimento de formulários e envio aos participantes.

BDD
Cenário: Automação E2E de criação e salvamento em rascunho
Dado que o usuário está autenticado na tela de novo combinado
Quando preencher os campos básicos e clicar em &#39;Salvar Rascunho&#39;
Então o card do combinado deve aparecer na lista de rascunhos
E não deve haver disparos de convite para os contatos listados.

Critérios de Aceitação Associados
\- CA02 — O fluxo de criação de combinado, salvamento parcial como rascunho, posterior edição e publicação deve ser testado de ponta a ponta no navegador.

###### [TASK] #31412 — [testes] Escrever teste Cypress para salvar e editar rascunho

- Pai: 30827
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31412/activity)

**Descrição:**

Criado: 2026-09-05T20:14:51.564Z
Atualizado: 2026-09-05T20:14:51.601Z
Automatizar preenchimento inicial, clique no botão de salvar rascunho e checar persistência na lista.

###### [TASK] #31414 — [testes] Escrever teste Cypress para finalização e envio do convite

- Pai: 30827
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31414/activity)

**Descrição:**

Criado: 2026-09-05T20:15:20.893Z
Atualizado: 2026-09-05T20:15:20.937Z
Testar publicação do rascunho e conferir alteração de status para Aguardando Confirmação no card.

###### [USER STORY] #30828 — US112 - Teste funcional do fluxo de convite, aceitação e recusa

- Pai: 30700
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30828/activity)

**Descrição:**

Criado: 2026-09-03T02:46:07.466Z
Atualizado: 2026-09-05T20:16:40.154Z
História do Usuário
Como analista de QA,
quero automatizar os fluxos de resposta de convites através do navegador,
para verificar se os estados de aceite e recusa refletem corretamente na interface.

BDD
Cenário: Teste funcional de aceite de proposta recebida
Dado que o usuário convidado acessa a tela de visualização do convite
Quando ele clicar no botão &#39;Aceitar&#39; e confirmar no modal
Então o badge do convite deve mudar para &#39;Aceito&#39;
E deve ser exibida notificação de sucesso na tela sem necessidade de reload.

Critérios de Aceitação Associados
\- CA03 — As interações de recebimento, visualização de proposta, aceite e recusa de convites com justificativa devem ser automatizadas via Cypress.

###### [TASK] #31416 — [testes] Automatizar com Cypress fluxo de aceite de convite

- Pai: 30828
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H30M
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31416/activity)

**Descrição:**

Criado: 2026-09-05T20:16:07.367Z
Atualizado: 2026-09-05T20:16:07.415Z
Simular login do participante convidado, acesso à tela de convite e submissão da confirmação de aceite.

###### [TASK] #31418 — [testes] Automatizar com Cypress fluxo de recusa de convite

- Pai: 30828
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31418/activity)

**Descrição:**

Criado: 2026-09-05T20:16:40.008Z
Atualizado: 2026-09-05T20:16:40.076Z
Simular recusa de convite inserindo justificativa opcional e verificar atualização do badge na tela.

###### [USER STORY] #30835 — US113 - Teste funcional do fluxo completo (criação → convite → aceite → conclusão)

- Pai: 30700
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30835/activity)

**Descrição:**

Criado: 2026-09-03T02:49:27.405Z
Atualizado: 2026-09-05T20:17:54.103Z
História do Usuário
Como analista de QA,
quero desenvolver uma spec Cypress que percorra o ciclo de vida integral de um combinado,
para certificar a integridade integrada de todo o sistema antes da entrega final.

BDD
Cenário: Ciclo integral de ponta a ponta de um acordo
Dado dois usuários autenticados no sistema em sessões distintas
Quando o primeiro usuário criar um combinado, adicionar o segundo e enviar o convite
E o segundo usuário aceitar a participação
E o criador finalizar o combinado clicando em &#39;Concluir&#39;
Então o status geral deve passar para &#39;Concluído&#39;
E todos os eventos devem constar ordenados na linha do tempo.

Critérios de Aceitação Associados
\- CA04 — Deve ser validada a jornada integral do sistema: cadastro dos usuários, criação do combinado, envio e aceite de convites, ativação e finalização com status Concluído.

###### [TASK] #31419 — [testes] Configurar cenário integrado E2E no Cypress para o ciclo completo

- Pai: 30835
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT3H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31419/activity)

**Descrição:**

Criado: 2026-09-05T20:17:24.327Z
Atualizado: 2026-09-05T20:17:24.377Z
Criar fixture simulando criador e convidado para executar toda a sequência de criação, envio e aceite.

###### [TASK] #31420 — [testes] Validar atualização da linha do tempo e status CONCLUÍDO via Cypress

- Pai: 30835
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31420/activity)

**Descrição:**

Criado: 2026-09-05T20:17:54.016Z
Atualizado: 2026-09-05T20:17:54.062Z
Automatizar clique de encerramento do acordo e testar checagem dos registros na linha do tempo.

###### [CRITÉRIO DE ACEITAÇÃO] #31787 — CA-F31.1 - Testes Funcionais

- Pai: 30700
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31787/activity)

**Descrição:**

Criado: 2026-09-06T00:51:42.709Z
Atualizado: 2026-09-16T02:08:36.738Z
Agrupador dos critérios de aceitação referentes aos testes automatizados de ponta a ponta (E2E) com Cypress, cobrindo cadastro, login, rascunhos, convites e ciclo completo.

###### [CRITÉRIO DE ACEITAÇÃO] #31175 — CA01 — Testes E2E de Cadastro e Login

- Pai: 31787
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31175/activity)

**Descrição:**

Criado: 2026-09-05T14:30:46.380Z
Atualizado: 2026-09-06T00:51:56.658Z
O fluxo completo de criação de conta no frontend seguido de login e redirecionamento para o dashboard deve ser coberto por testes automatizados com Cypress.

###### [CRITÉRIO DE ACEITAÇÃO] #31176 — CA02 — Testes E2E de Criação e Rascunho

- Pai: 31787
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31176/activity)

**Descrição:**

Criado: 2026-09-05T14:31:06.755Z
Atualizado: 2026-09-06T00:52:11.211Z
O fluxo de criação de combinado, salvamento parcial como rascunho, posterior edição e publicação deve ser testado de ponta a ponta no navegador.

###### [CRITÉRIO DE ACEITAÇÃO] #31177 — CA03 — Testes E2E de Resposta a Convites

- Pai: 31787
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31177/activity)

**Descrição:**

Criado: 2026-09-05T14:31:28.764Z
Atualizado: 2026-09-06T00:52:23.291Z
As interações de recebimento, visualização de proposta, aceite e recusa de convites com justificativa devem ser automatizadas via Cypress.

###### [CRITÉRIO DE ACEITAÇÃO] #31178 — CA04 — Testes E2E do Ciclo Completo do Combinado

- Pai: 31787
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31178/activity)

**Descrição:**

Criado: 2026-09-05T14:31:46.401Z
Atualizado: 2026-09-06T00:52:34.971Z
Deve ser validada a jornada integral do sistema: cadastro dos usuários, criação do combinado, envio e aceite de convites, ativação e finalização com status Concluído.

#### [EPIC] #30647 — 07.7 — Documentação da API

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30647/activity)

**Descrição:**

Criado: 2026-09-02T21:55:30.142Z
Atualizado: 2026-09-16T22:46:12.779Z
Swagger/OpenAPI para documentar os endpoints REST.

##### [FEATURE] #30701 — F32 — Documentação da API

- Pai: 30647
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30701/activity)

**Descrição:**

Criado: 2026-09-03T00:13:51.463Z
Atualizado: 2026-09-16T22:46:12.761Z
Swagger/OpenAPI para documentar os endpoints REST.

###### [USER STORY] #30798 — US114 - Configuração do Swagger/OpenAPI

- Pai: 30701
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30798/activity)

**Descrição:**

Criado: 2026-09-03T02:18:02.835Z
Atualizado: 2026-09-16T22:46:12.750Z
História do Usuário
Como desenvolvedor da equipe,
quero integrar a biblioteca SpringDoc OpenAPI na aplicação backend,
para disponibilizar a interface gráfica do Swagger UI acessível via navegador.

BDD
Cenário: Acesso à documentação Swagger UI
Dado que o servidor backend está em execução
Quando o desenvolvedor acessar a rota /swagger-ui/index.html
Então a interface gráfica do Swagger UI deve carregar sem bloqueio de segurança
E deve listar as informações gerais e versões da API do Combinado.

Critérios de Aceitação Associados
\- CA01 — A documentação interativa da API deve ser disponibilizada publicamente via interface gráfica do Swagger UI no endpoint /swagger-ui.html sem barreiras de autenticação.

###### [TASK] #31421 — [back-end] Adicionar dependência SpringDoc OpenAPI e configurar OpenApiConfig

- Pai: 30798
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31421/activity)

**Descrição:**

Criado: 2026-09-05T20:18:30.868Z
Atualizado: 2026-09-16T22:46:12.717Z
Inserir lib springdoc-openapi no pom.xml e configurar bean com metadados e suporte a Bearer Token.

###### [TASK] #31422 — [back-end] Liberar rotas do Swagger no SecurityFilterChain

- Pai: 30798
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-14
- Prazo: 2026-09-14
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31422/activity)

**Descrição:**

Criado: 2026-09-05T20:19:00.736Z
Atualizado: 2026-09-16T22:46:36.156Z
Inserir rotas /v3/api-docs/\*\* e /swagger-ui/\*\* nas regras de acesso público do Spring Security.

###### [USER STORY] #30803 — US115 - Documentação de endpoints de usuário (cadastro, login, per l)

- Pai: 30701
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30803/activity)

**Descrição:**

Criado: 2026-09-03T02:21:24.891Z
Atualizado: 2026-09-05T20:19:46.253Z
História do Usuário
Como consumidor da API,
quero consultar as operações de usuário e autenticação documentadas no Swagger,
para entender as estruturas de entrada DTO, respostas de sucesso e mensagens de erro esperadas.

BDD
Cenário: Consulta das anotações OpenAPI dos endpoints de usuário
Dado que a página do Swagger UI está aberta
Quando o desenvolvedor consultar as seções de Usuário e Autenticação
Então os endpoints de cadastro e login devem exibir suas descrições
E devem indicar claramente os retornos HTTP 200, 201, 400 e 401 com exemplos de JSON.

Critérios de Aceitação Associados
\- CA02 — As rotas de cadastro, login e gerenciamento de perfil devem conter anotações OpenAPI com descrições de métodos, códigos de retorno e schemas JSON.

###### [TASK] #31423 — [back-end] Anotar endpoints de autenticação e usuário com OpenAPI

- Pai: 30803
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31423/activity)

**Descrição:**

Criado: 2026-09-05T20:19:46.159Z
Atualizado: 2026-09-05T20:19:46.215Z
Incluir anotações @Operation e @ApiResponses nos controladores de autenticação e usuário.

###### [TASK] #31424 — [po/validação] Validar clareza da documentação de endpoints de usuário

- Pai: 30803
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31424/activity)

**Descrição:**

Criado: 2026-09-05T20:20:20.192Z
Atualizado: 2026-09-05T20:20:20.229Z
Conferir se as especificações dos métodos e schemas descritos no Swagger estão alinhadas aos requisitos.

###### [USER STORY] #30806 — US116 - Documentação de endpoints de Combinado (criação, rascunho, listagem)

- Pai: 30701
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30806/activity)

**Descrição:**

Criado: 2026-09-03T02:22:38.761Z
Atualizado: 2026-09-05T20:21:41.345Z
História do Usuário
Como desenvolvedor consumidor da API,
quero consultar a documentação OpenAPI das rotas de combinados,
para integrar telas de rascunhos e acordos com exatidão sobre os parâmetros esperados.

BDD
Cenário: Documentação dos métodos do CombinadoController
Dado que o Swagger UI é aberto na seção &#39;Combinados&#39;
Quando o método POST /api/combinados for inspecionado
Então deve exibir os campos obrigatórios do corpo da requisição (título, categoria, prazo)
E exemplificar os códigos de status de retorno esperados.

Critérios de Aceitação Associados
\- CA03 — Os métodos de criação, salvamento de rascunho e consulta de combinados devem detalhar parâmetros de busca, payloads de entrada e status de resposta no Swagger.

###### [TASK] #31425 — [back-end] Inserir anotações Swagger nos endpoints de criação e rascunho

- Pai: 30806
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31425/activity)

**Descrição:**

Criado: 2026-09-05T20:21:04.726Z
Atualizado: 2026-10-22T01:32:15.921Z
Documentar anotações @Operation, @ApiResponses e payloads de erro para criação e salvamento de rascunho.

###### [TASK] #31426 — [back-end] Documentar rotas de listagem de combinados no Swagger

- Pai: 30806
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31426/activity)

**Descrição:**

Criado: 2026-09-05T20:21:41.255Z
Atualizado: 2026-10-22T01:32:27.246Z
Mapear parâmetros de busca, paginação e schemas de retorno no controlador de combinados.

###### [USER STORY] #30808 — US117 - Documentação de endpoints de Convite e Histórico

- Pai: 30701
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30808/activity)

**Descrição:**

Criado: 2026-09-03T02:23:04.553Z
Atualizado: 2026-09-05T20:23:18.076Z
História do Usuário
Como integrador do sistema,
quero dispor da documentação dos endpoints de convites e histórico,
para integrar as interfaces de linha do tempo e confirmação com os contratos definidos.

BDD
Cenário: Exibição dos contratos de histórico e convites
Dado que a documentação da API está aberta
Quando navegar para os controladores de Histórico e Convites
Então devem constar descritos os métodos de resposta de convite
E a estrutura do array JSON retornado na rota da linha do tempo.

Critérios de Aceitação Associados
\- CA04 — Os endpoints relacionados ao ciclo de convites e à linha do tempo cronológica devem apresentar esquemas de dados e modelos de resposta documentados no OpenAPI.

###### [TASK] #31427 — [back-end] Documentar endpoints de convite no Swagger

- Pai: 30808
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31427/activity)

**Descrição:**

Criado: 2026-09-05T20:22:44.475Z
Atualizado: 2026-09-11T20:23:53.009Z
Mapear parâmetros de envio de convite, rotas de aceite/recusa e esquemas de dados.

###### [TASK] #31428 — [back-end] Documentar rota de histórico e linha do tempo no Swagger

- Pai: 30808
- Status: New
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31428/activity)

**Descrição:**

Criado: 2026-09-05T20:23:18.005Z
Atualizado: 2026-09-11T20:23:42.224Z
Descrever modelo de saída do evento cronológico e tipos aceitos no Swagger UI.

###### [TASK] #31788 — CA - Documentação da API

- Pai: 30701
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31788/activity)

**Descrição:**

Criado: 2026-09-06T00:53:23.591Z
Atualizado: 2026-09-06T00:54:11.393Z
Agrupador dos critérios de aceitação referentes à disponibilização do Swagger UI e à especificação OpenAPI dos contratos de endpoints de usuários, combinados, convites e histórico.

###### [CRITÉRIO DE ACEITAÇÃO] #31203 — CA01 — Disponibilização da Interface Swagger UI

- Pai: 31788
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31203/activity)

**Descrição:**

Criado: 2026-09-05T17:01:13.277Z
Atualizado: 2026-09-06T00:53:35.542Z
A documentação interativa da API deve ser disponibilizada publicamente via interface gráfica do Swagger UI no endpoint /swagger-ui.html sem barreiras de autenticação.

###### [CRITÉRIO DE ACEITAÇÃO] #31206 — CA02 — Especificação de Endpoints de Usuário

- Pai: 31788
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31206/activity)

**Descrição:**

Criado: 2026-09-05T17:02:30.807Z
Atualizado: 2026-09-06T00:53:47.419Z
As rotas de cadastro, login e gerenciamento de perfil devem conter anotações OpenAPI com descrições de métodos, códigos de retorno e schemas JSON.

###### [CRITÉRIO DE ACEITAÇÃO] #31207 — CA03 — Especificação de Endpoints de Combinado

- Pai: 31788
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31207/activity)

**Descrição:**

Criado: 2026-09-05T17:02:59.587Z
Atualizado: 2026-09-06T00:53:59.641Z
Os métodos de criação, salvamento de rascunho e consulta de combinados devem detalhar parâmetros de busca, payloads de entrada e status de resposta no Swagger.

###### [CRITÉRIO DE ACEITAÇÃO] #31209 — CA04 — Especificação de Convites e Histórico

- Pai: 31788
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31209/activity)

**Descrição:**

Criado: 2026-09-05T17:03:35.231Z
Atualizado: 2026-09-06T00:54:11.429Z
Os endpoints relacionados ao ciclo de convites e à linha do tempo cronológica devem apresentar esquemas de dados e modelos de resposta documentados no OpenAPI.

#### [EPIC] #30648 — 07.8 — Configuração de Ambiente

- Pai: 30584
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-09
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30648/activity)

**Descrição:**

Criado: 2026-09-02T21:55:46.335Z
Atualizado: 2026-09-16T22:50:33.249Z
Definir ambientes de desenvolvimento, homologação e produção.

##### [FEATURE] #30702 — F33 — Configuração de Ambiente

- Pai: 30648
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: José Correia da Cruz Júnior
- Início: 2026-09-09
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30702/activity)

**Descrição:**

Criado: 2026-09-03T00:14:18.535Z
Atualizado: 2026-09-16T22:50:33.237Z
Definir ambientes de desenvolvimento, homologação e produção.

###### [USER STORY] #30772 — US118 -  Definição de ambiente de desenvolvimento

- Pai: 30702
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30772/activity)

**Descrição:**

Criado: 2026-09-03T02:07:49.540Z
Atualizado: 2026-09-15T22:32:31.410Z
História do Usuário
Como desenvolvedor da equipe,
quero consultar um guia detalhado de configuração do ambiente no repositório,
para preparar minha máquina local de desenvolvimento de maneira padronizada e sem conflitos de versão.

BDD
Cenário: Configuração padronizada da máquina local
Dado que um membro da equipe realiza o clone do projeto
Quando seguir as orientações descritas no README.md
Então ele deve conseguir instalar as dependências necessárias
E compilar e executar o frontend e backend com versões de ambiente compatíveis.

Critérios de Aceitação Associados
\- CA01 — O repositório deve conter instruções documentadas no README para que qualquer membro clone o projeto e configure dependências de desenvolvimento sem falhas.

###### [TASK] #31429 — [devops] Redigir especificações e guia de setup no README

- Pai: 30772
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31429/activity)

**Descrição:**

Criado: 2026-09-05T20:24:17.754Z
Atualizado: 2026-09-15T22:28:05.965Z
Documentar versões mínimas exigidas (JDK 17+, Node 18+, PostgreSQL) e comandos de execução no README.md.

###### [TASK] #31430 — [devops] Documentar padrões de branches e fluxo de Git da equipe

- Pai: 30772
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31430/activity)

**Descrição:**

Criado: 2026-09-05T20:24:44.691Z
Atualizado: 2026-09-15T22:32:54.629Z
Registrar regras de pull request, revisões de código e estratégia de branches do time.

###### [USER STORY] #30776 — US119 - Configuração de variáveis de ambiente (banco, JWT)

- Pai: 30702
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-09
- Prazo: 2026-09-11
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30776/activity)

**Descrição:**

Criado: 2026-09-03T02:08:49.891Z
Atualizado: 2026-09-16T22:50:33.221Z
História do Usuário
Como desenvolvedor,
quero carregar dados sensíveis e credenciais através de variáveis de ambiente,
para proteger as chaves secretas do JWT e as senhas do banco de dados contra vazamentos no Git.

BDD
Cenário: Carregamento de credenciais via variáveis locais
Dado que as propriedades da aplicação utilizam referências a variáveis de ambiente
Quando a aplicação backend for iniciada pelo desenvolvedor
Então os valores locais de banco e segredo JWT devem ser carregados
E nenhum arquivo contendo senhas reais deve ser versionado no repositório.

Critérios de Aceitação Associados
\- CA02 — Senhas de banco de dados e chaves de criptografia JWT devem ser carregadas por variáveis de ambiente, sem exposição direta no código-fonte versionado.

###### [TASK] #31432 — [back-end] Parametrizar properties com variáveis de ambiente no backend

- Pai: 30776
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-09
- Prazo: 2026-09-09
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31432/activity)

**Descrição:**

Criado: 2026-09-05T20:25:18.815Z
Atualizado: 2026-09-16T22:50:33.175Z
Mapear parâmetros DB\_URL, DB\_USER, DB\_PASSWORD e JWT\_SECRET no application.properties do Spring Boot.

###### [TASK] #31433 — [devops] Criar templates de variáveis (.env.example) e atualizar .gitignore

- Pai: 30776
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-11
- Prazo: 2026-09-11
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31433/activity)

**Descrição:**

Criado: 2026-09-05T20:25:49.377Z
Atualizado: 2026-09-15T21:12:24.625Z
Disponibilizar modelo das variáveis necessárias e verificar exclusão de arquivos sensíveis no gitignore.

###### [USER STORY] #30781 — US120 - Configuração de scripts de inicialização

- Pai: 30702
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30781/activity)

**Descrição:**

Criado: 2026-09-03T02:11:22.754Z
Atualizado: 2026-09-15T22:02:34.831Z
História do Usuário
Como desenvolvedor,
quero utilizar scripts automatizados de inicialização,
para subir o banco de dados PostgreSQL local e executar a aplicação sem necessidade de etapas manuais demoradas.

BDD
Cenário: Inicialização automatizada da base de desenvolvimento
Dado que o desenvolvedor precisa iniciar o ambiente de desenvolvimento local
Quando ele executar o comando do script de inicialização do banco
Então o serviço do PostgreSQL deve ser iniciado na porta padrão
E a base de dados do projeto Combinado deve estar pronta para receber conexões.

Critérios de Aceitação Associados
\- CA03 — A equipe deve dispor de scripts ou arquivos de configuração que permitam inicializar os serviços de apoio e o banco PostgreSQL local de maneira rápida.

###### [TASK] #31435 — [banco] Criar arquivo de inicialização do banco PostgreSQL local

- Pai: 30781
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31435/activity)

**Descrição:**

Criado: 2026-09-05T20:26:40.576Z
Atualizado: 2026-09-15T22:22:13.266Z
Criar arquivo compose/script para inicialização simplificada da base PostgreSQL local da equipe.

###### [TASK] #31438 — [front-end] Padronizar scripts de execução no package.json

- Pai: 30781
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: 2026-09-15
- Prazo: 2026-09-15
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31438/activity)

**Descrição:**

Criado: 2026-09-05T20:27:16.030Z
Atualizado: 2026-09-15T22:02:34.778Z
Organizar scripts de start, build e testes no package.json para facilitar a execução pelos integrantes.

###### [USER STORY] #30788 — US121 - Configuração do ambiente de homologação

- Pai: 30702
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/30788/activity)

**Descrição:**

Criado: 2026-09-03T02:14:37.811Z
Atualizado: 2026-09-16T02:05:00.487Z
História do Usuário
Como desenvolvedor DevOps,
quero padronizar o perfil de build para ambiente de homologação,
para permitir a geração de artefatos executáveis e testes em condições similares às de produção.

BDD
Cenário: Build da aplicação em profile de homologação
Dado que a aplicação está pronta para o empacotamento da versão de teste
Quando for disparado o script de build com profile de homologação
Então deve ser gerado o pacote sem dependências de debug
E a aplicação deve conectar à base de homologação com CORS e rotas ajustadas.

Critérios de Aceitação Associados
\- CA04 — A aplicação deve possuir perfil de execução configurado para empacotamento e testes finais em ambiente de homologação.

###### [TASK] #31441 — [devops] Criar profile de produção e homologação no backend

- Pai: 30788
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT2H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31441/activity)

**Descrição:**

Criado: 2026-09-05T20:27:54.225Z
Atualizado: 2026-10-24T17:22:46.739Z
Configurar application-prod.properties e políticas de CORS para ambiente de testes finais.

###### [TASK] #31759 — [devops] Configurar build otimizado do frontend para homologação

- Pai: 30788
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31759/activity)

**Descrição:**

Criado: 2026-09-06T00:17:53.547Z
Atualizado: 2026-10-24T17:23:00.188Z
Ajustar comandos de build e variáveis de ambiente para publicação e testes do pacote do cliente web.

###### [CRITÉRIO DE ACEITAÇÃO] #31789 — CA-F33.1 - Configuração de Ambiente

- Pai: 30702
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31789/activity)

**Descrição:**

Criado: 2026-09-06T00:58:48.647Z
Atualizado: 2026-09-16T02:08:38.760Z
Agrupador dos critérios de aceitação referentes às instruções de setup, segurança de variáveis sensíveis, scripts de inicialização do PostgreSQL e ambiente de homologação.

###### [CRITÉRIO DE ACEITAÇÃO] #31213 — CA01 — Guia de Setup e Instruções de Ambiente

- Pai: 31789
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31213/activity)

**Descrição:**

Criado: 2026-09-05T17:14:28.284Z
Atualizado: 2026-09-06T00:59:03.559Z
O repositório deve conter instruções documentadas no README para que qualquer membro clone o projeto e configure dependências de desenvolvimento sem falhas.

###### [CRITÉRIO DE ACEITAÇÃO] #31214 — CA02 — Isolamento e Segurança de Variáveis Sensíveis

- Pai: 31789
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31214/activity)

**Descrição:**

Criado: 2026-09-05T17:14:57.442Z
Atualizado: 2026-09-06T00:59:18.633Z
Senhas de banco de dados e chaves de criptografia JWT devem ser carregadas por variáveis de ambiente, sem exposição direta no código-fonte versionado.

###### [CRITÉRIO DE ACEITAÇÃO] #31215 — CA03 — Scripts e Automação de Inicialização

- Pai: 31789
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31215/activity)

**Descrição:**

Criado: 2026-09-05T17:16:23.272Z
Atualizado: 2026-09-06T01:04:19.701Z
A equipe deve dispor de scripts ou arquivos de configuração que permitam inicializar os serviços de apoio e o banco PostgreSQL local de maneira rápida.

###### [CRITÉRIO DE ACEITAÇÃO] #31216 — CA04 — Padronização do Ambiente de Homologação

- Pai: 31789
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Felipe Macedo
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31216/activity)

**Descrição:**

Criado: 2026-09-05T17:16:50.788Z
Atualizado: 2026-09-06T01:04:32.416Z
A aplicação deve possuir perfil de execução configurado para empacotamento e testes finais em ambiente de homologação.

### [CRITÉRIO DE ACEITAÇÃO] #31790 — CA - Visualização de categoria e participantes confirmados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31790/activity)

**Descrição:**

Criado: 2026-09-06T00:58:51.951Z
Atualizado: 2026-09-06T00:58:52.091Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31791 — CA1 - A categoria do combinado deve ser exibida de forma destacada na interface

- Pai: 31790
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31791/activity)

**Descrição:**

Criado: 2026-09-06T00:59:37.176Z
Atualizado: 2026-09-06T00:59:37.222Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31792 — CA2 - A lista de participantes confirmados deve exibir o nome e avatar dos usuários que aceitaram

- Pai: 31790
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31792/activity)

**Descrição:**

Criado: 2026-09-06T01:04:01.316Z
Atualizado: 2026-09-06T01:04:36.336Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31793 — CA3 - Exibir mensagem indicativa caso não haja participantes confirmados além do criador

- Pai: 31790
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31793/activity)

**Descrição:**

Criado: 2026-09-06T01:04:11.774Z
Atualizado: 2026-09-06T01:04:40.609Z
(Sem descrição)

### [USER STORY] #31800 — CA - Botões de ação Aceitar/Recusar convite

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31800/activity)

**Descrição:**

Criado: 2026-09-06T02:02:27.634Z
Atualizado: 2026-09-06T02:11:29.241Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31801 — CA1 - A interface deve exibir botões visíveis para "Aceitar" e "Recusar" o convite

- Pai: 31800
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31801/activity)

**Descrição:**

Criado: 2026-09-06T02:02:47.315Z
Atualizado: 2026-09-06T02:11:16.523Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31802 — CA2 - Os botões só devem estar ativos quando o status do convite for "Pendente"

- Pai: 31800
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31802/activity)

**Descrição:**

Criado: 2026-09-06T02:03:09.573Z
Atualizado: 2026-09-06T02:11:20.889Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31803 — CA3 - Exibir estado de confirmação ou desabilitar botões ao clicar para evitar duplo clique

- Pai: 31800
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31803/activity)

**Descrição:**

Criado: 2026-09-06T02:03:22.688Z
Atualizado: 2026-09-06T02:11:24.083Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31804 — CA - Aceitação de convite com registro de data/hora

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31804/activity)

**Descrição:**

Criado: 2026-09-06T02:03:50.856Z
Atualizado: 2026-09-06T02:09:28.448Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31805 — CA1 - O clique no botão "Aceitar" deve disparar a requisição de confirmação ao backend

- Pai: 31804
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31805/activity)

**Descrição:**

Criado: 2026-09-06T02:04:07.355Z
Atualizado: 2026-09-06T02:09:50.971Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31806 — CA2 - O sistema deve registrar o timestamp (data e hora exatas) do momento do aceite

- Pai: 31804
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31806/activity)

**Descrição:**

Criado: 2026-09-06T02:04:20.719Z
Atualizado: 2026-09-06T02:09:54.866Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31807 — CA3 - A interface deve exibir um feedback visual de sucesso após a confirmação

- Pai: 31804
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31807/activity)

**Descrição:**

Criado: 2026-09-06T02:04:36.171Z
Atualizado: 2026-09-06T02:09:58.598Z
(Sem descrição)

### [USER STORY] #31808 — CA - Atualização automática do status do combinado ao aceitar

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31808/activity)

**Descrição:**

Criado: 2026-09-06T02:04:59.036Z
Atualizado: 2026-09-06T02:10:40.789Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31809 — CA1 - O status exibido na tela deve mudar de "Pendente" para "Ativo" sem recarregar a página

- Pai: 31808
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31809/activity)

**Descrição:**

Criado: 2026-09-06T02:05:13.115Z
Atualizado: 2026-09-06T02:10:21.588Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31810 — CA2 - O estado global/local no Frontend deve refletir o novo status do usuário no combinado

- Pai: 31808
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31810/activity)

**Descrição:**

Criado: 2026-09-06T02:05:22.372Z
Atualizado: 2026-09-06T02:10:25.180Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31811 — CA3 - Em caso de erro na requisição, exibir alerta e manter o estado anterior

- Pai: 31808
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31811/activity)

**Descrição:**

Criado: 2026-09-06T02:05:35.327Z
Atualizado: 2026-09-06T02:10:30.007Z
(Sem descrição)

### [USER STORY] #31812 — CA - Notificação visual ao aceitar convite

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31812/activity)

**Descrição:**

Criado: 2026-09-06T02:05:59.821Z
Atualizado: 2026-09-06T02:12:33.466Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31813 — CA1 - Exibir notificação visual (Toast/Alerta) informando o sucesso da confirmação

- Pai: 31812
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31813/activity)

**Descrição:**

Criado: 2026-09-06T02:06:15.665Z
Atualizado: 2026-09-06T02:12:37.002Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31814 — CA2 - A notificação deve desaparecer automaticamente após alguns segundos

- Pai: 31812
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31814/activity)

**Descrição:**

Criado: 2026-09-06T02:06:27.146Z
Atualizado: 2026-09-06T02:12:41.086Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31815 — CA3 - O alerta deve possuir estilo visual claro de sucesso (cor verde/ícone de check)

- Pai: 31812
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31815/activity)

**Descrição:**

Criado: 2026-09-06T02:06:42.992Z
Atualizado: 2026-09-06T02:12:45.038Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31816 — CA - Recusa de convite com alteração de status

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31816/activity)

**Descrição:**

Criado: 2026-09-06T02:07:27.941Z
Atualizado: 2026-09-06T02:08:57.408Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31817 — CA1 - O botão "Recusar" deve alterar o status do convite para "Recusado"

- Pai: 31816
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31817/activity)

**Descrição:**

Criado: 2026-09-06T02:13:18.082Z
Atualizado: 2026-09-06T02:13:18.137Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31818 — CA2 - Solicitar confirmação no modal antes de efetivar a recusa

- Pai: 31816
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31818/activity)

**Descrição:**

Criado: 2026-09-06T02:13:33.669Z
Atualizado: 2026-09-06T02:13:58.705Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31819 — CA3 - Bloquear novas ações na tela após a confirmação da recusa

- Pai: 31816
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31819/activity)

**Descrição:**

Criado: 2026-09-06T02:13:54.611Z
Atualizado: 2026-09-06T02:13:54.647Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31820 — CA - Inclusão de justificativa opcional ao recusar

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31820/activity)

**Descrição:**

Criado: 2026-09-06T02:14:50.600Z
Atualizado: 2026-09-06T02:14:50.647Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31821 — CA1 - Disponibilizar campo de texto opcional para o motivo da recusa no modal

- Pai: 31820
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31821/activity)

**Descrição:**

Criado: 2026-09-06T02:15:07.158Z
Atualizado: 2026-09-06T02:15:07.191Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31822 — CA2 - Permitir o envio da recusa mesmo com o campo de texto em branco

- Pai: 31820
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31822/activity)

**Descrição:**

Criado: 2026-09-06T02:15:21.061Z
Atualizado: 2026-09-06T02:15:21.099Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31823 — CA3 - Enviar o texto digitado junto com a requisição de recusa

- Pai: 31820
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31823/activity)

**Descrição:**

Criado: 2026-09-06T02:15:32.473Z
Atualizado: 2026-09-06T02:15:32.515Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31824 — CA - Notificação visual e atualização após recusa

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31824/activity)

**Descrição:**

Criado: 2026-09-06T02:16:14.630Z
Atualizado: 2026-09-06T02:16:14.661Z
(Sem descrição)

#### [TASK] #31825 — CA1 - Exibir notificação visual (Toast) informando que o convite foi recusado

- Pai: 31824
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31825/activity)

**Descrição:**

Criado: 2026-09-06T02:16:27.914Z
Atualizado: 2026-09-06T02:16:27.951Z
(Sem descrição)

#### [TASK] #31826 — CA2 - Atualizar o card do convite para o estado "Recusado" ou removê-lo de pendências

- Pai: 31824
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31826/activity)

**Descrição:**

Criado: 2026-09-06T02:16:37.007Z
Atualizado: 2026-09-06T02:16:37.063Z
(Sem descrição)

#### [TASK] #31827 — CA3 - Redirecionar o usuário para a lista de combinados após a notificação

- Pai: 31824
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31827/activity)

**Descrição:**

Criado: 2026-09-06T02:16:47.152Z
Atualizado: 2026-09-06T02:16:47.184Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31828 — CA - Visualização do status atual do convite

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31828/activity)

**Descrição:**

Criado: 2026-09-06T02:17:28.300Z
Atualizado: 2026-09-06T02:17:28.334Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31829 — CA1 - Exibir badges visuais com cores distintas para cada status (Pendente, Aceito, Recusado)

- Pai: 31828
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31829/activity)

**Descrição:**

Criado: 2026-09-06T02:17:46.586Z
Atualizado: 2026-09-06T02:18:05.288Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31830 — CA2 - Posicionar o indicador de status em local visível na tela de detalhes

- Pai: 31828
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31830/activity)

**Descrição:**

Criado: 2026-09-06T02:18:01.415Z
Atualizado: 2026-09-06T02:18:01.487Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31831 — CA3 - Atualizar o badge dinamicamente caso o status mude

- Pai: 31828
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31831/activity)

**Descrição:**

Criado: 2026-09-06T02:18:17.018Z
Atualizado: 2026-09-06T02:18:17.054Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31832 — CA - Histórico de alterações de status do convite

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31832/activity)

**Descrição:**

Criado: 2026-09-06T02:18:41.539Z
Atualizado: 2026-09-06T02:18:41.573Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31833 — CA1 - Exibir linha do tempo (timeline) com os eventos e datas das mudanças do convite

- Pai: 31832
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31833/activity)

**Descrição:**

Criado: 2026-09-06T02:18:56.685Z
Atualizado: 2026-09-06T02:18:56.723Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31834 — CA2 - Identificar a data, a hora e o nome do usuário responsável por cada alteração

- Pai: 31832
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31834/activity)

**Descrição:**

Criado: 2026-09-06T02:19:07.647Z
Atualizado: 2026-09-06T02:19:07.692Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31835 — CA3 - Apresentar os registros em ordem cronológica

- Pai: 31832
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31835/activity)

**Descrição:**

Criado: 2026-09-06T02:19:18.242Z
Atualizado: 2026-09-06T02:19:18.279Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31836 — CA - Cancelamento ou reenvio de convite pendente pelo criador

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31836/activity)

**Descrição:**

Criado: 2026-09-06T02:19:47.851Z
Atualizado: 2026-09-06T02:19:47.896Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31837 — CA1 - Exibir botões "Reenviar Convite" e "Cancelar Convite" apenas para o criador em convites pendentes

- Pai: 31836
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31837/activity)

**Descrição:**

Criado: 2026-09-06T02:20:06.619Z
Atualizado: 2026-09-06T02:20:06.661Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31838 — CA2 - Pedir confirmação em modal antes de cancelar o convite

- Pai: 31836
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31838/activity)

**Descrição:**

Criado: 2026-09-06T02:20:24.249Z
Atualizado: 2026-09-06T02:20:24.284Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31839 — CA3 - Emitir notificação visual de sucesso após o reenvio do convite

- Pai: 31836
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31839/activity)

**Descrição:**

Criado: 2026-09-06T02:20:39.353Z
Atualizado: 2026-09-06T02:20:39.393Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31840 — CA - Listagem de combinados criados pelo usuário

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31840/activity)

**Descrição:**

Criado: 2026-09-06T02:21:02.482Z
Atualizado: 2026-09-06T02:21:02.525Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31841 — CA1 - Listar todos os combinados em que o usuário logado figura como criador

- Pai: 31840
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31841/activity)

**Descrição:**

Criado: 2026-09-06T02:21:20.325Z
Atualizado: 2026-09-06T02:21:20.362Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31842 — CA2 - Exibir cada item em formato de card/linha com título e categoria

- Pai: 31840
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31842/activity)

**Descrição:**

Criado: 2026-09-06T02:21:33.615Z
Atualizado: 2026-09-06T02:21:33.649Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31843 — CA3 - Exibir indicador de carregamento (loading) durante a busca de dados

- Pai: 31840
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31843/activity)

**Descrição:**

Criado: 2026-09-06T02:21:44.316Z
Atualizado: 2026-09-06T02:21:44.367Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31844 — CA - Exibição de informações resumidas em cada card

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31844/activity)

**Descrição:**

Criado: 2026-09-06T02:22:11.484Z
Atualizado: 2026-09-06T02:22:11.513Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31845 — CA1 - Exibir no card: título, categoria, prazo/data e total de participantes

- Pai: 31844
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31845/activity)

**Descrição:**

Criado: 2026-09-06T02:22:27.366Z
Atualizado: 2026-09-06T02:22:27.430Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31846 — CA2 - Incluir badge visual simples com o status geral do combinado

- Pai: 31844
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31846/activity)

**Descrição:**

Criado: 2026-09-06T02:22:43.033Z
Atualizado: 2026-09-06T02:22:43.096Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31847 — CA3 - Garantir layout responsivo para desktop e mobile

- Pai: 31844
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31847/activity)

**Descrição:**

Criado: 2026-09-06T02:22:55.734Z
Atualizado: 2026-09-06T02:22:55.769Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31848 — CA - Paginação/Scroll infinito na lista de criados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31848/activity)

**Descrição:**

Criado: 2026-09-06T02:23:19.071Z
Atualizado: 2026-09-06T02:23:19.102Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31849 — CA1 - Implementar paginação ou rolagem infinita para listas extensas de combinados

- Pai: 31848
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31849/activity)

**Descrição:**

Criado: 2026-09-06T02:23:49.611Z
Atualizado: 2026-09-06T02:23:49.641Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31850 — CA2 - Fornecer botões de navegação de página ou indicador de carregamento ao rolar

- Pai: 31848
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31850/activity)

**Descrição:**

Criado: 2026-09-06T02:24:00.155Z
Atualizado: 2026-09-06T02:24:00.200Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31851 — CA3 - Carregar novos lotes de dados sem recarregar a página

- Pai: 31848
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31851/activity)

**Descrição:**

Criado: 2026-09-06T02:24:10.053Z
Atualizado: 2026-09-06T02:24:10.083Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31852 — CA - Ordenação dos combinados criados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31852/activity)

**Descrição:**

Criado: 2026-09-06T02:24:39.630Z
Atualizado: 2026-09-06T02:24:39.662Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31853 — CA1 - Disponibilizar campo de seleção para ordenar por "Mais recentes", "Mais antigos" ou "A-Z"

- Pai: 31852
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31853/activity)

**Descrição:**

Criado: 2026-09-06T02:24:56.423Z
Atualizado: 2026-09-06T02:24:56.458Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31854 — CA2 - Reorganizar os cards na tela imediatamente após selecionar a ordenação

- Pai: 31852
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31854/activity)

**Descrição:**

Criado: 2026-09-06T02:25:12.509Z
Atualizado: 2026-09-06T02:25:12.605Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31855 — CA3 - Definir "Mais recentes" como a ordenação padrão inicial

- Pai: 31852
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31855/activity)

**Descrição:**

Criado: 2026-09-06T02:25:25.765Z
Atualizado: 2026-09-06T02:25:25.797Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31856 — CA - Mensagem de estado vazio (Empty State)

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31856/activity)

**Descrição:**

Criado: 2026-09-06T02:25:48.753Z
Atualizado: 2026-09-06T02:25:48.784Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31857 — CA1 - Exibir mensagem amigável caso o usuário não tenha criado nenhum combinado

- Pai: 31856
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31857/activity)

**Descrição:**

Criado: 2026-09-06T02:26:29.735Z
Atualizado: 2026-09-06T02:26:29.775Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31858 — CA2 - Apresentar o texto: "Você ainda não criou nenhum combinado"

- Pai: 31856
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31858/activity)

**Descrição:**

Criado: 2026-09-06T02:26:40.002Z
Atualizado: 2026-09-06T02:26:40.062Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31859 — CA3 - Incluir botão em destaque direcionando para a criação do primeiro combinado

- Pai: 31856
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31859/activity)

**Descrição:**

Criado: 2026-09-06T02:26:50.556Z
Atualizado: 2026-09-06T02:26:50.589Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31860 — CA - Listagem de combinados recebidos

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31860/activity)

**Descrição:**

Criado: 2026-09-06T02:27:22.103Z
Atualizado: 2026-09-06T02:27:22.187Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31861 — CA1 - Renderizar todos os combinados onde o usuário foi adicionado como participante

- Pai: 31860
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31861/activity)

**Descrição:**

Criado: 2026-09-06T02:27:34.800Z
Atualizado: 2026-09-06T02:27:34.836Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31862 — CA2 - Identificar em cada card o nome do usuário que criou e enviou o convite

- Pai: 31860
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31862/activity)

**Descrição:**

Criado: 2026-09-06T02:27:53.290Z
Atualizado: 2026-09-06T02:27:53.380Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31863 — CA3 - Exibir componente de loading durante o carregamento

- Pai: 31860
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31863/activity)

**Descrição:**

Criado: 2026-09-06T02:28:03.292Z
Atualizado: 2026-09-06T02:28:03.332Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31864 — CA - Indicadores visuais de convites pendentes

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31864/activity)

**Descrição:**

Criado: 2026-09-06T02:28:30.149Z
Atualizado: 2026-09-06T02:28:30.186Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31865 — CA1 - Aplicar destaque visual (borda/selo "Novo") para convites com status "Pendente"

- Pai: 31864
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31865/activity)

**Descrição:**

Criado: 2026-09-06T02:28:40.020Z
Atualizado: 2026-09-06T02:28:53.773Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31866 — CA2 - Remover o destaque assim que o convite for respondido

- Pai: 31864
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31866/activity)

**Descrição:**

Criado: 2026-09-06T02:29:04.536Z
Atualizado: 2026-09-06T02:29:04.580Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31867 — CA3 - Exibir o número total de convites pendentes em um badge no menu/topo

- Pai: 31864
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31867/activity)

**Descrição:**

Criado: 2026-09-06T02:29:16.806Z
Atualizado: 2026-09-06T02:29:16.852Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31868 — CA - Ações rápidas diretamente na lista de recebidos

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31868/activity)

**Descrição:**

Criado: 2026-09-06T02:29:39.074Z
Atualizado: 2026-09-06T02:29:39.105Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31869 — CA1 - Incluir botões rápidos de "Aceitar" e "Recusar" direto nos cards da listagem

- Pai: 31868
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31869/activity)

**Descrição:**

Criado: 2026-09-06T02:30:13.516Z
Atualizado: 2026-09-06T02:30:13.567Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31870 — CA2 - Atualizar o status do card na própria lista logo após o clique

- Pai: 31868
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31870/activity)

**Descrição:**

Criado: 2026-09-06T02:30:25.439Z
Atualizado: 2026-09-06T02:30:25.470Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31871 — CA3 - Exibir modal de confirmação ao clicar no botão rápido de recusa

- Pai: 31868
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31871/activity)

**Descrição:**

Criado: 2026-09-06T02:30:36.484Z
Atualizado: 2026-09-06T02:30:36.515Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31872 — CA - Mensagem de estado vazio para recebidos

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31872/activity)

**Descrição:**

Criado: 2026-09-06T02:30:55.209Z
Atualizado: 2026-09-06T02:30:55.245Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31873 — CA1 - Exibir estado vazio caso o usuário não tenha convites recebidos

- Pai: 31872
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31873/activity)

**Descrição:**

Criado: 2026-09-06T02:31:07.671Z
Atualizado: 2026-09-06T02:31:11.208Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31874 — CA2 - Exibir o texto: "Você não possui convites de combinados no momento"

- Pai: 31872
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31874/activity)

**Descrição:**

Criado: 2026-09-06T02:31:21.089Z
Atualizado: 2026-09-06T02:31:21.124Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31875 — CA3 - Apresentar ilustração adequada ao contexto

- Pai: 31872
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31875/activity)

**Descrição:**

Criado: 2026-09-06T02:31:31.492Z
Atualizado: 2026-09-06T02:31:31.533Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31876 — CA - Exibição de informações gerais do combinado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31876/activity)

**Descrição:**

Criado: 2026-09-06T02:31:58.121Z
Atualizado: 2026-09-06T02:31:58.150Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31877 — CA1 - Exibir título, descrição, categoria, data de criação e nome do criador na tela de detalhes

- Pai: 31876
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31877/activity)

**Descrição:**

Criado: 2026-09-06T02:32:12.564Z
Atualizado: 2026-09-06T02:32:12.592Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31878 — CA2 - Organizar as informações em blocos visuais claros e legíveis

- Pai: 31876
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31878/activity)

**Descrição:**

Criado: 2026-09-06T02:32:24.811Z
Atualizado: 2026-09-06T02:32:24.859Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31879 — CA3 - Disponibilizar botão "Voltar" funcional no topo da tela

- Pai: 31876
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31879/activity)

**Descrição:**

Criado: 2026-09-06T02:32:35.109Z
Atualizado: 2026-09-06T02:32:35.178Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31880 — US - Lista detalhada de participantes e status individuais

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31880/activity)

**Descrição:**

Criado: 2026-09-06T02:32:58.645Z
Atualizado: 2026-09-06T02:32:58.680Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31881 — CA1 - Exibir seção contendo a lista completa de convidados do combinado

- Pai: 31880
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31881/activity)

**Descrição:**

Criado: 2026-09-06T02:33:14.584Z
Atualizado: 2026-09-06T02:33:14.639Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31882 — CA2 - Apresentar avatar, nome e status (Aceito, Pendente, Recusado) de cada membro

- Pai: 31880
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31882/activity)

**Descrição:**

Criado: 2026-09-06T02:33:23.664Z
Atualizado: 2026-09-06T02:33:23.696Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31883 — CA3 - Adicionar uma tag indicativa de "Organizador" no criador do combinado

- Pai: 31880
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31883/activity)

**Descrição:**

Criado: 2026-09-06T02:33:32.664Z
Atualizado: 2026-09-06T02:33:32.702Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31884 — CA - Exibição de regras, termos e condições do combinado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31884/activity)

**Descrição:**

Criado: 2026-09-06T02:33:54.877Z
Atualizado: 2026-09-06T02:33:54.932Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31885 — CA1 - Apresentar os termos, valores e regras do combinado em um card/accordion de destaque

- Pai: 31884
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31885/activity)

**Descrição:**

Criado: 2026-09-06T02:34:11.710Z
Atualizado: 2026-09-06T02:34:11.788Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31886 — CA2 - Garantir boa formatação e legibilidade para o texto de regras

- Pai: 31884
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31886/activity)

**Descrição:**

Criado: 2026-09-06T02:34:26.693Z
Atualizado: 2026-09-06T02:34:26.725Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31887 — CA3 - Ocultar a seção ou exibir "Sem regras adicionais" se não houver termos cadastrados

- Pai: 31884
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31887/activity)

**Descrição:**

Criado: 2026-09-06T02:34:40.944Z
Atualizado: 2026-09-06T02:34:40.989Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31888 — CA - Ações contextualizadas na tela de detalhes

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31888/activity)

**Descrição:**

Criado: 2026-09-06T02:35:12.560Z
Atualizado: 2026-09-06T02:35:12.593Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31889 — CA1 - Renderizar botões de ação dinâmicos de acordo com o papel do usuário

- Pai: 31888
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31889/activity)

**Descrição:**

Criado: 2026-09-06T02:35:27.855Z
Atualizado: 2026-09-06T02:35:27.887Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31890 — CA2 - Executar as ações diretamente na tela sem necessidade de redirecionamento

- Pai: 31888
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31890/activity)

**Descrição:**

Criado: 2026-09-06T02:35:38.480Z
Atualizado: 2026-09-06T02:35:38.517Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31891 — CA3 - Ocultar botões de ações não permitidas para o perfil logado

- Pai: 31888
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31891/activity)

**Descrição:**

Criado: 2026-09-06T02:35:49.814Z
Atualizado: 2026-09-06T02:35:49.847Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31892 — CA - Campo de busca por nome/título do combinado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31892/activity)

**Descrição:**

Criado: 2026-09-06T02:36:12.111Z
Atualizado: 2026-09-06T02:36:12.162Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31893 — CA1 - Incluir campo de entrada de texto para pesquisa na parte superior da listagem

- Pai: 31892
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31893/activity)

**Descrição:**

Criado: 2026-09-06T02:36:28.792Z
Atualizado: 2026-09-06T02:36:28.830Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31894 — CA2 - Filtrar os cards dinamicamente conforme o texto for digitado

- Pai: 31892
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31894/activity)

**Descrição:**

Criado: 2026-09-06T02:36:49.762Z
Atualizado: 2026-09-06T02:36:49.799Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31895 — CA3 - Incluir ícone de lupa e botão para limpar o campo de texto

- Pai: 31892
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31895/activity)

**Descrição:**

Criado: 2026-09-06T02:37:02.583Z
Atualizado: 2026-09-06T02:37:02.626Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31896 — CA - Filtro por categoria do combinado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31896/activity)

**Descrição:**

Criado: 2026-09-06T02:37:40.343Z
Atualizado: 2026-09-06T02:37:40.381Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31897 — CA1 - Disponibilizar dropdown/chips com as categorias disponíveis no sistema

- Pai: 31896
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31897/activity)

**Descrição:**

Criado: 2026-09-06T02:37:58.813Z
Atualizado: 2026-09-06T02:37:58.848Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31898 — CA2 - Exibir na listagem apenas os combinados pertencentes à categoria selecionada

- Pai: 31896
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31898/activity)

**Descrição:**

Criado: 2026-09-06T02:38:12.898Z
Atualizado: 2026-09-06T02:38:12.934Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31899 — CA3 - Incluir opção "Todas as categorias" para redefinir a busca

- Pai: 31896
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31899/activity)

**Descrição:**

Criado: 2026-09-06T02:38:26.554Z
Atualizado: 2026-09-06T02:38:26.618Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31900 — CA - Filtro por status do convite/combinado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31900/activity)

**Descrição:**

Criado: 2026-09-06T02:38:55.290Z
Atualizado: 2026-09-06T02:38:55.332Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31901 — CA1 - Oferecer seletores de filtro por status (Pendentes, Aceitos, Recusados, Concluídos)

- Pai: 31900
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31901/activity)

**Descrição:**

Criado: 2026-09-06T02:39:19.359Z
Atualizado: 2026-09-06T02:39:19.404Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31902 — CA2 - Atualizar a listagem na tela imediatamente após selecionar o status

- Pai: 31900
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31902/activity)

**Descrição:**

Criado: 2026-09-06T02:39:31.538Z
Atualizado: 2026-09-06T02:39:31.575Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31903 — CA3 - Permitir o uso combinado do filtro de status junto com a busca por texto

- Pai: 31900
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31903/activity)

**Descrição:**

Criado: 2026-09-06T02:39:44.819Z
Atualizado: 2026-09-06T02:39:44.857Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31904 — CA - Ação de limpar e redefinir filtros

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31904/activity)

**Descrição:**

Criado: 2026-09-06T02:40:11.690Z
Atualizado: 2026-09-06T02:40:11.719Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31905 — CA1 - Exibir botão "Limpar filtros" sempre que houver filtros ativos

- Pai: 31904
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31905/activity)

**Descrição:**

Criado: 2026-09-06T02:40:36.492Z
Atualizado: 2026-09-06T02:40:36.530Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31906 — CA2 - Resetar todos os campos de busca, status e categorias para o padrão ao clicar

- Pai: 31904
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31906/activity)

**Descrição:**

Criado: 2026-09-06T02:40:46.742Z
Atualizado: 2026-09-06T02:40:46.813Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31907 — CA3 - Recarregar e exibir a listagem completa de combinados

- Pai: 31904
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31907/activity)

**Descrição:**

Criado: 2026-09-06T02:40:57.048Z
Atualizado: 2026-09-06T02:40:57.104Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31908 — CA - Alteração do status do combinado (Concluir/Finalizar)

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31908/activity)

**Descrição:**

Criado: 2026-09-06T02:41:31.299Z
Atualizado: 2026-09-06T02:41:31.330Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31909 — CA1 - Exibir o botão "Concluir Combinado" exclusivamente para o criador do acordo

- Pai: 31908
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31909/activity)

**Descrição:**

Criado: 2026-09-06T02:41:54.690Z
Atualizado: 2026-09-06T02:41:54.743Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31910 — CA2 - Solicitar confirmação em modal antes de realizar a conclusão

- Pai: 31908
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31910/activity)

**Descrição:**

Criado: 2026-09-06T02:42:03.849Z
Atualizado: 2026-09-06T02:42:03.883Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31911 — CA3 - Atualizar o status para "Concluído" e bloquear edições posteriores na interface

- Pai: 31908
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31911/activity)

**Descrição:**

Criado: 2026-09-06T02:42:12.615Z
Atualizado: 2026-09-06T02:42:12.650Z
(Sem descrição)

### [CRITÉRIO DE ACEITAÇÃO] #31912 — CA - Bloqueio e indicação visual para combinados finalizados/cancelados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31912/activity)

**Descrição:**

Criado: 2026-09-06T02:42:49.676Z
Atualizado: 2026-09-06T02:42:49.708Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31913 — CA1 - Exibir badge em destaque indicando o estado final ("Concluído" ou "Cancelado")

- Pai: 31912
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31913/activity)

**Descrição:**

Criado: 2026-09-06T02:43:15.011Z
Atualizado: 2026-09-06T02:43:15.057Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31914 — CA2 - Desabilitar/ocultar todos os botões de edição e resposta

- Pai: 31912
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31914/activity)

**Descrição:**

Criado: 2026-09-06T02:43:24.524Z
Atualizado: 2026-09-06T02:43:24.586Z
(Sem descrição)

#### [CRITÉRIO DE ACEITAÇÃO] #31915 — CA3 - Manter a tela e suas informações visíveis apenas para consulta e leitura

- Pai: 31912
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Carlos Silvio
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/31915/activity)

**Descrição:**

Criado: 2026-09-06T02:43:33.221Z
Atualizado: 2026-09-06T02:43:33.264Z
(Sem descrição)

### [TASK] #32373 — Registrar horas ao finalizar cada task

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32373/activity)

**Descrição:**

Criado: 2026-09-16T02:05:19.023Z
Atualizado: 2026-09-16T02:05:19.051Z
Sempre registrar horas trabalhadas ao concluir uma task.

### [TASK] #32374 — Manter tasks com menos de 4h

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32374/activity)

**Descrição:**

Criado: 2026-09-16T02:05:19.682Z
Atualizado: 2026-09-16T02:05:19.710Z
Subdividir tasks maiores que 4h em subtasks.

### [TASK] #32375 — Registrar bugs no Kanban de Bugs

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32375/activity)

**Descrição:**

Criado: 2026-09-16T02:05:20.377Z
Atualizado: 2026-09-16T02:05:20.409Z
Nunca registrar bug como melhoria ou vice-versa.

### [TASK] #32376 — Realizar daily meeting diária

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32376/activity)

**Descrição:**

Criado: 2026-09-16T02:05:21.075Z
Atualizado: 2026-09-16T02:05:21.118Z
O que fiz ontem? O que farei hoje? Impedimentos?

### [TASK] #32377 — Realizar Sprint Review ao final de cada Sprint

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32377/activity)

**Descrição:**

Criado: 2026-09-16T02:05:21.784Z
Atualizado: 2026-09-16T02:05:21.820Z
Demonstrar incremento e coletar feedback.

### [TASK] #32378 — Realizar Sprint Retrospective ao final de cada Sprint

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32378/activity)

**Descrição:**

Criado: 2026-09-16T02:05:22.438Z
Atualizado: 2026-09-16T02:05:22.464Z
Registrar melhorias para o próximo ciclo.

### [TASK] #32379 — Manter status atualizado

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32379/activity)

**Descrição:**

Criado: 2026-09-16T02:05:23.060Z
Atualizado: 2026-09-16T02:05:23.093Z
Não deixar tasks ABERTAS em sprints passadas.

### [TASK] #32380 — US não podem ter nomes duplicados

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: High
- Autor: Maria Luiza
- Início: Não informado
- Prazo: Não informado
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32380/activity)

**Descrição:**

Criado: 2026-09-16T02:05:23.692Z
Atualizado: 2026-09-16T02:05:23.717Z
Cada US deve ter um nome único e descritivo.

### [EPIC] #32381 — BUGS/MELHORIAS

- Pai: Não informado
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32381/activity)

**Descrição:**

Criado: 2026-09-16T03:16:46.367Z
Atualizado: 2026-10-22T01:55:05.174Z
(Sem descrição)

#### [FEATURE] #32382 — GERAL

- Pai: 32381
- Status: New
- Responsável: Não informado
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32382/activity)

**Descrição:**

Criado: 2026-09-16T03:16:46.760Z
Atualizado: 2026-10-22T01:55:05.134Z
(Sem descrição)

##### [BUG] #32383 — Adicionar participantes no formulário de combinado gera erro 403

- Pai: 32382
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32383/activity)

**Descrição:**

Criado: 2026-09-16T03:16:47.325Z
Atualizado: 2026-09-17T10:55:21.002Z
Descrição

Ao tentar adicionar um participante no campo de busca do formulário de criação de combinado, a aplicação retorna erro 403 “Acesso negado”.

Passos

1.  Acessar o formulário de criação de combinado

2.  Digitar um e-mail no campo de participantes


Resultado esperado

O sistema deve localizar o usuário e adicioná-lo à lista de participantes.

Resultado atual

A aplicação retorna erro 403 e redireciona para a tela de acesso negado.

Severidade

Alta

Prioridade

Alta

##### [BUG] #32384 — Aceitar convite não atualiza o status do card

- Pai: 32382
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32384/activity)

**Descrição:**

Criado: 2026-09-16T20:58:41.239Z
Atualizado: 2026-09-17T10:55:28.811Z
**Descrição**

Ao tentar aceitar um convite recebido, o usuário confirma a ação no modal, mas a interface não reflete a atualização esperada.O card continua exibindo o convite como pendente.

**Passos para reprodução**

1.  Acessar a tela de convites recebidos.

2.  Selecionar um convite pendente.

3.  Clicar no botão de aceitar.

4.  Confirmar a ação no modal.


**Resultado esperado**

*   O status do convite deve atualizar para aceito.

*   O card deve refletir a mudança imediatamente.


**Resultado atual**

*   O card continua com status pendente.

*   A resposta do usuário não fica refletida na interface.

##### [BUG] #32385 — Botão de recusar convite não responde ao clique

- Pai: 32382
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32385/activity)

**Descrição:**

Criado: 2026-09-16T21:01:36.892Z
Atualizado: 2026-10-22T01:02:56.876Z
**Descrição**

Ao tentar recusar um convite recebido, o botão de recusar não executa a ação esperada. O usuário clica no botão, mas a confirmação não é acionada corretamente e o fluxo não avança como deveria.

**Passos para reprodução**

1.  Acessar a tela de convites recebidos.

2.  Selecionar um convite pendente.

3.  Clicar no botão de recusar.

4.  Observar que a ação não responde corretamente.


**Resultado esperado**

*   O usuário deve conseguir abrir a confirmação de recusa.

*   O fluxo deve seguir para a etapa de confirmação da ação.

*   A recusa deve ser registrada corretamente.


**Resultado atual**

*   O botão de recusar não está funcionando de forma consistente.

*   A ação não é processada corretamente pela interface.

##### [BUG] #32386 — Confirmação de recusa não atualiza o status do convite

- Pai: 32382
- Status: Closed
- Responsável: Felipe Macedo
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32386/activity)

**Descrição:**

Criado: 2026-09-16T21:02:38.216Z
Atualizado: 2026-10-22T01:03:08.963Z
**Descrição**

O convite continua exibido como pendente e o card não reflete a mudança de status.

**Passos para reprodução**

1.  Acessar a tela de convites recebidos.

2.  Abrir um convite pendente.

3.  Clicar em recusar.

4.  Confirmar a recusa no modal.


**Resultado esperado**

*   O modal deve fechar.

*   O convite deve mudar de status para recusado.

*   O card deve refletir a atualização imediatamente.


**Resultado atual**

*   O card continua pendente.

*   A interface não reflete a recusa.

*   O convite permanece visualmente como não respondido.

##### [MELHORIA] #32387 — Exibir lista de participantes no card de criado

- Pai: 32382
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32387/activity)

**Descrição:**

Criado: 2026-09-16T21:12:15.064Z
Atualizado: 2026-10-21T20:58:26.692Z
**Título**

Na aba de criados, o usuário não consegue visualizar os participantes do combinado ao abrir o convite

**Descrição**

Ao abrir um combinado na aba de criados, a visualização atual mostra apenas o resumo geral do convite, mas não apresenta a lista de participantes envolvidos no acordo. Isso dificulta o acompanhamento do convite e impede uma visão clara de quem foi convidado.

**Passos para reprodução**

1.  Acessar a aba de “Combinados criados”.

2.  Selecionar um combinado já criado.

3.  Clicar em “Ver convite” ou abrir o card.

4.  Observar a visualização atual.


**Resultado esperado**

*   O criador deve conseguir visualizar todos os participantes do combinado.

*   A lista deve incluir os nomes e, quando aplicável, os e-mails ou identificadores dos convidados.

*   A visualização deve facilitar o acompanhamento do convite.


**Resultado atual**

*   A tela não exibe claramente a lista de participantes.

*   O usuário precisa buscar informações adicionais para saber quem foi convidado.

##### [MELHORIA] #32388 — Exibir status individual de cada participante no convite

- Pai: 32382
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32388/activity)

**Descrição:**

Criado: 2026-09-16T21:12:59.560Z
Atualizado: 2026-10-21T21:01:05.257Z
**Título**

Na aba de criados, o status do convite não é exibido individualmente para cada participante

**Descrição**

Quando um combinado possui vários participantes, cada um pode responder de forma diferente: aceitar, recusar ou permanecer pendente. No momento, a interface não mostra esse status individual, o que impede que o criador acompanhe o andamento dos convites de forma precisa.

**Passos para reprodução**

1.  Acessar a aba de “Combinados criados”.

2.  Abrir um combinado com mais de um participante.

3.  Verificar a visualização do convite.

4.  Observar que o status não é apresentado por participante.


**Resultado esperado**

*   Para cada participante, deve ser exibido o status atual do convite:

*   pendente

*   aceito

*   recusado

*   O criador deve conseguir identificar rapidamente quem já respondeu e quem ainda precisa responder.


**Resultado atual**

*   A interface não mostra a situação de cada convite individualmente.

*   O criador não consegue acompanhar de forma clara quais participantes aceitaram, recusaram ou ainda estão pendentes.

##### [BUG] #32389 — Convites são enviados para todos os usuários da sessão demo

- Pai: 32382
- Status: Closed
- Responsável: Maria Luiza
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: Não informado
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32389/activity)

**Descrição:**

Criado: 2026-09-16T21:23:08.277Z
Atualizado: 2026-09-17T10:56:30.796Z
**Título**

Ao criar um combinado, os convites são enviados para todos os usuários da sessão demo, e não apenas para os participantes selecionados

**Descrição**

Durante a criação de um combinado, ao adicionar participantes específicos na lista, o sistema está enviando o convite para todos os usuários da sessão demo, em vez de limitar a entrega apenas aos participantes selecionados no formulário. Esse comportamento compromete a lógica correta do convite e pode gerar envios indevidos para usuários que não foram escolhidos.

**Passos para reprodução**

1.  Acessar a tela de criação de combinado.

2.  Preencher os dados do acordo.

3.  Selecionar apenas alguns participantes da sessão demo.

4.  Salvar o combinado.

5.  Observar que o convite aparece para todos os usuários da sessão demo, e não apenas para os participantes selecionados.


**Resultado esperado**

*   O convite deve ser enviado somente para os usuários incluídos na lista de participantes do combinado.

*   Usuários fora da seleção não devem receber o convite.

*   A lista de destinatários deve refletir exatamente os participantes escolhidos no formulário.


**Resultado atual**

*   O sistema envia o convite para todos os usuários da sessão demo.

*   A seleção de participantes não está sendo respeitada na criação do combinado.


**Impacto**

*   Envios indevidos de convites.

*   Falha na lógica de destinatários.

*   Compromete a privacidade e a consistência do processo de criação de combinados.

##### [BUG] #32392 — Rascunho não é salvo

- Pai: 32382
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: Immediate
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32392/activity)

**Descrição:**

Criado: 2026-09-16T21:53:51.034Z
Atualizado: 2026-10-22T02:02:23.495Z
**Título**

Rascunho não é salvo quando campos obrigatórios da criação do combinado ainda não foram preenchidos

**Descrição**

O sistema deve permitir que o usuário salve o progresso da criação de um combinado com status RASCUNHO, mesmo que alguns campos obrigatórios da criação definitiva ainda não tenham sido preenchidos. No entanto, a interface atual bloqueia esse fluxo e impede a persistência do rascunho.

**Passos para reprodução**

1.  Acessar a tela de criação de combinado.

2.  Iniciar a criação do conteúdo.

3.  Não preencher todos os campos obrigatórios da criação definitiva.

4.  Clicar em “Salvar como rascunho”.

5.  Observar o comportamento do sistema.


**Resultado esperado**

*   O sistema deve salvar o progresso como rascunho.

*   O status do registro deve ser RASCUNHO.

*   O usuário deve conseguir continuar a edição posteriormente.

*   A validação obrigatória deve ser aplicada somente ao envio definitivo do combinado, e não ao salvamento como rascunho.


**Resultado atual**

*   O sistema impede o salvamento do rascunho quando os campos obrigatórios não estão preenchidos.

*   O progresso não é persistido.

*   O fluxo não está em conformidade com o critério CA01.


**Impacto**

*   O usuário não consegue salvar o andamento da criação do combinado.

*   A funcionalidade de rascunho fica indisponível.

*   A experiência do fluxo de criação fica comprometida.

##### [BUG] #32393 — Registro de aceite no histórico não está validado

- Pai: 32382
- Status: Closed
- Responsável: José Correia da Cruz Júnior
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-09-16
- Progresso: 0
- Estimativa: PT1H
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/32393/activity)

**Descrição:**

Criado: 2026-09-16T21:57:55.742Z
Atualizado: 2026-10-21T01:48:23.328Z
**Título**

Registro de aceite no histórico não está sendo persistido com autor e data corretamente

**Descrição**

O sistema deve registrar a ação de aceite do convite no histórico com identificação do autor e da data da ação. No entanto, o comportamento atual não garante que esse evento seja armazenado de forma consistente e rastreável, impedindo a validação correta do histórico de resposta do convite.

**Passos para reprodução**

1.  Acessar um convite pendente.

2.  Confirmar o aceite do convite.

3.  Verificar o histórico ou timeline da ação.

4.  Observar se o evento foi registrado com autor e data.


**Resultado esperado**

*   O aceite deve ser registrado no histórico.

*   O evento deve conter o autor da ação.

*   O evento deve conter a data/hora da confirmação.

*   A informação deve aparecer corretamente na timeline ou histórico do convite.


**Resultado atual**

*   O sistema registra apenas o status do convite, mas não há evidência consistente de um histórico auditável com autor e data.

*   O evento de aceite não está totalmente validado na timeline conforme a especificação.


**Impacto**

*   Dificulta a rastreabilidade das ações realizadas.

*   Compromete a validação do histórico de resposta.

*   Pode gerar inconsistência na auditoria do processo de convite.

##### [BUG] #34289 — E-mail inválido exibe mensagem incorreta ao adicionar participante

- Pai: 32382
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/34289/activity)

**Descrição:**

Criado: 2026-10-21T23:12:39.744Z
Atualizado: 2026-10-22T01:55:44.045Z
**Título**

E-mail inválido exibe mensagem incorreta ao adicionar participante

**Descrição**

Ao informar um e-mail em formato inválido no campo de participantes, o sistema bloqueia a inclusão, mas exibe a mensagem “Nenhum usuário encontrado com este e-mail.” Esse retorno não esclarece que o problema é o formato do endereço.

**Passos para reprodução**

1.  Acessar a tela de criação de combinado.

2.  Informar um e-mail em formato inválido, como `email-invalido`.

3.  Clicar em **Adicionar**.

4.  Observar a mensagem exibida.


**Resultado esperado**

*   O sistema deve impedir a inclusão do participante.

*   A interface deve informar claramente que o formato do e-mail é inválido.


**Resultado atual**

*   O sistema impede a inclusão.

*   A interface exibe “Nenhum usuário encontrado com este e-mail.”, mensagem que não identifica o erro de formato.

##### [BUG] #34290 — Mensagem de erro não é exibida ao tentar login com credenciais inválidas

- Pai: 32382
- Status: Closed
- Responsável: Carlos Silvio
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-10-14
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/34290/activity)

**Descrição:**

Criado: 2026-10-22T00:13:49.295Z
Atualizado: 2026-10-22T01:55:05.107Z
**Título**

Mensagem de erro não é exibida ao tentar login com credenciais inválidas

**Descrição**

Ao tentar entrar com uma senha incorreta, o backend rejeita a autenticação com HTTP 401, mas a interface não apresenta uma mensagem de erro. Sem esse feedback, o usuário não entende por que o acesso foi negado.

**Passos para reprodução**

1.  Acessar a tela de login.

2.  Informar um e-mail cadastrado.

3.  Informar uma senha incorreta.

4.  Clicar em **Entrar**.


**Resultado esperado**

*   O acesso deve ser negado.

*   A interface deve exibir uma mensagem clara, como “E-mail ou senha inválidos.”.


**Resultado atual**

*   O backend rejeita o login com HTTP 401.

*   A interface permanece na tela de login sem exibir mensagem de erro.

##### [BUG] #34294 — Validação de e-mail permite domínios sem ponto no backend e frontend

- Pai: 32382
- Status: New
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/34294/activity)

**Descrição:**

Criado: 2026-10-22T02:41:48.445Z
Atualizado: 2026-10-22T05:07:17.763Z
*   **Descrição:** A expressão regular (`Regex @(.+)`) utilizada no `UsuarioService` e nas validações do frontend é insuficiente para garantir o formato correto de um e-mail. Atualmente, a aplicação aceita endereços de e-mail sem ponto após o caractere `@` (ex.: `sem@arroba`), registrando status HTTP `201 Created` no backend e exibindo a mensagem `"E-mail válido."` na interface.


*   **Passos para Reproduzir:**

1.  Acessar a tela de cadastro/edição de usuário.

2.  Preencher o campo de e-mail com o valor `sem@arroba`.

3.  Submeter o formulário (ou realizar a requisição `POST` diretamente no endpoint de usuários).

4.  Observar a resposta da API e o feedback exibido na interface.


*   **Resultado Esperado:** A aplicação deve rejeitar o cadastro tanto no frontend quanto no backend, retornando erro de validação (ex.: HTTP `400 Bad Request`) e exibindo a mensagem referente a e-mail inválido, exigindo o formato correto (ex.: `usuario@dominio.com`).


*   **Resultado Atual:** O backend aceita o cadastro retornando HTTP `201`, e a interface valida o campo informando `"E-mail válido."`.


*   **Impacto:** **Médio/Alto.** Permite a gravação de e-mails inválidos no banco de dados, o que impede a entrega de notificações do sistema, envios de convites e etapas de recuperação de senha para esses usuários.

##### [BUG] #34295 — Ausência de funcionalidade para exclusão/descarte de rascunhos

- Pai: 32382
- Status: New
- Responsável: Felipe Macedo
- Prioridade: High
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/34295/activity)

**Descrição:**

Criado: 2026-10-22T02:42:33.739Z
Atualizado: 2026-10-22T05:07:06.620Z
*   **Descrição:** O critério de aceitação **CA03** (relacionado à task #31512) exige que o usuário consiga descartar ou remover um rascunho mediante confirmação. No entanto, a funcionalidade não está implementada: não há endpoint com anotação `@DeleteMapping` mapeado no _Controller_ do backend, tampouco botão ou ação de descarte na interface gráfica (_UI_).



*   **Passos para Reproduzir:**

1.  Criar e salvar um rascunho na aplicação.

2.  Acessar a listagem ou visualização do rascunho salvo na interface.

3.  Tentar localizar o botão/opção para excluir ou descartar o rascunho.

4.  Verificar na API a existência de rota HTTP `DELETE` correspondente para a remoção da entidade.

5.

*   **Resultado Esperado:** Deve haver um botão visível para descarte na UI que acione uma caixa de diálogo de confirmação. Ao confirmar, o frontend deve realizar uma chamada HTTP `DELETE` ao backend, e o registro deve ser removido da base de dados.



*   **Resultado Atual:** A funcionalidade de exclusão não existe no backend (ausência do método `@DeleteMapping`) e não possui elemento visual correspondente na UI.



*   **Impacto:** **Médio.** Impede o cumprimento integral da estória de usuário/critério de aceitação CA03, fazendo com que rascunhos indesejados fiquem acumulados indefinidamente na conta do usuário sem opção de limpeza.

##### [BUG] #34296 — Aceite de proposta via interface pendente de validação em tela

- Pai: 32382
- Status: New
- Responsável: Felipe Macedo
- Prioridade: Normal
- Autor: Maria Luiza
- Início: 2026-09-16
- Prazo: 2026-10-14
- Progresso: Não informado
- Estimativa: Não informado
[Abrir no OpenProject](https://200.129.79.50:8104/projects/combinado/work_packages/34296/activity)

**Descrição:**

Criado: 2026-10-22T02:43:48.613Z
Atualizado: 2026-10-22T05:07:13.239Z
*   **Descrição:** A funcionalidade de aceite de proposta foi validada no backend com 100% de conformidade (quando o convite é aceito, o combinado passa para o status `ATIVO`, com cobertura de 8/8 testes unitários). No entanto, o fluxo ponta a ponta (E2E) através da interface gráfica (UI) não pôde ser completamente homologado por falta de um convite real gerado diretamente na tela.