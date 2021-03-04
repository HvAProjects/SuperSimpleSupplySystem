package nl.jed.supersimplesupplysystem.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OAuth2AuthenticationProcessingExceptionTest {

    private OAuth2AuthenticationProcessingException oAuth2AuthenticationProcessingException1;
    private OAuth2AuthenticationProcessingException oAuth2AuthenticationProcessingException2;
    private final Exception exception = new Exception("message");

    @BeforeEach
    void setUp() {
        oAuth2AuthenticationProcessingException1 = new OAuth2AuthenticationProcessingException("message", exception);
        oAuth2AuthenticationProcessingException2 = new OAuth2AuthenticationProcessingException("message");
    }

    @Test
    void testMessage() {
        assertEquals("message", oAuth2AuthenticationProcessingException1.getMessage());
        assertEquals("message", oAuth2AuthenticationProcessingException2.getMessage());
    }

    @Test
    void testException() {
        assertEquals(exception, oAuth2AuthenticationProcessingException1.getCause());
    }
}
