package nl.jed.supersimplesupplysystem.security.oauth2;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.PASSWORD;
import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;

@ExtendWith(MockitoExtension.class)
class CustomOAuth2UserServiceTest {

    @Mock
    private UserService mockUserService;

    @InjectMocks
    private CustomOAuth2UserService customOAuth2UserServiceUnderTest;

    private final ClientRegistration registration = ClientRegistration.withRegistrationId("123")
            .authorizationGrantType(PASSWORD)
            .clientId("clientid")
            .tokenUri("http://bla.com")
            .userInfoUri("http://bla.com")
            .userNameAttributeName("name")
            .build();

    @Test
    void testLoadUser() {
        // Setup
        final OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(registration, new OAuth2AccessToken(BEARER, "tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC)));

        // Configure UserService.processUserRegistration(...).
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
        final LocalUser localUser = new LocalUser("userID", "password", false, false, false, false, List.of(), user);
        localUser.setAttributes(new HashMap<>());

        when(mockUserService.processUserRegistration(registration.getRegistrationId(), registration.getProviderDetails().getConfigurationMetadata(), null, null)).thenReturn(localUser);
        customOAuth2UserServiceUnderTest = spy(customOAuth2UserServiceUnderTest);
        Mockito.doReturn(localUser).when(customOAuth2UserServiceUnderTest).fetchUserFromSuper(oAuth2UserRequest);

        // Run the test
        final OAuth2User result = customOAuth2UserServiceUnderTest.loadUser(oAuth2UserRequest);

        // Verify the results
    }

    @Test
    void testLoadUser_ThrowsOAuth2AuthenticationException() {
        // Setup
        final OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(registration, new OAuth2AccessToken(BEARER, "tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC)));
        customOAuth2UserServiceUnderTest = spy(customOAuth2UserServiceUnderTest);
        Mockito.doThrow(new OAuth2AuthenticationException(new OAuth2Error("123"))).when(customOAuth2UserServiceUnderTest).fetchUserFromSuper(oAuth2UserRequest);
        // Run the test
        assertThrows(OAuth2AuthenticationException.class, () -> customOAuth2UserServiceUnderTest.loadUser(oAuth2UserRequest));
    }

    @Test
    void testLoadUser_AuthExecption() {
        // Setup
        final OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(registration, new OAuth2AccessToken(BEARER, "tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC)));
        // Run the test
        assertThrows(AuthenticationException.class, () -> customOAuth2UserServiceUnderTest.loadUser(oAuth2UserRequest));
    }
}
