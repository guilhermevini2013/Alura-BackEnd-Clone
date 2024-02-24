package com.alura.aluraAPI.models.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.models.content.Category;
import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToMany
    private Set<Category> categories;
    private Integer numberOfResponses;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Response> responses;
    private Date publicationDate;
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;

    public Publication(PublicationDto publicationsDto) {
        this.title = publicationsDto.title();
        this.description = publicationsDto.description();
        this.publicationDate = Date.from(Instant.now());
        this.numberOfResponses = 0;
        this.status = PublicationStatus.UNSOLVED;
    }
    public void addResponse(Response response){
        responses.add(response);
    }
    public void solvedPublish(){
        this.status = PublicationStatus.SOLVED;
    }
}