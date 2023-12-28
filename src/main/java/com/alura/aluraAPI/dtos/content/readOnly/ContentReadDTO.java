package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Content;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentReadDTO {
    private String nameContent;
    private String description;
    private LocalDate publicationDate;
    private Integer totalHours;
    private Double assessment;
    private Long totalStudent;

    public ContentReadDTO(Content entityFind) {
        this.nameContent = entityFind.getNameContent();
        this.description = entityFind.getDescription();
        this.publicationDate = entityFind.getPublicationDate();
        this.totalHours = entityFind.getTotalHours();
        this.assessment = entityFind.getAssessment();
        this.totalStudent = entityFind.getTotalStudent();
    }
}
