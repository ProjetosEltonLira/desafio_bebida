package br.com.portifolio.ProjetoBebidas.model.entities;

import br.com.portifolio.ProjetoBebidas.model.entities.pk.BebidaSecaoKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bebida_secao")
public class BebidaSecaoEntity implements Serializable {

    @EmbeddedId
    private BebidaSecaoKey id = new BebidaSecaoKey();

    private Double quantidade;

    public BebidaSecaoEntity(){}
    public BebidaSecaoEntity(BebidaEntity bebidaEntity, SecaoEntity secaoEntity, Double quantidade) {
        this.id.setBebida(bebidaEntity);
        this.id.setSecaoId(secaoEntity);
        this.quantidade = quantidade;
    }

    // Getters and Setters
    public BebidaSecaoKey getId() {
        return id;
    }

    public void setId(BebidaSecaoKey id) {
        this.id = id;
    }

    @JsonIgnore
    public BebidaEntity getBebida() {
        return id.getBebida();
    }

    public void setBebida(BebidaEntity bebidaEntity) {
        this.id.setBebida(bebidaEntity);
    }

    public SecaoEntity getSecao() {
        return id.getSecao();
    }

    public void setSecao(SecaoEntity secaoEntity) {
        this.id.setSecaoId(secaoEntity);
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BebidaSecaoEntity that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
