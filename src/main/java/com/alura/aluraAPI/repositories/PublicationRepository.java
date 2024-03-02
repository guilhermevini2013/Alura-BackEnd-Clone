package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.forum.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>, JpaSpecificationExecutor<Publication> {
    @Query("select p from Publication p where p.id= :idPublication and p.student.id= :idStudent")
    Optional<Publication> findByIdAndStudentId(Long idPublication, Long idStudent);
}
