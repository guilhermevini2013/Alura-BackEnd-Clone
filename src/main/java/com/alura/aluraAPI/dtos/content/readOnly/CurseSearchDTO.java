package com.alura.aluraAPI.dtos.content.readOnly;

import java.time.LocalDate;

public record CurseSearchDTO(String nameContent, LocalDate publicationDate, Double assessment) {
}
