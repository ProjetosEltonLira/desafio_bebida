package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;

import java.util.Set;

public class TipoBebida {

    private int id;
    private String descricao;
    private Double capacidade;
    private Set<Bebida> bebida;

    public TipoBebida(){}
    public TipoBebida(int id){
        this.id = id;
    }

    public TipoBebida(int id, String descricao, Double capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }
    public TipoBebida(int id, String descricao, Double capacidade, Set<Bebida> bebida) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.bebida = bebida;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Set<Bebida> getBebidas() {
        return bebida;
    }
    public void setBebidas(Set<Bebida> bebida) {
        this.bebida = bebida;
    }
    public void validarDisponibilidadeArmazenamento(Double volumeAtual, Double qtdePedido) throws ExceptionError {
        Double capacidadeRestante = capacidade - volumeAtual ;
        if (capacidadeRestante - qtdePedido < 0.0) {
            throw new ExceptionError("A sessão " + id + " só pode receber até " + capacidadeRestante + " Litros");
        }
    }

    @Override
    public String toString() {
        return "TipoBebida{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", capacidade=" + capacidade +
                ", bebida=" + bebida +
                '}';
    }
}
