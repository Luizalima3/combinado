package br.edu.ifpb.combinado.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * US62 - Validação para conclusão (todos os participantes aceitaram).
 * Bloqueia a conclusão de um combinado enquanto existirem participantes
 * pendentes ou recusados, informando quem ainda não confirmou.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ParticipantesPendentesException extends RuntimeException {
    public ParticipantesPendentesException(String mensagem) {
        super(mensagem);
    }
}
