package com.amazon.ecommerce.services.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.amazon.ecommerce.dto.category.CategoryRetrieveDTO;
import com.amazon.ecommerce.dto.product.AddProductRequestDTO;
import com.amazon.ecommerce.dto.product.RetrieveProductDTO;
import com.amazon.ecommerce.dto.product.UpdateProductRequestDTO;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.repository.CategoryRepository;
import com.amazon.ecommerce.repository.ProductRepository;
import com.amazon.ecommerce.services.categories.CategoryService;
import com.amazon.ecommerce.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // for autowiring (injecting) final fields
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public RetrieveProductDTO retrieveProduct(Product product) {
        return new RetrieveProductDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),

                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),

                new CategoryRetrieveDTO(
                        product.getCategory().getId(),
                        product.getCategory().getName()),
                product.getImages());
    }

    @Override
    public List<RetrieveProductDTO> getALlProducts() {
        return productRepository.findAll()
                .stream()
                .map((product) -> retrieveProduct(product))
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id " + id));
    }

    @Override
    public RetrieveProductDTO getProductById(Long id) {
        var product = findProductById(id);
        return retrieveProduct(product);
    }

    @Override
    public RetrieveProductDTO addProduct(AddProductRequestDTO request) {
        Category newCategory = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category category = new Category(request.getCategory().getName());
                    return categoryRepository.save(category);
                });
        request.setCategory(newCategory);
        var product = productRepository.save(createProduct(request, newCategory));

        return retrieveProduct(product);
    }

    private Product createProduct(AddProductRequestDTO request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getDescription(),
                request.getQuantity(),
                category);
    }

    @Override
    public RetrieveProductDTO updateProduct(UpdateProductRequestDTO request, long id) {
        var product = productRepository.findById(id)
                .map((existingProduct) -> updateExistingProduct(request, existingProduct))
                .map(productRepository::save) // This is equivalent to using a lambda expression
                                              // eg:.map(product -> productRepository.save(product))
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id " + id));
        return retrieveProduct(product);
    }

    private Product updateExistingProduct(
            UpdateProductRequestDTO request, Product existingProduct) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setQuantity(request.getQuantity());

        var ctgName = request.getCategory().getName();
        var category = categoryRepository.findByName(ctgName);
        if (category == null) {
            throw new ResourceNotFoundException("category not found with name " + ctgName);
        }
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        // productRepository.findById(id).ifPresentOrElse(productRepository::delete,
        // () -> new ResourceNotFoundException("product not found with id " + id));

        var product = findProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Long countByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<RetrieveProductDTO> getFilteredProducts(String categoryName, String brand, String name) {
        var specifications = Specification.where(ProductSpecification.hasCategory(categoryName))
                .and(ProductSpecification.hasBrand(brand))
                .and(ProductSpecification.hasName(name));

        return productRepository.findAll(specifications)
                .stream()
                .map((product) -> retrieveProduct(product))
                .collect(Collectors.toList());
    }

}
