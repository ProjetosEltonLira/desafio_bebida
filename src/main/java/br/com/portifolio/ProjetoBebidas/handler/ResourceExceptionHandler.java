package br.com.portifolio.ProjetoBebidas.handler;

import br.com.portifolio.ProjetoBebidas.model.dto.ResponseObjectDTO;
import br.com.portifolio.ProjetoBebidas.service.exceptions.DataBaseException;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ExceptionError;
import br.com.portifolio.ProjetoBebidas.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice //isso que vai interceptar as exceções que acontecerem
public class ResourceExceptionHandler {

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

    //Intercepta qualquer exceção do tipo DataBaseException
    @ExceptionHandler(ExceptionError.class)
    public ResponseEntity<StandardError> exceptionError(ExceptionError exception, HttpServletRequest request){
        String msgErro = "Erro";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError bodyErro = new StandardError(Instant.now(),httpStatus.value(),msgErro,exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(bodyErro);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        String msgErro = "Campo(s) com preenchimento inválido";
        //Percorre a lista de erros que foi recebida
        List<ResponseObjectDTO> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ResponseObjectDTO(error.getField().toUpperCase(), error.getDefaultMessage(), error.getRejectedValue())).toList();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError bodyErro = new StandardError(Instant.now(),httpStatus.value(),msgErro, null, request.getRequestURI(),errors);
        return ResponseEntity.status(httpStatus).body(bodyErro);
    }


}
