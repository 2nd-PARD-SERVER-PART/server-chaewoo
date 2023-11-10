package club.pard.server.assignment04.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@OpenAPIDefinition(info = @Info(title = "Tasks - Operation API Docs", description = "API Docs about tasks and operations", version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean public OpenAPI openAPI()
    {
        return new OpenAPI().components(new Components());
    }
}
