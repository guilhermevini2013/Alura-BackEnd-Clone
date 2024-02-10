package com.alura.aluraAPI.services.factory;

import com.alura.aluraAPI.models.content.Category;

public class CategoryFactory {
    public static Category createValidCategory(){
        return new Category(1l,"Back-end");
    }
}
