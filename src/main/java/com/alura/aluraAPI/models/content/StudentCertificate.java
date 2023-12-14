package com.alura.aluraAPI.models.content;

import com.alura.aluraAPI.models.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "student_certificate")
public class StudentCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    private Certificate certificate;
    private Date initialDate;
    private Date finalDate;
}
