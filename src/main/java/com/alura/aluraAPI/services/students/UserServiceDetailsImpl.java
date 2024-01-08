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
public class UserServiceDetailsImpl implements UserDetailsService {
    private StudentRepository studentRepository;

    public UserServiceDetailsImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = studentRepository.searchUserAndRolesByEmail(username);
        Student user = new Student();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new TypeRole(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
