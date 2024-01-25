package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.warn.Blocked;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {
    @Query(nativeQuery = true, value = """
            	SELECT student.email AS username, student.password
            	, student.is_Non_Locked, student.is_Non_Expired
            	, student.is_Credentials_Non_Expired, student.is_Enabled
            	, role.id AS roleId, role.authority
            	FROM student
            	INNER JOIN student_role ON student.id = student_role.id_student
            	INNER JOIN role ON role.id = student_role.id_role
            	WHERE student.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<Student> findByEmail(String email);
    @Query("SELECT s FROM Student s LEFT JOIN Blocked b ON s.id = b.idStudentBlocked.id WHERE s.signature IS NOT NULL AND b.idStudentBlocked IS NULL")
    Page<Student> findAllStudentNotBlocked(PageRequest request);
    @Query("SELECT s FROM Student s LEFT JOIN Blocked b ON s.id = b.idStudentBlocked.id WHERE s.signature IS NOT NULL AND b.idStudentBlocked IS NULL")
    List<Student> findAllStudentNotBlocked(Specification<Student> specification);
}
