package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.person.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<TypeRole, Long> {
    TypeRole findByAuthority(String authority);
}
