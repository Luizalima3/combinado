import { describe, expect, it } from "vitest";
import { getTimelineEventPresentation } from "./combinadoTimeline";

describe("Mapeamento de ícones da timeline (#32353)", () => {
  it("mapeia corretamente os ícones de criação, alteração de status, conclusão, cancelamento e fallback", () => {
    expect(getTimelineEventPresentation("CRIACAO").icon).toBe("+");
    expect(getTimelineEventPresentation("ALTERACAO_STATUS").icon).toBe("↻");
    expect(getTimelineEventPresentation("CONCLUSAO").icon).toBe("✓");
    expect(getTimelineEventPresentation("CANCELAMENTO").icon).toBe("×");
    expect(getTimelineEventPresentation("OUTRO_EVENTO").icon).toBe("•");
  });
});
