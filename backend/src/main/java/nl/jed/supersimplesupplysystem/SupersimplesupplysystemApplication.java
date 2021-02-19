package nl.jed.supersimplesupplysystem;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = "nl.jed")
@EnableJpaRepositories
@EnableTransactionManagement
public class SupersimplesupplysystemApplication extends SpringBootServletInitializer {

	// https://www.javachinna.com/spring-boot-angular-10-user-registration-oauth2-social-login-part-2/
	public static void main(String[] args) {
		SpringApplication.run(SupersimplesupplysystemApplication.class, args);
	}
}

