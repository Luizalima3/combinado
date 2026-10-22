import { describe, expect, it } from "vitest";
import { isPublicAuthFailureRequest } from "./httpAuth";

describe("isPublicAuthFailureRequest", () => {
  it("identifica falha de login e cadastro", () => {
    expect(isPublicAuthFailureRequest("/auth/login")).toBe(true);
    expect(isPublicAuthFailureRequest("/usuarios")).toBe(true);
    expect(isPublicAuthFailureRequest("/combinados")).toBe(false);
    expect(isPublicAuthFailureRequest("/auth/logout")).toBe(false);
  });
});
