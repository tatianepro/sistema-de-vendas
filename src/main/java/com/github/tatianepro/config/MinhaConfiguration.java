package com.github.tatianepro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

    @Bean   // annotation that scans the object to be used in the application context
    public String applicationName() {
        return "Sistema de Vendas";
    }

}
