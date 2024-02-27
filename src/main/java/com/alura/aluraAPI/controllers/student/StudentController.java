package com.alura.aluraAPI.controllers.student;

import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.services.students.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> create(@RequestBody @Valid StudentInsertDTO studentInsertDTO) {
        studentService.create(studentInsertDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginToken> login(@RequestBody @Valid PersonLoadDTO studentLoadDTO) {
        LoginToken loginToken = studentService.login(studentLoadDTO);
        return ResponseEntity.ok(loginToken);
    }
}
