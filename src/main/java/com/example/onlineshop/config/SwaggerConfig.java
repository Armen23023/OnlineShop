package com.example.onlineshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.onlineshop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer ", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer ", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "APP REST API",
                "App management rest API",
                "0.0.1", "",
                new Contact("Company", "https://www.company.am/", "info@company.am"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

//    private ApiInfo apiDetails() {
//        return new ApiInfo(
//                "Online shop API",
//                "Sample API for Online shop tutorial",
//                "1.0",
//                "Free to use",
//                new springfox.documentation.service.Contact("OnlieShop", "http://inlineShop.io", "a@b.com"),
//                "Api License",
//                "http://javabrains.io",
//                Collections.emptyList()
//        );
//    }
}
