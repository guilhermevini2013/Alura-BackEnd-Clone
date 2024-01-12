package com.alura.aluraAPI.config.security;

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
        String role = "STUDENT";
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/course").hasAnyRole(role, "ADMIN")
                .requestMatchers(HttpMethod.GET, "/training").hasAnyRole(role, "ADMIN")
                .requestMatchers(HttpMethod.GET, "/course/filter").hasAnyRole(role, "ADMIN")
                .requestMatchers(HttpMethod.GET, "/training/filter").hasAnyRole(role, "ADMIN"));
    }

    private void configureRoutesAdminSecurity(HttpSecurity http) throws Exception {
        String role = "ADMIN";
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/training/id/").hasRole(role)
                .requestMatchers(HttpMethod.GET, "/course/id/").hasRole(role)
                .requestMatchers(HttpMethod.GET, "/h2/**").hasRole(role)
                .requestMatchers(HttpMethod.POST, "/h2/**").hasRole(role)
                .requestMatchers(HttpMethod.POST, "/course").hasRole(role)
                .requestMatchers(HttpMethod.DELETE, "/course").hasRole(role)
                .requestMatchers(HttpMethod.POST, "/training").hasRole(role)
                .requestMatchers(HttpMethod.DELETE, "/training").hasRole(role));
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
