export const EMAIL_PATTERN = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;

export function isValidEmail(value: string) {
  const trimmed = value.trim();
  if (!trimmed) return false;
  return EMAIL_PATTERN.test(trimmed);
}

export type PasswordStrength = "fraca" | "media" | "forte";

export function getPasswordStrength(password: string): PasswordStrength {
  if (!password) return "fraca";
  let score = 0;
  if (password.length >= 8) score += 1;
  if (password.length >= 12) score += 1;
  if (/[A-Z]/.test(password)) score += 1;
  if (/[a-z]/.test(password)) score += 1;
  if (/\d/.test(password)) score += 1;
  if (/[^A-Za-z0-9]/.test(password)) score += 1;
  if (score >= 5) return "forte";
  if (score >= 3) return "media";
  return "fraca";
}

export const PASSWORD_STRENGTH_LABEL: Record<PasswordStrength, string> = {
  fraca: "Fraca",
  media: "Média",
  forte: "Forte",
};

export function passwordsMatch(password: string, confirmation: string) {
  return password.length > 0 && password === confirmation;
}

/** Mensagem ao adicionar participante por e-mail (formato vs usuário inexistente). */
export function participantAddErrorMessage(email: string, userFound: boolean): string | null {
  const trimmed = email.trim();
  if (!trimmed) return "Informe o e-mail do participante.";
  if (!isValidEmail(trimmed)) return "Informe um e-mail válido.";
  if (!userFound) return "Nenhum usuário encontrado com este e-mail.";
  return null;
}
