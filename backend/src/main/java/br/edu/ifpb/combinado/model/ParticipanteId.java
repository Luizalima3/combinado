package br.edu.ifpb.combinado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipanteId implements Serializable {
    @Column(name = "combinado_id")
    private Long combinadoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    protected ParticipanteId() {}

    public ParticipanteId(Long combinadoId, Long usuarioId) {
        this.combinadoId = combinadoId;
        this.usuarioId = usuarioId;
    }

    public Long getCombinadoId() { return combinadoId; }
    public Long getUsuarioId() { return usuarioId; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ParticipanteId that)) return false;
        return Objects.equals(combinadoId, that.combinadoId)
                && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combinadoId, usuarioId);
    }
}