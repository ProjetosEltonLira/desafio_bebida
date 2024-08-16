package br.com.portifolio.ProjetoBebidas.model.dto;

import java.io.Serializable;

public class ResponseObjectDTO {

    private String campo;

    private String mensagem;

    private Object parametro;

    public ResponseObjectDTO(){}
    public ResponseObjectDTO(String campo, String mensagem, Object parametro) {
        this.campo = campo;
        this.mensagem = mensagem;
        this.parametro = parametro;
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

    public Object getParametro() {
        return parametro;
    }

    public void setParametro(Object parametro) {
        this.parametro = parametro;
    }
}
