package br.com.portifolio.ProjetoBebidas.service.exceptions;

public class DataBaseException extends RuntimeException{

    public DataBaseException(String mensagem){
        super(mensagem);
    }
}
