package com.amazon.ecommerce.services.products;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.dto.product.AddProductRequestDTO;
import com.amazon.ecommerce.dto.product.UpdateProductRequestDTO;
import com.amazon.ecommerce.exceptions.ProductNotFoundException;
import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.repository.CategoryRepository;
import com.amazon.ecommerce.repository.ProductRepository;

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
    public Product addProduct(AddProductRequestDTO request) {
        Category newCategory = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                        .orElseGet(()->{
                            Category category = new Category(request.getCategory().getName());
                            return categoryRepository.save(category);
                        });
        request.setCategory(newCategory);
        return productRepository.save(createProduct(request, newCategory));
    }

    private Product createProduct(AddProductRequestDTO request, Category category){
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
    public Product updateProduct(UpdateProductRequestDTO request, long id) {
        return productRepository.findById(id)
                        .map((existingProduct)->updateExistingProduct(request, existingProduct))
                        .map(productRepository :: save)     // This is equivalent to using a lambda expression eg:.map(product -> productRepository.save(product))
                        .orElseThrow(() -> new ProductNotFoundException("product not found with id " + id));
    }

    private Product updateExistingProduct(
        UpdateProductRequestDTO request, Product existingProduct){
            existingProduct.setName(request.getName());
            existingProduct.setBrand(request.getBrand());
            existingProduct.setPrice(request.getPrice());
            existingProduct.setDescription(request.getDescription());
            existingProduct.setQuantity(request.getQuantity());


            var category = categoryRepository.findByName(request.getCategory().getName());
            existingProduct.setCategory(category);
            return existingProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).
        ifPresentOrElse(productRepository::delete, 
        ()-> new ProductNotFoundException("product not found with id " + id));;
    }


    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(Category categoryName, String brand) {
        return productRepository.findByCategoryAndBrand(categoryName, brand);
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
    public Long countByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }



    
    
}
