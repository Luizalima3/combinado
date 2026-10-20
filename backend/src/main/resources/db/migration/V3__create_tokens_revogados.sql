CREATE TABLE tokens_revogados (
    jti VARCHAR(36) PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    encerrado_em TIMESTAMP WITH TIME ZONE NOT NULL,
    expira_em TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_tokens_revogados_usuario ON tokens_revogados (usuario_id, encerrado_em);
