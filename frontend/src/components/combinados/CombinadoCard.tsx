import { useMemo } from "react";
import { Link } from "react-router-dom";
import type { Combinado, ConviteStatus, UsuarioResumo } from "../../types/combinado";
import { STATUS_LABELS } from "../../types/combinado";
import { conviteStatusForUser } from "../../utils/combinadoMapper";
import { CategoryBadge } from "../ui/CategoryBadge";
import { ConviteStatusBadge } from "../ui/ConviteStatusBadge";
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
  /** US64 / Task #31681: dispara a conclusão do combinado (criador OU participante aceito, status ATIVO). */
  onConclude?: (combinado: Combinado) => void;
  /** US67 / Task #31692: abre o modal de confirmação do cancelamento (apenas criador). */
  onCancel?: (combinado: Combinado) => void;
  busy?: boolean;
}

export function CombinadoCard({
  combinado,
  currentUser,
  variant,
  onAccept,
  onRefuse,
  onOpen,
  onConclude,
  onCancel,
  busy,
}: CombinadoCardProps) {
  const convite = useMemo(
    () => conviteStatusForUser(combinado, currentUser),
    [combinado, currentUser],
  );
  const pending = variant === "recebido" && convite === "PENDENTE";

  // US64 / Task #31681: botão "Concluir" visível apenas quando o combinado
  // está ATIVO e o usuário tem permissão (criador — CA01 US70; ou participante
  // com convite ACEITO — Task #32340/US71).
  const isCreator =
    combinado.criadorEmail?.toLowerCase() === currentUser.email.toLowerCase() ||
    combinado.criadorNome.trim().toLowerCase() === currentUser.nome.trim().toLowerCase();
  const podeConcluir =
    combinado.status === "ATIVO" && (isCreator || convite === "ACEITO");
  // US67 / CA #31691: botão "Cancelar" disponível em qualquer status, exceto
  // Concluído/Cancelado, e apenas para o criador (US70 / CA #31691).
  const podeCancelar =
    isCreator && combinado.status !== "CONCLUIDO" && combinado.status !== "CANCELADO";

  return (
    <article
      className={`rounded-2xl border bg-card p-5 shadow-sm transition hover:shadow-md ${
        pending ? CONVITE_CLASS.PENDENTE : "border-line"
      }`}
    >
      <div className="flex flex-wrap items-start justify-between gap-3">
        <div>
          <h3 className="text-base font-semibold text-ink">{combinado.titulo}</h3>
          <div className="mt-2 flex flex-wrap items-center gap-2 text-sm text-muted">
            <CategoryBadge categoria={combinado.categoria} />
            <span>Prazo {formatDate(combinado.dataPrazo)}</span>
          </div>
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
          <ConviteStatusBadge status={convite} />
        ) : (
          <span>{STATUS_LABELS[combinado.status]}</span>
        )}
      </div>

      <div className="mt-4 flex flex-wrap gap-2">
        <Link
          to={`/combinados/${combinado.id}`}
          className="rounded-lg border border-line px-3 py-1.5 text-sm font-medium text-ink hover:bg-bg"
        >
          Detalhes
        </Link>
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
        {podeConcluir ? (
          <button
            type="button"
            disabled={busy}
            className="rounded-lg bg-mint px-3 py-1.5 text-sm font-semibold text-white hover:bg-mint-dark disabled:opacity-60"
            onClick={() => onConclude?.(combinado)}
          >
            Concluir
          </button>
        ) : null}
        {podeCancelar && onCancel ? (
          <button
            type="button"
            disabled={busy}
            className="rounded-lg border border-danger px-3 py-1.5 text-sm font-semibold text-danger hover:bg-danger-soft disabled:opacity-60"
            onClick={() => onCancel(combinado)}
          >
            Cancelar
          </button>
        ) : null}
      </div>
    </article>
  );
}
