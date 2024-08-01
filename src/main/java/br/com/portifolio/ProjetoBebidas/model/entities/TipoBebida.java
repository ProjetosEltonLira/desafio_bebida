package br.com.portifolio.ProjetoBebidas.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tipobebida")
public class TipoBebida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 60)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoBebida")
    private Set<Bebida> bebidas;

    public TipoBebida(){}
    public TipoBebida(int id){
        this.id = id;
    }
    public TipoBebida(int id, String descricao, Set<Bebida> bebidas) {
        this.id = id;
        this.descricao = descricao;
        this.bebidas = bebidas;
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
        return bebidas;
    }

    public void setBebidas(Set<Bebida> bebidas) {
        this.bebidas = bebidas;
    }
}
