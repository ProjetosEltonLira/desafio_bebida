package br.com.portifolio.ProjetoBebidas.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Registro não encontrado. Id:" + id);
    }
}
