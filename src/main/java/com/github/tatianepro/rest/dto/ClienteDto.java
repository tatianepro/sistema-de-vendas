package com.github.tatianepro.rest.dto;

import com.github.tatianepro.domain.entity.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

public class ClienteDto {
    @NotEmpty(message = "Campo nome do cliente é obrigatório.")
    private String nome;
    @NotEmpty(message = "Campo CPF do cliente é obrigatório.")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Cliente toCliente() {
        return new Cliente(this.nome, this.cpf);
    }
}
