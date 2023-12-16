package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.content.Content;
import com.alura.aluraAPI.models.content.Curse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    @Query(value = "select c from Content c where TYPE(c) = Curse and c.nameContent=:name")
    Optional<Curse> findByNameContent(String name);
    @Query(value = "select c from Content c where TYPE(c) = Curse")
    Page<Curse> findAllCurse(PageRequest pageRequest);
}
