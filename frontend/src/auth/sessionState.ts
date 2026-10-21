import type { AuthUser, PerfilUsuario } from "../types/auth";

export function isSessionActive(token: string | null, user: AuthUser | null): boolean {
  return Boolean(token && user);
}

export function userHasRole(
  user: AuthUser | null,
  roles: PerfilUsuario | PerfilUsuario[],
): boolean {
  if (!user) return false;
  const list = Array.isArray(roles) ? roles : [roles];
  return list.includes(user.perfil);
}
