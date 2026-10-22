/// <reference types="cypress" />
// Homologação #30921 / #31439 — Cadastro completo (CA05, CA06, CA07)

describe("homologação do cadastro — confirmação de senha e formulário", () => {
  const uniqueEmail = () => `homolog.${Date.now()}@combinado.app`;

  it("CA05 — impede o cadastro quando as senhas são diferentes", () => {
    cy.visit("/cadastro");
    cy.contains("label", "Nome").find("input").type("Maria Homolog");
    cy.contains("label", "E-mail").find("input").type(uniqueEmail());
    cy.contains("label", "Senha").find("input").type("senha123");
    cy.contains("label", "Confirmar senha").find("input").type("senha456");

    cy.contains("As senhas não coincidem.").should("be.visible");
    cy.get("button[type=submit]").click();
    cy.url().should("include", "/cadastro");
    cy.contains("A confirmação deve ser igual à senha.").should("be.visible");
    cy.contains("button", "Cadastrando...").should("not.exist");
  });

  it("CA07 — apresenta mensagens de erro claras para dados inválidos", () => {
    cy.visit("/cadastro");
    cy.get("button[type=submit]").click();

    cy.contains("Informe seu nome completo.").should("be.visible");
    cy.contains("Informe um e-mail válido.").should("be.visible");
    cy.contains("A senha deve ter no mínimo 6 caracteres.").should("be.visible");
    cy.contains("A confirmação deve ser igual à senha.").should("be.visible");
  });

  it("CA06 — cria o usuário e informa sucesso quando os dados são válidos", () => {
    const email = uniqueEmail();
    cy.intercept("POST", "**/api/usuarios").as("cadastro");
    cy.intercept("POST", "**/api/auth/login").as("login");

    cy.visit("/cadastro");
    cy.contains("label", "Nome").find("input").type("Maria Homolog");
    cy.contains("label", "E-mail").find("input").type(email);
    cy.contains("label", "Senha").find("input").type("senha123");
    cy.contains("label", "Confirmar senha").find("input").type("senha123");

    cy.contains("Senhas conferem.").should("be.visible");
    cy.get("button[type=submit]").click();

    cy.wait("@cadastro").its("response.statusCode").should("eq", 201);
    cy.url().should("not.include", "/cadastro");
    cy.contains("Conta criada com sucesso.").should("be.visible");
    cy.window().then((win) => {
      expect(win.localStorage.getItem("combinado.jwt")).to.not.be.null;
    });
  });
});
