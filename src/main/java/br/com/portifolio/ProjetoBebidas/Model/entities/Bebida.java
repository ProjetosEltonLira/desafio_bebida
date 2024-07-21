package br.com.portifolio.ProjetoBebidas.Model.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebida;

public class Bebida {

    //private int id;
    private TipoBebida tipo;
    private String nome;
    private double quantidade;

    public Bebida() {}
    public Bebida(TipoBebida tipo, String nome, double quantidade) {
        //this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public TipoBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoBebida tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return  "Nome:" + nome + " Tipo:" + tipo + " Qtde:" + quantidade;
    }
}
