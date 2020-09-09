package com.kira.employeespringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title="Employee API",version = "1.0",description = "Employee Microservice"))
public class EmployeeSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSpringbootApplication.class, args);
    }

}
