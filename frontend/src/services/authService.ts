import { http } from "../api/http";
import type { AuthUser, PerfilUsuario } from "../types/auth";

export interface LoginResponse {
  token: string;
  tipo: string;
  expiraEm: string;
  usuario: {
    id: number;
    nome: string;
    email: string;
    perfil: PerfilUsuario;
    dataCriacao?: string;
  };
}

export interface RegisterPayload {
  nome: string;
  email: string;
  senha: string;
  confirmacaoSenha: string;
}

export function mapAuthUser(raw: LoginResponse["usuario"]): AuthUser {
  return {
    id: raw.id,
    nome: raw.nome,
    email: raw.email,
    perfil: raw.perfil,
  };
}

export async function login(email: string, senha: string): Promise<LoginResponse> {
  const { data } = await http.post<LoginResponse>("/auth/login", {
    email: email.trim().toLowerCase(),
    senha,
  });
  return data;
}

export async function register(payload: RegisterPayload): Promise<void> {
  await http.post("/usuarios", {
    nome: payload.nome.trim(),
    email: payload.email.trim().toLowerCase(),
    senha: payload.senha,
    confirmacaoSenha: payload.confirmacaoSenha,
  });
}

export async function logoutRequest(): Promise<void> {
  await http.post("/auth/logout");
}

export async function updateProfileName(nome: string): Promise<AuthUser> {
  const { data } = await http.put<LoginResponse["usuario"]>("/usuarios/perfil/nome", { nome });
  return mapAuthUser(data);
}

export async function updateProfileEmail(email: string, senhaAtual: string): Promise<AuthUser> {
  const { data } = await http.put<LoginResponse["usuario"]>("/usuarios/perfil/email", {
    email: email.trim().toLowerCase(),
    senhaAtual,
  });
  return mapAuthUser(data);
}

export async function updateProfilePassword(
  senhaAtual: string,
  novaSenha: string,
  confirmacaoSenha: string,
): Promise<void> {
  await http.put("/usuarios/perfil/senha", {
    senhaAtual,
    novaSenha,
    confirmacaoSenha,
  });
}
