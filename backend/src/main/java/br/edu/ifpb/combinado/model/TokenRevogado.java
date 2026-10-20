package br.edu.ifpb.combinado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tokens_revogados")
public class TokenRevogado {
    @Id
    @Column(name = "jti", length = 36, nullable = false)
    private String jti;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "encerrado_em", nullable = false)
    private Instant encerradoEm;

    @Column(name = "expira_em", nullable = false)
    private Instant expiraEm;

    protected TokenRevogado() {
    }

    public TokenRevogado(String jti, Long usuarioId, Instant encerradoEm, Instant expiraEm) {
        this.jti = jti;
        this.usuarioId = usuarioId;
        this.encerradoEm = encerradoEm;
        this.expiraEm = expiraEm;
    }

    public String getJti() { return jti; }
    public Long getUsuarioId() { return usuarioId; }
    public Instant getEncerradoEm() { return encerradoEm; }
    public Instant getExpiraEm() { return expiraEm; }
}
