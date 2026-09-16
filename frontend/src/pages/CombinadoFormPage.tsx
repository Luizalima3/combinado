import { type FormEvent, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { CategoryPicker } from "../components/combinados/CategoryPicker";
import { ParticipantPicker } from "../components/combinados/ParticipantPicker";
import { Modal } from "../components/ui/Modal";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import { createCombinado } from "../services/combinadoService";
import { type CategoriaCombinado, type ParticipanteCombinado } from "../types/combinado";

export function CombinadoFormPage() {
  const { user } = useAuth();
  const { push } = useToast();
  const navigate = useNavigate();
  const today = useMemo(() => new Date().toISOString().slice(0, 10), []);

  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [categoria, setCategoria] = useState<CategoriaCombinado | "">("");
  const [dataPrazo, setDataPrazo] = useState("");
  const [participantes, setParticipantes] = useState<ParticipanteCombinado[]>([]);
  const [mensagemConvite, setMensagemConvite] = useState("");
  const [showInviteModal, setShowInviteModal] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [submitting, setSubmitting] = useState<"draft" | "send" | null>(null);

  function validate(forDraft: boolean) {
    const next: Record<string, string> = {};
    if (!forDraft) {
      if (!titulo.trim()) next.titulo = "Informe o título do combinado.";
      if (!categoria) next.categoria = "Selecione uma categoria.";
      if (!dataPrazo) next.dataPrazo = "Defina um prazo.";
      else if (dataPrazo < today) next.dataPrazo = "O prazo precisa ser uma data futura.";
      if (!participantes.length) next.participantes = "Adicione pelo menos um participante.";
    } else if (dataPrazo && dataPrazo < today) {
      next.dataPrazo = "O prazo não pode ser uma data passada.";
    }
    setErrors(next);
    return Object.keys(next).length === 0;
  }

  async function submit(rascunho: boolean) {
    if (!validate(rascunho)) {
      push("error", "Revise os campos obrigatórios antes de continuar.");
      return;
    }

    setSubmitting(rascunho ? "draft" : "send");
    try {
      await createCombinado(user, {
        titulo,
        descricao,
        categoria,
        dataPrazo,
        participantes,
        mensagemConvite,
        rascunho,
      });
      push(
        "success",
        rascunho ? "Rascunho salvo." : "Combinado criado e convites enviados.",
      );
      navigate("/");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível salvar o combinado."));
    } finally {
      setSubmitting(null);
    }
  }

  function onSubmit(event: FormEvent) {
    event.preventDefault();
    if (!validate(false)) {
      push("error", "Revise os campos obrigatórios antes de continuar.");
      return;
    }
    setShowInviteModal(true);
  }

  return (
    <section className="mx-auto max-w-3xl">
      <h1 className="text-2xl font-semibold text-ink">Novo combinado</h1>
      <p className="mt-1 text-sm text-muted">
        Preencha os dados do acordo. Você pode salvar como rascunho ou enviar os convites.
      </p>

      <form onSubmit={onSubmit} className="mt-6 space-y-5 rounded-2xl border border-line bg-card p-6">
        <Field label="Título" error={errors.titulo}>
          <input
            value={titulo}
            onChange={(event) => setTitulo(event.target.value)}
            className={inputClass(errors.titulo)}
            placeholder="Ex: Empréstimo da furadeira"
          />
        </Field>

        <Field label="Descrição">
          <textarea
            value={descricao}
            onChange={(event) => setDescricao(event.target.value)}
            rows={4}
            className={inputClass()}
            placeholder="Detalhe o que está sendo combinado"
          />
        </Field>

        <div className="space-y-5">
          <Field label="Categoria" error={errors.categoria}>
            <CategoryPicker
              value={categoria}
              onChange={setCategoria}
              error={errors.categoria}
            />
          </Field>

          <Field label="Prazo" error={errors.dataPrazo}>
            <input
              type="date"
              min={today}
              value={dataPrazo}
              onChange={(event) => setDataPrazo(event.target.value)}
              className={inputClass(errors.dataPrazo)}
            />
          </Field>
        </div>

        <ParticipantPicker
          participants={participantes}
          onChange={setParticipantes}
          error={errors.participantes}
        />

        <div className="flex flex-wrap justify-end gap-3 pt-2">
          <button
            type="button"
            disabled={!!submitting}
            onClick={() => void submit(true)}
            className="rounded-lg border border-line px-4 py-2 text-sm font-semibold text-ink hover:bg-bg disabled:opacity-60"
          >
            {submitting === "draft" ? "Salvando..." : "Salvar como rascunho"}
          </button>
          <button
            type="submit"
            disabled={!!submitting}
            className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white hover:bg-primary-dark disabled:opacity-60"
          >
            {submitting === "send" ? "Enviando..." : "Enviar convites"}
          </button>
        </div>
      </form>

      {showInviteModal ? (
        <Modal title="Enviar convites" onClose={() => setShowInviteModal(false)}>
          <div className="space-y-5">
            <div>
              <p className="mb-2 text-sm font-medium text-ink">Participantes</p>
              <div className="max-h-44 space-y-2 overflow-auto rounded-xl border border-line bg-bg p-3">
                {participantes.length ? (
                  participantes.map((participante) => (
                    <div
                      key={`${participante.email}-${participante.nome}`}
                      className="flex items-center justify-between gap-3 rounded-lg border border-line bg-white px-3 py-2"
                    >
                      <div>
                        <p className="text-sm font-medium text-ink">{participante.nome}</p>
                        <p className="text-xs text-muted">{participante.email}</p>
                      </div>
                      <span className="rounded-full bg-primary-soft px-2 py-1 text-[10px] font-semibold uppercase tracking-wide text-primary-dark">
                        Pendente
                      </span>
                    </div>
                  ))
                ) : (
                  <p className="text-sm text-muted">Nenhum participante selecionado.</p>
                )}
              </div>
            </div>

            <div>
              <label htmlFor="mensagem-convite" className="mb-2 block text-sm font-medium text-ink">
                Mensagem personalizada (opcional)
              </label>
              <textarea
                id="mensagem-convite"
                value={mensagemConvite}
                onChange={(event) => setMensagemConvite(event.target.value)}
                rows={5}
                className="w-full rounded-xl border border-line bg-white px-3 py-2 text-sm text-slate-700 outline-none transition focus:border-primary"
                placeholder="Escreva uma mensagem para os convidados"
              />
            </div>

            <div className="flex justify-end gap-3 border-t border-line pt-4">
              <button
                type="button"
                onClick={() => setShowInviteModal(false)}
                className="rounded-lg border border-line px-4 py-2 text-sm font-semibold text-ink hover:bg-bg"
              >
                Cancelar
              </button>
              <button
                type="button"
                disabled={!!submitting || !participantes.length}
                onClick={() => {
                  setShowInviteModal(false);
                  void submit(false);
                }}
                className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white hover:bg-primary-dark disabled:cursor-not-allowed disabled:opacity-60"
              >
                {submitting === "send" ? "Enviando..." : `Enviar para ${participantes.length} participante${participantes.length === 1 ? "" : "s"}`}
              </button>
            </div>
          </div>
        </Modal>
      ) : null}
    </section>
  );
}

function Field({
  label,
  error,
  children,
}: {
  label: string;
  error?: string;
  children: React.ReactNode;
}) {
  return (
    <label className="block">
      <span className="mb-1 block text-sm font-medium text-ink">{label}</span>
      {children}
      {error ? <span className="mt-1 block text-sm text-danger">{error}</span> : null}
    </label>
  );
}

function inputClass(error?: string) {
  return `w-full rounded-lg border bg-white px-3 py-2 text-sm outline-none focus:border-primary ${
    error ? "border-danger" : "border-line"
  }`;
}
