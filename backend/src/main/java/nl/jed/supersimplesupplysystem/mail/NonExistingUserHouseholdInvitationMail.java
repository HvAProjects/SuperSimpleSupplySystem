package nl.jed.supersimplesupplysystem.mail;

import nl.jed.supersimplesupplysystem.dto.Mail;
import nl.jed.supersimplesupplysystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class NonExistingUserHouseholdInvitationMail extends Mail {

    @Autowired
    private Environment env;

    public NonExistingUserHouseholdInvitationMail(String email, User sender, String signUpUrl) {
        super();
        super.setMailTo(email);
        super.setMailSubject("SuperSimpleSupplySystem Household Invitation");
        String content = "User " + sender.getDisplayName() + " has invited you to their household!" + System.lineSeparator() +
                "Create an account at " + signUpUrl + " to accept this invitation!";
        super.setMailContent(content);
    }
}
