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

export function filterTimelineEvents(
  events: CombinadoHistoricoEvento[],
  filters: {
    action?: string;
    startDate?: string;
    endDate?: string;
    author?: string;
  },
) {
  const authorQuery = filters.author?.trim().toLocaleLowerCase() ?? "";
  if (filters.startDate && filters.endDate && filters.startDate > filters.endDate) {
    return [];
  }

  return events.filter((event) => {
    if (filters.action && event.acao !== filters.action) return false;
    if (
      authorQuery &&
      !(event.autorNome?.trim() || "Usuário")
        .toLocaleLowerCase()
        .includes(authorQuery)
    ) {
      return false;
    }

    if (filters.startDate || filters.endDate) {
      const occurredAt = new Date(event.ocorridoEm);
      if (!Number.isFinite(occurredAt.getTime())) return false;
      const occurredDate = [
        occurredAt.getFullYear(),
        String(occurredAt.getMonth() + 1).padStart(2, "0"),
        String(occurredAt.getDate()).padStart(2, "0"),
      ].join("-");
      if (filters.startDate && occurredDate < filters.startDate) return false;
      if (filters.endDate && occurredDate > filters.endDate) return false;
    }

    return true;
  });
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

export function formatRelativeEventDate(value: string, now = new Date()) {
  const date = new Date(value);
  if (!Number.isFinite(date.getTime())) return "Data indisponível";

  const differenceInSeconds = (date.getTime() - now.getTime()) / 1000;
  if (Math.abs(differenceInSeconds) < 30) return "agora";

  const units: Array<{ unit: Intl.RelativeTimeFormatUnit; seconds: number }> = [
    { unit: "year", seconds: 365 * 24 * 60 * 60 },
    { unit: "month", seconds: 30 * 24 * 60 * 60 },
    { unit: "day", seconds: 24 * 60 * 60 },
    { unit: "hour", seconds: 60 * 60 },
    { unit: "minute", seconds: 60 },
    { unit: "second", seconds: 1 },
  ];
  const unit = units.find(({ seconds }) => Math.abs(differenceInSeconds) >= seconds)
    ?? units[units.length - 1];

  return new Intl.RelativeTimeFormat("pt-BR", { numeric: "always" }).format(
    Math.round(differenceInSeconds / unit.seconds),
    unit.unit,
  );
}
