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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student entity = repository.findStudentByEmail(username).orElse(null);
        if (entity != null) {
            return UserDetailsUtil.load(new Student(), repository.searchStudentAndRolesByEmail(username));
        } else {
            return UserDetailsUtil.load(new Admin(), repository.searchAdminAndRolesByEmail(username));
        }
    }
}
