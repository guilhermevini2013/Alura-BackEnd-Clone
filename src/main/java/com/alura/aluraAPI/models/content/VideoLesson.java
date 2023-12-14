package com.alura.aluraAPI.models.content;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "video_lesson")
public class VideoLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameLesson;
    private String archive;
    @ManyToOne(fetch = FetchType.LAZY)
    private Curse curse;
}
