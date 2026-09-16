import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { CombinadoCard } from "../components/combinados/CombinadoCard";
import { EmptyState } from "../components/ui/EmptyState";
import { Modal } from "../components/ui/Modal";
import { CardSkeleton } from "../components/ui/Spinner";
import { StatusBadge } from "../components/ui/StatusBadge";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import {
  aceitarConvite,
  getCombinadoById,
  listCriados,
  listRecebidos,
  recusarConvite,
} from "../services/combinadoService";
import type { Combinado } from "../types/combinado";
import { CATEGORIA_LABELS } from "../types/combinado";
import { userIsCreator, userIsParticipant } from "../utils/combinadoMapper";

type Tab = "criados" | "recebidos";

export function DashboardPage() {
  const { user } = useAuth();
  const { push } = useToast();
  const [tab, setTab] = useState<Tab>("criados");
  const [criados, setCriados] = useState<Combinado[]>([]);
  const [recebidos, setRecebidos] = useState<Combinado[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [busyId, setBusyId] = useState<number | null>(null);
  const [invite, setInvite] = useState<Combinado | null>(null);
  const [acceptedInvite, setAcceptedInvite] = useState<Combinado | null>(null);
  const [refuseTarget, setRefuseTarget] = useState<Combinado | null>(null);
  const [refuseConfirmOpen, setRefuseConfirmOpen] = useState(false);
  const [refuseReason, setRefuseReason] = useState("");

  async function load() {
    setLoading(true);
    setError("");
    try {
      const [created, received] = await Promise.all([
        listCriados(user),
        listRecebidos(user),
      ]);
      setCriados(created);
      setRecebidos(received);
    } catch (err) {
      setError(getApiErrorMessage(err, "Não foi possível carregar os combinados."));
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    void load();
  }, [user.email]);

  const pendingCount = recebidos.filter(
    (item) => item.status === "AGUARDANDO_CONFIRMACAO",
  ).length;

  async function handleAccept(combinado: Combinado) {
    setBusyId(combinado.id);
    try {
      await aceitarConvite(combinado.id, user);
      push("success", "Convite aceito com sucesso.");
      setInvite(null);
      setAcceptedInvite(combinado);
      await load();
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível aceitar o convite."));
    } finally {
      setBusyId(null);
    }
  }

  async function handleRefuseConfirm() {
    if (!refuseTarget) return;
    setBusyId(refuseTarget.id);
    try {
      await recusarConvite(refuseTarget.id, user, refuseReason);
      push("info", "Convite recusado.");
      setRefuseTarget(null);
      setRefuseConfirmOpen(false);
      setRefuseReason("");
      setInvite(null);
      await load();
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível recusar o convite."));
    } finally {
      setBusyId(null);
    }
  }

  async function openInvite(combinado: Combinado) {
    try {
      const fresh = await getCombinadoById(combinado.id);
      if (!userIsCreator(fresh, user) && !userIsParticipant(fresh, user)) {
        window.dispatchEvent(new CustomEvent("combinado:forbidden"));
        return;
      }
      setInvite(fresh);
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível abrir o convite."));
    }
  }

  const list = tab === "criados" ? criados : recebidos;

  return (
    <section>
      <div className="mb-6 flex flex-wrap items-end justify-between gap-4">
        <div>
          <h1 className="text-2xl font-semibold text-ink">Meus combinados</h1>
          <p className="mt-1 text-sm text-muted">
            Acompanhe os acordos que você criou e os convites recebidos.
          </p>
        </div>
        <Link
          to="/combinados/novo"
          className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white hover:bg-primary-dark"
        >
          Novo combinado
        </Link>
      </div>

      <div className="mb-5 flex gap-2 border-b border-line">
        <TabButton active={tab === "criados"} onClick={() => setTab("criados")}>
          Combinados criados
        </TabButton>
        <TabButton active={tab === "recebidos"} onClick={() => setTab("recebidos")}>
          Convites recebidos
          {pendingCount ? (
            <span className="ml-2 rounded-full bg-warn px-2 py-0.5 text-[11px] text-white">
              {pendingCount}
            </span>
          ) : null}
        </TabButton>
      </div>

      {error ? (
        <div className="mb-4 rounded-xl border border-danger/30 bg-danger-soft px-4 py-3 text-sm text-danger">
          {error}
        </div>
      ) : null}

      {loading ? (
        <div className="grid gap-4 md:grid-cols-2">
          <CardSkeleton />
          <CardSkeleton />
        </div>
      ) : list.length === 0 ? (
        tab === "criados" ? (
          <EmptyState
            title="Você ainda não criou combinados"
            description="Crie o primeiro acordo com título, categoria, prazo e participantes."
            action={
              <Link
                to="/combinados/novo"
                className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white"
              >
                Criar combinado
              </Link>
            }
          />
        ) : (
          <EmptyState
            title="Você não possui convites de combinados no momento"
            description="Quando alguém te convidar, o convite aparece aqui para aceitar ou recusar."
          />
        )
      ) : (
        <div className="grid gap-4 md:grid-cols-2">
          {list.map((combinado) => (
            <CombinadoCard
              key={combinado.id}
              combinado={combinado}
              currentUser={user}
              variant={tab === "criados" ? "criado" : "recebido"}
              busy={busyId === combinado.id}
              onOpen={openInvite}
              onAccept={handleAccept}
              onRefuse={(item) => setRefuseTarget(item)}
            />
          ))}
        </div>
      )}

      {invite ? (
        <Modal title="Convite do combinado" onClose={() => setInvite(null)}>
          <p className="text-sm text-muted">Enviado por {invite.criadorNome}</p>
          <h3 className="mt-2 text-xl font-semibold text-ink">{invite.titulo}</h3>
          <p className="mt-2 text-sm text-ink">{invite.descricao || "Sem descrição."}</p>
          <div className="mt-4 flex flex-wrap gap-2 text-sm">
            <StatusBadge status={invite.status} />
            <span className="rounded-full bg-primary-soft px-2.5 py-1 text-primary">
              {CATEGORIA_LABELS[invite.categoria]}
            </span>
            <span className="rounded-full bg-bg px-2.5 py-1 text-muted">
              Prazo: {invite.dataPrazo ? new Date(`${invite.dataPrazo}T00:00:00`).toLocaleDateString("pt-BR") : "Não definido"}
            </span>
          </div>
          {invite.extras.mensagemConvite ? (
            <p className="mt-4 rounded-lg bg-bg p-3 text-sm text-ink">
              “{invite.extras.mensagemConvite}”
            </p>
          ) : null}
          <div className="mt-5 flex justify-end gap-2">
            {tab === "recebidos" && invite.status === "AGUARDANDO_CONFIRMACAO" ? (
              <>
                <button
                  type="button"
                  className="rounded-lg bg-danger px-3 py-2 text-sm font-semibold text-white"
                  onClick={() => {
                    setRefuseTarget(invite);
                    setRefuseConfirmOpen(true);
                  }}
                >
                  Recusar
                </button>
                <button
                  type="button"
                  className="rounded-lg bg-mint px-3 py-2 text-sm font-semibold text-white"
                  onClick={() => void handleAccept(invite)}
                >
                  Aceitar
                </button>
              </>
            ) : (
              <button
                type="button"
                className="rounded-lg bg-primary px-3 py-2 text-sm font-semibold text-white"
                onClick={() => setInvite(null)}
              >
                Fechar
              </button>
            )}
          </div>
        </Modal>
      ) : null}

      {acceptedInvite ? (
        <Modal title="Convite aceito" onClose={() => setAcceptedInvite(null)}>
          <div className="space-y-4">
            <div className="flex h-12 w-12 items-center justify-center rounded-full bg-mint/20 text-xl text-mint">
              ✓
            </div>
            <div>
              <p className="text-lg font-semibold text-ink">Você aceitou o convite.</p>
              <p className="mt-1 text-sm text-muted">
                {acceptedInvite.titulo} foi confirmado com sucesso e agora está ativo.
              </p>
            </div>
            <div className="rounded-xl bg-bg p-3 text-sm text-ink">
              <span className="font-medium">Combinado:</span> {acceptedInvite.titulo}
            </div>
            <div className="flex justify-end">
              <button
                type="button"
                className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white hover:bg-primary-dark"
                onClick={() => setAcceptedInvite(null)}
              >
                Fechar
              </button>
            </div>
          </div>
        </Modal>
      ) : null}

      {refuseConfirmOpen && refuseTarget ? (
        <Modal title="Confirmar recusa" onClose={() => {
          setRefuseConfirmOpen(false);
          setRefuseTarget(null);
          setRefuseReason("");
        }}>
          <div className="space-y-4">
            <p className="text-sm text-muted">
              Você está prestes a recusar o convite de <strong>{refuseTarget.titulo}</strong>.
              Essa ação registra a resposta e não pode ser desfeita.
            </p>
            <label className="block text-sm font-medium text-ink">
              Motivo da recusa (opcional)
              <textarea
                className="mt-1 w-full rounded-lg border border-line px-3 py-2 text-sm outline-none focus:border-primary"
                rows={4}
                value={refuseReason}
                onChange={(event) => setRefuseReason(event.target.value)}
                placeholder="Descreva o motivo da recusa"
              />
            </label>
            <div className="flex justify-end gap-2">
              <button
                type="button"
                className="rounded-lg border border-line px-3 py-2 text-sm font-medium text-ink"
                onClick={() => {
                  setRefuseConfirmOpen(false);
                  setRefuseTarget(null);
                  setRefuseReason("");
                }}
              >
                Cancelar
              </button>
              <button
                type="button"
                className="rounded-lg bg-danger px-3 py-2 text-sm font-semibold text-white"
                onClick={() => void handleRefuseConfirm()}
                disabled={!!busyId}
              >
                {busyId === refuseTarget.id ? "Confirmando..." : "Confirmar recusa"}
              </button>
            </div>
          </div>
        </Modal>
      ) : null}
    </section>
  );
}

function TabButton({
  active,
  onClick,
  children,
}: {
  active: boolean;
  onClick: () => void;
  children: React.ReactNode;
}) {
  return (
    <button
      type="button"
      onClick={onClick}
      className={`-mb-px border-b-2 px-3 py-2 text-sm font-medium ${
        active ? "border-primary text-primary" : "border-transparent text-muted hover:text-ink"
      }`}
    >
      {children}
    </button>
  );
}
