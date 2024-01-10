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
        this.setAttributesInStudent(result, user);
        return user;
    }

    private void setAttributesInStudent(List<UserDetailsProjection> result, Student student) {
        student.setEmail(result.get(0).getUsername());
        student.setPassword(result.get(0).getPassword());
        student.setIsAccountNonLocked(result.get(0).getIs_Non_Locked());
        student.setIsAccountNonExpired(result.get(0).getIs_Non_Expired());
        student.setIsCredentialsNonExpired(result.get(0).getIs_Credentials_Non_Expired());
        student.setIsEnabled(result.get(0).getIs_Enabled());
        for (UserDetailsProjection projection : result) {
            student.addRole(new TypeRole(projection.getRoleId(), projection.getAuthority()));
        }
    }
}
