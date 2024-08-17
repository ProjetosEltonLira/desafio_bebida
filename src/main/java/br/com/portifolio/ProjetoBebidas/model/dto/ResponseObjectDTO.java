package br.com.portifolio.ProjetoBebidas.model.dto;

public class ResponseObjectDTO {

    private String campo;

    private String mensagem;

    private Object valor;

    public ResponseObjectDTO(){}
    public ResponseObjectDTO(String campo, String mensagem, Object valor) {
        this.campo = campo;
        this.mensagem = mensagem;
        this.valor = valor;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
