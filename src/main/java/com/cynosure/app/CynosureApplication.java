package com.cynosure.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cynosure.*")
public class CynosureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CynosureApplication.class, args);
    }
}
