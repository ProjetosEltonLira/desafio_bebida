package br.com.portifolio.ProjetoBebidas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SecaoBebida implements Serializable {

    @Column(name = "secao_id")
    Long secaoId;

    @Column(name = "bebida_id")
    Long bebidaId;

    public SecaoBebida(){}
    public SecaoBebida(Long secaoId, Long bebidaId) {
        this.secaoId = secaoId;
        this.bebidaId = bebidaId;
    }

    public Long getSecaoId() {
        return secaoId;
    }

    public void setSecaoId(Long secaoId) {
        this.secaoId = secaoId;
    }

    public Long getBebidaId() {
        return bebidaId;
    }

    public void setBebidaId(Long bebidaId) {
        this.bebidaId = bebidaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecaoBebida that)) return false;
        return secaoId.equals(that.secaoId) && bebidaId.equals(that.bebidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secaoId, bebidaId);
    }
}
