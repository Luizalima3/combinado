import type { CategoriaCombinado, ParticipanteCombinado } from "../types/combinado";

export interface CombinadoFormFields {
  titulo: string;
  categoria: CategoriaCombinado | "";
  dataPrazo: string;
  participantes: ParticipanteCombinado[];
  today: string;
  requireRegisteredParticipants?: boolean;
}

/** Validação rigorosa só na submissão definitiva; rascunho não exige campos obrigatórios (bug #32392). */
export function validateCombinadoForm(
  fields: CombinadoFormFields,
  forDraft: boolean,
): Record<string, string> {
  if (forDraft) {
    return {};
  }

  const next: Record<string, string> = {};
  if (!fields.titulo.trim()) next.titulo = "Informe o título do combinado.";
  if (!fields.categoria) next.categoria = "Selecione uma categoria.";
  if (!fields.dataPrazo) next.dataPrazo = "Defina um prazo.";
  else if (fields.dataPrazo < fields.today) {
    next.dataPrazo = "O prazo precisa ser uma data futura.";
  }
  if (!fields.participantes.length) {
    next.participantes = "Adicione pelo menos um participante.";
  } else if (fields.requireRegisteredParticipants) {
    const missingId = fields.participantes.some((p) => typeof p.id !== "number");
    if (missingId) {
      next.participantes =
        "Para publicar, adicione participantes cadastrados pela busca de e-mail.";
    }
  }
  return next;
}
