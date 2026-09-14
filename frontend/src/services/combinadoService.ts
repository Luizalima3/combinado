import { http } from "../api/http";
import { MOCK_USERS } from "../data/mockUsers";
import type {
  Combinado,
  CreateCombinadoPayload,
  UsuarioResumo,
} from "../types/combinado";
import {
  buildCreateBody,
  conviteStatusForUser,
  mapCombinado,
  parseExtras,
  serializeExtras,
  userIsCreator,
  userIsParticipant,
  type CombinadoApi,
} from "../utils/combinadoMapper";

async function listAll(): Promise<Combinado[]> {
  const { data } = await http.get<CombinadoApi[]>("/combinados");
  return data.map(mapCombinado);
}

/** US43 — combinados em que o usuário atual é o criador. */
export async function listCriados(user: UsuarioResumo): Promise<Combinado[]> {
  const all = await listAll();
  return all
    .filter((item) => userIsCreator(item, user))
    .sort((a, b) => (b.criadoEm ?? "").localeCompare(a.criadoEm ?? ""));
}

/** US48 — combinados em que o usuário atual foi convidado. */
export async function listRecebidos(user: UsuarioResumo): Promise<Combinado[]> {
  const all = await listAll();
  return all
    .filter((item) => !userIsCreator(item, user) && userIsParticipant(item, user))
    .filter((item) => item.status !== "RASCUNHO")
    .sort((a, b) => (b.criadoEm ?? "").localeCompare(a.criadoEm ?? ""));
}

export async function getCombinadoById(id: number): Promise<Combinado> {
  const { data } = await http.get<CombinadoApi>(`/combinados/${id}`);
  return mapCombinado(data);
}

/** US15, US16, US17, US25 — cria combinado (rascunho ou com convites). */
export async function createCombinado(
  user: UsuarioResumo,
  payload: CreateCombinadoPayload,
): Promise<Combinado> {
  const categoria = payload.categoria || (payload.rascunho ? "OUTRO" : "");
  if (!categoria) {
    throw new Error("Categoria é obrigatória.");
  }

  const body = buildCreateBody(user, {
    ...payload,
    categoria,
  });

  const { data } = await http.post<CombinadoApi>("/combinados", body);
  return mapCombinado(data);
}

/** US34 — aceitar convite. */
export async function aceitarConvite(
  id: number,
  user: UsuarioResumo,
): Promise<Combinado> {
  const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/aceitar`);
  const combinado = mapCombinado(data);
  return stampParticipant(combinado, user, "ACEITO");
}

/** US37 — recusar convite. */
export async function recusarConvite(
  id: number,
  user: UsuarioResumo,
  motivo?: string,
): Promise<Combinado> {
  const { data } = await http.patch<CombinadoApi>(`/combinados/${id}/recusar`);
  const combinado = mapCombinado(data);
  combinado.extras.recusaMotivo = motivo;
  return stampParticipant(combinado, user, "RECUSADO");
}

/** US22 — busca de usuário por e-mail (com fallback local da Sprint 1). */
export async function buscarUsuarioPorEmail(email: string): Promise<UsuarioResumo | null> {
  const normalized = email.trim().toLowerCase();
  if (!normalized || !normalized.includes("@")) return null;

  try {
    const { data } = await http.get<UsuarioResumo>("/usuarios/buscar", {
      params: { email: normalized },
    });
    if (data?.email) return data;
  } catch {
    /* endpoint ainda não existe no backend atual */
  }

  return (
    MOCK_USERS.find((user) => user.email.toLowerCase() === normalized) ?? null
  );
}

export async function sugerirUsuarios(query: string): Promise<UsuarioResumo[]> {
  const term = query.trim().toLowerCase();
  if (term.length < 2) return [];

  try {
    const { data } = await http.get<UsuarioResumo[]>("/usuarios/buscar", {
      params: { q: term },
    });
    if (Array.isArray(data) && data.length) return data;
  } catch {
    /* fallback local */
  }

  return MOCK_USERS.filter(
    (user) =>
      user.email.toLowerCase().includes(term) ||
      user.nome.toLowerCase().includes(term),
  );
}

function stampParticipant(
  combinado: Combinado,
  user: UsuarioResumo,
  status: "ACEITO" | "RECUSADO",
): Combinado {
  const extras = parseExtras(combinado.responsabilidades);
  extras.participantes = extras.participantes.map((p) =>
    p.email.toLowerCase() === user.email.toLowerCase()
      ? { ...p, conviteStatus: status, respondidoEm: new Date().toISOString() }
      : p,
  );
  combinado.extras = extras;
  combinado.responsabilidades = serializeExtras(extras);
  return combinado;
}

export { conviteStatusForUser };
