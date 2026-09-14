import type { StatusCombinado } from "../../types/combinado";
import { STATUS_LABELS } from "../../types/combinado";

const STATUS_CLASS: Record<StatusCombinado, string> = {
  RASCUNHO: "bg-line text-muted",
  AGUARDANDO_CONFIRMACAO: "bg-warn-soft text-warn",
  ATIVO: "bg-mint-soft text-mint-dark",
  CONCLUIDO: "bg-mint text-white",
  CANCELADO: "bg-danger-soft text-danger",
};

export function StatusBadge({ status }: { status: StatusCombinado }) {
  return (
    <span className={`inline-flex rounded-full px-2.5 py-1 text-xs font-semibold ${STATUS_CLASS[status]}`}>
      {STATUS_LABELS[status]}
    </span>
  );
}
