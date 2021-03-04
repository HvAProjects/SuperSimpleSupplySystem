package nl.jed.supersimplesupplysystem.configuration;

import lombok.extern.slf4j.Slf4j;
import nl.jed.BuildConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class VersionLogger {
    @PostConstruct
    public void init() {
        log.info("==================================================================================");
        log.info("Version hash: {}", BuildConfig.GitHash);
        log.info("==================================================================================");
    }
}
