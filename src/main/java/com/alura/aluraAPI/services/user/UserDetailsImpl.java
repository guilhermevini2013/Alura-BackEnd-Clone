package com.alura.aluraAPI.services.user;

import com.alura.aluraAPI.models.person.Admin;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (verifyEmail(email)) {
            return UserDetailsUtil.load(new Student(), repository.searchStudentAndRolesByEmail(email));
        } else {
            return UserDetailsUtil.load(new Admin(), repository.searchAdminAndRolesByEmail(email));
        }
    }

    private Boolean verifyEmail(String email) {
        String[] addressEmail = email.split("@");
        return !addressEmail[1].equals("admin");
    }
}
