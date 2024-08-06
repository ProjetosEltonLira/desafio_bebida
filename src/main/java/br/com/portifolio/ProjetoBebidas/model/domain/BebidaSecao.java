package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;

import java.util.Objects;

public class BebidaSecao {

    private BebidaSecaoKey id = new BebidaSecaoKey();
    private Double quantidade;

    public BebidaSecao() {
    }

    public BebidaSecao(Bebida bebida, Secao secao, Double quantidade) {
        this.id.setBebida(bebida);
        this.id.setSecao(secao);
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
        this.id.setSecao(secao);
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public void validarTipoBebida(TipoBebidaEnum tipoBebidaSecao) throws ExceptionError {
        TipoBebidaEnum tipoBebidaPedido = TipoBebidaEnum.getCodigo(id.getBebida().getTipoBebida().getCodigo());

        if (tipoBebidaSecao != tipoBebidaPedido) {
            throw new ExceptionError("A sessão " + tipoBebidaSecao + " só pode receber bebidas do mesmo tipo");
        }
    }
    public void validarQtdeBebidaSolicitada() throws ExceptionError {
        if (quantidade < 0D) {
            throw new ExceptionError("Não é possível inserir um valor igual ou inferior a 0.0 litros de bebida na sessao");
        }
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

    @Override
    public String toString() {
        return "BebidaSecao{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                '}';
    }
}
