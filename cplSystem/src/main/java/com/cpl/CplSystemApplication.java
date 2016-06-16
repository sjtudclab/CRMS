package com.cpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class CplSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CplSystemApplication.class, args);
    }
}