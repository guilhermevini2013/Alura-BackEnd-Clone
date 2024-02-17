package com.alura.aluraAPI.services.admin;

import com.alura.aluraAPI.dtos.dashboard.DashBoardReadDTO;
import com.alura.aluraAPI.repositories.ContentRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DashBoardComponent {
    private final ContentRepository contentRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public DashBoardReadDTO getValues() {
        return new DashBoardReadDTO(studentRepository.count(), contentRepository.countTraining(), contentRepository.countCourse());
    }
}
