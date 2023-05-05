package com.nhs.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee Skill Management APP"
        , version = "0.0.1", description = "API to Manage skill for the resources"))
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
