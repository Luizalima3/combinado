import { useEffect, useMemo, useState } from "react";
import { useAuth } from "../../context/AuthContext";
import { buscarUsuarioPorEmail, sugerirUsuarios } from "../../services/combinadoService";
import type { ParticipanteCombinado, UsuarioResumo } from "../../types/combinado";

interface ParticipantPickerProps {
  participants: ParticipanteCombinado[];
  onChange: (participants: ParticipanteCombinado[]) => void;
  error?: string;
}

export function ParticipantPicker({ participants, onChange, error }: ParticipantPickerProps) {
  const { user } = useAuth();
  const [query, setQuery] = useState("");
  const [suggestions, setSuggestions] = useState<UsuarioResumo[]>([]);
  const [searchError, setSearchError] = useState("");
  const [searching, setSearching] = useState(false);

  useEffect(() => {
    const handle = window.setTimeout(async () => {
      if (query.trim().length < 2) {
        setSuggestions([]);
        return;
      }
      setSearching(true);
      const results = await sugerirUsuarios(query);
      setSuggestions(
        results.filter(
          (item) =>
            item.email.toLowerCase() !== user.email.toLowerCase() &&
            !participants.some((p) => p.email.toLowerCase() === item.email.toLowerCase()),
        ),
      );
      setSearching(false);
    }, 280);

    return () => window.clearTimeout(handle);
  }, [query, participants, user.email]);

  const emails = useMemo(
    () => new Set(participants.map((p) => p.email.toLowerCase())),
    [participants],
  );

  async function addByEmail() {
    setSearchError("");
    const found = await buscarUsuarioPorEmail(query);
    if (!found) {
      setSearchError("Nenhum usuário encontrado com este e-mail.");
      return;
    }
    addParticipant(found);
  }

  function addParticipant(found: UsuarioResumo) {
    if (found.email.toLowerCase() === user.email.toLowerCase()) {
      setSearchError("Você já é o criador e não pode se autoadicionar.");
      return;
    }
    if (emails.has(found.email.toLowerCase())) {
      setSearchError("Este participante já está na lista.");
      return;
    }
    onChange([...participants, { ...found, conviteStatus: "PENDENTE" }]);
    setQuery("");
    setSuggestions([]);
    setSearchError("");
  }

  function removeParticipant(email: string) {
    onChange(participants.filter((p) => p.email !== email));
  }

  return (
    <div>
      <label className="mb-1 block text-sm font-medium text-ink">Participantes</label>
      <div className="flex gap-2">
        <input
          value={query}
          onChange={(event) => setQuery(event.target.value)}
          onKeyDown={(event) => {
            if (event.key === "Enter") {
              event.preventDefault();
              void addByEmail();
            }
          }}
          placeholder="Buscar por e-mail (ex: ana@combinado.app)"
          className="w-full rounded-lg border border-line bg-white px-3 py-2 text-sm outline-none focus:border-primary"
        />
        <button
          type="button"
          className="rounded-lg bg-primary px-3 py-2 text-sm font-semibold text-white hover:bg-primary-dark"
          onClick={() => void addByEmail()}
        >
          Adicionar
        </button>
      </div>
      {searching ? <p className="mt-1 text-xs text-muted">Buscando...</p> : null}
      {suggestions.length ? (
        <ul className="mt-2 overflow-hidden rounded-lg border border-line bg-white shadow-sm">
          {suggestions.map((item) => (
            <li key={item.email}>
              <button
                type="button"
                className="flex w-full items-center justify-between px-3 py-2 text-left text-sm hover:bg-primary-soft"
                onClick={() => addParticipant(item)}
              >
                <span className="font-medium text-ink">{item.nome}</span>
                <span className="text-muted">{item.email}</span>
              </button>
            </li>
          ))}
        </ul>
      ) : null}
      {searchError || error ? (
        <p className="mt-1 text-sm text-danger">{searchError || error}</p>
      ) : (
        <p className="mt-1 text-xs text-muted">
          Digite o e-mail e pressione Enter, ou escolha uma sugestão.
        </p>
      )}

      <div className="mt-3 flex flex-wrap gap-2">
        {participants.length === 0 ? (
          <span className="text-sm text-muted">Nenhum participante selecionado.</span>
        ) : (
          participants.map((p) => (
            <span
              key={p.email}
              className="inline-flex items-center gap-2 rounded-full bg-primary-soft px-3 py-1 text-sm text-primary-dark"
            >
              {p.nome}
              <button
                type="button"
                className="font-bold"
                onClick={() => removeParticipant(p.email)}
                aria-label={`Remover ${p.nome}`}
              >
                ×
              </button>
            </span>
          ))
        )}
      </div>
    </div>
  );
}
