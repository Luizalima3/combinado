import axios from "axios";

export const http = axios.create({
  baseURL: "/api",
  headers: { "Content-Type": "application/json" },
  timeout: 15000,
});

http.interceptors.response.use(
  (response) => response,
  (error) => {
    // Demo temporário: a sessão local usa usuários mockados e a busca por participantes
    // ainda não depende do backend real. Não redirecionar para acesso negado por 403
    // enquanto a funcionalidade por e-mail não estiver ativa.
    return Promise.reject(error);
  },
);

export function getApiErrorMessage(error: unknown, fallback: string) {
  if (axios.isAxiosError(error)) {
    const data = error.response?.data as { message?: string } | string | undefined;
    if (typeof data === "string" && data.trim()) return data;
    if (data && typeof data === "object" && data.message) return data.message;
    if (error.message === "Network Error") {
      return "Não foi possível conectar à API. Confira se o backend está em execução.";
    }
  }
  return fallback;
}
