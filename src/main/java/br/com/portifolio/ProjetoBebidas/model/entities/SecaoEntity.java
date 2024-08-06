package br.com.portifolio.ProjetoBebidas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "secao")
public class SecaoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "capacidade")
    private Double capacidade;

    @JsonIgnore
    @OneToMany(mappedBy = "id.secaoEntity")
    private Set<BebidaSecaoEntity> bebidaSessoes;

    public SecaoEntity(){}
    public SecaoEntity(Integer id, Double capacidade) {
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
}
