package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.RoleRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.email.EmailService;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeSignatureStrategy;
import com.alura.aluraAPI.services.strategies.verify.IVerify;
import com.alura.aluraAPI.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final CalculateTimeSignatureStrategy timeSignatureStrategy;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final List<IVerify> verifyList;
    private final EmailService emailService;

    @Transactional(rollbackFor = ValidationException.class)
    public void create(StudentInsertDTO studentInsertDTO) {
        Student student = new Student(studentInsertDTO, roleRepository.findByAuthority("ROLE_STUDENT"), timeSignatureStrategy);
        student.setPassword(passwordEncoder.encode(studentInsertDTO.password()));
        studentRepository.save(student);
        //emailService.sendEmailToStudent(studentInsertDTO.email(), "Cadastro concluido", "Ola " + studentInsertDTO.name() + " Seja bem-vindo a maior plataforma de estudo do BRASIL!!!");
    }

    @Transactional
    public LoginToken login(PersonLoadDTO studentLoadDTO) {
        Student student = studentRepository.findByEmail(studentLoadDTO.email()).orElseThrow(() -> new ResourceNotFoundException("Email incorrect or no exists"));
        verifyList.forEach(strategy -> strategy.verify(student));
        var usernamePassword = new UsernamePasswordAuthenticationToken(studentLoadDTO.email(), studentLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Student) auth.getPrincipal());
        return new LoginToken(token);
    }
}
