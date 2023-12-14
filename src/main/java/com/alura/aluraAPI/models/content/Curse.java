package com.alura.aluraAPI.models.content;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curses")
@DiscriminatorValue("VideoLesson")
public class Curse extends Content{
    @OneToMany(mappedBy = "curse",cascade = CascadeType.ALL)
    private Set<VideoLesson> videoLessons = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Training trainings;
}
