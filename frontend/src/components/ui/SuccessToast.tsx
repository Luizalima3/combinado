import type { ToastItem } from "../../context/ToastContext";

interface SuccessToastProps {
  toast: ToastItem;
  onDismiss: (id: number) => void;
}

/**
 * US66 / Task #31686 — Mensagem de sucesso ao concluir.
 *
 * BDD:
 * DADO que o combinado foi concluído com sucesso
 * QUANDO a ação for processada pelo backend
 * ENTÃO o front-end deve exibir uma mensagem de confirmação ao usuário
 *
 * Toast dedicado a feedbacks de sucesso (ex.: conclusão de um combinado),
 * com ícone de confirmação e barra de progresso do tempo restante.
 */
export function SuccessToast({ toast, onDismiss }: SuccessToastProps) {
  return (
    <button
      type="button"
      role="status"
      aria-live="polite"
      aria-label={`Sucesso: ${toast.message}. Clique para fechar.`}
      className="pointer-events-auto group relative w-full overflow-hidden rounded-lg border border-mint bg-card px-4 py-3 text-left shadow-lg transition hover:shadow-xl"
      onClick={() => onDismiss(toast.id)}
    >
      <span className="flex items-start gap-2.5">
        <span
          aria-hidden="true"
          className="mt-0.5 flex h-5 w-5 shrink-0 items-center justify-center rounded-full bg-mint text-xs font-bold text-white"
        >
          ✓
        </span>
        <span className="text-sm font-medium text-ink">{toast.message}</span>
      </span>
      <span
        aria-hidden="true"
        className="absolute bottom-0 left-0 h-0.5 w-full origin-left animate-toast-progress bg-mint"
      />
    </button>
  );
}
