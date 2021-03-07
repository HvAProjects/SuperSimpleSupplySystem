package nl.jed.supersimplesupplysystem.services;

import nl.jed.supersimplesupplysystem.models.PasswordResetToken;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.UserActivationToken;
import nl.jed.supersimplesupplysystem.repository.PasswordResetTokenRepository;
import nl.jed.supersimplesupplysystem.repository.UserActivationTokenRepository;
import nl.jed.supersimplesupplysystem.services.security.SecurityServiceImpl;
import nl.jed.supersimplesupplysystem.services.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceImplTest {

    @InjectMocks
    private SecurityServiceImpl securityServiceImplTest;

    @Mock
    private UserActivationTokenRepository userActivationTokenRepository;

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Test
    void testValidatePasswordResetToken() {
        PasswordResetToken token = new PasswordResetToken();
        token.setToken("test");
        User user = new User();
        token.setUser(user);
        when(passwordResetTokenRepository.findByToken(token.getToken())).thenReturn(token);
        User returnUser = securityServiceImplTest.validatePasswordResetToken(token.getToken());
        assertEquals(user, returnUser);
    }

    @Test
    void testValidateActivateAccountToken() {
        UserActivationToken token = new UserActivationToken();
        User testUser = new User();
        token.setUser(testUser);
        token.setToken("test");
        when(userActivationTokenRepository.findByToken(token.getToken())).thenReturn(token);
        User returnUser = securityServiceImplTest.validateActivateAccountToken(token.getToken());
        assertEquals(testUser, returnUser);
    }
}
