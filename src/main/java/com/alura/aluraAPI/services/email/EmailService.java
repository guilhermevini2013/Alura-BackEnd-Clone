package com.alura.aluraAPI.services.email;

import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String EMAIL_FROM = "aluraclone@gmail.com";
    private final StudentRepository studentRepository;

    public void sendEmailToStudent(String emailStudent, String title, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(EMAIL_FROM);
            message.setTo(emailStudent);
            message.setSubject(title);
            message.setText(content);
            javaMailSender.send(message);
        } catch (MailException ex) {
            throw new ValidationException("Email incorrect or no exists");
        }
    }

    @Async
    @Scheduled(fixedRate = 50000)
    public void sendEmailAllStudent(String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        studentRepository.findAll().forEach(student -> {
            message.setFrom(EMAIL_FROM);
            message.setTo(student.getEmail());
            message.setSubject(title);
            message.setText(content);
            javaMailSender.send(message);
        });
    }
}
