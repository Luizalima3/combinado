package br.edu.ifpb.combinado.service;

import java.util.Locale;

final class TextoConsulta {
    private TextoConsulta() {}
    static String titulo(String titulo) {
        String termo = titulo == null ? "" : titulo.strip();
        if (termo.length() > 255) throw new IllegalArgumentException("A busca por título aceita até 255 caracteres.");
        return "%" + termo.toLowerCase(Locale.ROOT).replace("!", "!!").replace("%", "!%").replace("_", "!_") + "%";
    }
}
