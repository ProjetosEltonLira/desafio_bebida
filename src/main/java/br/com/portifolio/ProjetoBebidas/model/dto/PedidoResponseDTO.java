package br.com.portifolio.ProjetoBebidas.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoResponseDTO implements Serializable {

    private long secaoId;
    private long bebidaId;
    private double quantidade;
    private String tipoPedido;
    private String solicitante;

    private LocalDateTime dataSolicitacao;

    public PedidoResponseDTO(long secaoId, long bebidaId, double quantidade, String tipoPedido, String solicitante) {
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

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
