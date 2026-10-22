/// <reference types="cypress" />
// Homologação #30933 / #31447 — Encerramento seguro de sessão (CA04)

describe("homologação do encerramento seguro de sessão", () => {
  function loginViaApi(): Cypress.Chainable<string> {
    const email = `logout.${Date.now()}@combinado.app`;
    return cy
      .request("POST", "/api/usuarios", {
        nome: "Homolog Logout",
        email,
        senha: "senha123",
        confirmacaoSenha: "senha123",
      })
      .then(() =>
        cy.request<string>("POST", "/api/auth/login", { email, senha: "senha123" }).its("body.token"),
      );
  }

  it("CA04 — logout invalida a sessão, limpa o cliente e redireciona para a tela pública", () => {
    loginViaApi().then((token) => {
      cy.intercept("POST", "**/api/auth/logout").as("logoutApi");
      cy.visit("/", {
        onBeforeLoad(win) {
          win.localStorage.setItem("combinado.jwt", token);
          win.localStorage.setItem(
            "combinado.authUser",
            JSON.stringify({ id: 1, nome: "Homolog Logout", email: "logout@combinado.app", perfil: "ROLE_USUARIO" }),
          );
        },
      });
      cy.contains("button", "Sair").click();

      // Redirecionamento para a tela pública inicial de acesso
      cy.url().should("include", "/login");

      // Token removido do cliente
      cy.window().then((win) => {
        expect(win.localStorage.getItem("combinado.jwt")).to.be.null;
        expect(win.localStorage.getItem("combinado.authUser")).to.be.null;
      });

      // Backend invalidou o token (revogação persistida)
      cy.wait("@logoutApi").its("response.statusCode").should("eq", 204);
      cy.request({
        method: "GET",
        url: "/api/usuarios/1",
        headers: { Authorization: `Bearer ${token}` },
        failOnStatusCode: false,
      }).its("status").should("eq", 401);

      // Rota privada permanece bloqueada após sair
      cy.visit("/perfil");
      cy.url().should("include", "/login");
    });
  });
});
