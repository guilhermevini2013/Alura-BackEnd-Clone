package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Course;

import java.time.LocalDate;

public record CourseReadDTO(String nameContent,
                            String description,
                            LocalDate publicationDate) {
    public CourseReadDTO(Course curse) {
        this(curse.getNameContent(), curse.getDescription(),curse.getPublicationDate());
    }
}
