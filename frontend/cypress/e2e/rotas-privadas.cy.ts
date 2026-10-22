/// <reference types="cypress" />
// Homologação #30937 / #31451 — Proteção de rotas privadas (CA05)

describe("homologação das restrições de acesso a rotas privadas", () => {
  const ROTAS_PRIVADAS = ["/", "/combinados/novo", "/perfil"];

  it("CA05 — usuário não autenticado é redirecionado para /login em todas as rotas privadas", () => {
    cy.window().then((win) => {
      win.localStorage.removeItem("combinado.jwt");
      win.localStorage.removeItem("combinado.authUser");
    });
    ROTAS_PRIVADAS.forEach((rota) => {
      cy.visit(rota);
      cy.url().should("include", "/login");
    });
  });

  it("CA05 — usuário autenticado acessa a rota privada normalmente", () => {
    const email = `guard.${Date.now()}@combinado.app`;
    cy.request("POST", "/api/usuarios", {
      nome: "Homolog Guard",
      email,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });
    cy.request("POST", "/api/auth/login", { email, senha: "senha123" })
      .its("body.token")
      .then((token) => {
        expect(token, "token emitido pelo login").to.be.a("string").and.not.empty;
        cy.visit("/perfil", {
          onBeforeLoad(win) {
            win.localStorage.setItem("combinado.jwt", token);
            win.localStorage.setItem(
              "combinado.authUser",
              JSON.stringify({ id: 1, nome: "Homolog Guard", email, perfil: "ROLE_USUARIO" }),
            );
          },
        });
        cy.url().should("include", "/perfil");
        cy.contains("Alterar senha").should("be.visible");
      });
  });

  it("CA05 — sessão inexistente/expirada via API retorna 401 e a interface redireciona para /login", () => {
    cy.intercept("GET", "**/api/combinados*", (req) => {
      req.headers.Authorization = "Bearer token-invalido";
    }).as("api");
    cy.window().then((win) => {
      win.localStorage.setItem("combinado.jwt", "token-invalido");
      win.localStorage.setItem(
        "combinado.authUser",
        JSON.stringify({ id: 1, nome: "Demo", email: "demo@test.com", perfil: "ROLE_USUARIO" }),
      );
    });
    cy.visit("/perfil");
    cy.wait("@api").its("response.statusCode").should("eq", 401);
    cy.url().should("include", "/login");
  });
});
