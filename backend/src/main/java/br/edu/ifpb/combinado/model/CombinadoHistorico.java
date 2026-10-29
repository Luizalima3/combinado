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

    /**
     * US69 / Task #31696 — motivo (nullable) do evento de cancelamento,
     * persistido na coluna `motivo` (VARCHAR(500), nulo quando não informado).
     */
    @Column(nullable = true, updatable = false, length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AcaoCombinadoHistorico acao;

    @Column(nullable = false, updatable = false)
    private Instant ocorridoEm;

    protected CombinadoHistorico() {}

    /**
     * US72 / Task #31706 — todo evento de histórico deve gravar obrigatoriamente
     * o usuário (autor_id) e o timestamp (ocorrido_em / criado_em).
     *
     * BDD:
     * DADO que qualquer ação de mudança de status ocorre (concluir, cancelar)
     * QUANDO a ação for executada
     * ENTÃO o sistema deve registrar o usuário responsável e o timestamp da ação
     *
     * Validação fail-fast: se autor ou timestamp vierem nulos, o evento não é
     * criado — garante a regra na origem, antes mesmo da checagem do banco
     * (NOT NULL em autor_id/ocorrido_em nas migrations V8/V9).
     */
    private CombinadoHistorico(
            Combinado combinado,
            Usuario autor,
            String descricao,
            String motivo,
            AcaoCombinadoHistorico acao,
            Instant ocorridoEm
    ) {
        if (combinado == null) throw new IllegalArgumentException("Evento de histórico exige combinado.");
        if (autor == null) throw new IllegalArgumentException("Evento de histórico exige autor (usuário_id).");
        if (ocorridoEm == null) throw new IllegalArgumentException("Evento de histórico exige timestamp (criado_em).");
        if (acao == null) throw new IllegalArgumentException("Evento de histórico exige ação.");
        this.combinado = combinado;
        this.autor = autor;
        this.autorNome = autor.getNome();
        this.descricao = descricao;
        this.motivo = motivo;
        this.acao = acao;
        this.ocorridoEm = ocorridoEm;
    }

    public CombinadoHistorico(
            Combinado combinado,
            Usuario autor,
            String descricao,
            AcaoCombinadoHistorico acao,
            Instant ocorridoEm
    ) {
        this(combinado, autor, descricao, null, acao, ocorridoEm);
    }

    /**
     * US69 / Task #31696 — construtor com motivo (nullable) junto ao evento.
     */
    public CombinadoHistorico(
            Combinado combinado,
            Usuario autor,
            String descricao,
            String motivo,
            AcaoCombinadoHistorico acao,
            Instant ocorridoEm
    ) {
        this(combinado, autor, descricao, motivo, acao, ocorridoEm);
    }

    /**
     * US72 / Task #31706 — factory que padroniza o timestamp: sempre
     * Instant.now() no momento da criação do evento, sem passagem manual.
     */
    public static CombinadoHistorico de(
            Combinado combinado,
            Usuario autor,
            String descricao,
            AcaoCombinadoHistorico acao
    ) {
        return new CombinadoHistorico(combinado, autor, descricao, null, acao, Instant.now());
    }

    /**
     * US72 / Task #31706 — variante com motivo (nullable) para eventos de cancelamento.
     */
    public static CombinadoHistorico de(
            Combinado combinado,
            Usuario autor,
            String descricao,
            String motivo,
            AcaoCombinadoHistorico acao
    ) {
        return new CombinadoHistorico(combinado, autor, descricao, motivo, acao, Instant.now());
    }

    public Long getId() { return id; }
    public Combinado getCombinado() { return combinado; }
    public Usuario getAutor() { return autor; }
    public String getAutorNome() { return autorNome; }
    public String getDescricao() { return descricao; }
    public String getMotivo() { return motivo; }
    public AcaoCombinadoHistorico getAcao() { return acao; }
    public Instant getOcorridoEm() { return ocorridoEm; }
}