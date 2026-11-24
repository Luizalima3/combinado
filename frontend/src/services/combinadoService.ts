import { http } from "../api/http";
import type {
  Combinado,
  CombinadoHistoricoEvento,
  CreateCombinadoPayload,
  ConviteStatus,
  UsuarioResumo,
} from "../types/combinado";
import {
  buildCreateBody,
  buildPublishBody,
  conviteStatusForUser,
  mapCombinado,
  parseExtras,
  serializeExtras,
  mapConviteRecebido,
  type CombinadoApi,
  type ConviteRecebidoApi,
} from "../utils/combinadoMapper";

export async function listMeusRascunhos(): Promise<Combinado[]> {
  const { data } = await http.get<CombinadoApi[]>("/combinados/meus-rascunhos");
  return data.map(mapCombinado);
}

export interface ParticipanteConvidado {
  id: number;
  nome: string;
  email: string;
  status: ConviteStatus;
}

/** US43 — combinados criados pelo usuário autenticado. */
export async function listCriados(): Promise<Combinado[]> {
  const [all, drafts] = await Promise.all([
    http.get<CombinadoApi[]>("/combinados/criados").then(({ data }) => data.map(mapCombinado)),
    listMeusRascunhos().catch(() => [] as Combinado[]),
  ]);
  const byId = new Map<number, Combinado>();
  for (const item of [...all, ...drafts]) {
    byId.set(item.id, item);
  }
  return [...byId.values()].sort((a, b) => (b.criadoEm ?? "").localeCompare(a.criadoEm ?? ""));
}

/** US48 — combinados em que o usuário atual foi convidado. */
export async function listRecebidos(user: UsuarioResumo): Promise<Combinado[]> {
  const pageSize = 100;
  const convites: ConviteRecebidoApi[] = [];
  let pagina = 0;
  let recebidos: ConviteRecebidoApi[];

  do {
    const { data } = await http.get<ConviteRecebidoApi[]>("/convites/recebidos", {
      params: { pagina, limite: pageSize, sort: "dataEnvio,desc" },
    });
    recebidos = data;
    convites.push(...recebidos);
    pagina += 1;
  } while (recebidos.length === pageSize);

  const maisRecentesPorCombinado = new Map<number, ConviteRecebidoApi>();
  for (const convite of convites) {
    if (!maisRecentesPorCombinado.has(convite.combinadoId)) {
      maisRecentesPorCombinado.set(convite.combinadoId, convite);
    }
  }

  return [...maisRecentesPorCombinado.values()]
    .map((convite) => mapConviteRecebido(convite, user))
    .sort((a, b) => (b.criadoEm ?? "").localeCompare(a.criadoEm ?? ""));
}

export async function concluirCombinado(id: number): Promise<Combinado> {
  const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/concluir`);
  return mapCombinado(data);
}

/**
 * US67 / Task #31692: cancelamento do combinado.
 * Task #31693 (back-end): PATCH /combinados/{id}/cancelar — o backend valida
 * permissão (apenas criador) e regra de status (exceto Concluído/Cancelado).
 * `motivo` é opcional (US69 / Task #31695).
 */
export async function cancelarCombinado(id: number, motivo?: string): Promise<Combinado> {
  const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/cancelar`, {
    motivo: motivo?.trim() ? motivo.trim() : null,
  });
  return mapCombinado(data);
}

export async function getCombinadoById(id: number): Promise<Combinado> {
  const { data } = await http.get<CombinadoApi>(`/combinados/${id}`);
  return mapCombinado(data);
}

export async function listHistoricoCombinado(
  id: number,
): Promise<CombinadoHistoricoEvento[]> {
  const { data } = await http.get<CombinadoHistoricoEvento[]>(
    `/combinados/${id}/historico`,
  );
  return data;
}

export async function listParticipantes(id: number): Promise<ParticipanteConvidado[]> {
  const { data } = await http.get<ParticipanteConvidado[]>(`/combinados/${id}/participantes`);
  return data;
}

/** US15, US16, US17, US25 — cria combinado (rascunho ou com convites). */
export async function createCombinado(
  user: UsuarioResumo,
  payload: CreateCombinadoPayload,
): Promise<Combinado> {
  if (!payload.rascunho && !payload.categoria) {
    throw new Error("Categoria é obrigatória.");
  }

  const body = buildCreateBody(user, payload);

  const { data } = await http.post<CombinadoApi>("/combinados", body);
  return mapCombinado(data);
}

/** Publica rascunho existente com validação completa no backend. */
export async function publicarCombinado(
  id: number,
  user: UsuarioResumo,
  payload: CreateCombinadoPayload,
): Promise<Combinado> {
  const body = buildPublishBody(user, payload);
  const { data } = await http.post<CombinadoApi>(`/combinados/${id}/publicar`, body);
  return mapCombinado(data);
}

/** Exclui/descarta um rascunho existente do criador (#34295). */
export async function excluirRascunho(id: number): Promise<void> {
  await http.delete(`/combinados/rascunhos/${id}`);
}

/** US34 — aceitar convite. */
export async function aceitarConvite(
  id: number,
  user: UsuarioResumo,
): Promise<Combinado> {
  const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/aceitar`);
  const combinado = mapCombinado(data);
  const atualizado = stampParticipant(combinado, user, "ACEITO");
  return atualizado;
}

/** US37 — recusar convite. */
export async function recusarConvite(
  id: number,
  user: UsuarioResumo,
  motivo?: string,
): Promise<Combinado> {
  const timestamp = new Date().toISOString();
  let combinado: Combinado;
  try {
    const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/recusar`, {
      respondidoEm: timestamp,
      motivo,
    });
    combinado = mapCombinado(data);
  } catch (err: unknown) {
    if (
      typeof err === "object" &&
      err !== null &&
      "response" in err &&
      (err as { response?: { status?: number } }).response?.status === 403
    ) {
      combinado = await getCombinadoById(id);
    } else {
      throw err;
    }
  }
  combinado.extras.recusaMotivo = motivo;
  return stampParticipant(combinado, user, "RECUSADO");
}

/** US22 — busca de usuário por e-mail (com fallback local da Sprint 1). */
export async function buscarUsuarioPorEmail(email: string): Promise<UsuarioResumo | null> {
  const normalized = email.trim().toLowerCase();
  if (!normalized || !normalized.includes("@")) return null;

  const { data } = await http.get<UsuarioResumo[]>("/usuarios/buscar", {
    params: { q: normalized, limite: 5 },
  });
  return data.find((item) => item.email.toLowerCase() === normalized) ?? null;
}

export async function sugerirUsuarios(query: string): Promise<UsuarioResumo[]> {
  const term = query.trim().toLowerCase();
  if (term.length < 2) return [];

  const { data } = await http.get<UsuarioResumo[]>("/usuarios/buscar", {
    params: { q: term },
  });
  return data;
}

function stampParticipant(
  combinado: Combinado,
  user: UsuarioResumo,
  status: "ACEITO" | "RECUSADO",
): Combinado {
  const extras = parseExtras(combinado.responsabilidades);
  const timestamp = new Date().toISOString();
  const existingIndex = extras.participantes.findIndex(
    (p) => p.email.toLowerCase() === user.email.toLowerCase(),
  );

  const updatedParticipant = {
    nome: user.nome,
    email: user.email,
    conviteStatus: status,
    respondidoEm: timestamp,
  };

  if (existingIndex >= 0) {
    extras.participantes[existingIndex] = {
      ...extras.participantes[existingIndex],
      ...updatedParticipant,
    };
  } else {
    extras.participantes = [...extras.participantes, updatedParticipant];
  }

  combinado.extras = extras;
  combinado.responsabilidades = serializeExtras(extras);
  if (status === "RECUSADO") combinado.status = "CANCELADO";
  return combinado;
}

export { conviteStatusForUser };
