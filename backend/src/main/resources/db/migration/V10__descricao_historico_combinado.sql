ALTER TABLE combinado_historico
    ADD COLUMN descricao VARCHAR(500) NOT NULL DEFAULT 'Evento registrado';

ALTER TABLE combinado_historico
    ALTER COLUMN descricao DROP DEFAULT;