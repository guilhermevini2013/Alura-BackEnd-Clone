package com.alura.aluraAPI.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.GET,"/course/filter").permitAll()
                        .requestMatchers(HttpMethod.GET,"/course/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/course").permitAll()
                        .requestMatchers(HttpMethod.GET,"training/filter").permitAll()
                        .requestMatchers(HttpMethod.GET,"/training").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/course/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/training/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/training").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/course").hasRole("ADMIN"))
                .httpBasic(x-> x.setBuilder(http))
                .build();
    }
}
