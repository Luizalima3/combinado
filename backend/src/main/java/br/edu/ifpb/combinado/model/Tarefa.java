package br.edu.ifpb.combinado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "combinado_id", nullable = false)
    private Combinado combinado;
    @ManyToMany
    @JoinTable(name = "tarefa_responsaveis", joinColumns = @JoinColumn(name = "tarefa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis = new HashSet<>();

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Combinado getCombinado() { return combinado; }
    public void setCombinado(Combinado combinado) { this.combinado = combinado; }
    public Set<Usuario> getResponsaveis() { return responsaveis; }
}
