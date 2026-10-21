import { beforeEach, describe, expect, it } from "vitest";
import {
  clearAuthStorage,
  persistSession,
  readStoredUser,
  readToken,
  TOKEN_STORAGE_KEY,
  USER_STORAGE_KEY,
} from "./storage";

describe("auth storage", () => {
  beforeEach(() => {
    localStorage.clear();
  });

  it("persiste e limpa credenciais", () => {
    persistSession("jwt-token", {
      id: 1,
      nome: "Ana",
      email: "ana@test.com",
      perfil: "ROLE_USUARIO",
    });
    expect(readToken()).toBe("jwt-token");
    expect(readStoredUser()?.email).toBe("ana@test.com");

    clearAuthStorage();
    expect(localStorage.getItem(TOKEN_STORAGE_KEY)).toBeNull();
    expect(localStorage.getItem(USER_STORAGE_KEY)).toBeNull();
  });
});
