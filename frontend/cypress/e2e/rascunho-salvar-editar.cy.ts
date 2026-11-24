/// <reference types="cypress" />
// Task #31412 — Teste Cypress para salvar e editar rascunho

describe("Salvar e editar rascunho de combinado (#31412)", () => {
  it("salva um novo rascunho apenas com título e permite reabri-lo para edição", () => {
    const email = `rascunho.e2e.${Date.now()}@combinado.app`;
    const tituloInicial = `Rascunho Inicial ${Date.now()}`;

    cy.loginViaApi("Criador Rascunho", email).then((token) => {
      cy.visit("/combinados/novo", {
        onBeforeLoad(win) {
          win.localStorage.setItem("combinado.jwt", token);
          win.localStorage.setItem(
            "combinado.authUser",
            JSON.stringify({ id: 1, nome: "Criador Rascunho", email, perfil: "ROLE_USUARIO" }),
          );
        },
      });

      cy.get('input[placeholder="Ex: Empréstimo da furadeira"]').type(tituloInicial);
      cy.contains("button", "Salvar como rascunho").click();
      cy.contains("Rascunho salvo.").should("be.visible");
      cy.contains(tituloInicial).should("be.visible");
      cy.contains("a", "Continuar rascunho").click();
      cy.url().should("include", "/combinados/novo?from=");
    });
  });
});
