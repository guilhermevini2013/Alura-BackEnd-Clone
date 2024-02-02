package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.Admin;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
    @Query(nativeQuery = true, value = """
            	SELECT admin.email AS username, admin.password
            	, admin.is_Non_Locked, admin.is_Non_Expired
            	, admin.is_Credentials_Non_Expired, admin.is_Enabled
            	, role.id AS roleId, role.authority
            	FROM admin
            	INNER JOIN admin_role ON admin.id = admin_role.id_admin
            	INNER JOIN role ON role.id = admin_role.id_role
            	WHERE admin.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
