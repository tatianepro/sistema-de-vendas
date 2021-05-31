package com.github.tatianepro.rest.dto;

import javax.validation.constraints.NotEmpty;

public class CredenciaisDto {
    @NotEmpty(message = "{campo.login-usuario.obrigatorio}")
    private String login;
    @NotEmpty(message = "{campo.senha-usuario.obrigatorio}")
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
