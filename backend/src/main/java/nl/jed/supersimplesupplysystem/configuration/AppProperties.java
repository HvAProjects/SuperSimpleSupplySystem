package nl.jed.supersimplesupplysystem.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
@EnableConfigurationProperties(AppProperties.class)
public class AppProperties {
    @Getter
    private final Auth auth = new Auth();
    @Getter
    private final OAuth2 oauth2 = new OAuth2();

    public static class Auth {
        @Getter @Setter
        private String tokenSecret;
        @Getter @Setter
        private long tokenExpirationMsec;
    }

    public static final class OAuth2 {
        @Getter
        private final List<String> authorizedRedirectUris = new ArrayList<>();
    }
}
