-- US69 / Task #31696 — Persistir motivo (nullable) junto ao evento de cancelamento.
--
-- CA #31823: o texto do campo de motivo deve ser persistido na coluna
-- `motivo` da tabela `combinado_historico` (nulo quando não informado).
-- A coluna é nullable — nulo ≠ string vazia, e o normalizador do
-- CancelamentoDTO.trim() já garante: sem motivo → nulo, com motivo →
-- texto (limitado a 500 caracteres pela validação do DTO).
ALTER TABLE combinado_historico
    ADD COLUMN motivo VARCHAR(500) NULL;
