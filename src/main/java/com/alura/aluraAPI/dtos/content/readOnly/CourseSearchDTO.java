package com.alura.aluraAPI.dtos.content.readOnly;

import java.time.LocalDate;

public record CourseSearchDTO(String nameContent, LocalDate publicationDate, Double assessment) {
}
