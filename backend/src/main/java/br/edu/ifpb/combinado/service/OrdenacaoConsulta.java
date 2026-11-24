package br.edu.ifpb.combinado.service;

import org.springframework.data.domain.Sort;
import java.util.*;

final class OrdenacaoConsulta {
    private OrdenacaoConsulta() {}

    static Sort combinados(List<String> parametros) {
        return montar(parametros, Map.of("criadoEm", "criadoEm", "prazo", "dataPrazo",
                "dataPrazo", "dataPrazo", "status", "status", "id", "id"), "criadoEm");
    }

    static Sort convites(List<String> parametros) {
        return montar(parametros, Map.of("criadoEm", "dataEnvio", "dataEnvio", "dataEnvio",
                "status", "status", "id", "id"), "dataEnvio");
    }

    private static Sort montar(List<String> parametros, Map<String, String> campos, String padrao) {
        if (parametros == null || parametros.isEmpty()) return Sort.by(Sort.Order.desc(padrao), Sort.Order.desc("id"));
        if (parametros.size() > 3) throw new IllegalArgumentException("Informe no máximo três critérios de ordenação.");
        List<Sort.Order> ordens = new ArrayList<>();
        Set<String> utilizados = new HashSet<>();
        for (String parametro : parametros) {
            if (parametro == null) throw new IllegalArgumentException("Ordenação inválida.");
            String[] partes = parametro.split(",", -1);
            String campo = campos.get(partes[0].strip());
            if (campo == null || partes.length > 2 || !utilizados.add(campo)) {
                throw new IllegalArgumentException("Campo de ordenação inválido ou repetido.");
            }
            String direcao = partes.length == 1 ? "asc" : partes[1].strip();
            if (!direcao.equalsIgnoreCase("asc") && !direcao.equalsIgnoreCase("desc")) {
                throw new IllegalArgumentException("Use asc ou desc na ordenação.");
            }
            ordens.add(new Sort.Order(Sort.Direction.fromString(direcao), campo));
        }
        if (!utilizados.contains("id")) ordens.add(Sort.Order.desc("id"));
        return Sort.by(ordens);
    }
}
