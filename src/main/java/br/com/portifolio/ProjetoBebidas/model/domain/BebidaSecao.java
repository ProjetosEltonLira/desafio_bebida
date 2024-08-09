package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.model.dto.PedidoDto;
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

    public void validarTipoBebida(TipoBebida tipoBebidaExistenteNaSecao) throws ExceptionError {

        if (id.getBebida().getTipoBebida().getId() != tipoBebidaExistenteNaSecao.getId()) {
            throw new ExceptionError("A sessão " + tipoBebidaExistenteNaSecao.getDescricao() + " só pode receber bebidas do mesmo tipo");
        }
    }
    public void calcularQuantidadeBebida(PedidoDto pedido, Double qtdeBebidaExisteNaSecao){
        if (pedido.getQuantidade() < 0D) {
            throw new ExceptionError("Não é possível inserir um valor igual ou inferior a 0.0 litros de bebida na sessao");
        }

        if (pedido.getTipoPedido().equals("ENTRADA")){
            if (qtdeBebidaExisteNaSecao != 0) {
                setQuantidade(pedido.getQuantidade() + qtdeBebidaExisteNaSecao);
            }
        }
        else if (pedido.getTipoPedido().equals("SAIDA")){
            if (qtdeBebidaExisteNaSecao >= pedido.getQuantidade()) {
                setQuantidade(qtdeBebidaExisteNaSecao - pedido.getQuantidade());
            }
            else {
                throw new ExceptionError("Não é possível retirar mais bebidas do que existe na secao, consulte a quantidade de bebida disponível nessa secao.");
            }
        }
        else {
            throw new ExceptionError("Tipo de pedido inválido, informar: 'ENTRADA' ou 'SAIDA'");
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
