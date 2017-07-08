package com.cynosure.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({"com.cynosure.*"})
@EntityScan(basePackages = "com.cynosure.pojo")
public class CynosureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CynosureApplication.class, args);
    }
}
