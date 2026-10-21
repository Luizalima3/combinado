import { type FormEvent, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { getApiErrorMessage } from "../api/http";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import { isValidEmail } from "../utils/validation";

export function LoginPage() {
  const { login } = useAuth();
  const { push } = useToast();
  const navigate = useNavigate();
  const location = useLocation();
  const redirectTo = (location.state as { from?: string } | null)?.from ?? "/";

  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [emailError, setEmailError] = useState("");
  const [submitting, setSubmitting] = useState(false);

  async function onSubmit(event: FormEvent) {
    event.preventDefault();
    if (!isValidEmail(email)) {
      setEmailError("Informe um e-mail válido.");
      return;
    }
    setEmailError("");
    setSubmitting(true);
    try {
      await login(email, senha);
      push("success", "Login realizado com sucesso.");
      navigate(redirectTo, { replace: true });
    } catch (err) {
      push("error", getApiErrorMessage(err, "E-mail ou senha inválidos."));
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="flex min-h-svh items-center justify-center bg-bg px-4">
      <section className="w-full max-w-md rounded-2xl border border-line bg-card p-8 shadow-sm">
        <h1 className="text-2xl font-semibold text-ink">Entrar no Combinado</h1>
        <p className="mt-1 text-sm text-muted">Use seu e-mail e senha para acessar a plataforma.</p>
        <form onSubmit={(event) => void onSubmit(event)} className="mt-6 space-y-4">
          <label className="block text-sm font-medium text-ink">
            E-mail
            <input
              type="email"
              autoComplete="email"
              value={email}
              onChange={(event) => {
                setEmail(event.target.value);
                if (emailError && isValidEmail(event.target.value)) setEmailError("");
              }}
              onBlur={() => {
                if (email && !isValidEmail(email)) setEmailError("Informe um e-mail válido.");
              }}
              className={`mt-1 w-full rounded-lg border bg-white px-3 py-2 text-sm outline-none focus:border-primary ${
                emailError ? "border-danger" : "border-line"
              }`}
            />
            {emailError ? <span className="mt-1 block text-sm text-danger">{emailError}</span> : null}
          </label>
          <label className="block text-sm font-medium text-ink">
            Senha
            <input
              type="password"
              autoComplete="current-password"
              value={senha}
              onChange={(event) => setSenha(event.target.value)}
              className="mt-1 w-full rounded-lg border border-line bg-white px-3 py-2 text-sm outline-none focus:border-primary"
            />
          </label>
          <button
            type="submit"
            disabled={submitting}
            className="w-full rounded-lg bg-primary py-2.5 text-sm font-semibold text-white hover:bg-primary-dark disabled:opacity-60"
          >
            {submitting ? "Entrando..." : "Entrar"}
          </button>
        </form>
        <p className="mt-4 text-center text-sm text-muted">
          Ainda não tem conta?{" "}
          <Link to="/cadastro" className="font-semibold text-primary hover:underline">
            Cadastre-se
          </Link>
        </p>
      </section>
    </div>
  );
}
