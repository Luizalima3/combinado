package br.edu.ifpb.combinado.model;

import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "combinados")
public class Combinado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCombinado categoria;

    private LocalDate dataPrazo;

    @Column(nullable = false)
    private String criadorNome;

    @Column(nullable = false)
    private String participanteNome;

    @Column(columnDefinition = "TEXT")
    private String responsabilidades;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCombinado status;

    private LocalDateTime aceitoEm;

    private LocalDateTime recusadoEm;

    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusCombinado.AGUARDANDO_CONFIRMACAO;
        }
    }

    public Combinado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public CategoriaCombinado getCategoria() { return categoria; }
    public void setCategoria(CategoriaCombinado categoria) { this.categoria = categoria; }

    public LocalDate getDataPrazo() { return dataPrazo; }
    public void setDataPrazo(LocalDate dataPrazo) { this.dataPrazo = dataPrazo; }

    public String getCriadorNome() { return criadorNome; }
    public void setCriadorNome(String criadorNome) { this.criadorNome = criadorNome; }

    public String getParticipanteNome() { return participanteNome; }
    public void setParticipanteNome(String participanteNome) { this.participanteNome = participanteNome; }

    public String getResponsabilidades() { return responsabilidades; }
    public void setResponsabilidades(String responsabilidades) { this.responsabilidades = responsabilidades; }

    public StatusCombinado getStatus() { return status; }
    public void setStatus(StatusCombinado status) { this.status = status; }

    public LocalDateTime getAceitoEm() { return aceitoEm; }
    public void setAceitoEm(LocalDateTime aceitoEm) { this.aceitoEm = aceitoEm; }

    public LocalDateTime getRecusadoEm() { return recusadoEm; }
    public void setRecusadoEm(LocalDateTime recusadoEm) { this.recusadoEm = recusadoEm; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}