export type CategoriaCombinado =
  | "EMPRESTIMO_OBJETO"
  | "DIVISAO_DESPESA"
  | "PRESTACAO_SERVICO"
  | "CUIDADO_ANIMAL"
  | "USO_ESPACO"
  | "ENTREGA_DEVOLUCAO"
  | "OUTRO";

export type StatusCombinado =
  | "RASCUNHO"
  | "AGUARDANDO_CONFIRMACAO"
  | "ATIVO"
  | "CONCLUIDO"
  | "CANCELADO";

export type ConviteStatus = "PENDENTE" | "ACEITO" | "RECUSADO";

export interface UsuarioResumo {
  nome: string;
  email: string;
}

export interface ParticipanteCombinado extends UsuarioResumo {
  conviteStatus?: ConviteStatus;
  respondidoEm?: string;
}

export interface CombinadoExtras {
  participantes: ParticipanteCombinado[];
  rascunho?: boolean;
  mensagemConvite?: string;
  recusaMotivo?: string;
}

export interface Combinado {
  id: number;
  titulo: string;
  descricao?: string;
  categoria: CategoriaCombinado;
  dataPrazo?: string;
  criadorNome: string;
  criadorEmail?: string;
  participanteNome: string;
  responsabilidades?: string;
  status: StatusCombinado;
  criadoEm?: string;
  extras: CombinadoExtras;
}

export interface CreateCombinadoPayload {
  titulo: string;
  descricao: string;
  categoria: CategoriaCombinado | "";
  dataPrazo: string;
  participantes: ParticipanteCombinado[];
  mensagemConvite?: string;
  rascunho: boolean;
}

export const CATEGORIA_LABELS: Record<CategoriaCombinado, string> = {
  EMPRESTIMO_OBJETO: "Empréstimo",
  DIVISAO_DESPESA: "Divisão de despesa",
  PRESTACAO_SERVICO: "Prestação de serviço",
  CUIDADO_ANIMAL: "Cuidado de animal",
  USO_ESPACO: "Uso de espaço",
  ENTREGA_DEVOLUCAO: "Entrega / devolução",
  OUTRO: "Outro",
};

export const STATUS_LABELS: Record<StatusCombinado, string> = {
  RASCUNHO: "Rascunho",
  AGUARDANDO_CONFIRMACAO: "Aguardando confirmação",
  ATIVO: "Ativo",
  CONCLUIDO: "Concluído",
  CANCELADO: "Cancelado",
};
