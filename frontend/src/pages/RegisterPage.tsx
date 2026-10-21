import { type FormEvent, useMemo, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { PasswordStrengthBar } from "../components/auth/PasswordStrengthBar";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import { isValidEmail, passwordsMatch } from "../utils/validation";

export function RegisterPage() {
  const { register } = useAuth();
  const { push } = useToast();
  const navigate = useNavigate();

  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [confirmacao, setConfirmacao] = useState("");
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [submitting, setSubmitting] = useState(false);

  const emailOk = useMemo(() => isValidEmail(email), [email]);
  const confirmOk = useMemo(() => passwordsMatch(senha, confirmacao), [senha, confirmacao]);

  function validate() {
    const next: Record<string, string> = {};
    if (!nome.trim() || nome.trim().length < 2) next.nome = "Informe seu nome completo.";
    if (!emailOk) next.email = "Informe um e-mail válido.";
    if (senha.length < 6) next.senha = "A senha deve ter no mínimo 6 caracteres.";
    if (!confirmOk) next.confirmacao = "A confirmação deve ser igual à senha.";
    setErrors(next);
    return Object.keys(next).length === 0;
  }

  async function onSubmit(event: FormEvent) {
    event.preventDefault();
    if (!validate()) return;
    setSubmitting(true);
    try {
      await register({ nome, email, senha, confirmacaoSenha: confirmacao });
      push("success", "Conta criada com sucesso.");
      navigate("/", { replace: true });
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível concluir o cadastro."));
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="flex min-h-svh items-center justify-center bg-bg px-4 py-10">
      <section className="w-full max-w-md rounded-2xl border border-line bg-card p-8 shadow-sm">
        <h1 className="text-2xl font-semibold text-ink">Criar conta</h1>
        <p className="mt-1 text-sm text-muted">Cadastre-se para criar e gerenciar combinados.</p>
        <form onSubmit={(event) => void onSubmit(event)} className="mt-6 space-y-4">
          <Field label="Nome" error={errors.nome}>
            <input
              value={nome}
              onChange={(event) => setNome(event.target.value)}
              className={inputClass(errors.nome)}
            />
          </Field>
          <Field label="E-mail" error={errors.email}>
            <input
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              className={inputClass(errors.email)}
            />
            {email && !emailOk ? (
              <span className="mt-1 block text-xs text-danger">Formato de e-mail inválido.</span>
            ) : emailOk ? (
              <span className="mt-1 block text-xs text-mint-dark">E-mail válido.</span>
            ) : null}
          </Field>
          <Field label="Senha" error={errors.senha}>
            <input
              type="password"
              value={senha}
              onChange={(event) => setSenha(event.target.value)}
              className={inputClass(errors.senha)}
            />
            <PasswordStrengthBar password={senha} />
          </Field>
          <Field label="Confirmar senha" error={errors.confirmacao}>
            <input
              type="password"
              value={confirmacao}
              onChange={(event) => setConfirmacao(event.target.value)}
              className={inputClass(errors.confirmacao)}
            />
            {confirmacao && !confirmOk ? (
              <span className="mt-1 block text-xs text-danger">As senhas não coincidem.</span>
            ) : confirmOk ? (
              <span className="mt-1 block text-xs text-mint-dark">Senhas conferem.</span>
            ) : null}
          </Field>
          <button
            type="submit"
            disabled={submitting}
            className="w-full rounded-lg bg-primary py-2.5 text-sm font-semibold text-white hover:bg-primary-dark disabled:opacity-60"
          >
            {submitting ? "Cadastrando..." : "Cadastrar"}
          </button>
        </form>
        <p className="mt-4 text-center text-sm text-muted">
          Já possui conta?{" "}
          <Link to="/login" className="font-semibold text-primary hover:underline">
            Fazer login
          </Link>
        </p>
      </section>
    </div>
  );
}

function Field({
  label,
  error,
  children,
}: {
  label: string;
  error?: string;
  children: React.ReactNode;
}) {
  return (
    <label className="block text-sm font-medium text-ink">
      {label}
      <div className="mt-1">{children}</div>
      {error ? <span className="mt-1 block text-sm text-danger">{error}</span> : null}
    </label>
  );
}

function inputClass(error?: string) {
  return `w-full rounded-lg border bg-white px-3 py-2 text-sm outline-none focus:border-primary ${
    error ? "border-danger" : "border-line"
  }`;
}
