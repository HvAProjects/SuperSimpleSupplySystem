package nl.jed.supersimplesupplysystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "classpath:secrets/secrets.properties", ignoreResourceNotFound = false)
public class Secrets {
}
