package com.alura.aluraAPI.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        this.configureRoutesAdminSecurity(http);
        this.configureRoutesStudentSecurity(http);
        this.configureRoutesPublicSecurity(http);
        return http
                .cors(x -> x.disable())
                .csrf(x -> x.disable())
                .headers(x -> x.frameOptions(y -> y.disable()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void configureRoutesPublicSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/student/create").permitAll()
                .requestMatchers(HttpMethod.POST, "/student/login").permitAll()
                .anyRequest().authenticated());
    }

    private void configureRoutesStudentSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET,"/course").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/training").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/course/filter").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET,"/training/filter").hasRole("STUDENT"));
    }

    private void configureRoutesAdminSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/course/id/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/h2/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/course").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/course").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/training").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/training").hasRole("ADMIN"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
