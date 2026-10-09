package com.rafael.apiagendamento.handler;

import com.rafael.apiagendamento.dto.ErroCampo;
import com.rafael.apiagendamento.dto.ErroResposta;
import com.rafael.apiagendamento.exceptions.DadosNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList = e.getFieldErrors();
        List<ErroCampo> listErrs = fieldErrorList.stream().map(fi->new ErroCampo(fi.getField(),fi.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação",listErrs);
    }

    @ExceptionHandler(DadosNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handleDadosNaoEncontradoException(DadosNaoEncontradoException e){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(),e.getMessage(),List.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleIllegalArgumentException(IllegalArgumentException e){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), e.getMessage(),List.of());
    }

}
