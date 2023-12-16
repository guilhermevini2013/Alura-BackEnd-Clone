package com.alura.aluraAPI.dtos.content.insert;

import com.alura.aluraAPI.models.content.VideoLesson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;

public record VideoLessonDTO(@JsonIgnore Long id,
                             @Size(max = 80, message = "Maximum of 80 characters") String nameLesson,
                             String archive,
                             Integer duration) {
    public VideoLessonDTO(VideoLesson x) {
        this(x.getId(), x.getNameLesson(), x.getArchive(), x.getDuration());
    }
}
