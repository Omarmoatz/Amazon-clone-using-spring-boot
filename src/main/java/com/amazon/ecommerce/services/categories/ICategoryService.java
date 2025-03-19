package com.amazon.ecommerce.services.categories;

import java.util.List;

import com.amazon.ecommerce.models.Category;


public interface ICategoryService {
    Category findById(long id);
    Category findByName(String name);
    List<Category> findAll();

    Category addCategory(Category category);
    Category updateCategory(Category category, long id);
    String deleteCategoryById(long id);
}
