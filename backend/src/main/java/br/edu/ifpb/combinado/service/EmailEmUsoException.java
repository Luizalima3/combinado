package br.edu.ifpb.combinado.service;

public class EmailEmUsoException extends RuntimeException {
    public EmailEmUsoException() { super("Este e-mail já está em uso."); }
}
