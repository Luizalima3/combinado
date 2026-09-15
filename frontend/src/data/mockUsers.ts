import type { UsuarioResumo } from "../types/combinado";

/** Diretório local enquanto o endpoint de usuários não está disponível no backend da Sprint 1. */
export const MOCK_USERS: UsuarioResumo[] = [
  { nome: "Rodrigo Oliveira", email: "rodrigo@combinado.app" },
  { nome: "Ana Souza", email: "ana@combinado.app" },
  { nome: "Bruno Lima", email: "bruno@combinado.app" },
  { nome: "Catarina Dias", email: "carla@combinado.app" },
  { nome: "Diego Alves", email: "diego@combinado.app" },
];

export const DEFAULT_SESSION_USER = MOCK_USERS[0];
