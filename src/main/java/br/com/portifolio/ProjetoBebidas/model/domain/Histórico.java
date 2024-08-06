

package br.com.portifolio.ProjetoBebidas.model.domain;

import java.time.Instant;


public class Histórico {

    private long secaoId;
    private long bebidaId;
    private double quantidade;
    private String tipoPedido;
    private String solicitante;
    private Instant momento ;

    public Histórico(long secaoId, long bebidaId, double quantidade, String tipoPedido, String solicitante) {
        this.secaoId = secaoId;
        this.bebidaId = bebidaId;
        this.quantidade = quantidade;
        this.tipoPedido = tipoPedido;
        this.solicitante = solicitante;
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
        return "Histórico{" +
                "secaoId=" + secaoId +
                ", bebidaId=" + bebidaId +
                ", quantidade=" + quantidade +
                ", tipoPedido='" + tipoPedido + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", momento=" + momento +
                '}';
    }
}
