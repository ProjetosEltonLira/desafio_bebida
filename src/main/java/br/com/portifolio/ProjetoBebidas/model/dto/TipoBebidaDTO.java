package br.com.portifolio.ProjetoBebidas.model.dto;

public class TipoBebidaDTO {

    private int id;
    private String descricao;
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
