package nl.jed.supersimplesupplysystem.security.oauth2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GithubOAuth2UserInfoTest {

    private GithubOAuth2UserInfo githubOAuth2UserInfoUnderTest;

    @BeforeEach
    void setUp() {
        githubOAuth2UserInfoUnderTest = new GithubOAuth2UserInfo(
                Map.ofEntries(
                        Map.entry("id", 123),
                        Map.entry("name", "result"),
                        Map.entry("email", "result"),
                        Map.entry("avatar_url", "result")
                )
        );
    }

    @Test
    void testGetId() {
        // Setup

        // Run the test
        final String result = githubOAuth2UserInfoUnderTest.getId();

        // Verify the results
        assertEquals("123", result);
    }

    @Test
    void testGetName() {
        // Setup

        // Run the test
        final String result = githubOAuth2UserInfoUnderTest.getName();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetEmail() {
        // Setup

        // Run the test
        final String result = githubOAuth2UserInfoUnderTest.getEmail();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetImageUrl() {
        // Setup

        // Run the test
        final String result = githubOAuth2UserInfoUnderTest.getImageUrl();

        // Verify the results
        assertEquals("result", result);
    }
}
