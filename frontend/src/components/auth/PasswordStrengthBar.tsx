import {
  getPasswordStrength,
  PASSWORD_STRENGTH_LABEL,
  type PasswordStrength,
} from "../../utils/validation";

const BAR_CLASS: Record<PasswordStrength, string> = {
  fraca: "bg-danger",
  media: "bg-warn",
  forte: "bg-mint",
};

const WIDTH: Record<PasswordStrength, string> = {
  fraca: "w-1/3",
  media: "w-2/3",
  forte: "w-full",
};

export function PasswordStrengthBar({ password }: { password: string }) {
  if (!password) return null;
  const strength = getPasswordStrength(password);
  return (
    <div className="mt-2 space-y-1" aria-live="polite">
      <div className="h-1.5 w-full overflow-hidden rounded-full bg-line">
        <div className={`h-full transition-all ${WIDTH[strength]} ${BAR_CLASS[strength]}`} />
      </div>
      <p className="text-xs text-muted">
        Força da senha:{" "}
        <span className="font-semibold text-ink">{PASSWORD_STRENGTH_LABEL[strength]}</span>
      </p>
    </div>
  );
}
