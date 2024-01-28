package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedRepository extends JpaRepository<Blocked, Long> {
    void deleteByIdStudentBlocked(Student student);

    Optional<Blocked> findByIdStudentBlocked(Student student);

    @Query("SELECT b FROM Blocked b LEFT JOIN Student s ON s.id = b.idStudentBlocked.id WHERE s.signature IS NOT NULL AND b.idStudentBlocked IS NOT NULL AND s.name LIKE CONCAT('%', :name, '%')")
    List<Blocked> findAllStudentBlocked(String name);
}
