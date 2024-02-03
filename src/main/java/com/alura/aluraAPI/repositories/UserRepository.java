package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Admin;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.models.person.User;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true, value = """
            	SELECT admin.email AS username, admin.password
            	, admin.is_Non_Locked, admin.is_Non_Expired
            	, admin.is_Credentials_Non_Expired, admin.is_Enabled
            	, role.id AS roleId, role.authority
            	FROM admin
            	INNER JOIN user_role ON admin.id = user_role.id_user
            	INNER JOIN role ON role.id = user_role.id_role
            	WHERE admin.email = :email
            """)
    List<UserDetailsProjection> searchAdminAndRolesByEmail(String email);
    @Query(nativeQuery = true, value = """
            	SELECT student.email AS username, student.password
            	, student.is_Non_Locked, student.is_Non_Expired
            	, student.is_Credentials_Non_Expired, student.is_Enabled
            	, role.id AS roleId, role.authority
            	FROM student
            	INNER JOIN user_role ON student.id = user_role.id_user
            	INNER JOIN role ON role.id = user_role.id_role
            	WHERE student.email = :email
            """)
    List<UserDetailsProjection> searchStudentAndRolesByEmail(String email);
    @Query("select s from Student s where s.email= :email")
    Optional<Student> findStudentByEmail(String email);
    @Query("select a from Admin a where a.email= :email")
    Optional<Admin> findAdminByEmail(String email);
}
