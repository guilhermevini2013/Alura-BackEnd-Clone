package com.alura.aluraAPI.config.security;

import com.alura.aluraAPI.repositories.AdminRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    TokenService tokenService;
    StudentRepository userRepository;
    AdminRepository adminRepository;

    public SecurityFilter(TokenService tokenService, StudentRepository userRepository, AdminRepository adminRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            String login = tokenService.validateToken(token);
            UserDetails user = null;

            if (verifyEmail(login))
                user = userRepository.findByEmail(login).orElseThrow(() -> new ResourceNotFoundException("Email incorrect or no exists"));
            else
                user = adminRepository.findByEmail(login).orElseThrow(() -> new ResourceNotFoundException("Email incorrect or no exists"));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private Boolean verifyEmail(String email) {
        String[] addressEmail = email.split("@");
        if (addressEmail[1].equals("admin"))
            return false;
        else
            return true;
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
