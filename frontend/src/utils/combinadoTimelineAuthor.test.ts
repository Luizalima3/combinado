import { describe, expect, it } from "vitest";
import type { CombinadoHistoricoEvento } from "../types/combinado";
import { orderChronologically } from "./combinadoTimeline";

describe("Exibição de nome do autor na timeline (#32355)", () => {
  it("preserva o nome do autor em cada evento ordenado e permite fallback quando vazio", () => {
    const eventos: CombinadoHistoricoEvento[] = [
      {
        id: 2,
        autorNome: "Ana Participante",
        descricao: "Status alterado de AGUARDANDO_CONFIRMACAO para ATIVO.",
        acao: "ALTERACAO_STATUS",
        ocorridoEm: "2026-10-21T14:00:00Z",
      },
      {
        id: 1,
        autorNome: "Felipe Criador",
        descricao: "Combinado criado.",
        acao: "CRIACAO",
        ocorridoEm: "2026-10-21T12:00:00Z",
      },
    ];

    const ordenados = orderChronologically(eventos);
    expect(ordenados[0].autorNome).toBe("Felipe Criador");
    expect(ordenados[1].autorNome).toBe("Ana Participante");
    expect(`Por ${ordenados[0].autorNome || "Usuário"}`).toBe("Por Felipe Criador");
  });
});
