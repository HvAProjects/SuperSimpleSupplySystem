package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class Mail {
    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType = "text/plain";

    private List<Object> attachments;
}
