import { MOCK_USERS } from "../data/mockUsers";
import type {
  Combinado,
  CombinadoExtras,
  CategoriaCombinado,
  ParticipanteCombinado,
  StatusCombinado,
  UsuarioResumo,
} from "../types/combinado";

export interface CombinadoApi {
  id: number;
  titulo: string;
  descricao?: string;
  categoria: CategoriaCombinado;
  dataPrazo?: string;
  criadorNome: string;
  participanteNome: string;
  responsabilidades?: string;
  status: StatusCombinado;
  criadoEm?: string;
}

export function parseExtras(raw?: string): CombinadoExtras {
  if (!raw) return { participantes: [] };
  try {
    const parsed = JSON.parse(raw) as CombinadoExtras;
    return {
      participantes: parsed.participantes ?? [],
      rascunho: parsed.rascunho,
      mensagemConvite: parsed.mensagemConvite,
      recusaMotivo: parsed.recusaMotivo,
    };
  } catch {
    return { participantes: [] };
  }
}

export function serializeExtras(extras: CombinadoExtras) {
  return JSON.stringify(extras);
}

export function mapCombinado(api: CombinadoApi): Combinado {
  const extras = parseExtras(api.responsabilidades);
  const status: StatusCombinado =
    extras.rascunho && api.status !== "ATIVO" && api.status !== "CANCELADO"
      ? "RASCUNHO"
      : api.status;

  return {
    ...api,
    extras,
    status,
    criadorEmail: extras.participantes.find((p) => p.nome === api.criadorNome)?.email,
  };
}

export function buildCreateBody(
  criador: UsuarioResumo,
  payload: {
    titulo: string;
    descricao: string;
    categoria: CategoriaCombinado;
    dataPrazo?: string;
    participantes: ParticipanteCombinado[];
    mensagemConvite?: string;
    rascunho: boolean;
  },
) {
  const participantes = payload.participantes.map((p) => ({
    ...p,
    conviteStatus: payload.rascunho ? undefined : ("PENDENTE" as const),
  }));

  const extras: CombinadoExtras = {
    participantes,
    rascunho: payload.rascunho,
    mensagemConvite: payload.mensagemConvite,
  };

  return {
    titulo: payload.titulo.trim() || "Sem título",
    descricao: payload.descricao.trim(),
    categoria: payload.categoria,
    dataPrazo: payload.dataPrazo || null,
    criadorNome: criador.nome,
    participanteNome:
      participantes.map((p) => p.nome).join(", ") || "—",
    responsabilidades: serializeExtras({
      ...extras,
      participantes: [
        { nome: criador.nome, email: criador.email, conviteStatus: "ACEITO" as const },
        ...participantes,
      ],
    }),
    status: payload.rascunho ? "RASCUNHO" : "AGUARDANDO_CONFIRMACAO",
  };
}

export function userIsCreator(combinado: Combinado, user: UsuarioResumo) {
  return (
    combinado.criadorNome.trim().toLowerCase() === user.nome.trim().toLowerCase() ||
    combinado.criadorEmail?.toLowerCase() === user.email.toLowerCase()
  );
}

export function userIsParticipant(combinado: Combinado, user: UsuarioResumo) {
  const email = user.email.toLowerCase();
  const nome = user.nome.trim().toLowerCase();

  // Demo temporário: enquanto a autenticação por e-mail de participantes não está ativa,
  // qualquer usuário da sessão demo pode acessar os detalhes do combinado.
  const isDemoSessionUser = MOCK_USERS.some((demoUser) => demoUser.email.toLowerCase() === email);
  if (isDemoSessionUser) return true;

  if (combinado.extras.participantes.some((p) => p.email.toLowerCase() === email)) {
    return true;
  }
  return combinado.participanteNome.toLowerCase().includes(nome);
}

export function conviteStatusForUser(combinado: Combinado, user: UsuarioResumo) {
  const found = combinado.extras.participantes.find(
    (p) => p.email.toLowerCase() === user.email.toLowerCase(),
  );
  if (found?.conviteStatus) return found.conviteStatus;
  if (combinado.status === "ATIVO") return "ACEITO";
  if (combinado.status === "CANCELADO") return "RECUSADO";
  if (combinado.status === "RASCUNHO") return "PENDENTE";
  return "PENDENTE";
}
