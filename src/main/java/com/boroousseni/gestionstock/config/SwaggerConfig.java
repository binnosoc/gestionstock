package com.boroousseni.gestionstock.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-config.json")
                .addResourceLocations("classpath:/static/swagger-config.json");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestion de Stock")
                .version("v0")
                .description("Cette API permet de gérer les stocks d'une entreprise. Elle offre des fonctionnalités pour enregistrer et modifier des ventes, des utilisateurs, des articles, des fournisseurs, des entreprises, et des commandes fournisseurs. Elle permet également de gérer les mouvements de stock tels que les entrées, les sorties, et les corrections. L'API est conçue pour être utilisée par des applications qui nécessitent une gestion de stock robuste et flexible. <br> A titre d'information l'API n'est testable qu'en local pour des raisons personnelles. Pour visualiser le resultat du test, consulter la video <a href=\"./portfolio-demo.php\" data-gallery=\"portfolioDetailsGallery\" data-glightbox=\"type: external\" class=\"portfolio-details-lightbox\"> demo </a>")
                .contact(new Contact()
                    .name("OUSSENI BORO")
                    .url("https://binnosoc.com/")
                    .email("ousseni.boro@binnosoc.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Code source accessible sur GitHub")
                .url("https://github.com/binnosoc/gestionstock/tree/master"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/**")
            .build();
    }
}


