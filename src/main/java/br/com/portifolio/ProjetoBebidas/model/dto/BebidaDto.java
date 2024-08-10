package br.com.portifolio.ProjetoBebidas.model.dto;



public class BebidaDto {

    private long id;
    private String nome;
    private long tipoBebidaId;

    public BebidaDto() {}
    public BebidaDto(long id, String nome, long tipoBebidaId) {
        this.id = id;
        this.nome = nome;
        this.tipoBebidaId = tipoBebidaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
