import { useEffect } from "react";
import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import { AppLayout } from "./components/layout/AppLayout";
import { AccessDeniedPage } from "./pages/AccessDeniedPage";
import { CombinadoDetailPage } from "./pages/CombinadoDetailPage";
import { CombinadoFormPage } from "./pages/CombinadoFormPage";
import { DashboardPage } from "./pages/DashboardPage";

export default function App() {
  const navigate = useNavigate();

  useEffect(() => {
    const onForbidden = () => navigate("/acesso-negado");
    window.addEventListener("combinado:forbidden", onForbidden);
    return () => window.removeEventListener("combinado:forbidden", onForbidden);
  }, [navigate]);

  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route path="/" element={<DashboardPage />} />
        <Route path="/combinados/novo" element={<CombinadoFormPage />} />
        <Route path="/combinados/:id" element={<CombinadoDetailPage />} />
        <Route path="/acesso-negado" element={<AccessDeniedPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Route>
    </Routes>
  );
}
