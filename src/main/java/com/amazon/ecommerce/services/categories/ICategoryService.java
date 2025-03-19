package com.amazon.ecommerce.services.categories;

import java.util.List;

import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.models.Product;


public interface ICategoryService {
    Category findById(long id);
    Category findByName(String name);
    List<Category> findAll();

    Product addProduct(Product product);
    Product updateProduct(Product product);
    String deleteProductById(long id);
}
