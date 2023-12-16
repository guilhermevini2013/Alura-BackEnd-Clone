package com.alura.aluraAPI.dtos.content.insert;

import com.alura.aluraAPI.models.content.Curse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public record CurseDTO(@JsonIgnore Long id,
                       @Size(max = 80, min = 20, message = "Maximum of 80 characters and minimum of 20") String nameContent,
                       @Size(min = 40, message = "minimum of 100 characters") String description,
                       @NotEmpty List<VideoLessonDTO> videoLessonDTOList,
                       @NotNull CertificateDTO certificateDTO) {
    public CurseDTO(Long id, String nameContent, String description, CertificateDTO certificateDTO) {
        this(id, nameContent, description, new ArrayList<>(), certificateDTO);
    }

    public CurseDTO(Curse entity) {
        this(entity.getId(), entity.getNameContent(), entity.getDescription(), new CertificateDTO(entity.getCertificate()));
        entity.getVideoLessons().stream().forEach(x -> videoLessonDTOList.add(new VideoLessonDTO(x)));
    }

}
