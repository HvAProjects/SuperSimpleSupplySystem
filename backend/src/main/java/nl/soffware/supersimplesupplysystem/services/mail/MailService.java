package nl.soffware.supersimplesupplysystem.services.mail;


import nl.soffware.supersimplesupplysystem.dto.Mail;

public interface MailService {
    public void sendEmail(Mail mail);
}
