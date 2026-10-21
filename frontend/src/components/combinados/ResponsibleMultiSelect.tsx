import { useId, useState } from "react";

export interface ResponsibleOption {
  id: number;
  nome: string;
  email: string;
}

interface ResponsibleMultiSelectProps {
  options: ResponsibleOption[];
  selectedIds: number[];
  onChange: (selectedIds: number[]) => void;
  error?: string;
}

export function ResponsibleMultiSelect({
  options,
  selectedIds,
  onChange,
  error,
}: ResponsibleMultiSelectProps) {
  const id = useId();
  const [query, setQuery] = useState("");
  const term = query.trim().toLowerCase();
  const selectedOptions = options.filter((option) => selectedIds.includes(option.id));
  const visibleOptions = options.filter(
    (option) =>
      option.nome.toLowerCase().includes(term) ||
      option.email.toLowerCase().includes(term),
  );

  function toggleParticipant(participantId: number) {
    onChange(
      selectedIds.includes(participantId)
        ? selectedIds.filter((id) => id !== participantId)
        : [...selectedIds, participantId],
    );
  }

  return (
    <fieldset aria-describedby={error ? `${id}-error` : undefined}>
      <legend className="mb-1 block text-sm font-medium text-ink">Responsáveis</legend>

      <input
        type="search"
        value={query}
        onChange={(event) => setQuery(event.target.value)}
        placeholder="Buscar participante por nome ou e-mail"
        aria-label="Buscar participantes responsáveis"
        className="w-full rounded-lg border border-line bg-white px-3 py-2 text-sm outline-none focus:border-primary"
      />

      <div className="mt-3 flex min-h-7 flex-wrap gap-2">
        {selectedOptions.length ? (
          selectedOptions.map((participant) => (
            <span
              key={participant.id}
              className="inline-flex items-center gap-2 rounded-full bg-primary-soft px-3 py-1 text-sm text-primary-dark"
            >
              {participant.nome}
              <button
                type="button"
                className="font-bold"
                onClick={() => toggleParticipant(participant.id)}
                aria-label={`Remover ${participant.nome} da lista de responsáveis`}
              >
                ×
              </button>
            </span>
          ))
        ) : (
          <span className="text-sm text-muted">Nenhum responsável selecionado.</span>
        )}
      </div>

      <ul className="mt-3 max-h-52 overflow-y-auto rounded-lg border border-line bg-white">
        {visibleOptions.length ? (
          visibleOptions.map((participant) => {
            const checkboxId = `${id}-${participant.id}`;
            return (
              <li key={participant.id} className="border-b border-line last:border-b-0">
                <label
                  htmlFor={checkboxId}
                  className="flex cursor-pointer items-center gap-3 px-3 py-2 hover:bg-primary-soft"
                >
                  <input
                    id={checkboxId}
                    type="checkbox"
                    checked={selectedIds.includes(participant.id)}
                    onChange={() => toggleParticipant(participant.id)}
                    className="h-4 w-4 shrink-0 accent-primary"
                  />
                  <span className="min-w-0">
                    <span className="block truncate text-sm font-medium text-ink">
                      {participant.nome}
                    </span>
                    <span className="block truncate text-xs text-muted">
                      {participant.email}
                    </span>
                  </span>
                </label>
              </li>
            );
          })
        ) : (
          <li className="px-3 py-2 text-sm text-muted">
            {options.length ? "Nenhum participante encontrado." : "Nenhum participante disponível."}
          </li>
        )}
      </ul>

      {error ? (
        <p id={`${id}-error`} className="mt-1 text-sm text-danger" role="alert">
          {error}
        </p>
      ) : null}
    </fieldset>
  );
}