export function Spinner({ label = "Carregando..." }: { label?: string }) {
  return (
    <div className="flex flex-col items-center justify-center gap-3 py-16 text-muted">
      <div className="h-10 w-10 animate-spin rounded-full border-4 border-primary-soft border-t-primary" />
      <p className="text-sm">{label}</p>
    </div>
  );
}

export function CardSkeleton() {
  return (
    <div className="animate-pulse rounded-2xl border border-line bg-card p-5">
      <div className="mb-3 h-5 w-2/3 rounded bg-line" />
      <div className="mb-2 h-4 w-1/3 rounded bg-line" />
      <div className="h-4 w-1/2 rounded bg-line" />
    </div>
  );
}
