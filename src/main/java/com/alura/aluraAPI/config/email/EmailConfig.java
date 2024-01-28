package com.alura.aluraAPI.config.email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Bean
    public Email getInstance(){
        return new SimpleEmail();
    }
}
