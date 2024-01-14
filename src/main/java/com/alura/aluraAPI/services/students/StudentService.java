package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentLoadDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.RoleRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeSignatureStrategy;
import com.alura.aluraAPI.services.token.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private CalculateTimeSignatureStrategy timeSignatureStrategy;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public StudentService(StudentRepository studentRepository, RoleRepository roleRepository, CalculateTimeSignatureStrategy timeSignatureStrategy, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.timeSignatureStrategy = timeSignatureStrategy;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    public void create(StudentInsertDTO studentInsertDTO) {
        Student student = new Student(studentInsertDTO, timeSignatureStrategy);
        student.addRole(roleRepository.findByAuthority("ROLE_STUDENT"));
        student.setPassword(passwordEncoder.encode(studentInsertDTO.password()));
        studentRepository.save(student);
    }
    @Transactional
    public LoginToken login(StudentLoadDTO studentLoadDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(studentLoadDTO.email(),studentLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Student) auth.getPrincipal());
        return new LoginToken(token);
    }
}
