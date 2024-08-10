package br.com.portifolio.ProjetoBebidas.model.domain;

public class Bebida {

    private Long id;
    private String nome;
    private TipoBebida tipoBebida;

    public Bebida(){}
    public Bebida(Long id, String nome, TipoBebida tipoBebida) {
        this.id = id;
        this.nome = nome;
        this.tipoBebida = tipoBebida;
        //this.bebidaSessoes = bebidaSessoes;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoBebidam=" + tipoBebida +
                '}';
    }


}
