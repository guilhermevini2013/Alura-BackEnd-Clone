package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.person.TypeRole;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import com.alura.aluraAPI.repositories.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private StudentRepository studentRepository;

    public UserDetailsImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = studentRepository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }
        Student user = new Student();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new TypeRole(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
