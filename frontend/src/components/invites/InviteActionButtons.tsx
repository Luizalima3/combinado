interface InviteActionButtonsProps {
  onAccept: () => void;
  onRefuse: () => void;
  busy?: boolean;
  disabled?: boolean;
}

export function InviteActionButtons({
  onAccept,
  onRefuse,
  busy,
  disabled,
}: InviteActionButtonsProps) {
  const inactive = disabled || busy;
  return (
    <div className="flex flex-wrap justify-end gap-2">
      <button
        type="button"
        disabled={inactive}
        onClick={onRefuse}
        className="inline-flex items-center gap-2 rounded-lg bg-danger px-3 py-2 text-sm font-semibold text-white disabled:cursor-not-allowed disabled:opacity-60"
      >
        {busy ? <span className="inline-block h-4 w-4 animate-spin rounded-full border-2 border-white/40 border-t-white" /> : null}
        Recusar
      </button>
      <button
        type="button"
        disabled={inactive}
        onClick={onAccept}
        className="inline-flex items-center gap-2 rounded-lg bg-mint px-3 py-2 text-sm font-semibold text-white disabled:cursor-not-allowed disabled:opacity-60"
      >
        {busy ? <span className="inline-block h-4 w-4 animate-spin rounded-full border-2 border-white/40 border-t-white" /> : null}
        Aceitar
      </button>
    </div>
  );
}
