package com.alura.aluraAPI.models.forum;

import com.alura.aluraAPI.dtos.forum.insert.ResponseDTO;
import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;
    private String content;

    public Response(ResponseDTO responseDTO) {
        this.content = responseDTO.content();
    }
}