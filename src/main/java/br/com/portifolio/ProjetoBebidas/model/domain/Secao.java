package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;


import java.util.Set;

public class Secao {

    private Integer id;
    private Set<BebidaSecaoEntity> bebidaSessoes;

    public Secao(){}
    public Secao(Integer id) {
        this.id = id;
    }
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<BebidaSecaoEntity> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecaoEntity> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }

    @Override
    public String toString() {
        return "Secao{" +
                "id=" + id +
                ", bebidaSessoes=" + bebidaSessoes +
                '}';
    }
}
