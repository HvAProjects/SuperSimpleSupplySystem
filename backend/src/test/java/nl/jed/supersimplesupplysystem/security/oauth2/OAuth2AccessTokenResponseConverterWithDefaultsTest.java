package nl.jed.supersimplesupplysystem.security.oauth2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;

class OAuth2AccessTokenResponseConverterWithDefaultsTest {

    private OAuth2AccessTokenResponseConverterWithDefaults oAuth2AccessTokenResponseConverterWithDefaultsUnderTest;

    @BeforeEach
    void setUp() {
        oAuth2AccessTokenResponseConverterWithDefaultsUnderTest = new OAuth2AccessTokenResponseConverterWithDefaults();
    }

    @Test
    void testConvert() {
        // Setup
        final Map<String, String> tokenResponseParameters = Map.ofEntries(
                Map.entry(OAuth2ParameterNames.ACCESS_TOKEN, BEARER.getValue()),
                Map.entry(OAuth2ParameterNames.EXPIRES_IN, "60000"),
                Map.entry(OAuth2ParameterNames.SCOPE, "value")
        );

        // Run the test
        final OAuth2AccessTokenResponse result = oAuth2AccessTokenResponseConverterWithDefaultsUnderTest.convert(tokenResponseParameters);

        // Verify the results
    }

    @Test
    void testConvertBearerTokenType() {
        // Setup
        final Map<String, String> tokenResponseParameters = Map.ofEntries(
                Map.entry(OAuth2ParameterNames.ACCESS_TOKEN, "bear"),
                Map.entry(OAuth2ParameterNames.TOKEN_TYPE, BEARER.getValue()),
                Map.entry(OAuth2ParameterNames.EXPIRES_IN, "value"),
                Map.entry(OAuth2ParameterNames.SCOPE, "value")
        );

        // Run the test
        final OAuth2AccessTokenResponse result = oAuth2AccessTokenResponseConverterWithDefaultsUnderTest.convert(tokenResponseParameters);

        // Verify the results
    }

    @Test
    void testConvertWrongExpiresIn() {
        // Setup
        final Map<String, String> tokenResponseParameters = Map.ofEntries(
                Map.entry(OAuth2ParameterNames.ACCESS_TOKEN, "bearer"),
                Map.entry(OAuth2ParameterNames.EXPIRES_IN, "value"),
                Map.entry(OAuth2ParameterNames.SCOPE, "value")
        );

        // Run the test
        final OAuth2AccessTokenResponse result = oAuth2AccessTokenResponseConverterWithDefaultsUnderTest.convert(tokenResponseParameters);

        // Verify the results
    }

    @Test
    void testConvert_ThrowsIllegalArgumentException() {
        // Setup
        final Map<String, String> tokenResponseParameters = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> oAuth2AccessTokenResponseConverterWithDefaultsUnderTest.convert(tokenResponseParameters));
    }
}
