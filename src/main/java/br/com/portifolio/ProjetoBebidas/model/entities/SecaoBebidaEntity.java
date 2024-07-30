package br.com.portifolio.ProjetoBebidas.model.entities;

import br.com.portifolio.ProjetoBebidas.model.SecaoBebida;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class SecaoBebidaEntity implements Serializable {

    @EmbeddedId
    SecaoBebida id;

    @ManyToOne
    @MapsId("secaoId")
    @JoinColumn(name = "secao_id")
    SecaoEntity secao;

    @ManyToOne
    @MapsId("bebidaId")
    @JoinColumn(name = "bebida_id")
    BebidaEntity bebida;

    double quantidadeBebida;

    public SecaoBebidaEntity(){}

    public SecaoBebidaEntity(SecaoEntity secao, BebidaEntity bebida, double quantidadeBebida) {
        this.secao = secao;
        this.bebida = bebida;
        this.quantidadeBebida = quantidadeBebida;
    }

    public SecaoBebidaEntity(SecaoBebida id, SecaoEntity secao, BebidaEntity bebida, double quantidadeBebida) {
        this.id = id;
        this.secao = secao;
        this.bebida = bebida;
        this.quantidadeBebida = quantidadeBebida;
    }


    public SecaoBebida getId() {
        return id;
    }

    public void setId(SecaoBebida id) {
        this.id = id;
    }

    public SecaoEntity getSecao() {
        return secao;
    }

    public void setSecao(SecaoEntity secao) {
        this.secao = secao;
    }

    public BebidaEntity getBebida() {
        return bebida;
    }

    public void setBebida(BebidaEntity bebida) {
        this.bebida = bebida;
    }

    public double getQuantidadeBebida() {
        return quantidadeBebida;
    }

    public void setQuantidadeBebida(double quantidadeBebida) {
        this.quantidadeBebida = quantidadeBebida;
    }
}
