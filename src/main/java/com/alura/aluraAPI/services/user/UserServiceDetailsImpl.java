package com.alura.aluraAPI.services.user;

import com.alura.aluraAPI.models.person.Admin;
import com.alura.aluraAPI.models.person.TypeRole;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import com.alura.aluraAPI.repositories.AdminRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {
    private StudentRepository studentRepository;

    private AdminRepository repository;

    public UserServiceDetailsImpl(StudentRepository studentRepository, AdminRepository repository) {
        this.studentRepository = studentRepository;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        Admin user = new Admin();
        this.setAttributesInStudent(result, user);
        this.validateStudent(user);
        return user;
    }

    private void setAttributesInStudent(List<UserDetailsProjection> result, Admin student) {
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

    private void validateStudent(Admin student) {
        if (!student.getIsAccountNonExpired() || !student.getIsAccountNonLocked() || !student.getIsEnabled() || !student.getIsCredentialsNonExpired())
            throw new ValidationException("Account unreachable");
    }
}
