package br.edu.ifpb.combinado.service;

public class TarefaNaoEncontradaException extends RuntimeException {
    public TarefaNaoEncontradaException() { super("Tarefa não encontrada neste combinado."); }
}
