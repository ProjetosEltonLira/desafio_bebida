package br.com.portifolio.ProjetoBebidas.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tipobebida")
public class TipoBebidaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String descricao;

    public TipoBebidaEntity(){}
    public TipoBebidaEntity(Long id) {
        this.id = id;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
