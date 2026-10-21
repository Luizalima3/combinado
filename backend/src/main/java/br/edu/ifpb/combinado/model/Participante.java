package br.edu.ifpb.combinado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "combinado_participantes")
public class Participante {
    @EmbeddedId
    private ParticipanteId id = new ParticipanteId(null, null);

    @MapsId("combinadoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "combinado_id", nullable = false, updatable = false)
    private Combinado combinado;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, updatable = false)
    private Usuario usuario;

    protected Participante() {}

    public Participante(Combinado combinado, Usuario usuario) {
        this.combinado = combinado;
        this.usuario = usuario;
    }

    public ParticipanteId getId() { return id; }

    @JsonIgnore
    public Combinado getCombinado() { return combinado; }

    @JsonIgnore
    public Usuario getUsuario() { return usuario; }
}