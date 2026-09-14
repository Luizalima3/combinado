import { NavLink, Outlet } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { ToastHost } from "../ui/ToastHost";

const linkClass = ({ isActive }: { isActive: boolean }) =>
  `rounded-lg px-3 py-2 text-sm font-medium ${
    isActive ? "bg-white/15 text-white" : "text-white/80 hover:bg-white/10 hover:text-white"
  }`;

export function AppLayout() {
  const { user, users, setUser } = useAuth();

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
              </NavLink>
              <NavLink to="/combinados/novo" className={linkClass}>
                Novo combinado
              </NavLink>
            </nav>
          </div>
          <label className="flex items-center gap-2 text-sm text-white/90">
            Sessão demo
            <select
              className="rounded-md border-0 bg-white/15 px-2 py-1.5 text-white outline-none"
              value={user.email}
              onChange={(event) => {
                const next = users.find((item) => item.email === event.target.value);
                if (next) setUser(next);
              }}
            >
              {users.map((item) => (
                <option key={item.email} value={item.email} className="text-ink">
                  {item.nome}
                </option>
              ))}
            </select>
          </label>
        </div>
      </header>
      <main className="mx-auto max-w-6xl px-4 py-8">
        <Outlet />
      </main>
      <ToastHost />
    </div>
  );
}
