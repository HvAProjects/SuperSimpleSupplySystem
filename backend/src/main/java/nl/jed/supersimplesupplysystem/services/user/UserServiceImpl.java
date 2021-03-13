package nl.jed.supersimplesupplysystem.services.user;
import java.util.*;

import nl.jed.supersimplesupplysystem.dto.*;
import nl.jed.supersimplesupplysystem.exception.OAuth2AuthenticationProcessingException;
import nl.jed.supersimplesupplysystem.exception.UserAlreadyExistAuthenticationException;
import nl.jed.supersimplesupplysystem.models.PasswordResetToken;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.UserActivationToken;
import nl.jed.supersimplesupplysystem.repository.PasswordResetTokenRepository;
import nl.jed.supersimplesupplysystem.repository.RoleRepository;
import nl.jed.supersimplesupplysystem.repository.UserActivationTokenRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.security.oauth2.user.OAuth2UserInfo;
import nl.jed.supersimplesupplysystem.security.oauth2.user.OAuth2UserInfoFactory;
import nl.jed.supersimplesupplysystem.services.mail.MailService;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    @Autowired
    UserActivationTokenRepository userActivationTokenRepository;

    @Override
    @Transactional(value = "transactionManager")
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user = userRepository.save(user);
        userRepository.flush();

        UserActivationToken token = generateUserActivationToken(user);
        userActivationTokenRepository.save(token);
        userActivationTokenRepository.flush();
        mailService.sendEmail(generateUserActivationMail(user, token.getToken()));

        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setEnabled(false);
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (oAuth2UserInfo.getName().isBlank()) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (oAuth2UserInfo.getEmail().isBlank()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId)) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + registrationId + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private UserActivationToken generateUserActivationToken(User user) {
        UserActivationToken token = new UserActivationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        return token;
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.builder()
                .providerUserId(oAuth2UserInfo.getId())
                .displayName(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .socialProvider(GeneralUtils.toSocialProvider(registrationId))
                .password("changeit").build();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void resetPassword(String emailAddress) {
        User user = findUserByEmail(emailAddress);
        if (user == null) {
            return; // Bewust niks doen, geen melding tonen aan gebruiker ivm security
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(token);
        passwordResetTokenRepository.save(passwordResetToken);
        mailService.sendEmail(generateRecoveryMail(user, token));
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        userRepository.flush();
    }

    @Override
    public void activateAccount(User user) {
        user.setEnabled(true);
        userRepository.save(user);
        userRepository.flush();
    }

    private Mail generateRecoveryMail(User user, String token) {
        Mail mail = new Mail();
        mail.setMailTo(user.getEmail());
        mail.setMailSubject("Reset password");
        StringBuilder contentSb = new StringBuilder();
        contentSb.append("A request was made to reset your password. Click on the link to reset your password.").append(System.lineSeparator());
        contentSb.append(env.getProperty("properties.forgotMailUrl")).append(token).append(System.lineSeparator());
        contentSb.append("If it was not you who requested this, you can ignore this email.").append(System.lineSeparator());
        mail.setMailContent(contentSb.toString());
        return mail;
    }

    private Mail generateUserActivationMail(User user, String token) {
        Mail mail = new Mail();
        mail.setMailTo(user.getEmail());
        mail.setMailSubject("Activate account");
        StringBuilder contentSb = new StringBuilder();
        contentSb.append("Dear " + user.getDisplayName() + ",").append(System.lineSeparator());
        contentSb.append("A signup request was made. Click on the url to activate your account.").append(System.lineSeparator());
        contentSb.append(env.getProperty("properties.activateAccountUrl")).append(token).append(System.lineSeparator());
        contentSb.append("If it was not you who requested this, you can ignore this email.").append(System.lineSeparator());
        mail.setMailContent(contentSb.toString());
        return mail;
    }
}
