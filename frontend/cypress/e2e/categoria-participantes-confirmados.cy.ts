/// <reference types="cypress" />
// Testa retorno de categoria e lista de participantesConfirmados via endpoint GET /api/convites/{id}
// Cenarios: 0, 1 e multiplos participantes. Valida que so ACEITOS aparecem.

describe("categoria e participantesConfirmados no detalhe do convite", () => {
  let token: string;
  let email: string;
  const SENHA = "senha123";

  // -------------------------------------------------------------------------
  // Helpers
  // -------------------------------------------------------------------------

  function registrar(nome: string, emailConta: string) {
    return cy.request("POST", "/api/usuarios", {
      nome,
      email: emailConta,
      senha: SENHA,
      confirmacaoSenha: SENHA,
    });
  }

  function login(emailConta: string) {
    return cy
      .request("POST", "/api/auth/login", { email: emailConta, senha: SENHA })
      .its("body.token");
  }

  function criarCombinado(bearerToken: string, titulo: string) {
    return cy
      .request({
        method: "POST",
        url: "/api/combinados",
        headers: { Authorization: `Bearer ${bearerToken}` },
        body: {
          titulo,
          descricao: "Teste categoria/participantes",
          categoria: "OUTRO",
          dataPrazo: "2027-06-30",
          status: "RASCUNHO",
          participantesIds: [],
        },
      })
      .its("body.id") as Cypress.Chainable<number>;
  }

  function enviarConvite(bearerToken: string, combinadoId: number, usuarioId: number) {
    return cy.request({
      method: "POST",
      url: `/api/combinados/${combinadoId}/convites`,
      headers: { Authorization: `Bearer ${bearerToken}` },
      body: { usuarioId, mensagem: "Convite de teste" },
    });
  }

  function aceitarConvite(bearerToken: string, conviteId: number) {
    return cy.request({
      method: "POST",
      url: `/api/convites/${conviteId}/aceitar`,
      headers: { Authorization: `Bearer ${bearerToken}` },
    });
  }

  function recusarConvite(bearerToken: string, conviteId: number) {
    return cy.request({
      method: "POST",
      url: `/api/convites/${conviteId}/recusar`,
      headers: { Authorization: `Bearer ${bearerToken}` },
    });
  }

  function convitesRecebidos(bearerToken: string, combinadoId: number) {
    return cy
      .request({
        method: "GET",
        url: "/api/convites?tipo=recebidos",
        headers: { Authorization: `Bearer ${bearerToken}` },
      })
      .its("body")
      .then((lista: Array<{ combinadoId?: number; combinado?: { id: number }; id: number }>) =>
        lista.find(
          (c) => c.combinadoId === combinadoId || c.combinado?.id === combinadoId,
        ),
      );
  }

  // -------------------------------------------------------------------------
  // Setup: cria criador antes de todos os testes
  // -------------------------------------------------------------------------

  before(() => {
    email = `cat.criador.${Date.now()}@combinado.app`;
    registrar("Criador Cat", email);
    login(email).then((t) => {
      token = t as string;
    });
  });

  // -------------------------------------------------------------------------
  // 1. Retorno de categoria
  // -------------------------------------------------------------------------

  it("deve retornar categoria OUTRO no detalhe do convite", () => {
    const convidadoEmail = `cat.conv.${Date.now()}@combinado.app`;
    let conviteId: number;

    registrar("Convidado Cat", convidadoEmail);
    let convidadoId: number;

    cy.request({
      method: "GET",
      url: `/api/usuarios/buscar?q=Convidado Cat`,
      headers: { Authorization: `Bearer ${token}` },
    })
      .its("body")
      .then((lista: Array<{ email: string; id: number }>) => {
        // Busca pelo email exato pois pode haver varios "Convidado"
        const u = lista.find((x) => x.email === convidadoEmail);
        if (!u) {
          // Tenta buscar por email diretamente
          return cy
            .request({
              method: "GET",
              url: `/api/usuarios/buscar?q=${encodeURIComponent(convidadoEmail)}`,
              headers: { Authorization: `Bearer ${token}` },
              failOnStatusCode: false,
            })
            .its("body")
            .then(
              (l: Array<{ email: string; id: number }>) =>
                l.find((x) => x.email === convidadoEmail) ?? { id: -1 },
            );
        }
        return cy.wrap(u);
      })
      .then((convidado: { id: number }) => {
        convidadoId = convidado.id;
        criarCombinado(token, `Combinado Categoria ${Date.now()}`).then((combinadoId) => {
          enviarConvite(token, combinadoId, convidadoId).then((resp) => {
            conviteId = (resp.body as { id: number }).id;

            cy.request({
              method: "GET",
              url: `/api/convites/${conviteId}`,
              headers: { Authorization: `Bearer ${token}` },
            })
              .its("body")
              .then((detalhe: { categoria: string }) => {
                expect(detalhe.categoria).to.equal("OUTRO");
              });
          });
        });
      });
  });

  // -------------------------------------------------------------------------
  // 2. Zero participantes confirmados
  // -------------------------------------------------------------------------

  it("deve retornar participantesConfirmados vazio quando ninguem aceitou", () => {
    const convidadoEmail = `cat.zero.${Date.now()}@combinado.app`;
    registrar("Zero Aceito", convidadoEmail);

    login(email).then((t) => {
      const bearerToken = t as string;

      cy.request({
        method: "GET",
        url: `/api/usuarios/buscar?q=Zero Aceito`,
        headers: { Authorization: `Bearer ${bearerToken}` },
        failOnStatusCode: false,
      })
        .its("body")
        .then((lista: Array<{ email: string; id: number }>) => {
          const u = lista.find((x) => x.email === convidadoEmail);
          expect(u, "usuario encontrado").to.exist;

          criarCombinado(bearerToken, `Combinado Zero ${Date.now()}`).then((combinadoId) => {
            enviarConvite(bearerToken, combinadoId, u!.id).then((resp) => {
              const conviteId = (resp.body as { id: number }).id;

              cy.request({
                method: "GET",
                url: `/api/convites/${conviteId}`,
                headers: { Authorization: `Bearer ${bearerToken}` },
              })
                .its("body.participantesConfirmados")
                .should("have.length", 0);
            });
          });
        });
    });
  });

  // -------------------------------------------------------------------------
  // 3. Exatamente um participante confirmado (ACEITO)
  // -------------------------------------------------------------------------

  it("deve retornar exatamente 1 participante confirmado quando um aceita", () => {
    const aceitanteEmail = `cat.um.${Date.now()}@combinado.app`;
    const outroEmail = `cat.outro.${Date.now()}@combinado.app`;
    registrar("Um Aceito", aceitanteEmail);
    registrar("Outro Pendente", outroEmail);

    login(email).then((criadorToken: string) => {
      // Busca IDs dos dois convidados
      cy.request({
        method: "GET",
        url: `/api/usuarios/buscar?q=Um Aceito`,
        headers: { Authorization: `Bearer ${criadorToken}` },
        failOnStatusCode: false,
      })
        .its("body")
        .then((lista: Array<{ email: string; id: number }>) => {
          const aceitante = lista.find((x) => x.email === aceitanteEmail);
          expect(aceitante, "aceitante encontrado").to.exist;

          cy.request({
            method: "GET",
            url: `/api/usuarios/buscar?q=Outro Pendente`,
            headers: { Authorization: `Bearer ${criadorToken}` },
            failOnStatusCode: false,
          })
            .its("body")
            .then((l2: Array<{ email: string; id: number }>) => {
              const outro = l2.find((x) => x.email === outroEmail);
              expect(outro, "outro encontrado").to.exist;

              criarCombinado(criadorToken, `Combinado Um ${Date.now()}`).then((combinadoId) => {
                // Envia convite para o aceitante
                enviarConvite(criadorToken, combinadoId, aceitante!.id);
                // Envia convite para o outro (ficara pendente)
                enviarConvite(criadorToken, combinadoId, outro!.id).then((respOutro) => {
                  const conviteOutroId = (respOutro.body as { id: number }).id;

                  // Aceitante aceita
                  login(aceitanteEmail).then((aceitanteToken: string) => {
                    convitesRecebidos(aceitanteToken, combinadoId).then(
                      (convite: { id: number } | undefined) => {
                        expect(convite, "convite do aceitante").to.exist;
                        aceitarConvite(aceitanteToken, convite!.id).then(() => {
                          // Verifica detalhe pelo convite do outro (pendente)
                          cy.request({
                            method: "GET",
                            url: `/api/convites/${conviteOutroId}`,
                            headers: { Authorization: `Bearer ${criadorToken}` },
                          })
                            .its("body")
                            .then(
                              (detalhe: {
                                participantesConfirmados: Array<{ nome: string; status: string }>;
                              }) => {
                                expect(detalhe.participantesConfirmados).to.have.length(1);
                                expect(detalhe.participantesConfirmados[0].status).to.equal(
                                  "ACEITO",
                                );
                              },
                            );
                        });
                      },
                    );
                  });
                });
              });
            });
        });
    });
  });

  // -------------------------------------------------------------------------
  // 4. Multiplos participantes confirmados
  // -------------------------------------------------------------------------

  it("deve retornar todos os aceitos quando multiplos participantes confirmam", () => {
    const a1Email = `cat.mult1.${Date.now()}@combinado.app`;
    const a2Email = `cat.mult2.${Date.now()}@combinado.app`;
    registrar("Mult Um", a1Email);
    registrar("Mult Dois", a2Email);

    login(email).then((criadorToken: string) => {
      cy.request({
        method: "GET",
        url: `/api/usuarios/buscar?q=Mult Um`,
        headers: { Authorization: `Bearer ${criadorToken}` },
        failOnStatusCode: false,
      })
        .its("body")
        .then((l1: Array<{ email: string; id: number }>) => {
          const a1 = l1.find((x) => x.email === a1Email);
          expect(a1, "a1 encontrado").to.exist;

          cy.request({
            method: "GET",
            url: `/api/usuarios/buscar?q=Mult Dois`,
            headers: { Authorization: `Bearer ${criadorToken}` },
            failOnStatusCode: false,
          })
            .its("body")
            .then((l2: Array<{ email: string; id: number }>) => {
              const a2 = l2.find((x) => x.email === a2Email);
              expect(a2, "a2 encontrado").to.exist;

              criarCombinado(criadorToken, `Combinado Mult ${Date.now()}`).then((combinadoId) => {
                enviarConvite(criadorToken, combinadoId, a1!.id);
                enviarConvite(criadorToken, combinadoId, a2!.id);

                login(a1Email).then((t1: string) => {
                  convitesRecebidos(t1, combinadoId).then((c1: { id: number } | undefined) => {
                    expect(c1).to.exist;
                    aceitarConvite(t1, c1!.id).then(() => {
                      login(a2Email).then((t2: string) => {
                        convitesRecebidos(t2, combinadoId).then(
                          (c2: { id: number } | undefined) => {
                            expect(c2).to.exist;
                            aceitarConvite(t2, c2!.id).then(() => {
                              // Consulta detalhe como criador — ambos devem aparecer
                              cy.request({
                                method: "GET",
                                url: `/api/convites/${c1!.id}`,
                                headers: { Authorization: `Bearer ${criadorToken}` },
                                failOnStatusCode: false,
                              })
                                .its("body.participantesConfirmados")
                                .then(
                                  (confirmados: Array<{ status: string }>) => {
                                    expect(confirmados.length).to.be.gte(2);
                                    confirmados.forEach((p) => {
                                      expect(p.status).to.equal("ACEITO");
                                    });
                                  },
                                );
                            });
                          },
                        );
                      });
                    });
                  });
                });
              });
            });
        });
    });
  });

  // -------------------------------------------------------------------------
  // 5. Somente ACEITOS — RECUSADO e PENDENTE excluidos
  // -------------------------------------------------------------------------

  it("somente ACEITOS devem aparecer em participantesConfirmados; RECUSADO e PENDENTE excluidos", () => {
    const acEmail = `cat.filt.ac.${Date.now()}@combinado.app`;
    const recEmail = `cat.filt.rec.${Date.now()}@combinado.app`;
    const pendEmail = `cat.filt.pend.${Date.now()}@combinado.app`;

    registrar("Filtro Aceito", acEmail);
    registrar("Filtro Recusado", recEmail);
    registrar("Filtro Pendente", pendEmail);

    login(email).then((criadorToken: string) => {
      // Busca os tres usuarios
      function buscar(q: string, emailAlvo: string) {
        return cy
          .request({
            method: "GET",
            url: `/api/usuarios/buscar?q=${encodeURIComponent(q)}`,
            headers: { Authorization: `Bearer ${criadorToken}` },
            failOnStatusCode: false,
          })
          .its("body")
          .then((lista: Array<{ email: string; id: number }>) =>
            lista.find((x) => x.email === emailAlvo),
          );
      }

      buscar("Filtro Aceito", acEmail).then((uAc: { id: number } | undefined) => {
        expect(uAc, "aceito encontrado").to.exist;
        buscar("Filtro Recusado", recEmail).then((uRec: { id: number } | undefined) => {
          expect(uRec, "recusado encontrado").to.exist;
          buscar("Filtro Pendente", pendEmail).then((uPend: { id: number } | undefined) => {
            expect(uPend, "pendente encontrado").to.exist;

            criarCombinado(criadorToken, `Combinado Filtro ${Date.now()}`).then((combinadoId) => {
              enviarConvite(criadorToken, combinadoId, uAc!.id);
              enviarConvite(criadorToken, combinadoId, uRec!.id);
              enviarConvite(criadorToken, combinadoId, uPend!.id).then((respPend) => {
                const convitePendId = (respPend.body as { id: number }).id;

                // Aceito aceita
                login(acEmail).then((tAc: string) => {
                  convitesRecebidos(tAc, combinadoId).then((cAc: { id: number } | undefined) => {
                    aceitarConvite(tAc, cAc!.id).then(() => {
                      // Recusado recusa
                      login(recEmail).then((tRec: string) => {
                        convitesRecebidos(tRec, combinadoId).then(
                          (cRec: { id: number } | undefined) => {
                            recusarConvite(tRec, cRec!.id).then(() => {
                              // Pendente nao faz nada; consultamos o convite do pendente
                              cy.request({
                                method: "GET",
                                url: `/api/convites/${convitePendId}`,
                                headers: { Authorization: `Bearer ${criadorToken}` },
                              })
                                .its("body.participantesConfirmados")
                                .then((confirmados: Array<{ status: string }>) => {
                                  expect(confirmados).to.have.length(1);
                                  expect(confirmados[0].status).to.equal("ACEITO");
                                });
                            });
                          },
                        );
                      });
                    });
                  });
                });
              });
            });
          });
        });
      });
    });
  });
});
