/** Rotas em que 401 é falha esperada (login/cadastro), não sessão expirada. */
export function isPublicAuthFailureRequest(url?: string): boolean {
  if (!url) return false;
  const path = url.split("?")[0];
  return (
    path.endsWith("/auth/login") ||
    path.endsWith("/usuarios") ||
    path.includes("/auth/login")
  );
}
