package br.com.portifolio.ProjetoBebidas.model.domain;

import java.util.Set;

public class TipoBebida {

    private int id;
    private String descricao;
    private Set<Bebida> bebida;

    public TipoBebida(){}
    public TipoBebida(int id){
        this.id = id;
    }
    public TipoBebida(int id, String descricao, Set<Bebida> bebida) {
        this.id = id;
        this.descricao = descricao;
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
    public Set<Bebida> getBebidas() {
        return bebida;
    }
    public void setBebidas(Set<Bebida> bebida) {
        this.bebida = bebida;
    }
}
