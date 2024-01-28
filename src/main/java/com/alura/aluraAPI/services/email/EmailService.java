package com.alura.aluraAPI.services.email;

import com.alura.aluraAPI.config.email.EmailInitializer;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private EmailInitializer emailInitializer;

    public EmailService(EmailInitializer emailInitializer) {
        this.emailInitializer = emailInitializer;
    }
    public void sendEmail() throws EmailException {
        Email email = emailInitializer.getSenderEmail();
        email.setSubject("");
        email.setMsg("");
        email.addTo("");
        email.send();

    }
}
