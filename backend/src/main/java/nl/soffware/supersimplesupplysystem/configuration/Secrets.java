package nl.soffware.supersimplesupplysystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "classpath:secrets/secrets.yaml", ignoreResourceNotFound = false)
public class Secrets {
}
