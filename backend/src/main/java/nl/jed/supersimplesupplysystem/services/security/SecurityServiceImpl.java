package nl.jed.supersimplesupplysystem.services.security;

import nl.jed.supersimplesupplysystem.models.PasswordResetToken;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.UserActivationToken;
import nl.jed.supersimplesupplysystem.repository.PasswordResetTokenRepository;
import nl.jed.supersimplesupplysystem.repository.UserActivationTokenRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserActivationTokenRepository userActivationTokenRepository;

    @Override
    public User validatePasswordResetToken(String token) {
        final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null || isTokenExpired(passwordResetToken)) {
            return null;
        }
        return passwordResetToken.getUser();
    }

    @Override
    public User validateActivateAccountToken(String token) {
        final UserActivationToken userActivationToken = userActivationTokenRepository.findByToken(token);
        if (userActivationToken == null) {
            return null;
        }
        return userActivationToken.getUser();
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
