package com.alura.aluraAPI.controllers.student;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentLoadDTO;
import com.alura.aluraAPI.dtos.person.read.LoginResponse;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.services.students.StudentService;
import com.alura.aluraAPI.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private StudentService studentService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Void> create(@RequestBody StudentInsertDTO studentInsertDTO){
        studentService.create(studentInsertDTO);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody StudentLoadDTO studentLoadDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(studentLoadDTO.email(),studentLoadDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Student) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
