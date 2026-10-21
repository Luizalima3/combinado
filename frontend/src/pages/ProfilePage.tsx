import { type FormEvent, useState } from "react";
import { getApiErrorMessage } from "../api/http";
import { PasswordStrengthBar } from "../components/auth/PasswordStrengthBar";
import { useAuth } from "../context/AuthContext";
import { useToast } from "../context/ToastContext";
import {
  updateProfileEmail,
  updateProfileName,
  updateProfilePassword,
} from "../services/authService";
import { PERFIL_LABELS } from "../types/auth";
import { isValidEmail, passwordsMatch } from "../utils/validation";

export function ProfilePage() {
  const { user, updateUser, hasRole } = useAuth();
  const { push } = useToast();

  const [nome, setNome] = useState(user?.nome ?? "");
  const [email, setEmail] = useState(user?.email ?? "");
  const [senhaAtualEmail, setSenhaAtualEmail] = useState("");
  const [senhaAtual, setSenhaAtual] = useState("");
  const [novaSenha, setNovaSenha] = useState("");
  const [confirmacaoSenha, setConfirmacaoSenha] = useState("");
  const [busy, setBusy] = useState<string | null>(null);

  if (!user) return null;

  async function saveNome(event: FormEvent) {
    event.preventDefault();
    setBusy("nome");
    try {
      const updated = await updateProfileName(nome.trim());
      updateUser(updated);
      push("success", "Nome atualizado.");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível atualizar o nome."));
    } finally {
      setBusy(null);
    }
  }

  async function saveEmail(event: FormEvent) {
    event.preventDefault();
    if (!isValidEmail(email)) {
      push("error", "Informe um e-mail válido.");
      return;
    }
    setBusy("email");
    try {
      const updated = await updateProfileEmail(email, senhaAtualEmail);
      updateUser(updated);
      setSenhaAtualEmail("");
      push("success", "E-mail atualizado.");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível atualizar o e-mail."));
    } finally {
      setBusy(null);
    }
  }

  async function saveSenha(event: FormEvent) {
    event.preventDefault();
    if (novaSenha.length < 6) {
      push("error", "A nova senha deve ter no mínimo 6 caracteres.");
      return;
    }
    if (!passwordsMatch(novaSenha, confirmacaoSenha)) {
      push("error", "A confirmação deve ser igual à nova senha.");
      return;
    }
    setBusy("senha");
    try {
      await updateProfilePassword(senhaAtual, novaSenha, confirmacaoSenha);
      setSenhaAtual("");
      setNovaSenha("");
      setConfirmacaoSenha("");
      push("success", "Senha alterada com sucesso.");
    } catch (err) {
      push("error", getApiErrorMessage(err, "Não foi possível alterar a senha."));
    } finally {
      setBusy(null);
    }
  }

  return (
    <section className="mx-auto max-w-3xl space-y-6">
      <div>
        <h1 className="text-2xl font-semibold text-ink">Meu perfil</h1>
        <p className="mt-1 text-sm text-muted">
          Perfil de acesso:{" "}
          <strong>{PERFIL_LABELS[user.perfil]}</strong>
          {hasRole("ROLE_ADMIN") ? " — permissões administrativas ativas" : ""}
        </p>
      </div>

      <ProfileCard title="Alterar nome" onSubmit={saveNome} busy={busy === "nome"}>
        <input
          value={nome}
          onChange={(event) => setNome(event.target.value)}
          className="w-full rounded-lg border border-line px-3 py-2 text-sm"
        />
      </ProfileCard>

      <ProfileCard title="Alterar e-mail" onSubmit={saveEmail} busy={busy === "email"}>
        <div className="space-y-3">
          <input
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            className="w-full rounded-lg border border-line px-3 py-2 text-sm"
          />
          <input
            type="password"
            placeholder="Senha atual para confirmar"
            value={senhaAtualEmail}
            onChange={(event) => setSenhaAtualEmail(event.target.value)}
            className="w-full rounded-lg border border-line px-3 py-2 text-sm"
          />
        </div>
      </ProfileCard>

      <ProfileCard title="Alterar senha" onSubmit={saveSenha} busy={busy === "senha"}>
        <div className="space-y-3">
          <input
            type="password"
            placeholder="Senha atual"
            value={senhaAtual}
            onChange={(event) => setSenhaAtual(event.target.value)}
            className="w-full rounded-lg border border-line px-3 py-2 text-sm"
          />
          <input
            type="password"
            placeholder="Nova senha"
            value={novaSenha}
            onChange={(event) => setNovaSenha(event.target.value)}
            className="w-full rounded-lg border border-line px-3 py-2 text-sm"
          />
          <PasswordStrengthBar password={novaSenha} />
          <input
            type="password"
            placeholder="Confirmar nova senha"
            value={confirmacaoSenha}
            onChange={(event) => setConfirmacaoSenha(event.target.value)}
            className="w-full rounded-lg border border-line px-3 py-2 text-sm"
          />
        </div>
      </ProfileCard>
    </section>
  );
}

function ProfileCard({
  title,
  children,
  onSubmit,
  busy,
}: {
  title: string;
  children: React.ReactNode;
  onSubmit: (event: FormEvent) => void;
  busy?: boolean;
}) {
  return (
    <form
      onSubmit={(event) => void onSubmit(event)}
      className="rounded-2xl border border-line bg-card p-6"
    >
      <h2 className="text-lg font-semibold text-ink">{title}</h2>
      <div className="mt-4">{children}</div>
      <div className="mt-4 flex justify-end">
        <button
          type="submit"
          disabled={busy}
          className="rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white disabled:opacity-60"
        >
          {busy ? "Salvando..." : "Salvar"}
        </button>
      </div>
    </form>
  );
}
