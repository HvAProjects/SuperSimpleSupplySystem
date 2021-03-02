package nl.jed.supersimplesupplysystem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void getAuthority() {
        String name = "TEST";
        Role role = new Role(name);
        assertEquals(name, role.getAuthority());
    }
}