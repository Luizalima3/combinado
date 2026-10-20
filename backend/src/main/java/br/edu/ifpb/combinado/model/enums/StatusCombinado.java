package br.edu.ifpb.combinado.model.enums;

public enum StatusCombinado {
    RASCUNHO,
    AGUARDANDO_CONFIRMACAO,
    ATIVO,
    CONCLUIDO,
    CANCELADO;

    public boolean podeTransicionarPara(StatusCombinado destino) {
        if (destino == null) return false;
        return switch (this) {
            case RASCUNHO -> destino == AGUARDANDO_CONFIRMACAO || destino == CANCELADO;
            case AGUARDANDO_CONFIRMACAO -> destino == ATIVO || destino == CANCELADO;
            case ATIVO -> destino == CONCLUIDO || destino == CANCELADO;
            case CONCLUIDO, CANCELADO -> false;
        };
    }

    public void validarTransicao(StatusCombinado destino) {
        if (!podeTransicionarPara(destino)) {
            throw new IllegalArgumentException("Transição de " + this + " para " + destino + " não permitida.");
        }
    }
}
