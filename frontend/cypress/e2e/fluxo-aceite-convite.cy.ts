/// <reference types="cypress" />
// Task #31416 — Automatizar com Cypress fluxo de aceite de convite

describe("Fluxo de aceite de convite via interface (#31416)", () => {
  it("permite ao convidado aceitar um convite pendente e ativa o combinado", () => {
    const criadorEmail = `criador.aceite.${Date.now()}@combinado.app`;
    const convidadoEmail = `convidado.aceite.${Date.now()}@combinado.app`;
    const titulo = `Combinado Aceite ${Date.now()}`;

    cy.request("POST", "/api/usuarios", {
      nome: "Convidado Aceite",
      email: convidadoEmail,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });

    cy.loginViaApi("Criador Aceite", criadorEmail).then((criadorToken) => {
      cy.request({
        method: "GET",
        url: `/api/usuarios/buscar?q=Convidado`,
        headers: { Authorization: `Bearer ${criadorToken}` },
      }).then((busca) => {
        const convidado = busca.body.find((u: { email: string }) => u.email === convidadoEmail);
        cy.request({
          method: "POST",
          url: "/api/combinados",
          headers: { Authorization: `Bearer ${criadorToken}` },
          body: {
            titulo,
            descricao: "Fluxo de aceite",
            categoria: "OUTRO",
            dataPrazo: "2027-12-31",
            status: "AGUARDANDO_CONFIRMACAO",
            participantesIds: [convidado.id],
          },
        }).then(() => {
          cy.loginViaApi("Convidado Aceite", convidadoEmail).then((convidadoToken) => {
            cy.visit("/", {
              onBeforeLoad(win) {
                win.localStorage.setItem("combinado.jwt", convidadoToken);
                win.localStorage.setItem(
                  "combinado.authUser",
                  JSON.stringify({
                    id: convidado.id,
                    nome: "Convidado Aceite",
                    email: convidadoEmail,
                    perfil: "ROLE_USUARIO",
                  }),
                );
              },
            });

            cy.contains("button", "Convites recebidos").click();
            cy.contains(titulo).should("be.visible");
            cy.contains("article", titulo).contains("button", "Aceitar").click();
            cy.contains("Você aceitou o convite.").should("be.visible");
          });
        });
      });
    });
  });
});
