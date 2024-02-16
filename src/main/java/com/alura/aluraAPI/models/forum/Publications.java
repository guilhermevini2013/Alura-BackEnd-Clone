package com.alura.aluraAPI.models.forum;

import com.alura.aluraAPI.dtos.forum.PublicationsDto;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
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
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    private Student student;
    @OneToMany
    private Set<Response> responses;
    private Date publicationDate;

    public Publications(PublicationsDto publicationsDto) {
        this.title = publicationsDto.title();
        this.description = publicationsDto.description();
        this.publicationDate = Date.from(Instant.now());
    }
}