package com.github.tatianepro.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty(message = "{campo.login-usuario.obrigatorio}")
    private String login;
    @Column(nullable = false)
    @NotEmpty(message = "{campo.senha-usuario.obrigatorio}")
    private String senha;
    @Column
    private boolean admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
