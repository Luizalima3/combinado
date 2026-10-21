import { describe, expect, it } from "vitest";
import {
  getPasswordStrength,
  isValidEmail,
  passwordsMatch,
} from "./validation";

describe("validation", () => {
  it("valida e-mail em tempo real", () => {
    expect(isValidEmail("ana@combinado.app")).toBe(true);
    expect(isValidEmail("invalido")).toBe(false);
  });

  it("calcula força da senha", () => {
    expect(getPasswordStrength("123")).toBe("fraca");
    expect(getPasswordStrength("Abc123!")).toBe("media");
    expect(getPasswordStrength("Abc123!@#Segura")).toBe("forte");
  });

  it("confirma senhas iguais", () => {
    expect(passwordsMatch("abc123", "abc123")).toBe(true);
    expect(passwordsMatch("abc123", "abc124")).toBe(false);
  });
});
