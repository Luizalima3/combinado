package br.edu.ifpb.combinado.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * US62 - Validação para conclusão (todos os participantes aceitaram).
 * Bloqueia a conclusão de um combinado enquanto existirem participantes
 * pendentes ou recusados, carregando a lista de quem ainda não confirmou.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ParticipantesPendentesException extends RuntimeException {

    private final transient List<String> participantesPendentes;

    public ParticipantesPendentesException(String mensagem, List<String> participantesPendentes) {
        super(mensagem);
        this.participantesPendentes = List.copyOf(participantesPendentes);
    }

    public List<String> getParticipantesPendentes() {
        return participantesPendentes;
    }
}
