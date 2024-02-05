package com.alura.aluraAPI.repositories;

import com.alura.aluraAPI.models.content.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
