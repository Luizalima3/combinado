import { createElement } from "react";
import { renderToStaticMarkup } from "react-dom/server";
import { describe, expect, it } from "vitest";

import { CategoryBadge } from "./CategoryBadge";
import { ConviteStatusBadge } from "./ConviteStatusBadge";
import { StatusBadge } from "./StatusBadge";
import type {
  CategoriaCombinado,
  ConviteStatus,
  StatusCombinado,
} from "../../types/combinado";
import {
  CATEGORIA_LABELS,
  STATUS_LABELS,
} from "../../types/combinado";

const combinadoStatuses: Array<[StatusCombinado, string]> = [
  ["RASCUNHO", "bg-line text-muted"],
  ["AGUARDANDO_CONFIRMACAO", "bg-warn-soft text-warn"],
  ["ATIVO", "bg-mint-soft text-mint-dark"],
  ["CONCLUIDO", "bg-mint text-white"],
  ["CANCELADO", "bg-danger-soft text-danger"],
];

const conviteStatuses: Array<[ConviteStatus, string, string]> = [
  ["PENDENTE", "Pendente", "bg-warn-soft text-warn border-warn/30"],
  ["ACEITO", "Aceito", "bg-mint-soft text-mint-dark border-mint/30"],
  ["RECUSADO", "Recusado", "bg-danger-soft text-danger border-danger/30"],
  ["EXPIRADO", "Expirado", "bg-line text-muted border-line"],
];

const categories: CategoriaCombinado[] = [
  "EMPRESTIMO_OBJETO",
  "DIVISAO_DESPESA",
  "PRESTACAO_SERVICO",
  "CUIDADO_ANIMAL",
  "USO_ESPACO",
  "ENTREGA_DEVOLUCAO",
  "OUTRO",
];

describe("indicator badge visual consistency", () => {
  it("renders every agreement status with the shared badge shape and typography", () => {
    combinadoStatuses.forEach(([status, colorClass]) => {
      const markup = renderToStaticMarkup(
        createElement(StatusBadge, { status }),
      );

      expect(markup).toContain(STATUS_LABELS[status]);
      expect(markup).toContain(
        "inline-flex shrink-0 rounded-full px-2.5 py-1 text-xs font-semibold",
      );
      expect(markup).toContain(colorClass);
    });
  });

  it("renders every invite status with the shared badge shape, border, and typography", () => {
    conviteStatuses.forEach(([status, label, colorClass]) => {
      const markup = renderToStaticMarkup(
        createElement(ConviteStatusBadge, { status }),
      );

      expect(markup).toContain(label);
      expect(markup).toContain(
        "inline-flex shrink-0 rounded-full border px-2.5 py-1 text-xs font-semibold",
      );
      expect(markup).toContain(colorClass);
    });
  });

  it("renders every category with consistent badge shape and typography", () => {
    categories.forEach((categoria) => {
      const markup = renderToStaticMarkup(
        createElement(CategoryBadge, { categoria }),
      );

      expect(markup).toContain(CATEGORIA_LABELS[categoria]);
      expect(markup).toContain(
        "inline-flex items-center gap-1.5 rounded-full bg-primary-soft px-2.5 py-1 text-xs font-semibold text-primary-dark",
      );
    });
  });

  it("uses the same visual treatment when a category is absent", () => {
    const markup = renderToStaticMarkup(
      createElement(CategoryBadge, { categoria: null }),
    );

    expect(markup).toContain("Sem categoria");
    expect(markup).toContain(
      "inline-flex items-center gap-1.5 rounded-full bg-line px-2.5 py-1 text-xs font-semibold text-muted",
    );
  });
});
