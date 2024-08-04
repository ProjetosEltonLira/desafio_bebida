package br.com.portifolio.ProjetoBebidas.model.domain;

import java.util.Objects;

public class BebidaSecaoKey {

    private Bebida bebida;
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
