import { createContext, useContext, useMemo, useState, type ReactNode } from "react";
import { DEFAULT_SESSION_USER, MOCK_USERS } from "../data/mockUsers";
import type { UsuarioResumo } from "../types/combinado";

const STORAGE_KEY = "combinado.sessionUser";

interface AuthContextValue {
  user: UsuarioResumo;
  users: UsuarioResumo[];
  setUser: (user: UsuarioResumo) => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

function readStoredUser(): UsuarioResumo {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return DEFAULT_SESSION_USER;
    const parsed = JSON.parse(raw) as UsuarioResumo;
    return MOCK_USERS.find((u) => u.email === parsed.email) ?? DEFAULT_SESSION_USER;
  } catch {
    return DEFAULT_SESSION_USER;
  }
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUserState] = useState<UsuarioResumo>(readStoredUser);

  const setUser = (next: UsuarioResumo) => {
    setUserState(next);
    localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  };

  const value = useMemo(
    () => ({ user, users: MOCK_USERS, setUser }),
    [user],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth deve ser usado dentro de AuthProvider");
  return ctx;
}
