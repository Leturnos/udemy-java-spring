package com.udemy.spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean // Diz ao Spring COMO criar (instanciar e configurar) um objeto
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
                        .version("v1")
                        .description("Spring Boot is amazing")
                        .termsOfService("https://github.com/Leturnos/udemy-java-spring")
                        .license(new License().name("MIT License").url("https://github.com/Leturnos/udemy-java-spring/blob/main/LICENSE"))
                );
    }
    // http://localhost:8080/v3/api-docs
    // http://localhost:8080/swagger-ui/index.html
}
