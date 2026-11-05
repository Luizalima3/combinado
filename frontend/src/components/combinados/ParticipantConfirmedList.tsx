import type { ParticipanteCombinado } from "../../types/combinado";
import { ConviteStatusBadge } from "../ui/ConviteStatusBadge";

function initials(nome: string) {
  const parts = nome.trim().split(/\s+/).filter(Boolean);
  if (!parts.length) return "?";
  if (parts.length === 1) return parts[0].slice(0, 2).toUpperCase();
  return `${parts[0][0]}${parts[parts.length - 1][0]}`.toUpperCase();
}

export function ParticipantConfirmedList({
  participantes,
  criadorNome,
}: {
  participantes: ParticipanteCombinado[];
  criadorNome: string;
}) {
  if (!participantes.length) {
    return <p className="text-sm text-muted">Nenhum participante convidado.</p>;
  }

  return (
    <ul className="space-y-2">
      {participantes.map((p) => (
        <li
          key={p.email}
          className="flex items-center justify-between gap-3 rounded-xl border border-line bg-bg px-3 py-2"
        >
          <div className="flex min-w-0 items-center gap-3">
            <span className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-primary-soft text-xs font-bold text-primary-dark">
              {initials(p.nome)}
            </span>
            <div className="min-w-0">
              <p className="truncate text-sm font-medium text-ink">{p.nome}</p>
              <p className="truncate text-xs text-muted">{p.email}</p>
            </div>
          </div>
          <ConviteStatusBadge status={p.conviteStatus ?? "ACEITO"} />
        </li>
      ))}
    </ul>
  );
}
