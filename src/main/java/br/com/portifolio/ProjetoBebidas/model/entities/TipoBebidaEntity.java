package br.com.portifolio.ProjetoBebidas.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tipobebida")
public class TipoBebidaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 60)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoBebidaEntity")
    private Set<BebidaEntity> bebidaEntities;

    public TipoBebidaEntity(){}
    public TipoBebidaEntity(int id){
        this.id = id;
    }
    public TipoBebidaEntity(int id, String descricao, Set<BebidaEntity> bebidaEntities) {
        this.id = id;
        this.descricao = descricao;
        this.bebidaEntities = bebidaEntities;
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

    public Set<BebidaEntity> getBebidas() {
        return bebidaEntities;
    }

    public void setBebidas(Set<BebidaEntity> bebidaEntities) {
        this.bebidaEntities = bebidaEntities;
    }
}