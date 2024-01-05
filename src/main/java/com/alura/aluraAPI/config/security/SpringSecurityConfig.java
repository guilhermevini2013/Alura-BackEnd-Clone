//package com.alura.aluraAPI.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf(csrf-> csrf.disable())
//                .cors(cors-> cors.disable())
//                .authorizeHttpRequests(auth-> auth
//                        .anyRequest().permitAll())
//                .httpBasic(x-> x.setBuilder(http))
//                .build();
//    }
//}
