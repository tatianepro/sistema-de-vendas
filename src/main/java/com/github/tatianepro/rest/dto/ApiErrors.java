package com.github.tatianepro.rest.dto;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String mensagemDeErro) {
        this.errors = Arrays.asList(mensagemDeErro);
    }

    public List<String> getErrors() {
        return errors;
    }
}
