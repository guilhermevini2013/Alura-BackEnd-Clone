package com.alura.aluraAPI.models.person;

import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.models.content.StudentCertificate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY)
    private Signature signature;
    @ManyToMany
    @JoinTable(name ="completed_curses",
    joinColumns = @JoinColumn(name = "id_student"),
    inverseJoinColumns = @JoinColumn(name = "id_curse"))
    private Set<Course> completedCurses;
    @OneToMany(mappedBy = "student")
    private Set<StudentCertificate> studentCertificates = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "student_role",
    joinColumns = @JoinColumn(name = "id_student"),
    inverseJoinColumns = @JoinColumn(name = "id_sole"))
    private Set<TypeRole> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
