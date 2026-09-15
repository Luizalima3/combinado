import { useEffect, useState } from "react";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { Spinner } from "../components/ui/Spinner";
import { StatusBadge } from "../components/ui/StatusBadge";
import { useAuth } from "../context/AuthContext";
import { getCombinadoById } from "../services/combinadoService";
import type { Combinado } from "../types/combinado";
import { CATEGORIA_LABELS } from "../types/combinado";
import { userIsCreator, userIsParticipant } from "../utils/combinadoMapper";

export function CombinadoDetailPage() {
  const { id } = useParams();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [combinado, setCombinado] = useState<Combinado | null>(null);
  const [forbidden, setForbidden] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function load() {
      if (!id) return;
      setLoading(true);
      try {
        const data = await getCombinadoById(Number(id));
        if (!userIsCreator(data, user) && !userIsParticipant(data, user)) {
          setForbidden(true);
          return;
        }
        setCombinado(data);
      } catch (err) {
        setError(getApiErrorMessage(err, "Não foi possível carregar o combinado."));
      } finally {
        setLoading(false);
      }
    }
    void load();
  }, [id, user]);

  if (forbidden) return <Navigate to="/acesso-negado" replace />;
  if (loading) return <Spinner label="Carregando combinado..." />;
  if (error) {
    return (
      <div className="rounded-xl border border-danger/30 bg-danger-soft p-4 text-sm text-danger">
        {error}
      </div>
    );
  }
  if (!combinado) return null;

  return (
    <section className="mx-auto max-w-3xl rounded-2xl border border-line bg-card p-6">
      <button
        type="button"
        className="mb-4 text-sm text-primary hover:underline"
        onClick={() => navigate(-1)}
      >
        ← Voltar
      </button>
      <div className="flex flex-wrap items-start justify-between gap-3">
        <h1 className="text-2xl font-semibold text-ink">{combinado.titulo}</h1>
        <StatusBadge status={combinado.status} />
      </div>
      <p className="mt-3 text-sm text-ink">{combinado.descricao || "Sem descrição."}</p>
      <p className="mt-4 text-sm text-muted">
        {CATEGORIA_LABELS[combinado.categoria]} · Prazo{" "}
        {combinado.dataPrazo
          ? new Date(`${combinado.dataPrazo}T00:00:00`).toLocaleDateString("pt-BR")
          : "não definido"}
      </p>
      <h2 className="mt-6 text-sm font-semibold text-ink">Participantes</h2>
      <ul className="mt-2 space-y-1 text-sm text-muted">
        {combinado.extras.participantes.map((p) => (
          <li key={p.email}>
            {p.nome} ({p.email}) {p.conviteStatus ? `— ${p.conviteStatus}` : ""}
          </li>
        ))}
      </ul>
    </section>
  );
}
