package com.amazon.ecommerce.services.categories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id" + id));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(ctg -> !categoryRepository.existsByName(ctg.getName()))
                .map(categoryRepository::save)
                .orElseThrow(
                        () -> new ResourceNotFoundException("category already existed with name" + category.getName()));
    }

    @Override
    public Category updateCategory(Category category, long id) {
        return Optional.ofNullable(findById(id))
                .map((oldCtg) -> {
                    oldCtg.setName(category.getName());
                    return categoryRepository.save(oldCtg);
                }).orElseThrow(() -> new ResourceNotFoundException("category not found with id " + id));
    }

    @Override
    public String deleteCategoryById(long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> new ResourceNotFoundException("category not found with id" + id));

        return "category deleted successfully";
    }

}
