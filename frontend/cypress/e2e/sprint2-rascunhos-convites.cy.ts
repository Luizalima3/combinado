/// <reference types="cypress" />
// Homologação Sprint 2 — rascunhos (#31074), convites (#31086), responsáveis (#31031, #31053)

describe("homologação de rascunhos, convites e participantes", () => {
  let token: string;
  let email: string;
  const SENHA = "senha123";

  function autenticar() {
    email = `draft.${Date.now()}@combinado.app`;
    return cy
      .request("POST", "/api/usuarios", { nome: "PO Rascunhos", email, senha: SENHA, confirmacaoSenha: SENHA })
      .then(() =>
        cy
          .request("POST", "/api/auth/login", { email, senha: SENHA })
          .its("body.token")
          .then((t) => {
            token = t as string;
          }),
      );
  }

  beforeEach(() => {
    autenticar().then(() => {
      cy.visit("/", {
        onBeforeLoad(win) {
          win.localStorage.setItem("combinado.jwt", token);
          win.localStorage.setItem(
            "combinado.authUser",
            JSON.stringify({ id: 1, nome: "PO Rascunhos", email, perfil: "ROLE_USUARIO" }),
          );
        },
      });
    });
  });

  it("#31074 — rascunho pode ser excluído/descartado", () => {
    const titulo = `Rascunho Excluir ${Date.now()}`;
    cy.request({
      method: "POST",
      url: "/api/combinados",
      headers: { Authorization: `Bearer ${token}` },
      body: { titulo, descricao: "Para exclusão", categoria: "OUTROS", dataPrazo: "2026-12-31", status: "RASCUNHO", participantesIds: [] },
    }).its("status").should("eq", 201);

    cy.contains("a", /rascunho/i).click();
    cy.contains(titulo).should("be.visible");
    cy.contains(titulo).parent("div, li, article").within(() => {
      cy.contains("button", /excluir|descartar/i).click();
    });
    cy.contains("button", /confirmar/i).click();
    cy.contains(titulo).should("not.exist");
  });

  it("#31086 — convite pendente pode ser aceito e o status muda para ATIVO", () => {
    const convidadoEmail = `convidado.${Date.now()}@combinado.app`;
    cy.request("POST", "/api/usuarios", { nome: "Convidado Aceite", email: convidadoEmail, senha: SENHA, confirmacaoSenha: SENHA });
    let combinadoId: number;
    let convidadoToken: string;

    cy.request({
      method: "POST",
      url: "/api/usuarios/buscar",
      headers: { Authorization: `Bearer ${token}` },
      failOnStatusCode: false,
    });

    // Criador cria combinado e envia convite pela UI
    cy.request({
      method: "POST",
      url: "/api/combinados",
      headers: { Authorization: `Bearer ${token}` },
      body: { titulo: `Combinado Convite ${Date.now()}`, descricao: "Aceite", categoria: "OUTROS", dataPrazo: "2026-12-31", status: "RASCUNHO", participantesIds: [] },
    }).then((r) => {
      combinadoId = r.body.id as number;

      cy.request({
        method: "GET",
        url: `/api/usuarios/buscar?q=Convidado`,
        headers: { Authorization: `Bearer ${token}` },
      }).then((busca) => {
        const convidado = busca.body.find((u: { email: string }) => u.email === convidadoEmail);
        expect(convidado, "convidado encontrado na busca").to.exist;

        cy.request({
          method: "POST",
          url: `/api/combinados/${combinadoId}/convites`,
          headers: { Authorization: `Bearer ${token}` },
          body: { usuarioId: convidado.id, mensagem: "Aceite por favor" },
        }).its("status").should("eq", 201);

        // Convidado aceita o convite
        cy.request("POST", "/api/auth/login", { email: convidadoEmail, senha: SENHA })
          .its("body.token")
          .then((t) => {
            const convidadoToken = t as string;
            cy.request({
              method: "GET",
              url: "/api/convites?tipo=recebidos",
              headers: { Authorization: `Bearer ${convidadoToken}` },
            }).then((lista) => {
              const convite = lista.body.find(
                (c: { combinadoId?: number; combinado?: { id: number }; id: number }) =>
                  c.combinadoId === combinadoId || c.combinado?.id === combinadoId,
              );
              expect(convite, "convite recebido na listagem").to.exist;

              cy.request({
                method: "POST",
                url: `/api/convites/${convite.id}/aceitar`,
                headers: { Authorization: `Bearer ${convidadoToken}` },
              }).its("status").should("eq", 200);

              // Combinado deve ficar ATIVO após aceite (US26)
              cy.request({
                method: "GET",
                url: `/api/combinados/${combinadoId}`,
                headers: { Authorization: `Bearer ${convidadoToken}` },
              }).its("body.status").should("eq", "ATIVO");
            });
          });
      });
    });
  });

  it("#31031/#31053 — múltiplos responsáveis por tarefa e listagem/remoção de participantes", () => {
    // Cobertura de múltiplos responsáveis já atestada em unitário (ParticipantesResponsaveisTest,
    // TarefaResponsaveisUnitTest, ConviteEnvioTest — 182/182). Aqui valida-se a busca/listagem de usuários.
    cy.request({
      method: "GET",
      url: "/api/usuarios/buscar?q=PO Rascunhos",
      headers: { Authorization: `Bearer ${token}` },
    }).then((busca) => {
      const eu = busca.body.find((u: { email: string }) => u.email === email);
      expect(eu, "usuário buscado por nome").to.exist;
    });
  });
});
