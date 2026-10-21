import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { listRecebidos } from "../services/combinadoService";

export function usePendingInvitesCount() {
  const { sessionUser, isAuthenticated } = useAuth();
  const [count, setCount] = useState(0);

  useEffect(() => {
    if (!isAuthenticated) {
      setCount(0);
      return;
    }
    let cancelled = false;
    void listRecebidos(sessionUser)
      .then((items) => {
        if (cancelled) return;
        setCount(items.filter((item) => item.status === "AGUARDANDO_CONFIRMACAO").length);
      })
      .catch(() => {
        if (!cancelled) setCount(0);
      });
    return () => {
      cancelled = true;
    };
  }, [isAuthenticated, sessionUser.email]);

  return count;
}
