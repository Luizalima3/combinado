/// <reference types="cypress" />
// Task #31418 — Automatizar com Cypress fluxo de recusa de convite

describe("Fluxo de recusa de convite via interface (#31418)", () => {
  it("abre o modal de confirmação ao clicar em recusar e atualiza o status do convite para Recusado", () => {
    const criadorEmail = `criador.recusa.${Date.now()}@combinado.app`;
    const convidadoEmail = `convidado.recusa.${Date.now()}@combinado.app`;
    const titulo = `Combinado Recusa ${Date.now()}`;

    cy.request("POST", "/api/usuarios", {
      nome: "Convidado Recusa",
      email: convidadoEmail,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });

    cy.loginViaApi("Criador Recusa", criadorEmail).then((criadorToken) => {
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
            descricao: "Fluxo de recusa",
            categoria: "OUTRO",
            dataPrazo: "2027-12-31",
            status: "AGUARDANDO_CONFIRMACAO",
            participantesIds: [convidado.id],
          },
        }).then(() => {
          cy.loginViaApi("Convidado Recusa", convidadoEmail).then((convidadoToken) => {
            cy.visit("/", {
              onBeforeLoad(win) {
                win.localStorage.setItem("combinado.jwt", convidadoToken);
                win.localStorage.setItem(
                  "combinado.authUser",
                  JSON.stringify({
                    id: convidado.id,
                    nome: "Convidado Recusa",
                    email: convidadoEmail,
                    perfil: "ROLE_USUARIO",
                  }),
                );
              },
            });

            cy.contains("button", "Convites recebidos").click();
            cy.contains("article", titulo).contains("button", "Recusar").click();
            cy.contains("Confirmar recusa").should("be.visible");
            cy.get('textarea[placeholder*="motivo" i]').type("Não poderei participar");
            cy.contains("button", "Confirmar recusa").click();
            cy.contains("Convite recusado.").should("be.visible");
          });
        });
      });
    });
  });
});
