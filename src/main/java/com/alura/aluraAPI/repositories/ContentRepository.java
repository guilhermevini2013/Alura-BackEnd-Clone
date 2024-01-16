package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Course;
import com.alura.aluraAPI.models.content.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long>, JpaSpecificationExecutor<Content> {
    @Query(value = "select c from Course c where c.id = :id and c.trainings is null")
    Optional<Course> findByIdCourse(Long id);
    @Query(value = "select c from Content c where TYPE(c) = Course ")
    Page<Course> findAllCourse(PageRequest pageRequest);
    @Query(value = "select c from Content c where TYPE(c) = Training ")
    Page<Training> findAllTraining(PageRequest pageRequest);
    @Query(value = "select c from Content c where TYPE(c) = Training and c.id=:id")
    Optional<Training> findByIdTraining(Long id);
    @Query(value = "select count(c) from Content c where TYPE(c) = Training ")
    Integer countTraining();
    @Query(value = "select count(c) from Content c where TYPE(c) = Course ")
    Integer countCourse();
}
