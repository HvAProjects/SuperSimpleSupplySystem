package nl.jed.supersimplesupplysystem.mail;

import nl.jed.supersimplesupplysystem.dto.Mail;
import nl.jed.supersimplesupplysystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class UserActivationMail extends Mail {

    @Autowired
    private Environment env;

    public UserActivationMail(User user, String token, String activateAccountUrl) {
        super();
        super.setMailTo(user.getEmail());
        super.setMailSubject("Activate account");
        String content = "Dear " + user.getDisplayName() + "," + System.lineSeparator() +
                "A signup request was made. Click on the url to activate your account." + System.lineSeparator() +
                activateAccountUrl + token + System.lineSeparator() +
                "If it was not you who requested this, you can ignore this email." + System.lineSeparator();
        super.setMailContent(content);
    }
}
