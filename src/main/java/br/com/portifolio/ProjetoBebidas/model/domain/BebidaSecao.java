package br.com.portifolio.ProjetoBebidas.model.domain;



import java.io.Serializable;
import java.util.Objects;

public class BebidaSecao  {

    private BebidaSecaoKey id = new BebidaSecaoKey();
    private Double quantidade;

    public BebidaSecao(){}
    public BebidaSecao(Bebida bebida, Secao secao, Double quantidade) {
        this.id.setBebida(bebida);
        this.id.setSecaoId(secao);
        this.quantidade = quantidade;
    }



    public BebidaSecaoKey getId() {
        return id;
    }
    public void setId(BebidaSecaoKey id) {
        this.id = id;
    }
    public Bebida getBebida() {
        return id.getBebida();
    }
    public void setBebida(Bebida bebida) {
        this.id.setBebida(bebida);
    }
    public Secao getSecao() {
        return id.getSecao();
    }
    public void setSecao(Secao secao) {
        this.id.setSecaoId(secao);
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BebidaSecao that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}