package nl.jed.supersimplesupplysystem.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "properties")
public class CustomProperties {


    @Getter @Setter
    public String forgotMailUrl = null;
}