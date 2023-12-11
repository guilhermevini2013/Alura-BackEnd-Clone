package com.alura.aluraAPI.models.person;

import com.alura.aluraAPI.models.content.Certificate;
import com.alura.aluraAPI.models.content.Curses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToOne(mappedBy = "student")
    private Signature signature;
    @ManyToMany
    @JoinTable(name ="completed_curses",
    joinColumns = @JoinColumn(name = "id_student"),
    inverseJoinColumns = @JoinColumn(name = "id_curse"))
    private Set<Curses> completedCurses;
    @ManyToMany
    @JoinTable(name ="student_certificate",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_certificate"))
    private Set<Certificate> certificate = new HashSet<>();
}
