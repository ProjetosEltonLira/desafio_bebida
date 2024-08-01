package br.com.portifolio.ProjetoBebidas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "secao")
public class Secao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "capacidade")
    private Double capacidade;

    @Column(name = "volumeatual")
    private Double volumeAtual;

    @JsonIgnore
    @OneToMany(mappedBy = "id.secao")
    private Set<BebidaSecao> bebidaSessoes;

    public Secao(){}
    public Secao(Integer id, Double capacidade, Double volumeAtual) {
        this.id = id;
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
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

    public Double getVolumeAtual() {
        return volumeAtual;
    }

    public void setVolumeAtual(Double volumeAtual) {
        this.volumeAtual = volumeAtual;
    }

    public Set<BebidaSecao> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecao> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }
}
