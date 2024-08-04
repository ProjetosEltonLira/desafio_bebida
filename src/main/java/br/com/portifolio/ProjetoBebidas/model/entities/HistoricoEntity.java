
package br.com.portifolio.ProjetoBebidas.model.entities;
import jakarta.persistence.*;
import java.time.Instant;


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
    private Instant momento ;

    public HistoricoEntity(long secaoId, long bebidaId, double quantidade, String tipoPedido, String solicitante) {
        this.secaoId = secaoId;
        this.bebidaId = bebidaId;
        this.quantidade = quantidade;
        this.tipoPedido = tipoPedido;
        this.solicitante = solicitante;
        this.momento = Instant.now();
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

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "id=" + id +
                ", secaoId=" + secaoId +
                ", bebidaId=" + bebidaId +
                ", quantidade=" + quantidade +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", momento=" + momento +
                '}';
    }
}
