package com.alura.aluraAPI.services.students;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.RoleRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.calculates.CalculateTimeSignatureStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private CalculateTimeSignatureStrategy timeSignatureStrategy;
    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, RoleRepository roleRepository, CalculateTimeSignatureStrategy timeSignatureStrategy, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.timeSignatureStrategy = timeSignatureStrategy;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(StudentInsertDTO studentInsertDTO) {
        Student student = new Student(studentInsertDTO, timeSignatureStrategy);
        student.addRole(roleRepository.findByAuthority("ROLE_STUDENT"));
        student.setPassword(passwordEncoder.encode(studentInsertDTO.password()));
        studentRepository.save(student);
    }
}
