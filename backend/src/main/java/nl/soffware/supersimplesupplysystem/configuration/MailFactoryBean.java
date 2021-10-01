package nl.soffware.supersimplesupplysystem.configuration;

import nl.soffware.supersimplesupplysystem.util.MailFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailFactoryBean {
    @Bean
    public MailFactory getMailFactory() {
        return new MailFactory();
    }
}
