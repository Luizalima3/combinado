package br.edu.ifpb.combinado.model;

import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "combinado_historico", indexes = {
        @Index(name = "idx_combinado_historico_timeline", columnList = "combinado_id, ocorrido_em, id")
})
public class CombinadoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "combinado_id", nullable = false, updatable = false)
    private Combinado combinado;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false, updatable = false)
    private Usuario autor;

    @Column(nullable = false, updatable = false)
    private String autorNome;

    @Column(nullable = false, updatable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AcaoCombinadoHistorico acao;

    @Column(nullable = false, updatable = false)
    private Instant ocorridoEm;

    protected CombinadoHistorico() {}

    public CombinadoHistorico(
            Combinado combinado,
            Usuario autor,
            String descricao,
            AcaoCombinadoHistorico acao,
            Instant ocorridoEm
    ) {
        this.combinado = combinado;
        this.autor = autor;
        this.autorNome = autor.getNome();
        this.descricao = descricao;
        this.acao = acao;
        this.ocorridoEm = ocorridoEm;
    }

    public Long getId() { return id; }
    public Combinado getCombinado() { return combinado; }
    public Usuario getAutor() { return autor; }
    public String getAutorNome() { return autorNome; }
    public String getDescricao() { return descricao; }
    public AcaoCombinadoHistorico getAcao() { return acao; }
    public Instant getOcorridoEm() { return ocorridoEm; }
}