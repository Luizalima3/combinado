import { describe, expect, it } from "vitest";
import { validateCombinadoForm } from "./combinadoFormValidation";

describe("validateCombinadoForm rascunho (#32392)", () => {
  const today = "2026-10-21";
  const empty = {
    titulo: "",
    categoria: "" as const,
    dataPrazo: "",
    participantes: [],
    today,
  };

  it("não exige campos no salvamento parcial", () => {
    expect(validateCombinadoForm(empty, true)).toEqual({});
  });

  it("exige campos na submissão definitiva", () => {
    const errors = validateCombinadoForm(empty, false);
    expect(errors.titulo).toBeTruthy();
    expect(errors.categoria).toBeTruthy();
    expect(errors.dataPrazo).toBeTruthy();
    expect(errors.participantes).toBeTruthy();
  });

  it("exige participantes com id ao publicar rascunho", () => {
    const errors = validateCombinadoForm(
      {
        titulo: "Ok",
        categoria: "OUTRO",
        dataPrazo: "2026-12-01",
        participantes: [{ nome: "Bob", email: "bob@test.com" }],
        today,
        requireRegisteredParticipants: true,
      },
      false,
    );
    expect(errors.participantes).toMatch(/cadastrados/i);
  });
});
