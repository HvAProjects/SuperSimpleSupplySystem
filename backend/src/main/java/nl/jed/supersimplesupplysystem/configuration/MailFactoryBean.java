package nl.jed.supersimplesupplysystem.configuration;

import nl.jed.supersimplesupplysystem.util.MailFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailFactoryBean {
    @Bean
    public MailFactory getMailFactory() {
        return new MailFactory();
    }
}
