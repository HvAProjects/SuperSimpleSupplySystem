package nl.jed.supersimplesupplysystem.services.user;

import nl.jed.supersimplesupplysystem.dto.ChangePasswordRequest;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.SignUpRequest;
import nl.jed.supersimplesupplysystem.exception.UserAlreadyExistAuthenticationException;
import nl.jed.supersimplesupplysystem.models.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

    void resetPassword(String emailAddress);
    void changePassword(User user, String newPassword);
    void activateAccount(User user);
}
