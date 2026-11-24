/// <reference types="cypress" />
// Task #31420 — Validar atualização da linha do tempo e status CONCLUÍDO via Cypress

describe("Atualização da linha do tempo e status CONCLUÍDO (#31420)", () => {
  it("atualiza o badge para Concluído e exibe os eventos na Linha do tempo após encerrar o combinado", () => {
    const criadorEmail = `timeline.criador.${Date.now()}@combinado.app`;
    const convidadoEmail = `timeline.convidado.${Date.now()}@combinado.app`;
    const titulo = `Timeline Concluido ${Date.now()}`;

    cy.request("POST", "/api/usuarios", {
      nome: "Convidado Timeline",
      email: convidadoEmail,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });

    cy.loginViaApi("Criador Timeline", criadorEmail).then((criadorToken) => {
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
            descricao: "Verificação da linha do tempo",
            categoria: "OUTRO",
            dataPrazo: "2027-12-31",
            status: "AGUARDANDO_CONFIRMACAO",
            participantesIds: [convidado.id],
          },
        }).then((criado) => {
          const combinadoId = criado.body.id as number;
          cy.loginViaApi("Convidado Timeline", convidadoEmail).then((convidadoToken) => {
            cy.request({
              method: "PATCH",
              url: `/api/combinados/${combinadoId}/aceitar`,
              headers: { Authorization: `Bearer ${convidadoToken}` },
            });

            cy.visit(`/combinados/${combinadoId}`, {
              onBeforeLoad(win) {
                win.localStorage.setItem("combinado.jwt", criadorToken);
                win.localStorage.setItem(
                  "combinado.authUser",
                  JSON.stringify({
                    id: criado.body.criador?.id ?? 1,
                    nome: "Criador Timeline",
                    email: criadorEmail,
                    perfil: "ROLE_USUARIO",
                  }),
                );
              },
            });

            cy.contains("h2", "Linha do tempo").should("be.visible");
            cy.contains("Combinado criado").should("be.visible");
            cy.contains("button", "Concluir combinado").click();
            cy.get('input[type="checkbox"]').check();
            cy.contains("button", "Confirmar encerramento").click();
            cy.contains("Concluído").should("be.visible");
            cy.contains("Status alterado de ATIVO para CONCLUIDO.").should("be.visible");
          });
        });
      });
    });
  });
});
