package br.edu.ifpb.combinado.dto;

import jakarta.validation.constraints.Size;

/**
 * US69 / Task #31696 — Persistir motivo (nullable) junto ao evento de
 * cancelamento.
 *
 * BDD:
 * DADO que o usuário está cancelando um combinado
 * QUANDO ele preencher (ou não) o campo de motivo
 * ENTÃO o sistema deve aceitar o cancelamento com ou sem motivo informado
 * DADO que o motivo informado ultrapassa o limite de 500 caracteres
 * QUANDO o sistema persistir o evento de cancelamento
 * ENTÃO deve rejeitar a requisição com erro de validação
 *
 * CA #31823: o texto digitado no campo de motivo deve ser enviado junto
 * com a requisição de cancelamento e persistido na coluna `motivo` da
 * tabela `combinado_historico` (nullable — nulo quando não informado).
 */
public record CancelamentoDTO(
        @Size(max = 500, message = "O motivo não pode ultrapassar 500 caracteres") String motivo) {
    /**
     * Normaliza o motivo: nulo quando ausente, vazio ou apenas espaços —
     * a coluna `motivo` é nullable e nulo ≠ string vazia no banco.
     */
    public String motivoNormalizado() {
        if (motivo == null)
            return null;
        String trimmed = motivo.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
