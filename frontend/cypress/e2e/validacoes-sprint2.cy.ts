/// <reference types="cypress" />
// Homologação Sprint 2 — #30913 (CA03 e-mail), #30917 (CA04 senha), #30925 (login CA01/CA02)

describe("homologação de validações de cadastro e login", () => {
  it("#30913/CA03 — bloqueia envio e mostra feedback de e-mail inválido", () => {
    cy.visit("/cadastro");
    cy.contains("label", "Nome").find("input").type("Maria Homolog");
    cy.contains("label", "E-mail").find("input").type("maria@semdominio");
    cy.contains("Formato de e-mail inválido.").should("be.visible");
    cy.get("button[type=submit]").click();
    cy.url().should("include", "/cadastro");
    cy.contains("Informe um e-mail válido.").should("be.visible");
  });

  it("#30917/CA04 — senha curta bloqueada com mensagem e indicador de força", () => {
    cy.visit("/cadastro");
    cy.contains("label", "Nome").find("input").type("Maria Homolog");
    cy.contains("label", "E-mail").find("input").type(`ok.${Date.now()}@combinado.app`);
    cy.contains("label", "Senha").find("input").type("123");
    cy.contains("Força da senha:").should("be.visible");
    cy.get("button[type=submit]").click();
    cy.url().should("include", "/cadastro");
    cy.contains("A senha deve ter no mínimo 6 caracteres.").should("be.visible");
  });

  it("#30925/CA01 — login com credenciais válidas autentica e redireciona", () => {
    const email = `login.${Date.now()}@combinado.app`;
    cy.request("POST", "/api/usuarios", { nome: "Login Homolog", email, senha: "senha123", confirmacaoSenha: "senha123" });
    cy.intercept("POST", "**/api/auth/login").as("loginApi");
    cy.visit("/login");
    cy.contains("label", "E-mail").find("input").type(email);
    cy.contains("label", "Senha").find("input").type("senha123");
    cy.get("button[type=submit]").click();
    cy.wait("@loginApi").its("response.statusCode").should("eq", 200);
    cy.contains("Login realizado com sucesso.").should("be.visible");
    cy.url().should("not.include", "/login");
  });

  it("#30925/CA02 — credenciais inválidas bloqueadas sem expor detalhes", () => {
    cy.intercept("POST", "**/api/auth/login").as("loginApi");
    cy.visit("/login");
    cy.contains("label", "E-mail").find("input").type(`inexistente.${Date.now()}@combinado.app`);
    cy.contains("label", "Senha").find("input").type("errada123");
    cy.get("button[type=submit]").click();
    cy.wait("@loginApi").its("response.statusCode").should("eq", 401);
    cy.contains("E-mail ou senha inválidos.").should("be.visible");
    cy.url().should("include", "/login");
  });
});
