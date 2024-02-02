package com.alura.aluraAPI.models.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "admin")
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "admin_role",
            joinColumns = @JoinColumn(name = "id_admin"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<TypeRole> roles = new ArrayList<>();
    @Column(name = "isNonExpired")
    private Boolean isAccountNonExpired;
    @Column(name = "isNonLocked")
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    public void addRole(TypeRole role) {
        this.roles.add(role);
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
