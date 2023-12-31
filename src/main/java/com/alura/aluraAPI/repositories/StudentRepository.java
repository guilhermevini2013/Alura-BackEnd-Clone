package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(nativeQuery = true, value = """
				SELECT student.email AS username, student.password, role.id AS roleId, role.authority
				FROM student
				INNER JOIN student_role ON student.id = student_role.id_student
				INNER JOIN role ON role.id = student_role.id_role
				WHERE student.email = :email
			""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<Student> findByEmail(String email);
}
