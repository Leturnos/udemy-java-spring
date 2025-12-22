package com.udemy.spring_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
        // with EXTENSION. http://localhost:8080/api/person/v1/2.xml or http://localhost:8080/api/person/v1/2.JSON Deprecated on Spring Boot 2.6

        // with QUERY PARAM http://localhost:8080/api/person/v1/2?mediaType=xml
//        configurer.favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true) // não sofre interferência de headers automáticos (Accept)
//                .useRegisteredExtensionsOnly(false) //permite formatos personalizados
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML);

        // with HEADER PARAM http://localhost:8080/api/person/v1/2 and Accept=application/xml
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MediaType.APPLICATION_YAML);
    }
}
