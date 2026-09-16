import type { CategoriaCombinado } from "../../types/combinado";

const CATEGORY_META: Array<{
  value: CategoriaCombinado;
  label: string;
  description: string;
}> = [
  {
    value: "EMPRESTIMO_OBJETO",
    label: "Empréstimo",
    description: "Objetos e itens",
  },
  {
    value: "DIVISAO_DESPESA",
    label: "Despesa",
    description: "Gastos compartilhados",
  },
  {
    value: "PRESTACAO_SERVICO",
    label: "Serviço",
    description: "Atividades e tarefas",
  },
  {
    value: "CUIDADO_ANIMAL",
    label: "Cuidado",
    description: "Cuidados com pets",
  },
  {
    value: "USO_ESPACO",
    label: "Espaço",
    description: "Uso de ambiente",
  },
  {
    value: "ENTREGA_DEVOLUCAO",
    label: "Entrega",
    description: "Entrega e devolução",
  },
  {
    value: "OUTRO",
    label: "Outro",
    description: "Caso específico",
  },
];

export function CategoryPicker({
  value,
  onChange,
  error,
}: {
  value: CategoriaCombinado | "";
  onChange: (value: CategoriaCombinado | "") => void;
  error?: string;
}) {
  return (
    <div>
      <select
        value={value}
        onChange={(event) => onChange((event.target.value as CategoriaCombinado | "") || "")}
        className={`w-full rounded-lg border bg-white px-3 py-2.5 text-sm text-slate-700 outline-none transition focus:border-primary ${
          error ? "border-danger" : "border-line"
        }`}
      >
        <option value="">Selecione a categoria</option>
        {CATEGORY_META.map((category) => (
          <option key={category.value} value={category.value}>
            {category.label} — {category.description}
          </option>
        ))}
      </select>

      {error ? <span className="mt-2 block text-sm text-danger">{error}</span> : null}
    </div>
  );
}
