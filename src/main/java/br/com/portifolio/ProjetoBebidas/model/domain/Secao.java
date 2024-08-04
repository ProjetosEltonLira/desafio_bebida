package br.com.portifolio.ProjetoBebidas.model.domain;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Secao implements Serializable {

    private Integer id;
    private Double capacidade;
    private Double volumeAtual;
    private Set<BebidaSecaoEntity> bebidaSessoes;

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

    public Set<BebidaSecaoEntity> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecaoEntity> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }

    /*public void adicionarBebida(Bebida bebida) throws ExceptionError {
        if(!Objects.equals(bebida.getTipoBebida(), this.tipo)){
            throw new ExceptionError("A sessão " + tipo + " só pode receber bebidas do mesmo tipo");
        }
        if(consultarVolumeDisponivel() == 0){
            throw new ExceptionError("A sessao" + id + " atingiu o limite de bebidas, não é possível adicionar mais bebidas a essa sessão");

        }if(bebida.getQuantidade() <= 0){
            throw new ExceptionError("Não é possível inserir um valor igual ou inferior a 0 litros de bebida na sessao");
        }
        if(bebida.getQuantidade() > consultarVolumeDisponivel()){
            throw new ExceptionError("A sessao possui " + consultarVolumeDisponivel() + " litros disponíveis, não é possível inserir " + bebida.getQuantidade() + " litros da bebida");
        }
        volumeAtual += bebida.getQuantidade();
        this.bebida.add(bebida);
    }*/

}
