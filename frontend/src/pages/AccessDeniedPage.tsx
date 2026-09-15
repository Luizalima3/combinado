import { Link } from "react-router-dom";

export function AccessDeniedPage() {
  return (
    <section className="mx-auto max-w-xl rounded-2xl border border-line bg-card px-6 py-12 text-center">
      <p className="text-sm font-semibold tracking-wide text-danger">Erro 403</p>
      <h1 className="mt-2 text-3xl font-semibold text-ink">Acesso negado</h1>
      <p className="mt-3 text-sm text-muted">
        Você não tem permissão para ver este combinado. Apenas o criador e os participantes
        convidados podem acessar os detalhes do acordo.
      </p>
      <Link
        to="/"
        className="mt-6 inline-flex rounded-lg bg-primary px-4 py-2 text-sm font-semibold text-white hover:bg-primary-dark"
      >
        Voltar para meus combinados
      </Link>
    </section>
  );
}
