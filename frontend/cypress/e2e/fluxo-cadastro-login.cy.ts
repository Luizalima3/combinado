/// <reference types="cypress" />
// Task #31410 — Especificação Cypress para o fluxo de cadastro e login

describe("Fluxo integrado de cadastro e login (#31410)", () => {
  it("cadastra um novo usuário, encerra a sessão e realiza login com as credenciais criadas", () => {
    const email = `cadastro.login.${Date.now()}@combinado.app`;
    cy.intercept("POST", "**/api/usuarios").as("postUsuario");
    cy.intercept("POST", "**/api/auth/login").as("postLogin");

    cy.visit("/cadastro");
    cy.contains("label", "Nome").find("input").type("Felipe Teste E2E");
    cy.contains("label", "E-mail").find("input").type(email);
    cy.contains("label", "Senha").find("input").type("senha123");
    cy.contains("label", "Confirmar senha").find("input").type("senha123");
    cy.get("button[type=submit]").click();

    cy.wait("@postUsuario").its("response.statusCode").should("eq", 201);
    cy.url().should("not.include", "/cadastro");

    cy.window().then((win) => win.localStorage.clear());
    cy.visit("/login");
    cy.contains("label", "E-mail").find("input").type(email);
    cy.contains("label", "Senha").find("input").type("senha123");
    cy.get("button[type=submit]").click();

    cy.wait("@postLogin").its("response.statusCode").should("eq", 200);
    cy.contains("Meus combinados").should("be.visible");
  });
});
