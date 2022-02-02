package com.twine.AuthService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.twine.AuthService"))
                .paths(PathSelectors.ant("/twine-auth/**"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Authentication API",
                "Authentication & Authorization API Swagger documentation",
                "1.0",
                "Terms of Service",
                new Contact("Flinstones", "https://github.com/The-Flinstones-TWINE", ""),
                "Apache License Version 2.0",
                "https://www.apache.org/license.html",
                Collections.emptyList()
        );
        return apiInfo;
    }
}
