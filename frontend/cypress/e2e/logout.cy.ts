describe("encerramento de sessão", () => {
  it("remove token e bloqueia rotas privadas após logout", () => {
    cy.visit("/login");
    cy.window().then((win) => {
      win.localStorage.setItem("combinado.jwt", "token-demo");
      win.localStorage.setItem(
        "combinado.authUser",
        JSON.stringify({
          id: 1,
          nome: "Demo",
          email: "demo@test.com",
          perfil: "ROLE_USUARIO",
        }),
      );
    });
    cy.visit("/");
    cy.contains("Sair").click();
    cy.url().should("include", "/login");
    cy.window().then((win) => {
      expect(win.localStorage.getItem("combinado.jwt")).to.be.null;
      expect(win.localStorage.getItem("combinado.authUser")).to.be.null;
    });
    cy.visit("/perfil");
    cy.url().should("include", "/login");
    cy.window().then((win) => {
      expect(win.localStorage.getItem("combinado.authUser")).to.be.null;
    });
  });
});
