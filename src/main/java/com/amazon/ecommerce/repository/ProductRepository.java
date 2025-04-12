package com.amazon.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryAndBrand(Category category, String brand);

    List<Product> findByName(String name);

    List<Product> findByNameAndBrand(String brand, String name);

    Long countByBrandAndName(String brand, String name);
}
