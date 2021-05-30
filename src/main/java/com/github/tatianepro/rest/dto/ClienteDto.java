package com.github.tatianepro.rest.dto;

import com.github.tatianepro.domain.entity.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

public class ClienteDto {
    @NotEmpty(message = "{campo.nome-cliente.obrigatorio}")
    private String nome;
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
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
