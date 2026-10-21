import { describe, expect, it } from "vitest";
import type { AuthUser } from "../types/auth";

function hasRole(user: AuthUser | null, roles: AuthUser["perfil"] | AuthUser["perfil"][]) {
  if (!user) return false;
  const list = Array.isArray(roles) ? roles : [roles];
  return list.includes(user.perfil);
}

describe("perfil de acesso", () => {
  const admin: AuthUser = {
    id: 1,
    nome: "Admin",
    email: "admin@test.com",
    perfil: "ROLE_ADMIN",
  };
  const user: AuthUser = {
    id: 2,
    nome: "User",
    email: "user@test.com",
    perfil: "ROLE_USUARIO",
  };

  it("identifica administrador", () => {
    expect(hasRole(admin, "ROLE_ADMIN")).toBe(true);
    expect(hasRole(user, "ROLE_ADMIN")).toBe(false);
  });

  it("nega acesso sem sessão", () => {
    expect(hasRole(null, "ROLE_USUARIO")).toBe(false);
  });
});
