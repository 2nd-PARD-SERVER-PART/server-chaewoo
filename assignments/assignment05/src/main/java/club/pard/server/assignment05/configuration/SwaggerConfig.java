package club.pard.server.assignment05.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Tasks - Operation API Docs", description = "API Docs about tasks and operations", version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean public OpenAPI openAPI()
    {
        return new OpenAPI().addSecurityItem(securityRequirement()).components(components());
    }

    public Components components(){
        return new Components()
                .addSecuritySchemes("jwtAuth", new SecurityScheme()
                        .name("jwtAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }

    public SecurityRequirement securityRequirement(){
        return new SecurityRequirement().addList("jwtAuth");
    }
}
