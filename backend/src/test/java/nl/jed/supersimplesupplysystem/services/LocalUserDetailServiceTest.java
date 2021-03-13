package nl.jed.supersimplesupplysystem.services;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.exception.ResourceNotFoundException;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalUserDetailServiceTest {

    @Mock
    private UserService mockUserService;

    @InjectMocks
    private LocalUserDetailService localUserDetailServiceUnderTest;

    @Test
    void testLoadUserByUsername() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setProviderUserId("providerUserId");
        user.setEmail("email");
        user.setEnabled(true);
        user.setPasswordExpired(true);
        user.setExpired(true);
        user.setDisplayName("displayName");
        user.setCreatedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setModifiedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setPassword("password");
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final LocalUser expectedResult = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getRoles(), user);


        when(mockUserService.findUserByEmail("email")).thenReturn(user);

        // Run the test
        final LocalUser result = localUserDetailServiceUnderTest.loadUserByUsername("email");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testLoadUserByUsername_ThrowsUsernameNotFoundException() {
        // Run the test
        assertThrows(UsernameNotFoundException.class, () -> localUserDetailServiceUnderTest.loadUserByUsername("email"));
    }

    @Test
    void testLoadUserById() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setProviderUserId("providerUserId");
        user.setEmail("email");
        user.setEnabled(false);
        user.setPasswordExpired(false);
        user.setExpired(false);
        user.setDisplayName("displayName");
        user.setCreatedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setModifiedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        user.setPassword("password");
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final LocalUser expectedResult = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getRoles(), user);

        final Optional<User> user1 = Optional.of(user);
        when(mockUserService.findUserById(0L)).thenReturn(user1);

        // Run the test
        final LocalUser result = localUserDetailServiceUnderTest.loadUserById(user.getId());

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testLoadUserById_UserServiceReturnsAbsent() {
        when(mockUserService.findUserById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(ResourceNotFoundException.class, () ->localUserDetailServiceUnderTest.loadUserById(0L));
    }
}
