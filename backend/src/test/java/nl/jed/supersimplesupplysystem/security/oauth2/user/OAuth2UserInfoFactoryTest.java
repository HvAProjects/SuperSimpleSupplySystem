package nl.jed.supersimplesupplysystem.security.oauth2.user;

import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.exception.OAuth2AuthenticationProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OAuth2UserInfoFactoryTest {

    @ParameterizedTest
    @EnumSource(value = SocialProvider.class, names = {"LOCAL"}, mode = EnumSource.Mode.EXCLUDE)
    void testGetOAuth2UserInfo(SocialProvider provider) {
        // Setup
        final Map<String, Object> attributes = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final OAuth2UserInfo result = OAuth2UserInfoFactory.getOAuth2UserInfo(provider.getProviderType(), attributes);

        // Verify the results
    }

    @Test
    void assertThrowsException() {
        final Map<String, Object> attributes = Map.ofEntries(Map.entry("value", "value"));
        Exception exception = assertThrows(OAuth2AuthenticationProcessingException.class, () -> {
            OAuth2UserInfoFactory.getOAuth2UserInfo(SocialProvider.LOCAL.getProviderType(), attributes);
        });

        String expectedMessage = "Sorry! Login with local is not supported yet.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
