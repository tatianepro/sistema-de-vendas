package com.github.tatianepro.config;

import com.github.tatianepro.annotation.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Development
public class MinhaConfiguration {

    @Bean   // annotation that scans the object to be used in the application context
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÃO DE AMBIENTE DE DESENVOLVIMENTO");
        };
    }

}
