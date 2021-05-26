package com.github.tatianepro.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingRestController {

//    @Autowired
//    @Qualifier("applicationName")
    @Value("${application.name}")   // annotation that injects a property value into the property/attribute
    private String applicationName;

    @GetMapping("/test")
    public String getApplicationName() {
        return applicationName;
    }

}
