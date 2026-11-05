import { useState } from "react";

import { Modal } from "../ui/Modal";

interface CancelModalProps {
  /** Título do combinado que está sendo cancelado. */
  titulo: string;
  /** Torna o modal de confirmação acessível/apresentável para leitores de tela. */
  busy?: boolean;
  onConfirm: (motivo?: string) => void;
  onClose: () => void;
}

/**
 * US67 / Task #31692 — Botão "Cancelar" com modal de confirmação.
 * US69 / Task #31695 — Campo opcional "Motivo" no modal de cancelamento.
 *
 * BDD:
 * DADO que o combinado não está concluído nem já cancelado
 * QUANDO o usuário autorizado clicar em "Cancelar"
 * ENTÃO o status do combinado deve mudar para "Cancelado"
 * DADO que o usuário está cancelando um combinado
 * QUANDO ele preencher (ou não) o campo de motivo
 * ENTÃO o sistema deve aceitar o cancelamento com ou sem motivo informado
 *
 * CA #31818: pede confirmação antes de efetivar o cancelamento.
 * CA #31819: bloqueia novas ações (busy) após confirmar.
 * CA #31637: o motivo é opcional (CA03 US69).
 */
export function CancelModal({ titulo, busy, onConfirm, onClose }: CancelModalProps) {
  const [motivo, setMotivo] = useState("");

  return (
    <Modal title="Cancelar combinado" onClose={onClose}>
      <p className="text-sm text-muted">
        Tem certeza que deseja cancelar <strong>{titulo}</strong>? Esta ação registra o cancelamento
        no histórico do acordo e não pode ser desfeita.
      </p>
      <label className="mt-4 block text-sm text-ink">
        Motivo (opcional)
        <textarea
          rows={4}
          maxLength={500}
          disabled={busy}
          value={motivo}
          onChange={(event) => setMotivo(event.target.value)}
          aria-label="Motivo do cancelamento (opcional)"
          placeholder="Descreva o motivo do cancelamento (opcional)"
          className="mt-1 w-full rounded-lg border border-line px-3 py-2 text-sm outline-none focus:border-primary disabled:opacity-60"
        />
      </label>
      <div className="mt-5 flex justify-end gap-2">
        <button
          type="button"
          disabled={busy}
          onClick={onClose}
          className="rounded-lg border border-line px-3 py-2 text-sm font-medium text-ink disabled:opacity-60"
        >
          Cancelar
        </button>
        <button
          type="button"
          disabled={busy}
          onClick={() => onConfirm(motivo)}
          className="rounded-lg bg-danger px-3 py-2 text-sm font-semibold text-white hover:opacity-90 disabled:opacity-60"
        >
          {busy ? "Confirmando..." : "Confirmar cancelamento"}
        </button>
      </div>
    </Modal>
  );
}
