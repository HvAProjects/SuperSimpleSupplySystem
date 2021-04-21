package nl.jed.supersimplesupplysystem;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Generated
@SpringBootApplication(scanBasePackages = "nl.jed")
@EnableJpaRepositories
@EnableTransactionManagement
@EnableScheduling
public class SupersimplesupplysystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SupersimplesupplysystemApplication.class, args);
	}
}

