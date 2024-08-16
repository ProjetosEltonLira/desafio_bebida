package br.com.portifolio.ProjetoBebidas.model.dto;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class TipoBebidaDTO  {

    private int id;

    @NotBlank(message = "Nome da bebida não pode ser vazio ou preenchido com espaços")
    private String descricao;

    @NotNull(message = "Valor da capacidade não pode ser nulo e deve ser superior a zero.")
    @Positive(message = "Valor da capacidade não pode ser zero ou negativo.")
    private Double capacidade;

    public TipoBebidaDTO(){};
    public TipoBebidaDTO(int id, String descricao, Double capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }
}
