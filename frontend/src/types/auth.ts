export type PerfilUsuario = "ROLE_USUARIO" | "ROLE_ADMIN";

export interface AuthUser {
  id: number;
  nome: string;
  email: string;
  perfil: PerfilUsuario;
}

export const PERFIL_LABELS: Record<PerfilUsuario, string> = {
  ROLE_USUARIO: "Participante",
  ROLE_ADMIN: "Administrador",
};
