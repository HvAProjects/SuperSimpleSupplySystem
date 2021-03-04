package nl.jed.supersimplesupplysystem.security.oauth2;

import nl.jed.supersimplesupplysystem.configuration.AppProperties;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.security.jwt.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {

    @Mock
    private TokenProvider mockTokenProvider;
    @Mock
    private AppProperties mockAppProperties;
    @Mock
    private HttpCookieOAuth2AuthorizationRequestRepository mockHttpCookieOAuth2AuthorizationRequestRepository;

    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandlerUnderTest;

    @BeforeEach
    void setUp() {
        oAuth2AuthenticationSuccessHandlerUnderTest = new OAuth2AuthenticationSuccessHandler(mockTokenProvider, mockAppProperties, mockHttpCookieOAuth2AuthorizationRequestRepository);
    }

    @Test
    void testOnAuthenticationSuccess() throws Exception {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
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

        final Authentication authentication = new UsernamePasswordAuthenticationToken(localUser, null);

        when(mockTokenProvider.createToken(localUser)).thenReturn("result");

        // Run the test
        oAuth2AuthenticationSuccessHandlerUnderTest.onAuthenticationSuccess(request, response, authentication);

        // Verify the results
        verify(mockHttpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }

    @Test
    void testDetermineTargetUrl() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
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

        final Authentication authentication = new UsernamePasswordAuthenticationToken(localUser, null);

        when(mockTokenProvider.createToken(localUser)).thenReturn("result");

        // Run the test
        final String result = oAuth2AuthenticationSuccessHandlerUnderTest.determineTargetUrl(request, response, authentication);

        // Verify the results
        assertEquals("/?token=result", result);
    }

    @Test
    void testClearAuthenticationAttributes() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        oAuth2AuthenticationSuccessHandlerUnderTest.clearAuthenticationAttributes(request, response);

        // Verify the results
        verify(mockHttpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }

    @Test
    void testDetermineTargetUrlWithIncorrectRedirect() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
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

        final Authentication authentication = new UsernamePasswordAuthenticationToken(localUser, null);

        when(mockTokenProvider.createToken(localUser)).thenReturn("result");

        // Run the test
        final String result = oAuth2AuthenticationSuccessHandlerUnderTest.determineTargetUrl(request, response, authentication);

        // Verify the results
        assertEquals("/?token=result", result);
    }
}
