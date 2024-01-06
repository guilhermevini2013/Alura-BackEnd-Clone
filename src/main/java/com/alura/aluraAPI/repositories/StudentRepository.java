package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select s.email,s.password,s.roles from Student s where s.email=:email")
    Optional<Student> findByEmail(String email);
}
