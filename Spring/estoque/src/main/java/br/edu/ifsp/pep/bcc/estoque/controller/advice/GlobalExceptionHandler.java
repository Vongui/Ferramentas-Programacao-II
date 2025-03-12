package br.edu.ifsp.pep.bcc.estoque.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return "Campo: " + ex.getBindingResult().getFieldError().getField() +
                " - Descrição erro: " + ex.getBindingResult().getFieldError().getDefaultMessage();
    }
}
