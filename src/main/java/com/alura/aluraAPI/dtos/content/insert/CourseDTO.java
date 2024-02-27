package com.alura.aluraAPI.dtos.content.insert;

import com.alura.aluraAPI.models.content.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public record CourseDTO(@JsonIgnore Long id,
                        @NotBlank(message = "Required field")
                        @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameContent,
                        @NotBlank(message = "Required field")
                        @Size(min = 20, message = "minimum of 100 characters") String description,
                        @NotNull(message = "Required field")
                        @Length(min = 3)
                        List<VideoLessonDTO> videoLessonDTOList,
                        @NotNull(message = "Required field") CertificateDTO certificateDTO,
                        @NotBlank(message = "Required field") Long idCategory) {
    public CourseDTO(Long id, String nameContent, String description, CertificateDTO certificateDTO, Long idCategory) {
        this(id, nameContent, description, new ArrayList<>(), certificateDTO, idCategory);
    }

    public CourseDTO(Course entity) {
        this(entity.getId(), entity.getNameContent(), entity.getDescription(), new CertificateDTO(entity.getCertificate()), entity.getCategory().getId());
        entity.getVideoLessons().stream().forEach(x -> videoLessonDTOList.add(new VideoLessonDTO(x)));
    }

}
