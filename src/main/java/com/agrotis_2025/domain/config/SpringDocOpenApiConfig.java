package com.agrotis_2025.domain.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI challengeUser() {
        return new OpenAPI()
                .info(new Info()
                        .title("Agrotis")
                        .description("Microsserviços responsável pelo CRUD de Cliente.")
                        .version("v0.0.2")
                        .termsOfService("http://agrotis.com/terms-of-service") // URL fictícia
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Junior Martins")
                                .email("teste@email.com")) // Email fictício
                );
    }
}

