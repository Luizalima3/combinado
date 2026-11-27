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
  const [retryKey, setRetryKey] = useState(0);
  const [requestState, setRequestState] = useState({ key: "", error: "" });
  const requestKey = `${combinadoId}:${refreshKey}:${retryKey}`;
  const loading = requestState.key !== requestKey;
  const error = loading ? "" : requestState.error;
  const filteredEvents = filterTimelineEvents(events, selectedType);

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
      <label className="mt-3 block text-sm font-medium text-ink">
        Filtrar por tipo de evento
        <select
          className="mt-1 block w-full max-w-xs rounded-lg border border-line bg-card px-3 py-2 text-sm text-ink"
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

      {!loading && !error && events.length === 0 ? (
        <p className="mt-3 rounded-xl border border-dashed border-line p-4 text-sm text-muted">
          Ainda não há eventos registrados.
        </p>
      ) : null}

      {!loading && !error && events.length > 0 && filteredEvents.length === 0 ? (
        <p className="mt-3 rounded-xl border border-dashed border-line p-4 text-sm text-muted">
          Não há eventos desse tipo.
        </p>
      ) : null}

      {filteredEvents.length > 0 ? (
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
