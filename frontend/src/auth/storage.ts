import type { AuthUser } from "../types/auth";

export const TOKEN_STORAGE_KEY = "combinado.jwt";
export const USER_STORAGE_KEY = "combinado.authUser";

export function readToken(): string | null {
  return localStorage.getItem(TOKEN_STORAGE_KEY);
}

export function persistSession(token: string, user: AuthUser) {
  localStorage.setItem(TOKEN_STORAGE_KEY, token);
  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
}

export function readStoredUser(): AuthUser | null {
  try {
    const raw = localStorage.getItem(USER_STORAGE_KEY);
    if (!raw) return null;
    return JSON.parse(raw) as AuthUser;
  } catch {
    return null;
  }
}

export function clearAuthStorage() {
  localStorage.removeItem(TOKEN_STORAGE_KEY);
  localStorage.removeItem(USER_STORAGE_KEY);
  localStorage.removeItem("combinado.sessionUser");
}
