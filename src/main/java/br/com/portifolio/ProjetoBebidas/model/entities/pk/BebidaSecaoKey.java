package br.com.portifolio.ProjetoBebidas.model.entities.pk;

import br.com.portifolio.ProjetoBebidas.model.entities.Bebida;
import br.com.portifolio.ProjetoBebidas.model.entities.Secao;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BebidaSecaoKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bebida_id")
    private Bebida bebida;


    @ManyToOne
    @JoinColumn(name = "secao_id")
    private Secao secao;

    // Getters, Setters, hashCode, and equals methods
    public Bebida getBebida() {
        return bebida;
    }
    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Secao getSecao() {
        return secao;
    }
    public void setSecaoId(Secao secao) {
        this.secao = secao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BebidaSecaoKey that = (BebidaSecaoKey) o;
        return Objects.equals(bebida, that.bebida) && Objects.equals(secao, that.secao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bebida, secao);
    }
}
