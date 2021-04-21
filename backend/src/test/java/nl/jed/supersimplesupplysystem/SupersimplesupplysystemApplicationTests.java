package nl.jed.supersimplesupplysystem;

import nl.jed.supersimplesupplysystem.configuration.AppProperties;
import nl.jed.supersimplesupplysystem.dto.SignUpRequest;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.validator.PasswordMatchesValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AppProperties.class, loader = AnnotationConfigContextLoader.class)
@AutoConfigureMockMvc
class SupersimplesupplysystemApplicationTests {

	@Mock
	private UserRepository mockedUserRepository;

	@Test
	void TestValidPassword() {
		SignUpRequest signUpRequest = SignUpRequest.builder()
				.displayName("test")
				.email("test@email.com")
				.password("test")
				.matchingPassword("test")
				.build();
		boolean isValid = new PasswordMatchesValidator().isValid(signUpRequest, null);
		Assertions.assertTrue(isValid);
	}
	@Test
	void TestInvalidPassword() {
		SignUpRequest signUpRequest = SignUpRequest.builder()
				.displayName("test")
				.email("test@email.com")
				.password("test")
				.matchingPassword("test1")
				.build();
		boolean isValid = new PasswordMatchesValidator().isValid(signUpRequest, null);
		Assertions.assertFalse(isValid);
	}
}
