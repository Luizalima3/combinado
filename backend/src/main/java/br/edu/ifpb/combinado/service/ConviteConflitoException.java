package br.edu.ifpb.combinado.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConviteConflitoException extends RuntimeException {
    public ConviteConflitoException(String mensagem) { super(mensagem); }
}
