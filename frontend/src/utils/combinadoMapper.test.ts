import { describe, expect, it } from "vitest";
import { buildCreateBody, buildPublishBody } from "./combinadoMapper";

describe("buildCreateBody rascunho", () => {
  const criador = { nome: "Ana", email: "ana@test.com" };

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
});
