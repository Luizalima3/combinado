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
