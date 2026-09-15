import type { ReactNode } from "react";

interface EmptyStateProps {
  title: string;
  description: string;
  action?: ReactNode;
}

export function EmptyState({ title, description, action }: EmptyStateProps) {
  return (
    <div className="flex flex-col items-center rounded-2xl border border-dashed border-line bg-card px-6 py-14 text-center">
      <div className="mb-4 grid h-16 w-16 place-items-center rounded-full bg-primary-soft text-2xl text-primary">
        ⌘
      </div>
      <h3 className="text-lg font-semibold text-ink">{title}</h3>
      <p className="mt-1 max-w-md text-sm text-muted">{description}</p>
      {action ? <div className="mt-5">{action}</div> : null}
    </div>
  );
}
