package br.com.portifolio.ProjetoBebidas.model.dto.request;

import java.time.Instant;

public class PedidoDTO {

    private long secaoId;
    private long bebidaId;
    private double quantidade;
    private String tipoPedido;
    private String solicitante;

    private Instant DataSolicitacao;

    public PedidoDTO(long secaoId, long bebidaId, double quantidade, String tipoPedido, String solicitante) {
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

    public Instant getDataSolicitacao() {
        return DataSolicitacao;
    }

    public void setDataSolicitacao(Instant dataSolicitacao) {
        this.DataSolicitacao = dataSolicitacao;
    }
}
