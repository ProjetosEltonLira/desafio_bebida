package br.com.portifolio.ProjetoBebidas.model.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "bebida")
public class BebidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipobebida_id", nullable = false)
    private TipoBebidaEntity tipoBebidaEntity;

    @ManyToMany (mappedBy = "bebidas")
    private Set<SecaoEntity> secoes;

    @OneToMany(mappedBy = "bebida")
    Set<SecaoBebidaEntity> secaoBebidas;

    public BebidaEntity(){}

    public BebidaEntity(String nome, TipoBebidaEntity tipoBebidaEntity) {
        this.nome = nome;
        this.tipoBebidaEntity = tipoBebidaEntity;
    }
    public BebidaEntity(String nome, TipoBebidaEntity tipoBebidaEntity, Set<SecaoEntity> secoes) {
        this.nome = nome;
        this.tipoBebidaEntity = tipoBebidaEntity;
        this.secoes = secoes;
    }

    // Getters e Setters
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

    public TipoBebidaEntity getTipoBebida() {
        return tipoBebidaEntity;
    }

    public TipoBebidaEntity getTipoBebidaEntity() {
        return tipoBebidaEntity;
    }

    public void setTipoBebidaEntity(TipoBebidaEntity tipoBebidaEntity) {
        this.tipoBebidaEntity = tipoBebidaEntity;
    }

    public Set<SecaoEntity> getSecoes() {
        return secoes;
    }

    public void setSecoes(Set<SecaoEntity> secoes) {
        this.secoes = secoes;
    }

    public Set<SecaoBebidaEntity> getSecaoBebidas() {
        return secaoBebidas;
    }

    public void setSecaoBebidas(Set<SecaoBebidaEntity> secaoBebidas) {
        this.secaoBebidas = secaoBebidas;
    }
}
