package selenium;

import nl.jed.supersimplesupplysystem.SupersimplesupplysystemApplication;
import nl.jed.supersimplesupplysystem.configuration.*;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.repository.LocationRepository;
import nl.jed.supersimplesupplysystem.repository.RoleRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.servlet.OAuth2LoginDsl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import selenium.util.WebDriverUtils;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {SupersimplesupplysystemApplication.class,
        AppProperties.class,
        CustomProperties.class,
        MailConfiguration.class,
        MailFactoryBean.class,
        PasswordEncoderBean.class,
        WebSecurityConfig.class})
@ExtendWith(SpringExtension.class)
@ComponentScan(value = "nl.jed.supersimplesupplysystem")
public abstract class BaseSeleniumTests {

    protected WebDriver driver;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.driver = WebDriverUtils.createWebDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected String getBaseUrl() {
        return env.getProperty("baseUrls.frontend");
    }

    protected User loginUser() {
        String email = "testuser@gmail.com";
        String password = "password";
        String userName = "TestUser";
        User user = createUserIfNotFound(email, Set.of(createRoleIfNotFound("TEST")), userName, password);
        driver.get(getBaseUrl());
        driver.manage().window().setSize(new Dimension(1223, 824));
        {
            WebElement element = driver.findElement(By.cssSelector(".mat-menu-trigger"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".mat-menu-trigger")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".cdk-focused")).click();
        driver.findElement(By.id("mat-input-0")).click();
        driver.findElement(By.id("mat-input-0")).sendKeys(email);
        driver.findElement(By.id("mat-input-1")).sendKeys(password);
        driver.findElement(By.id("mat-input-1")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".content > .ng-star-inserted")).click();
        return user;
    }

    private User createUserIfNotFound(final String email, Set<Role> roles, String name, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setDisplayName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            Date now = Calendar.getInstance().getTime();
            user.setCreatedDate(now);
            user.setModifiedDate(now);
            user = userRepository.save(user);
        }
        return user;
    }

    public Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }

    public Household createHousehold(String name, User user) {
        Household household = new Household();
        household.setName(name);
        household.setAddress("TestAddress");
        household.setPostalCode("3535CZ");
        HashSet<User> users = new HashSet<>();
        users.add(user);
        household.setUsers(users);
        householdRepository.save(household);
        return household;
    }

    public Location createLocation(String name, Household household) {
        Location location = new Location();
        location.setName(name);
        location.setHousehold(household);
        locationRepository.save(location);
        return location;
    }
}