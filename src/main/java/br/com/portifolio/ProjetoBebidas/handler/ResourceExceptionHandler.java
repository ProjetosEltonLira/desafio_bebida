package br.com.portifolio.ProjetoBebidas.handler;

import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //isso que vai interceptar as exceções que acontecerem
public class ResourceExceptionHandler  {

    //Intercepta qualquer exceção do tipo ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        String msgErro = "Recurso não encontrado";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError bodyErro = new StandardError(Instant.now(),httpStatus.value(),msgErro,exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(bodyErro);
    }

    //Intercepta qualquer exceção do tipo DataBaseException
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException(DataBaseException exception, HttpServletRequest request){
        String msgErro = "Data base error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError bodyErro = new StandardError(Instant.now(),httpStatus.value(),msgErro,exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(bodyErro);
    }
}
