import { useEffect, useState } from "react";

import { getApiErrorMessage } from "../../api/http";
import { listHistoricoCombinado } from "../../services/combinadoService";
import type { CombinadoHistoricoEvento } from "../../types/combinado";
import {
  filterTimelineEvents,
  formatEventDateTime,
  formatRelativeEventDate,
  getTimelineEventPresentation,
  orderChronologically,
} from "../../utils/combinadoTimeline";

interface CombinadoTimelineProps {
  combinadoId: number;
  refreshKey: number;
}

export function CombinadoTimeline({
  combinadoId,
  refreshKey,
}: CombinadoTimelineProps) {
  const [events, setEvents] = useState<CombinadoHistoricoEvento[]>([]);
  const [selectedType, setSelectedType] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [authorQuery, setAuthorQuery] = useState("");
  const [retryKey, setRetryKey] = useState(0);
  const [requestState, setRequestState] = useState({ key: "", error: "" });
  const requestKey = `${combinadoId}:${refreshKey}:${retryKey}`;
  const loading = requestState.key !== requestKey;
  const error = loading ? "" : requestState.error;
  const invalidDateRange = Boolean(startDate && endDate && startDate > endDate);
  const filteredEvents = filterTimelineEvents(events, {
    action: selectedType,
    startDate,
    endDate,
    author: authorQuery,
  });

  useEffect(() => {
    let active = true;

    listHistoricoCombinado(combinadoId)
      .then((result) => {
        if (active) {
          setEvents(orderChronologically(result));
          setRequestState({ key: requestKey, error: "" });
        }
      })
      .catch((err: unknown) => {
        if (active) {
          setRequestState({
            key: requestKey,
            error: getApiErrorMessage(
              err,
              "Não foi possível carregar a linha do tempo.",
            ),
          });
        }
      })

    return () => {
      active = false;
    };
  }, [combinadoId, refreshKey, requestKey]);

  return (
    <section className="mt-8" aria-labelledby="combinado-timeline-title">
      <h2
        id="combinado-timeline-title"
        className="text-sm font-semibold text-ink"
      >
        Linha do tempo
      </h2>
      <div className="mt-3 grid gap-3 sm:grid-cols-2 lg:grid-cols-4">
        <label className="text-sm font-medium text-ink">
          Tipo de evento
          <select
            className="mt-1 block w-full rounded-lg border border-line bg-card px-3 py-2 text-sm text-ink"
            value={selectedType}
            onChange={(event) => setSelectedType(event.target.value)}
          >
            <option value="">Todos os tipos</option>
            <option value="CRIACAO">Combinado criado</option>
            <option value="ALTERACAO_STATUS">Status alterado</option>
            <option value="CONCLUSAO">Combinado concluído</option>
            <option value="CANCELAMENTO">Combinado cancelado</option>
          </select>
        </label>
        <label className="text-sm font-medium text-ink">
          Data inicial
          <input
            type="date"
            max={endDate || undefined}
            className="mt-1 block w-full rounded-lg border border-line bg-card px-3 py-2 text-sm text-ink"
            value={startDate}
            onChange={(event) => setStartDate(event.target.value)}
          />
        </label>
        <label className="text-sm font-medium text-ink">
          Data final
          <input
            type="date"
            min={startDate || undefined}
            className="mt-1 block w-full rounded-lg border border-line bg-card px-3 py-2 text-sm text-ink"
            value={endDate}
            onChange={(event) => setEndDate(event.target.value)}
          />
        </label>
        <label className="text-sm font-medium text-ink">
          Buscar por autor
          <input
            type="search"
            placeholder="Nome do autor"
            className="mt-1 block w-full rounded-lg border border-line bg-card px-3 py-2 text-sm text-ink"
            value={authorQuery}
            onChange={(event) => setAuthorQuery(event.target.value)}
          />
        </label>
      </div>
      {invalidDateRange ? (
        <p className="mt-2 text-sm text-danger" role="alert">
          A data inicial deve ser anterior ou igual à data final.
        </p>
      ) : null}
      {selectedType || startDate || endDate || authorQuery ? (
        <button
          type="button"
          className="mt-2 text-sm font-medium text-primary underline"
          onClick={() => {
            setSelectedType("");
            setStartDate("");
            setEndDate("");
            setAuthorQuery("");
          }}
        >
          Limpar filtros
        </button>
      ) : null}

      {error ? (
        <div
          className="mt-3 flex flex-wrap items-center justify-between gap-3 rounded-xl border border-danger/30 bg-danger-soft p-3 text-sm text-danger"
          role="alert"
        >
          <span>{error}</span>
          <button
            type="button"
            className="font-semibold underline"
            onClick={() => setRetryKey((current) => current + 1)}
          >
            Tentar novamente
          </button>
        </div>
      ) : null}

      {loading && events.length === 0 ? (
        <p className="mt-3 text-sm text-muted" role="status">
          Carregando eventos...
        </p>
      ) : null}

      {!loading && !error && !invalidDateRange && events.length === 0 ? (
        <p className="mt-3 rounded-xl border border-dashed border-line p-4 text-sm text-muted">
          Ainda não há eventos registrados.
        </p>
      ) : null}

      {!loading &&
      !error &&
      !invalidDateRange &&
      events.length > 0 &&
      filteredEvents.length === 0 ? (
        <p className="mt-3 rounded-xl border border-dashed border-line p-4 text-sm text-muted">
          Não há eventos com os filtros selecionados.
        </p>
      ) : null}

      {!invalidDateRange && filteredEvents.length > 0 ? (
        <ol className="mt-4 ml-2 border-l-2 border-line">
          {filteredEvents.map((event) => {
            const presentation = getTimelineEventPresentation(event.acao);
            return (
              <li key={event.id} className="relative pb-6 pl-6 last:pb-0">
                <span
                  aria-hidden="true"
                  className={`absolute -left-[13px] top-0 flex h-6 w-6 items-center justify-center rounded-full border-2 border-card text-sm font-bold shadow-sm ${presentation.colorClass}`}
                >
                  {presentation.icon}
                </span>
                <div className="flex flex-wrap items-baseline justify-between gap-x-3 gap-y-1">
                  <h3 className="text-sm font-semibold text-ink">
                    {presentation.label}
                  </h3>
                  <time
                    className="text-xs text-muted"
                    dateTime={event.ocorridoEm}
                  >
                    {formatRelativeEventDate(event.ocorridoEm)} ·{" "}
                    {formatEventDateTime(event.ocorridoEm)}
                  </time>
                </div>
                <p className="mt-1 text-sm text-ink">{event.descricao}</p>
                <p className="mt-1 text-xs text-muted">
                  Autor:{" "}
                  <span className="font-medium text-ink">
                    {event.autorNome?.trim() || "Usuário"}
                  </span>
                </p>
              </li>
            );
          })}
        </ol>
      ) : null}
    </section>
  );
}
