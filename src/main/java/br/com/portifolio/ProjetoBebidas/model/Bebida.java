package br.com.portifolio.ProjetoBebidas.model;

import br.com.portifolio.ProjetoBebidas.model.entities.SecaoBebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import jakarta.persistence.*;

import java.util.Set;

public class Bebida {

    private Long id;
    private String nome;
    private TipoBebidaEntity tipoBebidaEntity;
    private Set<SecaoEntity> secoes;
    Set<SecaoBebidaEntity> secaoBebidas;

    public Bebida(){}
    public Bebida(Long id, String nome, TipoBebidaEntity tipoBebidaEntity, Set<SecaoEntity> secoes) {
        this.id = id;
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
