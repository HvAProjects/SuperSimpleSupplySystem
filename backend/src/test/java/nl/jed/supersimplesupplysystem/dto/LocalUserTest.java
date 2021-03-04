package nl.jed.supersimplesupplysystem.dto;

import nl.jed.supersimplesupplysystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalUserTest {
    private LocalUser localUser;
    @BeforeEach
    void init() {
        User user = new User();
        user.setId(1L);
        user.setDisplayName("username");
        user.setEmail("username@company.com");
        user.setPassword("Password");
        user.setEnabled(true);
        user.setRoles(new HashSet<>());
        user.setProvider(SocialProvider.LOCAL.getProviderType());
        localUser = LocalUser.create(user, null, null, null);
    }

    @Test
    void getName() {
        String name = "name";
        localUser.getUser().setDisplayName(name);
        assertEquals(name,localUser.getName());
    }

    @Test
    void getClaims() {
        Map<String, Object> claims = new HashMap<>();
        localUser.setAttributes(claims);
        assertEquals(claims, localUser.getClaims());
    }
}