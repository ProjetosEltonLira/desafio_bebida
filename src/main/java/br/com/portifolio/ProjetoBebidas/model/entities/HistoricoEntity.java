
package br.com.portifolio.ProjetoBebidas.model.entities;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "historico")
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "secao_id")
    private long secaoId;

    @Column(name = "bebida_id")
    private long bebidaId;

    @Column(name = "quantidade")
    private double quantidade;

    @Column(name = "tiposolicitacao")
    private String tipoPedido;

    @Column(name = "nomesolicitante")
    private String solicitante;

    @Column(name = "datasolicitacao")
    private LocalDate dataSolicitacao;

    public HistoricoEntity(long secaoId, long bebidaId, double quantidade, String tipoPedido, String solicitante) {
        this.secaoId = secaoId;
        this.bebidaId = bebidaId;
        this.quantidade = quantidade;
        this.tipoPedido = tipoPedido;
        this.solicitante = solicitante;
        this.dataSolicitacao = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSecaoId() {
        return secaoId;
    }

    public void setSecaoId(long secaoId) {
        this.secaoId = secaoId;
    }

    public long getBebidaId() {
        return bebidaId;
    }

    public void setBebidaId(long bebidaId) {
        this.bebidaId = bebidaId;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }


}
