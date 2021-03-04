package nl.jed.supersimplesupplysystem.security.oauth2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class HttpCookieOAuth2AuthorizationRequestRepositoryTest {

    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        httpCookieOAuth2AuthorizationRequestRepositoryUnderTest = new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Test
    void testLoadAuthorizationRequest() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();

        // Run the test
        final OAuth2AuthorizationRequest result = httpCookieOAuth2AuthorizationRequestRepositoryUnderTest.loadAuthorizationRequest(request);

        // Verify the results
    }

    @Test
    void testSaveAuthorizationRequest() {
        // Setup
        final OAuth2AuthorizationRequest authorizationRequest = null;
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        httpCookieOAuth2AuthorizationRequestRepositoryUnderTest.saveAuthorizationRequest(authorizationRequest, request, response);

        // Verify the results
    }

    @Test
    void testRemoveAuthorizationRequest() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();

        // Run the test
        final OAuth2AuthorizationRequest result = httpCookieOAuth2AuthorizationRequestRepositoryUnderTest.removeAuthorizationRequest(request);

        // Verify the results
    }

    @Test
    void testRemoveAuthorizationRequestCookies() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        httpCookieOAuth2AuthorizationRequestRepositoryUnderTest.removeAuthorizationRequestCookies(request, response);

        // Verify the results
    }
}
