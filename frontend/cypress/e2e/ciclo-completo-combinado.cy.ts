/// <reference types="cypress" />
// Task #31419 — Cenário integrado E2E no Cypress para o ciclo completo do combinado

describe("Ciclo completo do combinado E2E (#31419)", () => {
  it("executa criação, envio de convite, aceite do participante e conclusão pelo criador", () => {
    const criadorEmail = `ciclo.criador.${Date.now()}@combinado.app`;
    const convidadoEmail = `ciclo.convidado.${Date.now()}@combinado.app`;
    const titulo = `Ciclo Completo ${Date.now()}`;

    cy.request("POST", "/api/usuarios", {
      nome: "Convidado Ciclo",
      email: convidadoEmail,
      senha: "senha123",
      confirmacaoSenha: "senha123",
    });

    cy.loginViaApi("Criador Ciclo", criadorEmail).then((criadorToken) => {
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
            descricao: "Ciclo completo E2E",
            categoria: "OUTRO",
            dataPrazo: "2027-12-31",
            status: "AGUARDANDO_CONFIRMACAO",
            participantesIds: [convidado.id],
          },
        }).then((criado) => {
          const combinadoId = criado.body.id as number;
          cy.loginViaApi("Convidado Ciclo", convidadoEmail).then((convidadoToken) => {
            cy.request({
              method: "PATCH",
              url: `/api/combinados/${combinadoId}/aceitar`,
              headers: { Authorization: `Bearer ${convidadoToken}` },
            }).its("status").should("eq", 200);

            cy.visit(`/combinados/${combinadoId}`, {
              onBeforeLoad(win) {
                win.localStorage.setItem("combinado.jwt", criadorToken);
                win.localStorage.setItem(
                  "combinado.authUser",
                  JSON.stringify({
                    id: criado.body.criador?.id ?? 1,
                    nome: "Criador Ciclo",
                    email: criadorEmail,
                    perfil: "ROLE_USUARIO",
                  }),
                );
              },
            });

            cy.contains("button", "Concluir combinado").click();
            cy.get('input[type="checkbox"]').check();
            cy.contains("button", "Confirmar encerramento").click();
            cy.contains("Combinado encerrado com sucesso.").should("be.visible");
          });
        });
      });
    });
  });
});
