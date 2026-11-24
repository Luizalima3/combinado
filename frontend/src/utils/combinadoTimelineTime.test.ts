import { describe, expect, it } from "vitest";
import { formatEventDateTime } from "./combinadoTimeline";

describe("Exibição de hora nos eventos da timeline (#32357)", () => {
  it("formata corretamente a hora e os minutos no padrão 24h (HH:mm)", () => {
    expect(formatEventDateTime("2026-10-21T09:05:00")).toContain("09:05");
    expect(formatEventDateTime("2026-10-21T22:45:00")).toContain("22:45");
  });

  it("retorna mensagem padrão quando a data/hora é inválida", () => {
    expect(formatEventDateTime("data-invalida")).toBe("Data indisponível");
  });
});
