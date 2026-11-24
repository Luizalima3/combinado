package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import jakarta.persistence.criteria.*;
import java.time.*;
import java.util.*;

record FiltroPeriodoCategoria(CategoriaCombinado categoria, LocalDate dataInicio, LocalDate dataFim) {
    static FiltroPeriodoCategoria de(String categoria, LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("dataInicio não pode ser posterior a dataFim.");
        }
        CategoriaCombinado tipo = null;
        if (categoria != null && !categoria.isBlank()) {
            String valor = categoria.strip().toUpperCase(Locale.ROOT);
            valor = switch (valor) {
                case "EMPRESTIMO" -> "EMPRESTIMO_OBJETO";
                case "DESPESA" -> "DIVISAO_DESPESA";
                default -> valor;
            };
            try { tipo = CategoriaCombinado.valueOf(valor); }
            catch (IllegalArgumentException e) { throw new IllegalArgumentException("Categoria inválida."); }
        }
        return new FiltroPeriodoCategoria(tipo, dataInicio, dataFim);
    }

    Predicate predicado(Path<?> combinado, CriteriaBuilder cb) {
        List<Predicate> filtros = new ArrayList<>();
        if (categoria != null) filtros.add(cb.equal(combinado.get("categoria"), categoria));
        // Regra confirmada: início usa criação, fim usa vencimento do prazo.
        if (dataInicio != null) filtros.add(cb.greaterThanOrEqualTo(
                combinado.<LocalDateTime>get("criadoEm"), dataInicio.atStartOfDay()));
        if (dataFim != null) filtros.add(cb.lessThanOrEqualTo(combinado.<LocalDate>get("dataPrazo"), dataFim));
        return cb.and(filtros.toArray(Predicate[]::new));
    }
}
