package br.com.portifolio.ProjetoBebidas.model.dto.request;

import jakarta.validation.constraints.*;

public class TipoBebidaDTO  {

    private int id;

    @NotBlank(message = "Nome da bebida não pode ser vazio ou preenchido com espaços")
    private String descricao;

    @Min(value = 1, message = "Valor da capacidade não pode ser zero ou negativo.")
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
