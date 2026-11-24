import { describe, expect, it } from "vitest";
import { formatRelativeDate } from "./combinadoTimeline";

describe("Função de data relativa da timeline (#32359)", () => {
  const ref = new Date("2026-10-21T15:00:00Z");

  it("retorna 'agora mesmo' para eventos ocorridos há menos de 60 segundos", () => {
    expect(formatRelativeDate("2026-10-21T14:59:30Z", ref)).toBe("agora mesmo");
  });

  it("retorna minutos, horas e dias relativos corretamente", () => {
    expect(formatRelativeDate("2026-10-21T14:45:00Z", ref)).toBe("há 15 min");
    expect(formatRelativeDate("2026-10-21T12:00:00Z", ref)).toBe("há 3 h");
    expect(formatRelativeDate("2026-10-20T15:00:00Z", ref)).toBe("há 1 dia");
    expect(formatRelativeDate("2026-10-16T15:00:00Z", ref)).toBe("há 5 dias");
  });

  it("retorna 'Data indisponível' para timestamp inválido", () => {
    expect(formatRelativeDate("invalido", ref)).toBe("Data indisponível");
  });
});
