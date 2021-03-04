package nl.jed.supersimplesupplysystem.testconfigurations;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.helpers.InMemoryUserDetailsManager;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@TestConfiguration
public class TestUserConfiguration {

    @Autowired
    private PasswordEncoder encoder;

    private final Random R = new Random();

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(Arrays.asList(
                createUser("user", Role.ROLE_USER),createUser("admin", Role.ROLE_ADMIN),createUser("mod", Role.ROLE_MODERATOR)
        ));
    }

    public LocalUser createUser(String username, String ...roles){
        User user = new User();
        user.setId(R.nextLong());
        user.setDisplayName(username);
        user.setEmail(username+"@company.com");
        user.setPassword(encoder.encode("Password"));
        user.setEnabled(true);
        user.setRoles(getRoles(roles));
        user.setProvider(SocialProvider.LOCAL.getProviderType());
        return createLocalUser(user);
    }

    private LocalUser createLocalUser(User user) {
        return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
    }

    private Set<Role> getRoles(String ...names){
        return Arrays.stream(names)
                .map(Role::new)
                .collect(Collectors.toSet());
    }
}
