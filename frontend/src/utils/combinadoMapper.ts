import type {
  Combinado,
  CombinadoExtras,
  CategoriaCombinado,
  ParticipanteCombinado,
  ConviteStatus,
  StatusCombinado,
  UsuarioResumo,
} from "../types/combinado";

export interface CombinadoApi {
  id: number;
  titulo: string;
  descricao?: string;
  categoria?: CategoriaCombinado | null;
  dataPrazo?: string;
  criadorNome: string;
  participanteNome: string;
  responsabilidades?: string;
  participantes?: Array<{
    id: number;
    nome: string;
    email: string;
    status: ConviteStatus;
  }>;
  status: StatusCombinado;
  criadoEm?: string;
}

export interface ConviteRecebidoApi {
  id: number;
  combinadoId: number;
  titulo: string;
  categoria?: CategoriaCombinado | null;
  nomeCriador: string;
  prazo?: string;
  statusCombinado: StatusCombinado;
  status: ConviteStatus;
  dataEnvio?: string;
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
      listaResponsabilidades: parsed.listaResponsabilidades ?? [],
    };
  } catch {
    return { participantes: [] };
  }
}

export function serializeExtras(extras: CombinadoExtras) {
  return JSON.stringify(extras);
}

export function mapCombinado(api: CombinadoApi): Combinado {
  const { participantes: participantesApi, ...combinadoApi } = api;
  const extras = parseExtras(api.responsabilidades);
  if (participantesApi) {
    const participantesPorEmail = new Map(
      participantesApi.map((participante) => [participante.email.toLowerCase(), participante]),
    );
    const emailsExistentes = new Set<string>();

    extras.participantes = extras.participantes.map((participante) => {
      const email = participante.email.toLowerCase();
      const participanteApi = participantesPorEmail.get(email);
      emailsExistentes.add(email);
      return participanteApi
        ? {
            ...participante,
            id: participanteApi.id,
            nome: participanteApi.nome,
            email: participanteApi.email,
            conviteStatus: participanteApi.status,
          }
        : participante;
    });

    for (const participante of participantesApi) {
      if (!emailsExistentes.has(participante.email.toLowerCase())) {
        extras.participantes.push({
          id: participante.id,
          nome: participante.nome,
          email: participante.email,
          conviteStatus: participante.status,
        });
      }
    }
  }

  const status: StatusCombinado =
    extras.rascunho && api.status !== "ATIVO" && api.status !== "CANCELADO"
      ? "RASCUNHO"
      : api.status;

  return {
    ...combinadoApi,
    extras,
    status,
    criadorEmail: extras.participantes.find((p) => p.nome === api.criadorNome)?.email,
  };
}

export function mapConviteRecebido(
  convite: ConviteRecebidoApi,
  participante: UsuarioResumo,
): Combinado {
  const extras: CombinadoExtras = {
    participantes: [{
      id: participante.id,
      nome: participante.nome,
      email: participante.email,
      conviteStatus: convite.status,
    }],
  };

  return {
    id: convite.combinadoId,
    titulo: convite.titulo,
    categoria: convite.categoria,
    dataPrazo: convite.prazo,
    criadorNome: convite.nomeCriador,
    participanteNome: participante.nome,
    responsabilidades: serializeExtras(extras),
    status: convite.statusCombinado,
    criadoEm: convite.dataEnvio,
    extras,
  };
}

export function buildCreateBody(
  criador: UsuarioResumo,
  payload: {
    titulo: string;
    descricao: string;
    categoria?: CategoriaCombinado | "";
    dataPrazo?: string;
    participantes: ParticipanteCombinado[];
    mensagemConvite?: string;
    listaResponsabilidades?: string[];
    rascunho: boolean;
  },
) {
  const participantes = payload.participantes.map((p) => ({
    ...p,
    conviteStatus: payload.rascunho ? undefined : ("PENDENTE" as const),
  }));

  const listaResponsabilidades = (payload.listaResponsabilidades ?? []).map((item) => item.trim()).filter(Boolean);

  const extras: CombinadoExtras = {
    participantes,
    rascunho: payload.rascunho,
    mensagemConvite: payload.mensagemConvite,
    listaResponsabilidades,
  };

  const titulo =
    payload.titulo.trim() ||
    (payload.rascunho ? "Rascunho em edição" : "");

  return {
    titulo,
    descricao: payload.descricao.trim(),
    categoria: payload.categoria || null,
    dataPrazo: payload.dataPrazo || null,
    participantesIds: payload.participantes
      .map((participante) => participante.id)
      .filter((id): id is number => typeof id === "number"),
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

export function buildPublishBody(
  criador: UsuarioResumo,
  payload: {
    titulo: string;
    descricao: string;
    categoria?: CategoriaCombinado | "";
    dataPrazo?: string;
    participantes: ParticipanteCombinado[];
    mensagemConvite?: string;
    listaResponsabilidades?: string[];
  },
) {
  const base = buildCreateBody(criador, { ...payload, rascunho: false });
  const participantesIds = payload.participantes
    .map((p) => p.id)
    .filter((id): id is number => typeof id === "number");

  return {
    titulo: base.titulo,
    descricao: base.descricao,
    categoria: base.categoria,
    dataPrazo: base.dataPrazo,
    responsabilidades: base.responsabilidades,
    participantesIds,
  };
}

export function userIsCreator(combinado: Combinado, user: UsuarioResumo) {
  if (combinado.criadorEmail) {
    return combinado.criadorEmail.toLowerCase() === user.email.toLowerCase();
  }
  return combinado.criadorNome.trim().toLowerCase() === user.nome.trim().toLowerCase();
}

export function userIsParticipant(combinado: Combinado, user: UsuarioResumo) {
  const email = user.email.toLowerCase();
  const nome = user.nome.trim().toLowerCase();

  const participantsWithEmail = combinado.extras.participantes.filter((p) => p.email);
  if (participantsWithEmail.length) {
    return participantsWithEmail.some((p) => p.email.toLowerCase() === email);
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
