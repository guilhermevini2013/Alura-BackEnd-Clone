package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentLoadDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.RoleRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeSignatureStrategy;
import com.alura.aluraAPI.services.strategies.verify.IVerify;
import com.alura.aluraAPI.services.token.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private CalculateTimeSignatureStrategy timeSignatureStrategy;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private List<IVerify> verifyList;

    public StudentService(StudentRepository studentRepository, RoleRepository roleRepository, CalculateTimeSignatureStrategy timeSignatureStrategy, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, List<IVerify> verifyList) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.timeSignatureStrategy = timeSignatureStrategy;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.verifyList = verifyList;
    }

    @Transactional
    public void create(StudentInsertDTO studentInsertDTO) {
        Student student = new Student(studentInsertDTO, timeSignatureStrategy);
        student.addRole(roleRepository.findByAuthority("ROLE_STUDENT"));
        student.setPassword(passwordEncoder.encode(studentInsertDTO.password()));
        studentRepository.save(student);
    }

    @Transactional
    public LoginToken login(StudentLoadDTO studentLoadDTO) {
        Student student = studentRepository.findByEmail(studentLoadDTO.email()).orElseThrow(() -> new ResourceNotFoundException("Email incorrect or no exists"));
        verifyList.forEach(strategy-> strategy.verify(student));
        var usernamePassword = new UsernamePasswordAuthenticationToken(studentLoadDTO.email(), studentLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Student) auth.getPrincipal());
        return new LoginToken(token);
    }
}
