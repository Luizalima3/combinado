/// <reference types="cypress" />
// Homologação Sprint 2 — perfil (#30943, #30947, #30952), combinados (#30990, #31011),
// múltiplos responsáveis (#31031), participantes (#31039, #31046, #31053), rascunhos (#31074)

describe("homologação de perfil, combinados e convites", () => {
  let token: string;
  let email: string;
  const SENHA = "senha123";

  before(() => {
    email = `po.${Date.now()}@combinado.app`;
    cy.request("POST", "/api/usuarios", { nome: "PO Homolog", email, senha: SENHA, confirmacaoSenha: SENHA });
    cy.request("POST", "/api/auth/login", { email, senha: SENHA }).its("body.token").then((t) => (token = t));
  });

  beforeEach(() => {
    cy.visit("/", {
      onBeforeLoad(win) {
        win.localStorage.setItem("combinado.jwt", token);
        win.localStorage.setItem(
          "combinado.authUser",
          JSON.stringify({ id: 1, nome: "PO Homolog", email, perfil: "ROLE_USUARIO" }),
        );
      },
    });
  });

  it("#30943 — atualização de nome do perfil persiste e dá feedback", () => {
    cy.intercept("PUT", "**/api/usuarios/perfil/nome").as("putNome");
    cy.visit("/perfil");
    cy.contains("h2", "Alterar nome").parent("form").find("input").clear().type("PO Homolog Renomeado");
    cy.contains("h2", "Alterar nome").parent("form").contains("button", "Salvar").click();
    cy.wait("@putNome").its("response.statusCode").should("eq", 200);
    cy.contains("Nome atualizado.").should("be.visible");
  });

  it("#30952 — alteração de senha: confirmação divergente bloqueada; correta concluída", () => {
    cy.intercept("PUT", "**/api/usuarios/perfil/senha").as("putSenha");
    cy.visit("/perfil");
    const form = cy.contains("h2", "Alterar senha").parent("form");
    cy.get('input[placeholder="Senha atual"]').type(SENHA);
    cy.get('input[placeholder="Nova senha"]').type("novaSenha456");
    cy.get('input[placeholder="Confirmar nova senha"]').type("divergente");
    form.contains("button", "Salvar").click();
    cy.contains("A confirmação deve ser igual à nova senha.").should("be.visible");

    cy.get('input[placeholder="Confirmar nova senha"]').clear().type("novaSenha456");
    cy.contains("h2", "Alterar senha").parent("form").contains("button", "Salvar").click();
    cy.wait("@putSenha").its("response.statusCode").should("eq", 204);
    cy.contains("Senha alterada com sucesso.").should("be.visible");
  });

  it("#30990/#31011 — criação de combinado: bloqueio com mensagens e criação com persistência", () => {
    cy.intercept("POST", "**/api/combinados").as("postCombinado");
    cy.visit("/combinados/novo");
    // Enviar sem preencher: bloqueio com mensagens claras (CA, #31011)
    cy.contains("button", "Enviar convites").click();
    cy.get(".text-danger").its("length").should("be.greaterThan", 0);
    cy.url().should("include", "/combinados/novo");

    // Salvar como rascunho com dados válidos (#30990 persistência + #31074 base)
    cy.get('input[placeholder="Ex: Empréstimo da furadeira"]').type("Combinado de Homologação");
    cy.get('textarea[placeholder="Detalhe o que está sendo combinado"]').type("Validação Sprint 2");
    cy.contains("button", "Salvar como rascunho").click();
    cy.wait("@postCombinado").its("response.statusCode").should("eq", 201);
    cy.contains("Rascunho salvo.").should("be.visible");
  });

  it("#31039/#31046 — busca de participante por e-mail com sugestão automática", () => {
    const partEmail = `part.${Date.now()}@combinado.app`;
    cy.request("POST", "/api/usuarios", {
      nome: "Participante Busca",
      email: partEmail,
      senha: "senha456",
      confirmacaoSenha: "senha456",
    });
    cy.visit("/combinados/novo");
    // US23: busca preditiva por NOME (o endpoint busca por nome, não por e-mail)
    cy.get('input[placeholder*="mail" i], input[placeholder*="articipante" i]').first()
      .type("Participante", { delay: 200 });
    cy.contains("Participante Busca").should("be.visible");
    // #31039: busca por e-mail exato via Enter adiciona o participante
    cy.get('input[placeholder*="mail" i], input[placeholder*="articipante" i]').first()
      .clear().type(partEmail).type("{enter}");
    cy.contains("Participante Busca").should("exist");
  });
});
