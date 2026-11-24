/// <reference types="cypress" />

declare global {
  namespace Cypress {
    interface Chainable {
      /**
       * Cadastra e autentica um usuário via API e injeta o token JWT no localStorage (#31409).
       */
      loginViaApi(nome: string, email: string, senha?: string): Chainable<string>;
    }
  }
}

Cypress.Commands.add("loginViaApi", (nome: string, email: string, senha = "senha123") => {
  return cy
    .request({
      method: "POST",
      url: "/api/usuarios",
      body: { nome, email, senha, confirmacaoSenha: senha },
      failOnStatusCode: false,
    })
    .then(() =>
      cy
        .request("POST", "/api/auth/login", { email, senha })
        .its("body.token")
        .then((token: string) => {
          window.localStorage.setItem("combinado.jwt", token);
          window.localStorage.setItem(
            "combinado.authUser",
            JSON.stringify({ id: 1, nome, email, perfil: "ROLE_USUARIO" }),
          );
          return token;
        }),
    );
});

export {};
