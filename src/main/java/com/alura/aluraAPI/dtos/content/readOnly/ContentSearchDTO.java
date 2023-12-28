package com.alura.aluraAPI.dtos.content.readOnly;

import java.time.LocalDate;

public record ContentSearchDTO(String nameContent, LocalDate publicationDate, Double assessment) {
}
