package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.forum.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long> {
}
