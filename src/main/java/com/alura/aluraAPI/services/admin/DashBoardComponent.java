package com.alura.aluraAPI.services.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DashBoardComponent {
    private ContentRepository contentRepository;
    private StudentRepository studentRepository;

    public DashBoardComponent(ContentRepository contentRepository, StudentRepository studentRepository) {
        this.contentRepository = contentRepository;
        this.studentRepository = studentRepository;
    }
    @Transactional(readOnly = true)
    public DashBoardReadDTO getValues(){
        return new DashBoardReadDTO(studentRepository.count(),contentRepository.countTraining(),contentRepository.countCourse());
    }
}
