package com.alura.aluraAPI.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenAPIConfig {
    @Bean
    public OpenAPI aluraApi() {
        return new OpenAPI()
                .info(new Info().title("Alura API")
                        .description("Online Study Platform REST API")
                        .version("v1.0.0")
                        .license(new License().name("Guilherme DEV")
                                .identifier("https://github.com/guilhermevini2013/Alura-BackEnd-Clone")));
    }
}
