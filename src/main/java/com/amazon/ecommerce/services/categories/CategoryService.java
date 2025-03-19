package com.amazon.ecommerce.services.categories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id)
                                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id" + id));
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
    public Product addProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }

    @Override
    public Product updateProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public String deleteProductById(long id) {
        categoryRepository.findById(id)
                        .ifPresentOrElse(categoryRepository::delete, 
                                        ()-> new ResourceNotFoundException("category not found with id" + id));

        return "category deleted successfully";
    }
    
}
