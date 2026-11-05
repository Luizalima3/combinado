package br.edu.ifpb.combinado.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * US63 - Bloqueio de edição após o combinado ficar ativo.
 * Lançada quando o usuário tenta editar título, descrição, categoria,
 * prazo ou participantes de um combinado que não pode mais ser alterado.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class CombinadoNaoEditavelException extends RuntimeException {
    public CombinadoNaoEditavelException(String mensagem) {
        super(mensagem);
    }
}
