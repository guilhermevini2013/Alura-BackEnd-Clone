package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.forum.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication, Long>, JpaSpecificationExecutor<Publication> {
}
