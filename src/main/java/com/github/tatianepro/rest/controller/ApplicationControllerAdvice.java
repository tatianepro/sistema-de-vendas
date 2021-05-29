package com.github.tatianepro.rest.controller;

import com.github.tatianepro.exception.PedidoNaoEncontradoException;
import com.github.tatianepro.exception.RegraDeNegocioException;
import com.github.tatianepro.rest.dto.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors regraDeNegocioExceptionHandler(RegraDeNegocioException ex) {
        String menssagemDeErro = ex.getMessage();
        return new ApiErrors(menssagemDeErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors pedidoNaoEncontradoExceptionHandler(PedidoNaoEncontradoException ex) {
        return new ApiErrors(ex.getMessage());
    }

}
