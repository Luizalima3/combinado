import { describe, expect, it } from "vitest";
import {
  buildCreateBody,
  buildPublishBody,
  mapCombinado,
  mapConviteRecebido,
  userIsCreator,
  userIsParticipant,
} from "./combinadoMapper";

describe("buildCreateBody rascunho", () => {
  const criador = { nome: "Ana", email: "ana@test.com" };

  it("inclui IDs de participantes cadastrados ao criar combinado", () => {
    const body = buildCreateBody(criador, {
      titulo: "Acordo",
      descricao: "Detalhe",
      categoria: "OUTRO",
      participantes: [
        { id: 9, nome: "Bob", email: "bob@test.com" },
        { nome: "Convidado externo", email: "externo@test.com" },
      ],
      rascunho: false,
    });

    expect(body.participantesIds).toEqual([9]);
  });

  it("envia status RASCUNHO sem exigir categoria ou participantes", () => {
    const body = buildCreateBody(criador, {
      titulo: "",
      descricao: "Parcial",
      categoria: "",
      participantes: [],
      rascunho: true,
    });
    expect(body.status).toBe("RASCUNHO");
    expect(body.categoria).toBeNull();
    expect(body.titulo).toBe("Rascunho em edição");
  });

  it("monta corpo de publicação com participantesIds", () => {
    const body = buildPublishBody(
      { nome: "Ana", email: "ana@test.com" },
      {
        titulo: "Acordo",
        descricao: "Detalhe",
        categoria: "OUTRO",
        dataPrazo: "2026-12-01",
        participantes: [{ id: 9, nome: "Bob", email: "bob@test.com" }],
      },
    );
    expect(body.participantesIds).toEqual([9]);
    expect(body.categoria).toBe("OUTRO");
  });

  it("não confunde criador e participante que têm o mesmo nome", () => {
    const combinado = mapCombinado({
      id: 1,
      titulo: "Acordo",
      criadorNome: "Alex Silva",
      participanteNome: "Alex Silva",
      status: "AGUARDANDO_CONFIRMACAO",
      responsabilidades: JSON.stringify({
        participantes: [
          { nome: "Alex Silva", email: "criador@example.com" },
          { nome: "Alex Silva", email: "participante@example.com" },
        ],
      }),
    });

    expect(userIsCreator(combinado, { nome: "Alex Silva", email: "participante@example.com" })).toBe(false);
    expect(userIsParticipant(combinado, { nome: "Alex Silva", email: "participante@example.com" })).toBe(true);
    expect(userIsCreator(combinado, { nome: "Alex Silva", email: "criador@example.com" })).toBe(true);
  });

  it("inclui convidados pendentes retornados pelo detalhe do backend", () => {
    const combinado = mapCombinado({
      id: 2,
      titulo: "Acordo",
      criadorNome: "Ana",
      participanteNome: "Bob",
      status: "AGUARDANDO_CONFIRMACAO",
      responsabilidades: JSON.stringify({
        participantes: [
          { nome: "Ana", email: "ana@test.com", conviteStatus: "ACEITO" },
          { nome: "Bob", email: "bob@test.com", conviteStatus: "ACEITO" },
        ],
      }),
      participantes: [
        { id: 9, nome: "Bob", email: "bob@test.com", status: "PENDENTE" },
        { id: 10, nome: "Carol", email: "carol@test.com", status: "PENDENTE" },
      ],
    });

    expect(combinado.extras.participantes).toEqual([
      { nome: "Ana", email: "ana@test.com", conviteStatus: "ACEITO" },
      { id: 9, nome: "Bob", email: "bob@test.com", conviteStatus: "PENDENTE" },
      { id: 10, nome: "Carol", email: "carol@test.com", conviteStatus: "PENDENTE" },
    ]);
  });

  it("mapeia o convite recebido pelo status persistido no backend", () => {
    const combinado = mapConviteRecebido(
      {
        id: 21,
        combinadoId: 7,
        titulo: "Acordo",
        categoria: "OUTRO",
        nomeCriador: "Ana",
        prazo: "2026-12-01",
        statusCombinado: "AGUARDANDO_CONFIRMACAO",
        status: "PENDENTE",
        dataEnvio: "2026-11-23T18:00:00",
      },
      { id: 9, nome: "Bruno", email: "bruno@test.com" },
    );

    expect(combinado.id).toBe(7);
    expect(combinado.extras.participantes).toEqual([
      { id: 9, nome: "Bruno", email: "bruno@test.com", conviteStatus: "PENDENTE" },
    ]);
  });
});
