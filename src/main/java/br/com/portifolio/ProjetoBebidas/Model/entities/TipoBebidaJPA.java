package br.com.portifolio.ProjetoBebidas.Model.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipobebida")
public class TipoBebidaJPA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private short id;

    @Column (name = "descricao")
    private String descricao;

    public TipoBebidaJPA() {}

    public TipoBebidaJPA(short id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
