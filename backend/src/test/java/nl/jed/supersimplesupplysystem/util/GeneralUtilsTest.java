package nl.jed.supersimplesupplysystem.util;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.dto.UserInfo;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralUtilsTest {

    @Test
    void testBuildSimpleGrantedAuthorities() {
        // Setup
        final String name = "name";
        final Set<Role> roles = Set.of(new Role(name));
        final List<SimpleGrantedAuthority> expectedResult = List.of(new SimpleGrantedAuthority(name));

        // Run the test
        final List<SimpleGrantedAuthority> result = GeneralUtils.buildSimpleGrantedAuthorities(roles);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @EnumSource(SocialProvider.class)
    void testToSocialProvider(SocialProvider provider) {
        assertEquals(provider, GeneralUtils.toSocialProvider(provider.getProviderType()));
    }
    @Test
    void testToSocialProviderNonExistent() {
        assertEquals(SocialProvider.LOCAL, GeneralUtils.toSocialProvider("THisdoesnotexist"));
    }

    @Test
    void testBuildUserInfo() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setProviderUserId("providerUserId");
        user.setEmail("email");
        user.setEnabled(false);
        user.setPasswordExpired(false);
        user.setExpired(false);
        user.setDisplayName("displayName");
        user.setCreatedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setModifiedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setPassword("password");
        user.setRoles(Set.of(new Role("role")));
        final LocalUser localUser = new LocalUser("userID", "password", false, false, false, false, user.getRoles(), user);
        final UserInfo expectedResult = new UserInfo(user.getId().toString(), "displayName", "email", List.of("role"));

        // Run the test
        final UserInfo result = GeneralUtils.buildUserInfo(localUser);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
