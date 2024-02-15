package com.alura.aluraAPI.models.forum;

import com.alura.aluraAPI.dtos.forum.PublicationsDto;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publications")
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany
    private Set<Category> categories;
    private Integer numberOfResponses;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToMany
    private Set<Response> responses;

    public Publications(PublicationsDto publicationsDto) {
        this.title = publicationsDto.title();
        this.description = publicationsDto.description();
    }
}