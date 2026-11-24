package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import java.util.*;
import java.util.function.Function;

/** Mesma regra para apresentar a ação e validar a conclusão efetiva. */
final class ConfirmacaoCombinado {
    private ConfirmacaoCombinado() {}
    static List<String> pendentes(Combinado combinado, Function<Long, Optional<StatusConvite>> statusAtual) {
        return combinado.getUsuariosParticipantes().stream()
                .filter(u -> statusAtual.apply(u.getId()).orElse(StatusConvite.PENDENTE) != StatusConvite.ACEITO)
                .map(Usuario::getNome).sorted().toList();
    }
}
