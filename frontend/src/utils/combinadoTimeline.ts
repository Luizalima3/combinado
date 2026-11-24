import type { CombinadoHistoricoEvento } from "../types/combinado";

export interface TimelineEventPresentation {
  label: string;
  icon: string;
  colorClass: string;
}

const eventPresentations: Record<string, TimelineEventPresentation> = {
  CRIACAO: {
    label: "Combinado criado",
    icon: "+",
    colorClass: "bg-primary-soft text-primary-dark",
  },
  ALTERACAO_STATUS: {
    label: "Status alterado",
    icon: "↻",
    colorClass: "bg-warn-soft text-warn",
  },
  CONCLUSAO: {
    label: "Combinado concluído",
    icon: "✓",
    colorClass: "bg-mint-soft text-mint-dark",
  },
  CANCELAMENTO: {
    label: "Combinado cancelado",
    icon: "×",
    colorClass: "bg-danger-soft text-danger",
  },
};

const fallbackPresentation: TimelineEventPresentation = {
  label: "Evento registrado",
  icon: "•",
  colorClass: "bg-bg text-muted",
};

export function getTimelineEventPresentation(action: string) {
  return eventPresentations[action] ?? fallbackPresentation;
}

export function orderChronologically(events: CombinadoHistoricoEvento[]) {
  return [...events].sort((first, second) => {
    const firstDate = Date.parse(first.ocorridoEm);
    const secondDate = Date.parse(second.ocorridoEm);
    const firstHasDate = Number.isFinite(firstDate);
    const secondHasDate = Number.isFinite(secondDate);

    if (firstHasDate && secondHasDate) {
      return firstDate - secondDate || first.id - second.id;
    }
    if (firstHasDate) return -1;
    if (secondHasDate) return 1;
    return first.id - second.id;
  });
}

export function formatEventDateTime(value: string) {
  const date = new Date(value);
  if (!Number.isFinite(date.getTime())) return "Data indisponível";
  return new Intl.DateTimeFormat("pt-BR", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    hourCycle: "h23",
  }).format(date);
}

export function formatRelativeDate(value: string, referenceDate: Date = new Date()): string {
  const date = new Date(value);
  if (!Number.isFinite(date.getTime())) return "Data indisponível";
  const diffSeconds = Math.floor((referenceDate.getTime() - date.getTime()) / 1000);
  if (diffSeconds < 60) return "agora mesmo";
  const diffMinutes = Math.floor(diffSeconds / 60);
  if (diffMinutes < 60) return `há ${diffMinutes} min`;
  const diffHours = Math.floor(diffMinutes / 60);
  if (diffHours < 24) return `há ${diffHours} h`;
  const diffDays = Math.floor(diffHours / 24);
  if (diffDays === 1) return "há 1 dia";
  if (diffDays < 30) return `há ${diffDays} dias`;
  return formatEventDateTime(value);
}
