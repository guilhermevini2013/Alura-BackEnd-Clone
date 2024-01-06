package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.repositories.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private StudentRepository studentRepository;

    public UserDetailsImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Student not found"));
    }
}
