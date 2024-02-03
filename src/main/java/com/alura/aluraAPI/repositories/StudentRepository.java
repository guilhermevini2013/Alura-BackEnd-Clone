package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByEmail(String email);

    @Query("SELECT s FROM Student s LEFT JOIN Blocked b ON s.id = b.idStudentBlocked.id WHERE s.signature IS NOT NULL AND b.idStudentBlocked IS NULL")
    Page<Student> findAllStudentNotBlocked(PageRequest request);

    @Query("SELECT s FROM Student s LEFT JOIN Blocked b ON s.id = b.idStudentBlocked.id WHERE s.signature IS NOT NULL AND b.idStudentBlocked IS NULL AND s.name LIKE CONCAT('%', :name, '%')")
    List<Student> findAllStudentNotBlocked(String name);

}
