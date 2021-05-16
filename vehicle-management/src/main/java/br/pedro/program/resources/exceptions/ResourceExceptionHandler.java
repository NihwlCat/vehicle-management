package br.pedro.program.resources.exceptions;

import br.pedro.program.services.exceptions.DataErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler (DataErrorException.class)
    public ResponseEntity<ErrorMessage> dataException (DataErrorException e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage("ERRO 404 - Falha ao buscar dados");
        errorMessage.setErrors(errors);
        errorMessage.setHttpStatus(HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validException (MethodArgumentNotValidException e){
        List<String> errors = new ArrayList<>();
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            errors.add(f.getField() + ": -> " + f.getDefaultMessage());
        }

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage("ERRO 400 - Erro de validação de dados");
        errorMessage.setErrors(errors);
        errorMessage.setHttpStatus(HttpStatus.valueOf(400).toString());
        return ResponseEntity.status(HttpStatus.valueOf(400)).body(errorMessage);
    }
}
