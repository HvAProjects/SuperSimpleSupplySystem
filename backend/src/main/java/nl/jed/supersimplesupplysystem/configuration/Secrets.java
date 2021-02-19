package nl.jed.supersimplesupplysystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "classpath:secrets.properties", ignoreResourceNotFound = true)
public class Secrets {
}
