package com.amazon.ecommerce.services.products;

import java.util.List;

import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.requests.AddProductRequest;
import com.amazon.ecommerce.requests.UpdateProductRequest;


public interface IProductService{
    
    List<Product> getALlProducts();
    Product getProductById(Long id);
    Product addProduct(AddProductRequest request);
    Product updateProduct(UpdateProductRequest request, long id);
    void deleteProductById(Long id);

    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByNameAndBrand(String brand, String name);

    Long CountByBrandAndName(String brand ,String name);

}
