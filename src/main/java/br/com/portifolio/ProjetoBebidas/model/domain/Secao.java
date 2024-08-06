package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;

import java.util.Set;

public class Secao {

    private Integer id;
    private Double capacidade;

    private Set<BebidaSecaoEntity> bebidaSessoes;

    public Secao(){}
    public Secao(Integer id, Double capacidade) {
        this.id = id;
        this.capacidade = capacidade;
        //this.bebidaSessoes = bebidaSessoes;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Set<BebidaSecaoEntity> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecaoEntity> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }

    public void validarDisponibilidadeArmazenamento(Double volumeAtual, Double qtdePedido) throws ExceptionError {
        Double capacidadeRestante = capacidade - volumeAtual ;
        if (capacidadeRestante - qtdePedido < 0.0) {
            throw new ExceptionError("A sessão " + id + " só pode receber até " + capacidadeRestante + " Litros");
            // 100 - 100 = 00>= 0, retorna true
        }
    }

    @Override
    public String toString() {
        return "Secao{" +
                "id=" + id +
                ", capacidade=" + capacidade +
                ", bebidaSessoes=" + bebidaSessoes +
                '}';
    }
}
