package nl.jed.supersimplesupplysystem.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserTest {
    private final User user = new User();

    @Test
    void getAuthorities() {
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
        assertEquals(roles, user.getAuthorities());
    }

    @Test
    void getUsername() {
        String name = "name";
        user.setEmail(name);
        assertEquals(name, user.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        assertFalse(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertFalse(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertFalse(user.isCredentialsNonExpired());
    }
}