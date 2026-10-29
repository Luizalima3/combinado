import axios from "axios";
import { readToken, TOKEN_STORAGE_KEY } from "../auth/storage";
import { isPublicAuthFailureRequest } from "./httpAuth";

export const http = axios.create({
  baseURL: "/api",
  headers: { "Content-Type": "application/json" },
  timeout: 15000,
});

http.interceptors.request.use((config) => {
  const token = readToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

http.interceptors.response.use(
  (response) => response,
  (error) => {
    const status = error.response?.status;
    if (status === 401 && !isPublicAuthFailureRequest(error.config?.url)) {
      // Evita logout imediato em caso de erro de rede ou instabilidade, 
      // permitindo que a aplicação tente recuperar a sessão ou exiba erro amigável.
      if (error.response?.headers?.get("WWW-Authenticate")?.includes("Bearer")) {
        localStorage.removeItem(TOKEN_STORAGE_KEY);
        window.dispatchEvent(new CustomEvent("combinado:unauthorized"));
      }
    }
    if (status === 403) {
      window.dispatchEvent(new CustomEvent("combinado:forbidden"));
    }
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
