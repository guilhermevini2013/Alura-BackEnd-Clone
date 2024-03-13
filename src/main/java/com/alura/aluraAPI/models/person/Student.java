package com.alura.aluraAPI.models.person;

import com.alura.aluraAPI.dtos.person.insert.StudentInsertDTO;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.models.content.StudentCertificate;
import com.alura.aluraAPI.models.forum.Publication;
import com.alura.aluraAPI.services.strategies.calculates.CalculateTimeSignatureStrategy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Table(name = "student")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private String name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "signature_id")
    private Signature signature;
    @ManyToMany
    @JoinTable(name = "completed_curses",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_curse"))
    private Set<Course> completedCurses;
    @OneToMany(mappedBy = "student")
    private Set<StudentCertificate> studentCertificates = new HashSet<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Publication> publications = new HashSet<>();

    public Student(StudentInsertDTO studentInsertDTO,TypeRole role, CalculateTimeSignatureStrategy timeSignatureStrategy) {
        this.name = studentInsertDTO.name();
        super.setPassword(studentInsertDTO.password());
        super.setEmail(studentInsertDTO.email());
        super.setIsAccountNonExpired(true);
        super.setIsAccountNonLocked(true);
        super.setIsCredentialsNonExpired(true);
        super.setIsEnabled(true);
        super.roles.add(role);
        this.signature = new Signature(studentInsertDTO.typeSignature(), timeSignatureStrategy);
    }
}
