package nl.jed.supersimplesupplysystem.security.oauth2;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.PASSWORD;
import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;

@ExtendWith(MockitoExtension.class)
class CustomOidcUserServiceTest {

    @Mock
    private UserService mockUserService;

    @InjectMocks
    private CustomOidcUserService customOidcUserServiceUnderTest;

    private final ClientRegistration registration = ClientRegistration.withRegistrationId("123")
            .authorizationGrantType(PASSWORD)
            .clientId("clientid")
            .tokenUri("http://bla.com")
            .userInfoUri("http://bla.com")
            .userNameAttributeName("name")
            .build();

    private final OAuth2AccessToken token = new OAuth2AccessToken(BEARER, "tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
    private final OidcIdToken idToken =  new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
    private  final OidcUserRequest userRequest = new OidcUserRequest(registration, token, idToken, null);

    @Test
    void testLoadUser() {
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
        when(mockUserService.processUserRegistration(userRequest.getClientRegistration().getRegistrationId(), localUser.getAttributes(), localUser.getIdToken(), localUser.getUserInfo() )).thenReturn(localUser);
        customOidcUserServiceUnderTest = Mockito.spy(customOidcUserServiceUnderTest);
        Mockito.doReturn(localUser).when(customOidcUserServiceUnderTest).fetchUserFromSuper(userRequest);

        // Run the test
        final OidcUser result = customOidcUserServiceUnderTest.loadUser(userRequest);

        // Verify the results
    }

    @Test
    void testLoadUser_ThrowsOAuth2AuthenticationException() {
        customOidcUserServiceUnderTest = Mockito.spy(customOidcUserServiceUnderTest);
        Mockito.doThrow(new OAuth2AuthenticationException(new OAuth2Error("error"))).when(customOidcUserServiceUnderTest).fetchUserFromSuper(userRequest);

        // Run the test
        assertThrows(OAuth2AuthenticationException.class, () -> customOidcUserServiceUnderTest.loadUser(userRequest));
    }
}
