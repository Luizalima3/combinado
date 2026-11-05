import { NavLink, Outlet, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { usePendingInvitesCount } from "../../hooks/usePendingInvitesCount";
import { PERFIL_LABELS } from "../../types/auth";

const linkClass = ({ isActive }: { isActive: boolean }) =>
  `relative rounded-lg px-3 py-2 text-sm font-medium ${
    isActive ? "bg-white/15 text-white" : "text-white/80 hover:bg-white/10 hover:text-white"
  }`;

export function AppLayout() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const pendingInvites = usePendingInvitesCount();

  async function handleLogout() {
    await logout();
    navigate("/login", { replace: true });
  }

  return (
    <div className="min-h-svh bg-bg">
      <header className="bg-primary text-white shadow-sm">
        <div className="mx-auto flex max-w-6xl flex-wrap items-center justify-between gap-4 px-4 py-4">
          <div className="flex items-center gap-6">
            <NavLink to="/" className="text-lg font-semibold tracking-tight">
              Combinado
            </NavLink>
            <nav className="flex gap-1">
              <NavLink to="/" className={linkClass} end>
                Meus combinados
                {pendingInvites > 0 ? (
                  <span className="absolute -right-1 -top-1 flex h-4 min-w-4 items-center justify-center rounded-full bg-danger px-1 text-[10px] font-bold">
                    {pendingInvites > 9 ? "9+" : pendingInvites}
                  </span>
                ) : null}
              </NavLink>
              <NavLink to="/combinados/novo" className={linkClass}>
                Novo combinado
              </NavLink>
              <NavLink to="/perfil" className={linkClass}>
                Perfil
              </NavLink>
            </nav>
          </div>
          <div className="flex flex-wrap items-center gap-3 text-sm text-white/90">
            {user ? (
              <>
                <span>
                  {user.nome}{" "}
                  <span className="text-white/70">({PERFIL_LABELS[user.perfil]})</span>
                </span>
                <button
                  type="button"
                  onClick={() => void handleLogout()}
                  className="rounded-lg border border-white/30 px-3 py-1.5 font-semibold hover:bg-white/10"
                >
                  Sair
                </button>
              </>
            ) : null}
          </div>
        </div>
      </header>
      <main className="mx-auto max-w-6xl px-4 py-8">
        <Outlet />
      </main>
    </div>
  );
}
