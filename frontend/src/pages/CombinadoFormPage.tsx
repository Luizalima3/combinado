import { type FormEvent, useEffect, useMemo, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { CategoryPicker } from "../components/combinados/CategoryPicker";
import { ParticipantPicker } from "../components/combinados/ParticipantPicker";
import { Modal } from "../components/ui/Modal";
import { Spinner } from "../components/ui/Spinner";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import {
  createCombinado,
  getCombinadoById,
  publicarCombinado,
} from "../services/combinadoService";
import { type CategoriaCombinado, type ParticipanteCombinado } from "../types/combinado";
import { validateCombinadoForm } from "../utils/combinadoFormValidation";
import { userIsCreator } from "../utils/combinadoMapper";

export function CombinadoFormPage() {
  const { sessionUser: user } = useAuth();
  const { push } = useToast();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const draftSourceId = searchParams.get("from");
  const today = useMemo(() => new Date().toISOString().slice(0, 10), []);

  const [draftId, setDraftId] = useState<number | null>(null);
  const [loadingDraft, setLoadingDraft] = useState(Boolean(draftSourceId));
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [categoria, setCategoria] = useState<CategoriaCombinado | "">("");
  const [dataPrazo, setDataPrazo] = useState("");
  const [participantes, setParticipantes] = useState<ParticipanteCombinado[]>([]);
  const [listaResponsabilidades, setListaResponsabilidades] = useState<string[]>([""]);
  const [mensagemConvite, setMensagemConvite] = useState("");
  const [showInviteModal, setShowInviteModal] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [submitting, setSubmitting] = useState<"draft" | "send" | null>(null);

  useEffect(() => {
    if (!draftSourceId) return;
    let cancelled = false;
    void (async () => {
      setLoadingDraft(true);
      try {
        const data = await getCombinadoById(Number(draftSourceId));
        if (cancelled) return;
        if (data.status !== "RASCUNHO" || !userIsCreator(data, user)) {
          push("error", "Rascunho inválido ou sem permissão.");
          navigate("/combinados/novo", { replace: true });
          return;
        }
        setDraftId(data.id);
        setTitulo(data.titulo === "Rascunho em edição" ? "" : data.titulo);
        setDescricao(data.descricao ?? "");
        setCategoria(data.categoria ?? "");
        setDataPrazo(data.dataPrazo ?? "");
        setMensagemConvite(data.extras.mensagemConvite ?? "");
        setListaResponsabilidades(
          data.extras.listaResponsabilidades?.length
            ? data.extras.listaResponsabilidades
            : [""],
        );
        setParticipantes(
          data.extras.participantes.filter(
            (p) => p.email.toLowerCase() !== user.email.toLowerCase(),
          ),
        );
      } catch (err) {
        if (!cancelled) {
          push("error", getApiErrorMessage(err, "Não foi possível carregar o rascunho."));
        }
      } finally {
        if (!cancelled) setLoadingDraft(false);
      }
    })();
    return () => {
      cancelled = true;
    };
  }, [draftSourceId, navigate, push, user]);

  function formFields(forDraft: boolean) {
    return {
      titulo,
      categoria,
      dataPrazo,
      participantes,
      today,
      requireRegisteredParticipants: !forDraft && Boolean(draftId),
    };
  }

  function runValidation(forDraft: boolean) {
    const next = validateCombinadoForm(formFields(forDraft), forDraft);
    setErrors(next);
    return Object.keys(next).length === 0;
  }

  function payloadBase() {
    return {
      titulo,
      descricao,
      categoria,
      dataPrazo,
      participantes,
      mensagemConvite,
      listaResponsabilidades,
    };
  }

  async function submit(rascunho: boolean) {
    if (!runValidation(rascunho)) {
      if (!rascunho) push("error", "Revise os campos obrigatórios antes de continuar.");
      return;
    }

    setSubmitting(rascunho ? "draft" : "send");
    try {
      if (draftId && !rascunho) {
        await publicarCombinado(draftId, user, { ...payloadBase(), rascunho: false });
      } else {
        await createCombinado(user, { ...payloadBase(), rascunho });
      }
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
    if (!runValidation(false)) {
      push("error", "Revise os campos obrigatórios antes de continuar.");
      return;
    }
    setShowInviteModal(true);
  }

  function updateResponsabilidade(index: number, value: string) {
    setListaResponsabilidades((items) => items.map((item, i) => (i === index ? value : item)));
  }

  function addResponsabilidade() {
    setListaResponsabilidades((items) => [...items, ""]);
  }

  function removeResponsabilidade(index: number) {
    setListaResponsabilidades((items) =>
      items.length <= 1 ? [""] : items.filter((_, i) => i !== index),
    );
  }

  if (loadingDraft) return <Spinner label="Carregando rascunho..." />;

  return (
    <section className="mx-auto max-w-3xl">
      <h1 className="text-2xl font-semibold text-ink">
        {draftId ? "Continuar rascunho" : "Novo combinado"}
      </h1>
      <p className="mt-1 text-sm text-muted">
        Preencha os dados do acordo. Você pode salvar como rascunho ou enviar os convites.
      </p>

      <form
        noValidate
        onSubmit={onSubmit}
        className="mt-6 space-y-5 rounded-2xl border border-line bg-card p-6"
      >
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

        <div>
          <span className="mb-2 block text-sm font-medium text-ink">Responsabilidades</span>
          <div className="space-y-2">
            {listaResponsabilidades.map((item, index) => (
              <div key={`resp-${index}`} className="flex gap-2">
                <input
                  value={item}
                  onChange={(event) => updateResponsabilidade(index, event.target.value)}
                  placeholder="Ex: Devolver o item até o prazo"
                  className={inputClass()}
                />
                <button
                  type="button"
                  className="rounded-lg border border-line px-3 text-sm text-muted hover:bg-bg"
                  onClick={() => removeResponsabilidade(index)}
                  aria-label="Remover responsabilidade"
                >
                  ×
                </button>
              </div>
            ))}
          </div>
          <button
            type="button"
            className="mt-2 text-sm font-semibold text-primary hover:underline"
            onClick={addResponsabilidade}
          >
            + Adicionar responsabilidade
          </button>
        </div>

        <ParticipantPicker
          participants={participantes}
          onChange={setParticipantes}
          error={errors.participantes}
        />

        <div className="flex flex-wrap justify-end gap-3 pt-2">
          {!draftId ? (
            <button
              type="button"
              disabled={!!submitting}
              onClick={() => void submit(true)}
              className="rounded-lg border border-line px-4 py-2 text-sm font-semibold text-ink hover:bg-bg disabled:opacity-60"
            >
              {submitting === "draft" ? "Salvando..." : "Salvar como rascunho"}
            </button>
          ) : (
            <p className="self-center text-xs text-muted">
              Este rascunho já está salvo. Complete os campos e envie os convites.
            </p>
          )}
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
