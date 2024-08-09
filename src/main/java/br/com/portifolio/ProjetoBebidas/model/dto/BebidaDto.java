package br.com.portifolio.ProjetoBebidas.model.dto;



public class BebidaDto {

    private long Id;
    private String nome;
    private long tipoBebidaId;

    public BebidaDto() {}
    public BebidaDto(long id, String nome, long tipoBebidaId) {
        Id = id;
        nome = nome;
        tipoBebidaId = tipoBebidaId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTipoBebidaId() {
        return tipoBebidaId;
    }

    public void setTipoBebidaId(long tipoBebidaId) {
        tipoBebidaId = tipoBebidaId;
    }


}
