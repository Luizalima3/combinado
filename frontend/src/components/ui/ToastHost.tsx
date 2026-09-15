import { useToast } from "../../context/ToastContext";

const STYLES = {
  success: "bg-mint text-white",
  error: "bg-danger text-white",
  info: "bg-primary text-white",
};

export function ToastHost() {
  const { toasts, dismiss } = useToast();

  return (
    <div className="pointer-events-none fixed top-4 right-4 z-50 flex w-[min(92vw,360px)] flex-col gap-2">
      {toasts.map((toast) => (
        <button
          key={toast.id}
          type="button"
          className={`pointer-events-auto rounded-lg px-4 py-3 text-left text-sm shadow-lg ${STYLES[toast.kind]}`}
          onClick={() => dismiss(toast.id)}
        >
          {toast.message}
        </button>
      ))}
    </div>
  );
}
