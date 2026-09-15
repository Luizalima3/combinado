package br.edu.ifpb.combinado.model;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "convites")
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "combinado_id")
    private Combinado combinado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConvite status = StatusConvite.PENDENTE;

    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    public Convite() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Combinado getCombinado() { return combinado; }
    public void setCombinado(Combinado combinado) { this.combinado = combinado; }

    public Usuario getRemetente() { return remetente; }
    public void setRemetente(Usuario remetente) { this.remetente = remetente; }

    public Usuario getDestinatario() { return destinatario; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }

    public StatusConvite getStatus() { return status; }
    public void setStatus(StatusConvite status) { this.status = status; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
}
