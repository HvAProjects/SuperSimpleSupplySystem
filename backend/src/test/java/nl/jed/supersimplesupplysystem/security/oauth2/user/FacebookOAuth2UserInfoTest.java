package nl.jed.supersimplesupplysystem.security.oauth2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FacebookOAuth2UserInfoTest {

    private FacebookOAuth2UserInfo facebookOAuth2UserInfoUnderTest;

    @BeforeEach
    void setUp() {
        facebookOAuth2UserInfoUnderTest = new FacebookOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("id", "result"),
                        Map.entry("name", "result"),
                        Map.entry("email", "result"),
                        Map.entry("picture",
                                Map.ofEntries(Map.entry("data",
                                        Map.ofEntries(Map.entry("url", "result"))
                                ))
                        )
                )
        );
    }

    @Test
    void testGetId() {
        // Setup

        // Run the test
        final String result = facebookOAuth2UserInfoUnderTest.getId();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetName() {
        // Setup

        // Run the test
        final String result = facebookOAuth2UserInfoUnderTest.getName();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetEmail() {
        // Setup

        // Run the test
        final String result = facebookOAuth2UserInfoUnderTest.getEmail();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetImageUrl() {
        // Setup

        // Run the test
        final String result = facebookOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetImageUrlNonExistent() {
        // Setup
        facebookOAuth2UserInfoUnderTest = new FacebookOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("id", "result"),
                        Map.entry("name", "result"),
                        Map.entry("email", "result")
                )
        );

        // Run the test
        final String result = facebookOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertNull(result);

        // Setup
        facebookOAuth2UserInfoUnderTest = new FacebookOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("id", "result"),
                        Map.entry("name", "result"),
                        Map.entry("email", "result"),
                        Map.entry("picture",
                                Map.ofEntries(Map.entry("dataa",
                                        Map.ofEntries(Map.entry("urll", "result"))
                                ))
                        )
                )
        );

        // Run the test
        final String result2 = facebookOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertNull(result2);

        // Setup
        facebookOAuth2UserInfoUnderTest = new FacebookOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("id", "result"),
                        Map.entry("name", "result"),
                        Map.entry("email", "result"),
                        Map.entry("picture",
                                Map.ofEntries(Map.entry("data",
                                        Map.ofEntries(Map.entry("urll", "result"))
                                ))
                        )
                )
        );

        // Run the test
        final String result3 = facebookOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertNull(result3);
    }
}
