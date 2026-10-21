import { useEffect } from "react";
import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import { GuestRoute, PrivateRoute } from "./components/auth/PrivateRoute";
import { AppLayout } from "./components/layout/AppLayout";
import { AccessDeniedPage } from "./pages/AccessDeniedPage";
import { CombinadoDetailPage } from "./pages/CombinadoDetailPage";
import { CombinadoFormPage } from "./pages/CombinadoFormPage";
import { DashboardPage } from "./pages/DashboardPage";
import { LoginPage } from "./pages/LoginPage";
import { ProfilePage } from "./pages/ProfilePage";
import { RegisterPage } from "./pages/RegisterPage";

export default function App() {
  const navigate = useNavigate();

  useEffect(() => {
    const onForbidden = () => navigate("/acesso-negado");
    const onUnauthorized = () => navigate("/login", { replace: true });
    window.addEventListener("combinado:forbidden", onForbidden);
    window.addEventListener("combinado:unauthorized", onUnauthorized);
    return () => {
      window.removeEventListener("combinado:forbidden", onForbidden);
      window.removeEventListener("combinado:unauthorized", onUnauthorized);
    };
  }, [navigate]);

  return (
    <Routes>
      <Route element={<GuestRoute />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/cadastro" element={<RegisterPage />} />
      </Route>

      <Route element={<PrivateRoute />}>
        <Route element={<AppLayout />}>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/combinados/novo" element={<CombinadoFormPage />} />
          <Route path="/combinados/:id" element={<CombinadoDetailPage />} />
          <Route path="/perfil" element={<ProfilePage />} />
          <Route path="/acesso-negado" element={<AccessDeniedPage />} />
        </Route>
      </Route>

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
