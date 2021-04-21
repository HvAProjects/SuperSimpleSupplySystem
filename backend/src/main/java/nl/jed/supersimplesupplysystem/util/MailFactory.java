package nl.jed.supersimplesupplysystem.util;

import nl.jed.supersimplesupplysystem.mail.NonExistingUserHouseholdInvitationMail;
import nl.jed.supersimplesupplysystem.mail.PasswordRecoveryMail;
import nl.jed.supersimplesupplysystem.mail.UserActivationMail;
import nl.jed.supersimplesupplysystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class MailFactory {

    @Autowired
    private Environment env;

    public NonExistingUserHouseholdInvitationMail getNonExistingUserHouseholdInvitationMail(String email, User sender) {
        return new NonExistingUserHouseholdInvitationMail(email, sender, env.getProperty("properties.signUpUrl"));
    }

    public PasswordRecoveryMail getPasswordRecoveryMail(User user, String token) {
        return new PasswordRecoveryMail(user, token, env.getProperty("properties.forgotMailUrl"));
    }

    public UserActivationMail getUserActivationMail(User user, String token) {
        return new UserActivationMail(user, token, env.getProperty("properties.activateAccountUrl"));
    }
}
