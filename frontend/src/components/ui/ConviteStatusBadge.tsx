import type { ConviteStatus } from "../../types/combinado";

const LABEL: Record<ConviteStatus, string> = {
  PENDENTE: "Pendente",
  ACEITO: "Aceito",
  RECUSADO: "Recusado",
};

const CLASS: Record<ConviteStatus, string> = {
  PENDENTE: "bg-warn-soft text-warn border-warn/30",
  ACEITO: "bg-mint-soft text-mint-dark border-mint/30",
  RECUSADO: "bg-danger-soft text-danger border-danger/30",
};

export function ConviteStatusBadge({ status }: { status: ConviteStatus }) {
  return (
    <span
      className={`inline-flex rounded-full border px-2.5 py-1 text-xs font-semibold ${CLASS[status]}`}
    >
      {LABEL[status]}
    </span>
  );
}
