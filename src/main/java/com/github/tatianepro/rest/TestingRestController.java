package com.github.tatianepro.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingRestController {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @GetMapping("/test")
    public String getApplicationName() {
        return applicationName;
    }

}
