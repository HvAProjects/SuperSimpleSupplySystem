package nl.jed.supersimplesupplysystem.services;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.SignUpRequest;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.exception.OAuth2AuthenticationProcessingException;
import nl.jed.supersimplesupplysystem.exception.UserAlreadyExistAuthenticationException;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.repository.RoleRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Optional;

import static nl.jed.supersimplesupplysystem.models.Role.ROLE_USER;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    void testRegisterNewUser() {
        // Setup
        final SocialProvider provider = SocialProvider.FACEBOOK;
        final User expectedResult = new User();
        expectedResult.setId(null);
        expectedResult.setProviderUserId("providerUserId");
        expectedResult.setEmail("email");
        expectedResult.setEnabled(true);
        expectedResult.setPasswordExpired(false);
        expectedResult.setExpired(false);
        expectedResult.setDisplayName("displayName");
        expectedResult.setProvider(provider.getProviderType());
        expectedResult.setModifiedDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        expectedResult.setPassword("password");

        when(mockUserRepository.existsByEmail("email")).thenReturn(false);
        when(mockPasswordEncoder.encode("password")).thenReturn("result");
        when(mockRoleRepository.findByName(ROLE_USER)).thenReturn(new Role(ROLE_USER));
        when(mockUserRepository.save(any())).thenReturn(expectedResult);


        final SignUpRequest signUpRequest = new SignUpRequest(expectedResult.getId(), expectedResult.getProviderUserId(), expectedResult.getDisplayName(), expectedResult.getEmail(), provider, expectedResult.getPassword(), expectedResult.getPassword());
        // Run the test
        final User result = userServiceImplUnderTest.registerNewUser(signUpRequest);
        expectedResult.setCreatedDate(result.getCreatedDate());
        expectedResult.setModifiedDate(result.getModifiedDate());
        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockUserRepository).flush();
    }

    @Test
    void testRegisterNewUser_ThrowsUserIdAlreadyExistAuthenticationException() {
        // Setup
        final SignUpRequest signUpRequest = new SignUpRequest(0L, "providerUserId", "displayName", "email", SocialProvider.FACEBOOK, "password", "matchingPassword");
        when(mockUserRepository.existsById(signUpRequest.getUserID())).thenReturn(true);

        // Run the test
        assertThrows(UserAlreadyExistAuthenticationException.class, () -> userServiceImplUnderTest.registerNewUser(signUpRequest));
    }

    @Test
    void testRegisterNewUser_ThrowsUserEmailAlreadyExistAuthenticationException() {
        // Setup
        final SignUpRequest signUpRequest = new SignUpRequest(0L, "providerUserId", "displayName", "email", SocialProvider.FACEBOOK, "password", "matchingPassword");
        when(mockUserRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

        // Run the test
        assertThrows(UserAlreadyExistAuthenticationException.class, () -> userServiceImplUnderTest.registerNewUser(signUpRequest));
    }

    @Test
    void testFindUserByEmail() {
        // Configure UserRepository.findByEmail(...).
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
        when(mockUserRepository.findByEmail(user.getEmail())).thenReturn(user);

        // Run the test
        final User result = userServiceImplUnderTest.findUserByEmail(user.getEmail());

        // Verify the results
        assertEquals(user, result);
    }

    @Test
    void testProcessUserRegistrationExistingUser() {
        // Setup
        final OidcIdToken idToken = new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
        final OidcUserInfo userInfo = new OidcUserInfo(Map.ofEntries(Map.entry("value", "value")));
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
        user.setProvider(SocialProvider.GOOGLE.getProviderType());
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final Map<String, Object> attributes = Map.ofEntries(
                Map.entry("sub", user.getDisplayName()),
                Map.entry("name", user.getEmail()),
                Map.entry("email", user.getEmail()),
                Map.entry("picture","result")
        );
        final LocalUser expectedResult = LocalUser.create(user,attributes, idToken, userInfo);

        when(mockUserRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(mockUserRepository.save(user)).thenReturn(user);
        // Run the test
        final LocalUser result = userServiceImplUnderTest.processUserRegistration(user.getProvider(), attributes, idToken, userInfo);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testProcessUserRegistrationWithoutName() {
        // Setup
        final OidcIdToken idToken = new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
        final OidcUserInfo userInfo = new OidcUserInfo(Map.ofEntries(Map.entry("value", "value")));
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
        user.setProvider(SocialProvider.GOOGLE.getProviderType());
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final Map<String, Object> attributes = Map.ofEntries(
                Map.entry("sub", user.getDisplayName()),
                Map.entry("name", ""),
                Map.entry("email", user.getEmail()),
                Map.entry("picture","result")
        );

        // Run the test
        OAuth2AuthenticationProcessingException e = assertThrows(OAuth2AuthenticationProcessingException.class, () -> userServiceImplUnderTest.processUserRegistration(user.getProvider(), attributes, idToken, userInfo));
        assertEquals("Name not found from OAuth2 provider", e.getMessage());
    }

    @Test
    void testProcessUserRegistrationWithoutEmail() {
        // Setup
        final OidcIdToken idToken = new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
        final OidcUserInfo userInfo = new OidcUserInfo(Map.ofEntries(Map.entry("value", "value")));
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
        user.setProvider(SocialProvider.GOOGLE.getProviderType());
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final Map<String, Object> attributes = Map.ofEntries(
                Map.entry("sub", user.getDisplayName()),
                Map.entry("name", user.getEmail()),
                Map.entry("email", ""),
                Map.entry("picture","result")
        );

        // Run the test
        OAuth2AuthenticationProcessingException e = assertThrows(OAuth2AuthenticationProcessingException.class, () -> userServiceImplUnderTest.processUserRegistration(user.getProvider(), attributes, idToken, userInfo));
        assertEquals("Email not found from OAuth2 provider", e.getMessage());
    }

    @Test
    void testProcessUserRegistrationNewUser() {
        // Setup
        final OidcIdToken idToken = new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
        final OidcUserInfo userInfo = new OidcUserInfo(Map.ofEntries(Map.entry("value", "value")));
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
        user.setProvider(SocialProvider.GOOGLE.getProviderType());
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final Map<String, Object> attributes = Map.ofEntries(
                Map.entry("sub", user.getDisplayName()),
                Map.entry("name", user.getEmail()),
                Map.entry("email", user.getEmail()),
                Map.entry("picture","result")
        );
        final LocalUser expectedResult = LocalUser.create(user,attributes, idToken, userInfo);

        when(mockUserRepository.findByEmail(user.getEmail())).thenReturn(null);
        userServiceImplUnderTest = spy(userServiceImplUnderTest);
        doReturn(user).when(userServiceImplUnderTest).registerNewUser(any());
        // Run the test
        final LocalUser result = userServiceImplUnderTest.processUserRegistration(user.getProvider(), attributes, idToken, userInfo);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testProcessUserRegistrationWrongProvider() {
        // Setup
        final OidcIdToken idToken = new OidcIdToken("tokenValue", LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), LocalDateTime.of(2021, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC), Map.ofEntries(Map.entry("value", "value")));
        final OidcUserInfo userInfo = new OidcUserInfo(Map.ofEntries(Map.entry("value", "value")));
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
        user.setProvider(SocialProvider.GOOGLE.getProviderType());
        user.setRoles(asSet(new Role(Role.ROLE_USER)));
        final Map<String, Object> attributes = Map.ofEntries(
                Map.entry("sub", user.getDisplayName()),
                Map.entry("name", user.getEmail()),
                Map.entry("email", user.getEmail()),
                Map.entry("picture","result")
        );
        final LocalUser expectedResult = LocalUser.create(user,attributes, idToken, userInfo);
        final User user2 = new User();
        user2.setProvider(SocialProvider.GITHUB.getProviderType());
        when(mockUserRepository.findByEmail(user.getEmail())).thenReturn(user2);
        // Run the test
        final OAuth2AuthenticationProcessingException e = assertThrows(OAuth2AuthenticationProcessingException.class, () -> userServiceImplUnderTest.processUserRegistration(user.getProvider(), attributes, idToken, userInfo));

        // Verify the results
        assertEquals("Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user2.getProvider() + " account to login.", e.getMessage());
    }

    @Test
    void testFindUserById() {
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
        final Optional<User> expectedResult = Optional.of(user);

        when(mockUserRepository.findById(user.getId())).thenReturn(expectedResult);

        // Run the test
        final Optional<User> result = userServiceImplUnderTest.findUserById(user.getId());

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindUserById_UserRepositoryReturnsAbsent() {
        final Optional<User> expectedResult = Optional.empty();
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<User> result = userServiceImplUnderTest.findUserById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}