package nl.jed.supersimplesupplysystem.security.oauth2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleOAuth2UserInfoTest {

    private GoogleOAuth2UserInfo googleOAuth2UserInfoUnderTest;

    @BeforeEach
    void setUp() {
        googleOAuth2UserInfoUnderTest = new GoogleOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("sub", "result"),
                        Map.entry("name", "result"),
                        Map.entry("email", "result"),
                        Map.entry("picture","result")
                )
        );
    }

    @Test
    void testGetId() {
        // Setup

        // Run the test
        final String result = googleOAuth2UserInfoUnderTest.getId();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetName() {
        // Setup

        // Run the test
        final String result = googleOAuth2UserInfoUnderTest.getName();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetEmail() {
        // Setup

        // Run the test
        final String result = googleOAuth2UserInfoUnderTest.getEmail();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetImageUrl() {
        // Setup

        // Run the test
        final String result = googleOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertEquals("result", result);
    }
}
