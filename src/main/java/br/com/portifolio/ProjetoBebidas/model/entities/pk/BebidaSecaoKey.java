package br.com.portifolio.ProjetoBebidas.model.entities.pk;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BebidaSecaoKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bebida_id")
    private BebidaEntity bebidaEntity;

    @ManyToOne
    @JoinColumn(name = "secao_id")
    private SecaoEntity secaoEntity;

    // Getters, Setters, hashCode, and equals methods
    public BebidaEntity getBebida() {
        return bebidaEntity;
    }
    public void setBebida(BebidaEntity bebidaEntity) {
        this.bebidaEntity = bebidaEntity;
    }

    public SecaoEntity getSecao() {
        return secaoEntity;
    }
    public void setSecaoId(SecaoEntity secaoEntity) {
        this.secaoEntity = secaoEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BebidaSecaoKey that = (BebidaSecaoKey) o;
        return Objects.equals(bebidaEntity, that.bebidaEntity) && Objects.equals(secaoEntity, that.secaoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bebidaEntity, secaoEntity);
    }
}
