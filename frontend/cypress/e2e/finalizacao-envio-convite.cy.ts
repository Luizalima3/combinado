/// <reference types="cypress" />
// Task #31414 — Teste Cypress para finalização e envio do convite

describe("Finalização do combinado e envio de convites (#31414)", () => {
  it("preenche todos os campos obrigatórios, seleciona participante e envia convite com sucesso", () => {
    const criadorEmail = `criador.envio.${Date.now()}@combinado.app`;
    const convidadoEmail = `convidado.envio.${Date.now()}@combinado.app`;

    cy.request("POST", "/api/usuarios", {
      nome: "Convidado Envio",
      email: convidadoEmail,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });

    cy.loginViaApi("Criador Envio", criadorEmail).then((token) => {
      cy.visit("/combinados/novo", {
        onBeforeLoad(win) {
          win.localStorage.setItem("combinado.jwt", token);
          win.localStorage.setItem(
            "combinado.authUser",
            JSON.stringify({ id: 1, nome: "Criador Envio", email: criadorEmail, perfil: "ROLE_USUARIO" }),
          );
        },
      });

      cy.get('input[placeholder="Ex: Empréstimo da furadeira"]').type("Combinado Completo E2E");
      cy.contains("button", /Outros|Empréstimo|Despesa/i).first().click();
      cy.get('input[type="date"]').type("2027-12-31");
      cy.get('input[placeholder*="mail" i], input[placeholder*="articipante" i]')
        .first()
        .type(convidadoEmail)
        .type("{enter}");
      cy.contains("button", "Enviar convites").click();
      cy.contains("button", /Enviar para 1 participante/i).click();
      cy.contains("Combinado criado e convites enviados.").should("be.visible");
    });
  });
});
