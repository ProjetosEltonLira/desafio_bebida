package br.com.portifolio.ProjetoBebidas.Model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipobebida")
public class TipoBebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "descricao")
    private String descricao;

    public TipoBebida() {}
    public TipoBebida(int id) {
        this.id = id;
    }
    public TipoBebida(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

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

    @Override
    public String toString() {
        return "TipoBebida{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
