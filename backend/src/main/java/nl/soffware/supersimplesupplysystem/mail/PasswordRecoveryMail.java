package nl.soffware.supersimplesupplysystem.mail;

import nl.soffware.supersimplesupplysystem.dto.Mail;
import nl.soffware.supersimplesupplysystem.models.User;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

public class PasswordRecoveryMail extends Mail {

    @Resource
    public Environment env;

    public PasswordRecoveryMail(User user, String token, String forgotMailUrl) {
        super();
        super.setMailTo(user.getEmail());
        super.setMailSubject("Reset password");
        String content = "A request was made to reset your password. Click on the link to reset your password." + System.lineSeparator() +
                forgotMailUrl + token + System.lineSeparator() +
                "If it was not you who requested this, you can ignore this email." + System.lineSeparator();
        super.setMailContent(content);
    }
}
