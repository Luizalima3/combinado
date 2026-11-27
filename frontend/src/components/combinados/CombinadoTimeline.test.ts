import { describe, expect, it } from "vitest";

import type { CombinadoHistoricoEvento } from "../../types/combinado";
import {
  filterTimelineEvents,
  formatEventDateTime,
  formatRelativeEventDate,
  getTimelineEventPresentation,
  orderChronologically,
} from "../../utils/combinadoTimeline";

const evento = (
  id: number,
  ocorridoEm: string,
): CombinadoHistoricoEvento => ({
  id,
  autorNome: "Participante",
  descricao: "Evento",
  acao: "ALTERACAO_STATUS",
  ocorridoEm,
});

describe("orderChronologically", () => {
  it("ordena os eventos do mais antigo para o mais recente", () => {
    const events = [
      evento(3, "2026-03-03T10:00:00Z"),
      evento(1, "2026-03-01T10:00:00Z"),
      evento(2, "2026-03-02T10:00:00Z"),
    ];

    expect(orderChronologically(events).map(({ id }) => id)).toEqual([1, 2, 3]);
  });

  it("usa o id para desempatar datas iguais sem alterar a lista original", () => {
    const events = [
      evento(2, "2026-03-01T10:00:00Z"),
      evento(1, "2026-03-01T10:00:00Z"),
    ];

    expect(orderChronologically(events).map(({ id }) => id)).toEqual([1, 2]);
    expect(events.map(({ id }) => id)).toEqual([2, 1]);
  });
});

describe("filterTimelineEvents", () => {
  it("filters by type, author, and inclusive date range", () => {
    const events = [
      { ...evento(1, "2026-03-01T10:00:00"), acao: "CRIACAO", autorNome: "Ana Silva" },
      { ...evento(2, "2026-03-02T10:00:00"), acao: "ALTERACAO_STATUS", autorNome: "Ana Souza" },
      { ...evento(3, "2026-03-03T10:00:00"), acao: "CRIACAO", autorNome: "Bruno" },
    ];

    expect(filterTimelineEvents(events, {
      startDate: "2026-03-01",
      endDate: "2026-03-02",
    }).map(({ id }) => id)).toEqual([1, 2]);
    expect(filterTimelineEvents(events, {
      action: "CRIACAO",
      startDate: "2026-03-01",
      endDate: "2026-03-02",
      author: "ana",
    }).map(({ id }) => id)).toEqual([1]);
    expect(filterTimelineEvents(events, { author: "ANA " }).map(({ id }) => id)).toEqual([1, 2]);
    expect(filterTimelineEvents(events, {}).map(({ id }) => id)).toEqual([1, 2, 3]);
  });

  it("returns no events when the date range is reversed or a selected range has invalid timestamps", () => {
    const events = [evento(1, "invalid")];

    expect(filterTimelineEvents(events, {
      startDate: "2026-03-03",
      endDate: "2026-03-01",
    })).toEqual([]);
    expect(filterTimelineEvents(events, { startDate: "2026-03-01" })).toEqual([]);
  });
});

describe("formatEventDateTime", () => {
  it("exibe data e hora em formato de 24 horas com minutos", () => {
    expect(formatEventDateTime("2026-03-01T16:07:00")).toContain("16:07");
  });

  it("indica quando o timestamp do evento é inválido", () => {
    expect(formatEventDateTime("inválido")).toBe("Data indisponível");
  });
});

describe("formatRelativeEventDate", () => {
  const now = new Date("2026-11-27T12:00:00Z");

  it("formats past events as a relative date in Portuguese", () => {
    expect(formatRelativeEventDate("2026-11-25T12:00:00Z", now)).toBe("há 2 dias");
  });

  it("formats future events as a relative date in Portuguese", () => {
    expect(formatRelativeEventDate("2026-11-28T12:00:00Z", now)).toBe("em 1 dia");
  });

  it("uses a friendly label for events from now and invalid timestamps", () => {
    expect(formatRelativeEventDate("2026-11-27T12:00:10Z", now)).toBe("agora");
    expect(formatRelativeEventDate("inválido", now)).toBe("Data indisponível");
  });
});

describe("getTimelineEventPresentation", () => {
  it("mapeia cada tipo de evento para seu ícone e cor", () => {
    expect(getTimelineEventPresentation("CRIACAO")).toMatchObject({
      icon: "+",
      colorClass: "bg-primary-soft text-primary-dark",
    });
    expect(getTimelineEventPresentation("ALTERACAO_STATUS")).toMatchObject({
      icon: "↻",
      colorClass: "bg-warn-soft text-warn",
    });
    expect(getTimelineEventPresentation("CONCLUSAO")).toMatchObject({
      icon: "✓",
      colorClass: "bg-mint-soft text-mint-dark",
    });
    expect(getTimelineEventPresentation("CANCELAMENTO")).toMatchObject({
      icon: "×",
      colorClass: "bg-danger-soft text-danger",
    });
  });

  it("usa uma apresentação padrão para tipos desconhecidos", () => {
    expect(getTimelineEventPresentation("DESCONHECIDO")).toMatchObject({
      label: "Evento registrado",
      icon: "•",
      colorClass: "bg-bg text-muted",
    });
  });
});
