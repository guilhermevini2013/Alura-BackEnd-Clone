package com.alura.aluraAPI.models.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "admin")
public class Admin extends User {
    public Admin() {
    }
}
