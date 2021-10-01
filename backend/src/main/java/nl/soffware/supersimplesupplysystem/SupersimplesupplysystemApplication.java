package nl.soffware.supersimplesupplysystem;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Generated
@SpringBootApplication(scanBasePackages = "nl.soffware.supersimplesupplysystem")
@EnableTransactionManagement
@EnableScheduling
public class SupersimplesupplysystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupersimplesupplysystemApplication.class, args);
	}
}

