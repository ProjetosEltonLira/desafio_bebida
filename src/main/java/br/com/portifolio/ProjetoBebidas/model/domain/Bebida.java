package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.Enum.TipoBebidaEnum;

public class Bebida {

    private Integer id;
    private String nome;
    private TipoBebidaEnum tipoBebidaEnum;

    public Bebida(){}
    public Bebida(Integer id, String nome, TipoBebidaEnum tipoBebidaEnum) {
        this.id = id;
        this.nome = nome;
        this.tipoBebidaEnum = tipoBebidaEnum;
        //this.bebidaSessoes = bebidaSessoes;
    }
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBebidaEnum getTipoBebida() {
        return tipoBebidaEnum;
    }

    public void setTipoBebida(TipoBebidaEnum tipoBebidaEnum) {
        this.tipoBebidaEnum = tipoBebidaEnum;
    }



    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoBebidaEnum=" + tipoBebidaEnum +
                '}';
    }


}
