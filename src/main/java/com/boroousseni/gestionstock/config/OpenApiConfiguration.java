package com.boroousseni.gestionstock.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("REST API V1").pathsToMatch("/**").build();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("REST API Gestion de Stock")
						.description("Documentation de l'application Gestion de Stock créée via Spring boot")
						.version("v0.0.1")
						.license(new License().name("Ousseni BORO").url("http://springdoc.org")))
						.externalDocs(new ExternalDocumentation().description("Code source sur GitHub")
						.url("https://github.com/binnosoc/gestionstock"));
	}
}
