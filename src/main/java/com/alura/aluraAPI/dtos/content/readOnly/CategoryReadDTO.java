package com.alura.aluraAPI.dtos.content.readOnly;

import com.alura.aluraAPI.models.content.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryReadDTO {
    private Long id;
    private String nameCategory;

    public CategoryReadDTO(Category category) {
        this.id = category.getId();
        this.nameCategory = category.getName();
    }
}
