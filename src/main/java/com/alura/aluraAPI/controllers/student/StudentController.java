package com.alura.aluraAPI.controllers.student;

import com.alura.aluraAPI.dtos.person.insert.PersonLoadDTO;
import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.dtos.person.read.LoginToken;
import com.alura.aluraAPI.services.students.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/student", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Controller for Student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "/create")
    @Operation(description = "Create new Student",
            summary = "Create new Student",
            responses = {
                    @ApiResponse(description = "Create", responseCode = "201"),
                    @ApiResponse(description = "Validation field", responseCode = "400")
            })
    public ResponseEntity<Void> create(@RequestBody @Valid StudentInsertDTO studentInsertDTO, UriComponentsBuilder componentsBuilder) {
        studentService.create(studentInsertDTO);
        return ResponseEntity.created(componentsBuilder.path("/student/create").build().toUri()).build();
    }

    @PostMapping(value = "/login")
    @Operation(description = "Login Student in Alura",
            summary = "Login Student",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Validation signature", responseCode = "400"),
                    @ApiResponse(description = "Validation account is block", responseCode = "400"),
                    @ApiResponse(description = "Validation field", responseCode = "400"),
            })
    public ResponseEntity<LoginToken> login(@RequestBody @Valid PersonLoadDTO studentLoadDTO) {
        LoginToken loginToken = studentService.login(studentLoadDTO);
        return ResponseEntity.ok(loginToken);
    }
}
