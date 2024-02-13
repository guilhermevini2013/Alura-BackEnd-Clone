package com.alura.aluraAPI.services.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String EMAIL_FROM = "aluraclone@gmail.com";

    public void sendEmailToStudent(String emailStudent, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_FROM);
        message.setTo(emailStudent);
        message.setSubject(title);
        message.setText(content);
        javaMailSender.send(message);
    }
}
