import { useEffect, useMemo, useState } from "react";

import axios from "axios";
import { Link, Navigate, useParams } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { ParticipantConfirmedList } from "../components/combinados/ParticipantConfirmedList";
import { InviteActionButtons } from "../components/invites/InviteActionButtons";
import { CategoryBadge } from "../components/ui/CategoryBadge";
import { Modal } from "../components/ui/Modal";
import { Spinner } from "../components/ui/Spinner";
import { StatusBadge } from "../components/ui/StatusBadge";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import {
  aceitarConvite,
  concluirCombinado,
  getCombinadoById,
  recusarConvite,
} from "../services/combinadoService";
import type { Combinado } from "../types/combinado";
import {
  conviteStatusForUser,
  userIsCreator,
  userIsParticipant,
} from "../utils/combinadoMapper";

export function CombinadoDetailPage() {
  const { id } = useParams();
  const { sessionUser: user } = useAuth();
  const { push } = useToast();
  const [combinado, setCombinado] = useState<Combinado | null>(null);
  const [forbidden, setForbidden] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  const [busy, setBusy] = useState(false);
  const [showFinishModal, setShowFinishModal] = useState(false);
  const [finishConfirm, setFinishConfirm] = useState(false);

  useEffect(() => {
    async function load() {
      if (!id) return;
      setLoading(true);
      try {
        const data = await getCombinadoById(Number(id));
        if (!userIsCreator(data, user) && !userIsParticipant(data, user)) {
          setForbidden(true);
          return;
        }
        setCombinado(data);
      } catch (err) {
        setError(getApiErrorMessage(err, "Não foi possível carregar o combinado."));
      } finally {
        setLoading(false);
      }
    }
    void load();
  }, [id, user]);

  const conviteStatus = useMemo(
    () => (combinado ? conviteStatusForUser(combinado, user) : "PENDENTE"),
    [combinado, user],
  );

  const isCreator = combinado ? userIsCreator(combinado, user) : false;
  const canRespondInvite =
    combinado?.status === "AGUARDANDO_CONFIRMACAO" &&
    !isCreator &&
    conviteStatus === "PENDENTE";

  // US64 / Task #31681 + Task #32340/US71: botão "Concluir" visível apenas quando
  // o combinado está ATIVO e o usuário tem permissão (criador — US70; ou
  // participante com convite aceito — US71). O backend valida novamente em PATCH /concluir.
  const podeConcluir =
    combinado?.status === "ATIVO" && (isCreator || conviteStatus === "ACEITO");

  async function handleAccept() {
    if (!combinado) return;
    setBusy(true);
    try {
      const updated = await aceitarConvite(combinado.id, user);
      setCombinado(updated);
      push("success", "Convite aceito.");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível aceitar o convite."));
    } finally {
      setBusy(false);
    }
  }

  async function handleRefuse() {
    if (!combinado) return;
    setBusy(true);
    try {
      const updated = await recusarConvite(combinado.id, user);
      setCombinado(updated);
      push("info", "Convite recusado.");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível recusar o convite."));
    } finally {
      setBusy(false);
    }
  }

  async function handleFinish() {
    if (!combinado || !finishConfirm) return;
    setBusy(true);
    try {
      const updated = await concluirCombinado(combinado.id);
      setCombinado(updated);
      setShowFinishModal(false);
      setFinishConfirm(false);
      push("success", "Combinado encerrado com sucesso.");
    } catch (err) {
      // US62 / Task #31672: exibe mensagem específica com os participantes pendentes
      let mensagem = getApiErrorMessage(err, "Não foi possível encerrar o combinado.");
      const pendentes =
        axios.isAxiosError(err) &&
        err.response?.status === 422 &&
        (err.response?.data as { pendentes?: string[] } | undefined)?.pendentes;
      if (Array.isArray(pendentes) && pendentes.length > 0) {
        mensagem += ` (${pendentes.join(", ")})`;
      }
      push("error", mensagem);
    } finally {
      setBusy(false);
    }
  }

  if (forbidden) return <Navigate to="/acesso-negado" replace />;
  if (loading) return <Spinner label="Carregando combinado..." />;
  if (error) {
    return (
      <div className="rounded-xl border border-danger/30 bg-danger-soft p-4 text-sm text-danger">
        {error}
      </div>
    );
  }
  if (!combinado) return null;

  return (
    <section className="mx-auto max-w-3xl rounded-2xl border border-line bg-card p-6">
      <Link to="/" className="mb-4 inline-block text-sm text-primary hover:underline">
        ← Voltar
      </Link>
      <div className="flex flex-wrap items-start justify-between gap-3">
        <h1 className="text-2xl font-semibold text-ink">{combinado.titulo}</h1>
        <StatusBadge status={combinado.status} />
      </div>
      <div className="mt-3 flex flex-wrap gap-2">
        <CategoryBadge categoria={combinado.categoria} />
        <span className="rounded-full bg-bg px-2.5 py-1 text-xs text-muted">
          Prazo{" "}
          {combinado.dataPrazo
            ? new Date(`${combinado.dataPrazo}T00:00:00`).toLocaleDateString("pt-BR")
            : "não definido"}
        </span>
      </div>
      <p className="mt-4 text-sm text-ink">{combinado.descricao || "Sem descrição."}</p>

      <h2 className="mt-8 text-sm font-semibold text-ink">Participantes confirmados</h2>
      <div className="mt-2">
        <ParticipantConfirmedList
          participantes={combinado.extras.participantes}
          criadorNome={combinado.criadorNome}
        />
      </div>

      {canRespondInvite ? (
        <div className="mt-8 rounded-xl border border-warn/30 bg-warn-soft/40 p-4">
          <p className="mb-3 text-sm text-ink">Este convite está pendente de resposta.</p>
          <InviteActionButtons
            busy={busy}
            onAccept={() => void handleAccept()}
            onRefuse={() => void handleRefuse()}
          />
        </div>
      ) : null}

      {podeConcluir ? (
        <div className="mt-8 flex justify-end">
          <button
            type="button"
            className="rounded-lg bg-mint px-4 py-2 text-sm font-semibold text-white"
            onClick={() => setShowFinishModal(true)}
          >
            Concluir combinado
          </button>
        </div>
      ) : null}

      {showFinishModal ? (
        <Modal title="Concluir combinado" onClose={() => setShowFinishModal(false)}>
          <p className="text-sm text-muted">
            Confirme que deseja marcar <strong>{combinado.titulo}</strong> como concluído. Esta ação
            altera o status do acordo para encerrado.
          </p>
          <label className="mt-4 flex items-center gap-2 text-sm text-ink">
            <input
              type="checkbox"
              checked={finishConfirm}
              onChange={(event) => setFinishConfirm(event.target.checked)}
            />
            Confirmo que o combinado foi cumprido e pode ser encerrado.
          </label>
          <div className="mt-5 flex justify-end gap-2">
            <button
              type="button"
              className="rounded-lg border border-line px-3 py-2 text-sm"
              onClick={() => setShowFinishModal(false)}
            >
              Cancelar
            </button>
            <button
              type="button"
              disabled={!finishConfirm || busy}
              className="rounded-lg bg-mint px-3 py-2 text-sm font-semibold text-white disabled:opacity-60"
              onClick={() => void handleFinish()}
            >
              {busy ? "Encerrando..." : "Confirmar encerramento"}
            </button>
          </div>
        </Modal>
      ) : null}
    </section>
  );
}
