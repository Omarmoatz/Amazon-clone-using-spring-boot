package com.amazon.ecommerce.services.categories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.dto.category.CategoryCreateDTO;
import com.amazon.ecommerce.dto.category.CategoryRetrieveDTO;
import com.amazon.ecommerce.dto.category.CategoryUpdateDTO;
import com.amazon.ecommerce.exceptions.ResourceAlreadyExistedException;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRetrieveDTO findById(long id) {
        var ctg = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id : " + id));

        return new CategoryRetrieveDTO(ctg.getId(), ctg.getName());
    }

    @Override
    public CategoryRetrieveDTO findByName(String name) {
        var ctg = categoryRepository.findByName(name);
        return new CategoryRetrieveDTO(ctg.getId(), ctg.getName());
    }

    @Override
    public List<CategoryRetrieveDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map((ctg) -> new CategoryRetrieveDTO(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRetrieveDTO addCategory(CategoryCreateDTO request) {
        if (categoryRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistedException("there is already a category with this name");
        }

        var ctg = new Category(request.getName());
        categoryRepository.save(ctg);
        return new CategoryRetrieveDTO(ctg.getId(), ctg.getName());
    }

    @Override
    public CategoryRetrieveDTO updateCategory(CategoryUpdateDTO request, long id) {
        var ctg = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no category found by id : " + id));

        ctg.setName(request.getName());
        categoryRepository.save(ctg);
        return new CategoryRetrieveDTO(ctg.getId(), ctg.getName());
    }

    @Override
    public String deleteCategoryById(long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> new ResourceNotFoundException("category not found with id" + id));

        return "category deleted successfully";
    }

}
