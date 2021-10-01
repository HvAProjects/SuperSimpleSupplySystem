package nl.soffware.supersimplesupplysystem.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.OPENIDCONNECT;

@Configuration
public class DocumentationConfig {
    @Bean
    public OpenAPI openAPI() {
        var oidc = new SecurityScheme()
                .type(OPENIDCONNECT)
                .openIdConnectUrl("https://supersimplesupplysystem.eu.auth0.com/.well-known/openid-configuration");
        return new OpenAPI()
                .info(new Info().title("SuperSimpleSupplySystem API")
                        .description("SuperSimpleSupplySystem")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(
                        new Components().addSecuritySchemes("auth0", oidc)
                );
    }
}
