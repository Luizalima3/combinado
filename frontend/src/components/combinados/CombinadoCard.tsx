import { useMemo } from "react";
import { Link } from "react-router-dom";
import type { Combinado, ConviteStatus, UsuarioResumo } from "../../types/combinado";
import { CATEGORIA_LABELS, STATUS_LABELS } from "../../types/combinado";
import { conviteStatusForUser } from "../../utils/combinadoMapper";
import { StatusBadge } from "../ui/StatusBadge";

function formatDate(value?: string) {
  if (!value) return "Sem prazo";
  const date = new Date(`${value}T00:00:00`);
  if (Number.isNaN(date.getTime())) return value;
  return date.toLocaleDateString("pt-BR");
}

function participantCount(combinado: Combinado) {
  const extras = combinado.extras.participantes.filter(
    (p) => p.nome.trim().toLowerCase() !== combinado.criadorNome.trim().toLowerCase(),
  );
  if (extras.length) return extras.length;
  if (!combinado.participanteNome || combinado.participanteNome === "—") return 0;
  return combinado.participanteNome.split(",").filter(Boolean).length;
}

const CONVITE_CLASS: Record<ConviteStatus, string> = {
  PENDENTE: "border-amber-300 bg-warn-soft",
  ACEITO: "border-mint/40",
  RECUSADO: "border-danger/30",
};

interface CombinadoCardProps {
  combinado: Combinado;
  currentUser: UsuarioResumo;
  variant: "criado" | "recebido";
  onAccept?: (combinado: Combinado) => void;
  onRefuse?: (combinado: Combinado) => void;
  onOpen?: (combinado: Combinado) => void;
  busy?: boolean;
}

export function CombinadoCard({
  combinado,
  currentUser,
  variant,
  onAccept,
  onRefuse,
  onOpen,
  busy,
}: CombinadoCardProps) {
  const convite = useMemo(
    () => conviteStatusForUser(combinado, currentUser),
    [combinado, currentUser],
  );
  const pending = variant === "recebido" && convite === "PENDENTE";

  return (
    <article
      className={`rounded-2xl border bg-card p-5 shadow-sm transition hover:shadow-md ${
        pending ? CONVITE_CLASS.PENDENTE : "border-line"
      }`}
    >
      <div className="flex flex-wrap items-start justify-between gap-3">
        <div>
          <h3 className="text-base font-semibold text-ink">{combinado.titulo}</h3>
          <p className="mt-1 text-sm text-muted">
            {CATEGORIA_LABELS[combinado.categoria]} · Prazo {formatDate(combinado.dataPrazo)}
          </p>
        </div>
        <StatusBadge status={combinado.status} />
      </div>

      {variant === "recebido" ? (
        <p className="mt-3 text-sm text-ink">
          Convite de <strong>{combinado.criadorNome}</strong>
          {pending ? (
            <span className="ml-2 rounded-full bg-warn px-2 py-0.5 text-[11px] font-semibold text-white">
              Novo
            </span>
          ) : null}
        </p>
      ) : null}

      <p className="mt-3 line-clamp-2 text-sm text-muted">
        {combinado.descricao || "Sem descrição."}
      </p>

      <div className="mt-4 flex flex-wrap items-center justify-between gap-3 text-sm text-muted">
        <span>{participantCount(combinado)} participante(s)</span>
        {variant === "recebido" ? (
          <span>
            Convite:{" "}
            <strong className="text-ink">
              {convite === "ACEITO" ? "Aceito" : convite === "RECUSADO" ? "Recusado" : "Pendente"}
            </strong>
          </span>
        ) : (
          <span>{STATUS_LABELS[combinado.status]}</span>
        )}
      </div>

      <div className="mt-4 flex flex-wrap gap-2">
        <button
          type="button"
          className="rounded-lg border border-line px-3 py-1.5 text-sm font-medium text-primary hover:bg-primary-soft"
          onClick={() => onOpen?.(combinado)}
        >
          Ver convite
        </button>
        {combinado.status === "RASCUNHO" ? (
          <Link
            to={`/combinados/novo?from=${combinado.id}`}
            className="rounded-lg border border-line px-3 py-1.5 text-sm font-medium text-ink hover:bg-bg"
          >
            Continuar rascunho
          </Link>
        ) : null}
        {pending ? (
          <>
            <button
              type="button"
              disabled={busy}
              className="rounded-lg bg-mint px-3 py-1.5 text-sm font-semibold text-white hover:bg-mint-dark disabled:opacity-60"
              onClick={() => onAccept?.(combinado)}
            >
              Aceitar
            </button>
            <button
              type="button"
              disabled={busy}
              className="rounded-lg bg-danger px-3 py-1.5 text-sm font-semibold text-white hover:opacity-90 disabled:opacity-60"
              onClick={() => onRefuse?.(combinado)}
            >
              Recusar
            </button>
          </>
        ) : null}
      </div>
    </article>
  );
}
