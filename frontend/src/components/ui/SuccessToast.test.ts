import { describe, expect, it, vi } from "vitest";
import { renderToStaticMarkup } from "react-dom/server";
import { createElement } from "react";
import { SuccessToast } from "./SuccessToast";

describe("SuccessToast (#32331)", () => {
  it("exibe a mensagem de sucesso ao concluir o combinado com atributos de acessibilidade", () => {
    const onDismiss = vi.fn();
    const html = renderToStaticMarkup(
      createElement(SuccessToast, {
        toast: {
          id: 42,
          type: "success",
          message: "Combinado encerrado com sucesso.",
        },
        onDismiss,
      }),
    );

    expect(html).toContain("Combinado encerrado com sucesso.");
    expect(html).toContain('role="status"');
    expect(html).toContain("Sucesso: Combinado encerrado com sucesso.");
    expect(html).toContain("✓");
  });
});
