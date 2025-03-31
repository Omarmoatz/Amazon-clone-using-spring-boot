package com.amazon.ecommerce.services.categories;

import java.util.List;

import com.amazon.ecommerce.dto.category.CategoryCreateDTO;
import com.amazon.ecommerce.dto.category.CategoryRetrieveDTO;
import com.amazon.ecommerce.dto.category.CategoryUpdateDTO;


public interface ICategoryService {
    CategoryRetrieveDTO findById(long id);
    CategoryRetrieveDTO findByName(String name);
    List<CategoryRetrieveDTO> findAll();

    CategoryRetrieveDTO addCategory(CategoryCreateDTO category);
    CategoryRetrieveDTO updateCategory(CategoryUpdateDTO category, long id);
    void deleteCategoryById(long id);
}
