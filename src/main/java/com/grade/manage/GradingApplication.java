package com.grade.manage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Studios TKOH!
 */
@OpenAPIDefinition(info = @Info(
        title = "Grading API",
        version = "v1",
        description = "Safe API to grade people you got under your command."))
@SpringBootApplication
public class GradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradingApplication.class, args);
    }
}
