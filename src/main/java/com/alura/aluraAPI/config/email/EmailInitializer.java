package com.alura.aluraAPI.config.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Component;

@Component
public class EmailInitializer {
    private final String HOST = "smtp.gmail.com";
    private final Integer PORT = 587;
    private final String SENDER_EMAIL = "";
    private final String SENDER_PASSWORD = "";
    private Email simpleEmail;

    public EmailInitializer(Email simpleEmail) {
        this.simpleEmail = simpleEmail;
    }

    public Email getSenderEmail() throws EmailException {
        System.setProperty("https.protocols", "TLSv1.2");
        simpleEmail.setHostName(HOST);
        simpleEmail.setSmtpPort(PORT);
        simpleEmail.setAuthenticator(new DefaultAuthenticator(SENDER_EMAIL, SENDER_PASSWORD));
        simpleEmail.setStartTLSEnabled(true);  // Use true para TLS
        simpleEmail.setFrom(SENDER_EMAIL);
        return simpleEmail;
    }
}
