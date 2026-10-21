import type { CategoriaCombinado } from "../../types/combinado";
import { CATEGORIA_LABELS } from "../../types/combinado";

const CATEGORY_ICON: Record<CategoriaCombinado, string> = {
  EMPRESTIMO_OBJETO: "📦",
  DIVISAO_DESPESA: "💰",
  PRESTACAO_SERVICO: "🛠",
  CUIDADO_ANIMAL: "🐾",
  USO_ESPACO: "🏠",
  ENTREGA_DEVOLUCAO: "🚚",
  OUTRO: "✨",
};

export function CategoryBadge({ categoria }: { categoria?: CategoriaCombinado | null }) {
  if (!categoria) {
    return (
      <span className="inline-flex items-center gap-1.5 rounded-full bg-line px-2.5 py-1 text-xs font-semibold text-muted">
        Sem categoria
      </span>
    );
  }
  return (
    <span className="inline-flex items-center gap-1.5 rounded-full bg-primary-soft px-2.5 py-1 text-xs font-semibold text-primary-dark">
      <span aria-hidden>{CATEGORY_ICON[categoria]}</span>
      {CATEGORIA_LABELS[categoria]}
    </span>
  );
}
