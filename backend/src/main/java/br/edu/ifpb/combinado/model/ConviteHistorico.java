package br.edu.ifpb.combinado.model;

import br.edu.ifpb.combinado.model.enums.AcaoConvite;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "convite_historico", uniqueConstraints = @UniqueConstraint(columnNames = {"convite_id", "acao"}))
public class ConviteHistorico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "convite_id", nullable = false, updatable = false)
    private Convite convite;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false, updatable = false)
    private Usuario autor;
    @Column(nullable = false, updatable = false)
    private String autorNome;
    @Enumerated(EnumType.STRING) @Column(nullable = false, updatable = false)
    private AcaoConvite acao;
    @Column(nullable = false, updatable = false)
    private Instant ocorridoEm;

    protected ConviteHistorico() {}
    public ConviteHistorico(Convite convite, Usuario autor, AcaoConvite acao, Instant ocorridoEm) {
        this.convite = convite;
        this.autor = autor;
        this.autorNome = autor.getNome();
        this.acao = acao;
        this.ocorridoEm = ocorridoEm;
    }
    public Long getId() { return id; }
    public Usuario getAutor() { return autor; }
    public String getAutorNome() { return autorNome; }
    public AcaoConvite getAcao() { return acao; }
    public Instant getOcorridoEm() { return ocorridoEm; }
}
