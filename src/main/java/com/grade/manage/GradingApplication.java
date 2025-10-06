package com.grade.manage;

import com.grade.manage.util.security.JwtProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 * @author Studios TKOH!
 */
@OpenAPIDefinition(info = @Info(
        title = "Grading API",
        version = "v1",
        description = "Safe API to grade people you got under your command."))
@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradingApplication.class, args);
    }
}
