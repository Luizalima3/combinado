import { describe, expect, it } from "vitest";
import { isSessionActive, userHasRole } from "./sessionState";

describe("sessão e rotas privadas (#30932, #30935)", () => {
  const user = {
    id: 1,
    nome: "Ana",
    email: "ana@test.com",
    perfil: "ROLE_USUARIO" as const,
  };

  it("considera sessão ativa somente com token e usuário", () => {
    expect(isSessionActive("jwt", user)).toBe(true);
    expect(isSessionActive(null, user)).toBe(false);
    expect(isSessionActive("jwt", null)).toBe(false);
    expect(isSessionActive(null, null)).toBe(false);
  });

  it("simula logout removendo credenciais", () => {
    expect(isSessionActive("jwt", user)).toBe(true);
    expect(isSessionActive(null, null)).toBe(false);
  });
});

describe("perfis de acesso (#30958)", () => {
  it("valida perfil administrativo", () => {
    expect(
      userHasRole(
        { id: 1, nome: "Admin", email: "a@t.com", perfil: "ROLE_ADMIN" },
        "ROLE_ADMIN",
      ),
    ).toBe(true);
    expect(
      userHasRole(
        { id: 2, nome: "User", email: "u@t.com", perfil: "ROLE_USUARIO" },
        "ROLE_ADMIN",
      ),
    ).toBe(false);
  });
});
