package com.sanvalero.practicadosspringmayo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Configuration
public class FarmConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Farm")
                        .description("FARM API REST")
                        .contact(new Contact()
                                .name("Pedro Oros")
                                .email("shadycreek@hotmail.com")
                                .url("https://github.com/PideroAntonio80/FarmAPI.git"))
                        .version("1.0"));
    }
}
