import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
  type ReactNode,
} from "react";
import {
  clearAuthStorage,
  persistSession,
  readStoredUser,
  readToken,
} from "../auth/storage";
import { isSessionActive, userHasRole } from "../auth/sessionState";
import {
  login as loginRequest,
  logoutRequest,
  mapAuthUser,
  register as registerRequest,
  type RegisterPayload,
} from "../services/authService";
import type { AuthUser, PerfilUsuario } from "../types/auth";
import type { UsuarioResumo } from "../types/combinado";

interface AuthContextValue {
  user: AuthUser | null;
  sessionUser: UsuarioResumo;
  isAuthenticated: boolean;
  login: (email: string, senha: string) => Promise<void>;
  register: (payload: RegisterPayload) => Promise<void>;
  logout: () => Promise<void>;
  updateUser: (user: AuthUser) => void;
  hasRole: (roles: PerfilUsuario | PerfilUsuario[]) => boolean;
}

const AuthContext = createContext<AuthContextValue | null>(null);

function toSessionUser(user: AuthUser | null): UsuarioResumo {
  if (!user) return { nome: "Visitante", email: "" };
  return { id: user.id, nome: user.nome, email: user.email, perfil: user.perfil };
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(() => readToken());
  const [user, setUser] = useState<AuthUser | null>(() => readStoredUser());

  const applySession = useCallback((nextToken: string, nextUser: AuthUser) => {
    persistSession(nextToken, nextUser);
    setToken(nextToken);
    setUser(nextUser);
  }, []);

  const clearSession = useCallback(() => {
    clearAuthStorage();
    setToken(null);
    setUser(null);
  }, []);

  useEffect(() => {
    const onUnauthorized = () => clearSession();
    window.addEventListener("combinado:unauthorized", onUnauthorized);
    return () => window.removeEventListener("combinado:unauthorized", onUnauthorized);
  }, [clearSession]);

  const login = useCallback(
    async (email: string, senha: string) => {
      const data = await loginRequest(email, senha);
      applySession(data.token, mapAuthUser(data.usuario));
    },
    [applySession],
  );

  const register = useCallback(
    async (payload: RegisterPayload) => {
      await registerRequest(payload);
      await login(payload.email, payload.senha);
    },
    [login],
  );

  const logout = useCallback(async () => {
    try {
      if (readToken()) await logoutRequest();
    } catch {
      /* encerra sessão local mesmo se a API falhar */
    } finally {
      clearSession();
    }
  }, [clearSession]);

  const updateUser = useCallback((next: AuthUser) => {
    const currentToken = readToken();
    if (currentToken) persistSession(currentToken, next);
    setUser(next);
  }, []);

  const hasRole = useCallback(
    (roles: PerfilUsuario | PerfilUsuario[]) => userHasRole(user, roles),
    [user],
  );

  const value = useMemo<AuthContextValue>(
    () => ({
      user,
      sessionUser: toSessionUser(user),
      isAuthenticated: isSessionActive(token, user),
      login,
      register,
      logout,
      updateUser,
      hasRole,
    }),
    [user, token, login, register, logout, updateUser, hasRole],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth deve ser usado dentro de AuthProvider");
  return ctx;
}
