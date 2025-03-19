package com.amazon.ecommerce.services.products;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.exceptions.ProductNotFoundException;
import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.repository.CategoryRepository;
import com.amazon.ecommerce.repository.ProductRepository;
import com.amazon.ecommerce.requests.AddProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  // for autowiring (injecting) final fields
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getALlProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
        .orElseThrow(()-> new ProductNotFoundException("product not found with id " + id));
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        Category newCategory = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getTitle()))
                        .orElseGet(()->{
                            Category category = new Category(request.getCategory().getTitle());
                            return categoryRepository.save(category);
                        });
        request.setCategory(newCategory);
        return productRepository.save(createProduct(request, newCategory));
    }

    public Product createProduct(AddProductRequest request, Category category){
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getDescription(),
            request.getQuantity(),
            category
        );
    }

    @Override
    public Product updateProduct(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).
        ifPresentOrElse(productRepository::delete, 
        ()-> new ProductNotFoundException("product not found with id " + id));;
    }


    @Override
    public List<Product> getProductsByCategoryName(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByNameAndBrand(String brand, String name) {
        return productRepository.findByNameAndBrand(brand, name);
    }

    @Override
    public Long CountByBrandAndName(String brand, String name) {
        return productRepository.CountByBrandAndName(brand, name);
    }



    
    
}
