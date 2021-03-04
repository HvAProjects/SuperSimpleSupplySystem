package nl.jed.supersimplesupplysystem.security.oauth2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OAuth2AuthenticationFailureHandlerTest {

    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandlerUnderTest;

    @BeforeEach
    void setUp() {
        oAuth2AuthenticationFailureHandlerUnderTest = new OAuth2AuthenticationFailureHandler();
        oAuth2AuthenticationFailureHandlerUnderTest.httpCookieOAuth2AuthorizationRequestRepository = mock(HttpCookieOAuth2AuthorizationRequestRepository.class);
    }

    @Test
    void testOnAuthenticationFailure() throws Exception {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
        final AuthenticationException exception = new BadCredentialsException("error");

        // Run the test
        oAuth2AuthenticationFailureHandlerUnderTest.onAuthenticationFailure(request, response, exception);

        // Verify the results
        verify(oAuth2AuthenticationFailureHandlerUnderTest.httpCookieOAuth2AuthorizationRequestRepository).removeAuthorizationRequestCookies(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}
