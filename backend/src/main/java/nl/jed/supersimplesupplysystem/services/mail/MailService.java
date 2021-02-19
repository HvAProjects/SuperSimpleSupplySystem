package nl.jed.supersimplesupplysystem.services.mail;


import nl.jed.supersimplesupplysystem.dto.Mail;

public interface MailService {
    public void sendEmail(Mail mail);
}
