package com.alura.aluraAPI.services.email;

import com.alura.aluraAPI.config.email.EmailInitializer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailInitializer emailInitializer;

    public void sendEmail() throws EmailException {
        Email email = emailInitializer.getSenderEmail();
        email.setSubject("");
        email.setMsg("");
        email.addTo("");
        email.send();
    }
}
