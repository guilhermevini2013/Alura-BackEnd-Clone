package com.alura.aluraAPI.controllers.student;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.services.students.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> create(@RequestBody StudentInsertDTO studentInsertDTO){
        studentService.create(studentInsertDTO);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginToken> login(@RequestBody PersonLoadDTO studentLoadDTO){
        LoginToken loginToken = studentService.login(studentLoadDTO);
        return ResponseEntity.ok(loginToken);
    }
}
